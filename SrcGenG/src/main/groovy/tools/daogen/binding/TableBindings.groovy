/**
 *
 */
package tools.daogen.binding

import groovy.transform.ToString
import tools.daogen.config.DaogenConfig
import tools.daogen.config.DaogenConfigFactory
import tools.daogen.config.roules.additoin.DaoMethodAdditon
import tools.db.bean.Table

/**
 *
 *
 */
@ToString(includeNames = true, includeFields = true)
public class TableBindings extends Table {

	def String daoPackage
	def String dtoPackage
	def String dtoClassName
	def String daoClassName
	def List<String> excludeInsertColumnNameList = []
	def List<String> excludeUpdateColumnNameList = []
	def List<ColumnBindings> excludeInsertColumnList = []
	def List<ColumnBindings> excludeUpdateColumnList = []
	def List<DaoMethodAdditon> additionList = []
	def String order
	def boolean enableGeneric
	def boolean enableAnnotation

	protected DaogenConfig config = DaogenConfigFactory.getInstance();

	public String getGeneric() {
		assert config && config.dao
		return config.dao.enableGenerics ? "<$dtoClassName>" : ""
	}

	public String getOverrideAnnotation() {
		assert config && config.dao
		return config.dao.enableAnnotation ? '	@Override' : ""
	}

	def void setConfig(DaogenConfig config) {
		dtoPackage = config.dto.packageName
		daoPackage = config.dao.packageName

		dtoClassName = config.getDtoClassName(name)
		daoClassName = config.getDaoClassName(name)
		excludeInsertColumnNameList = config.excludeInsertColumnList.split(",").collect { it.trim().toUpperCase(); }
		excludeUpdateColumnNameList = config.excludeUpdateColumnList.split(",").collect { it.trim().toUpperCase(); }

		excludeInsertColumnList = excludeInsertColumnNameList.collect { String columnName ->
			columnList.find { ColumnBindings column ->
				column.columnName == columnName
			}
		}
//		assert excludeInsertColumnList.columnName == excludeInsertColumnNameList
		excludeUpdateColumnList = excludeUpdateColumnNameList.collect { String columnName ->
			columnList.find { ColumnBindings column ->
				column.columnName == columnName
			}
		}
//		assert excludeUpdateColumnList.columnName == excludeUpdateColumnNameList


		// TODO
		additionList = config.addition.getMethodAdditonList(this)
		assert columnMap
		order = config.addition.getOrder(this)
	}

	/**
	 * 更新（UPDATE）用の列
	 * @return
	 */
	public List<ColumnBindings> getUpdateParamList() {
		List<ColumnBindings> list = new ArrayList<ColumnBindings>()
		for (ColumnBindings column : this.columnList) {
			if (isExcludeUpdateColumn(column) || column.columnName == "UPDATE_DATE") {
				continue
			}
			list.add(column)
		}
		return list
	}

	/**
	 * 登録（INSERT）用の列
	 * @return
	 */
	public List<ColumnBindings> getInsertParamList() {
		List<ColumnBindings> list = new ArrayList<ColumnBindings>()
		for (ColumnBindings column : this.columnList) {
			if (isExcludeInsertColumn(column) || column.columnName == "CREATE_DATE") {
				continue
			}
			list.add(column)
		}
		return list
	}

	public List getInsertColumnList() {
		List list = columnList.findAll { ColumnBindings column ->
			def exclude = excludeUpdateColumnNameList.find { it == column.columnName }
			return (exclude == null)
		}
		return list
	}

	public List getUpdateColumnList() {
		def list = columnList - pkList// - excludeInsertColumnList
		list = list.findAll { ColumnBindings column ->
			def exclude = excludeInsertColumnNameList.find { it == column.columnName }
			return (exclude == null)
		}
		return list
	}

	public boolean isExcludeInsertColumn(ColumnBindings column) {
		def result = excludeInsertColumnNameList.find { it.equalsIgnoreCase(column.columnName) }
		return result != null
	}

	public boolean isExcludeUpdateColumn(ColumnBindings column) {
		boolean isExclude = false
		if (column.isPk) {
			isExclude = true
		} else {
			def result = excludeInsertColumnNameList.find { it.equalsIgnoreCase(column.columnName) }
			isExclude = (result != null)
		}
		return isExclude
	}
}
