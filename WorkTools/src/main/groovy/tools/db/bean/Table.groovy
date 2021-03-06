/**
 *
 */
package tools.db.bean

import groovy.transform.ToString
import groovy.transform.TypeChecked

/**
 *
 *
 */
@ToString(includeNames = true, includeFields = true)
@TypeChecked
public class Table {

	def String name
	def String logicalName
	def List<Column> columnList = []
	def Map<String, Column> columnMap = [:]
	def List<Column> pkList = []
	def String remarks

	public String getHistoryName() {
		return name + "_H"
	}

	public void initColumnMap() {
		// columnMap の初期化
		if (columnMap) {
			columnMap.clear()
		}
		columnList.each { Column column ->
			columnMap[column.columnName] = column
		}
	}

	/**
	 * logicalNameを取得します。
	 *
	 * @return logicalName
	 */
	public String getLogicalName() {
		return logicalName ?: name
	}

	public void setLogicalName(String logicalName) {
		// 空白以降は備考
		if (logicalName) {
			def values = logicalName.split(/\s+/)
			this.logicalName = values[0]
			if (values.size() >= 2) {
				remarks = logicalName.replaceFirst(/^${this.logicalName}\s+/, '')
			}
		} else {
			this.logicalName = logicalName
		}
	}
}
