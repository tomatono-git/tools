/**
 *
 */
package tools.db.loader;

import groovy.sql.Sql
import groovy.util.logging.Log4j

import java.sql.ResultSet
import java.sql.ResultSetMetaData

import tools.db.DB
import tools.db.JdbcConfig
import tools.db.bean.Column
import tools.db.bean.Table
import tools.db.bean.TargetToExtract
import tools.db.convertor.MetaDataConvertor

/**
 *
 *
 */
@Log4j
public abstract class BaseTableConfigLoader extends BaseObjectConfigLoader {

	/**
	 * �R���X�g���N�^
	 *
	 * @param tablePrefix �e�[�u���v���t�B�b�N�X
	 * @param allowPattern ������e�[�u�����̃p�^�[���i�ȗ��j
	 * @param denyPattern ���O����e�[�u�����̃p�^�[���i�ȗ��j
	 * @param isIncludeView isIncludeView �r���[���܂ނ��H
	 */
	protected BaseTableConfigLoader(String tablePrefix, String allowPattern, String denyPattern, String isIncludeView) {
		super(tablePrefix, allowPattern, denyPattern, isIncludeView)
	}

	/**
	 * �R���X�g���N�^
	 *
	 * @param target �ǂݍ��ݑΏ�
	 */
	protected BaseTableConfigLoader(TargetToExtract target) {
		super(target)
	}

	protected abstract String getFindTableNamesQuery(String prefix);
	protected abstract String getFindViewNamesQuery(String prefix);
	protected abstract DB createDB();
	protected Map<String, String> getTableComments(List tableNameList) {
		return createDB().getTableComments(tableNameList);
	}

	protected Map<String, String> getColumnComment(List tableNameList) {
		return createDB().getColumnComment(tableNameList);
	}

	/* (�� Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.loader.BaseObjectConfigLoader#load()
	 */
	@Override
	public List<Table> load(Map map = [:]) {
		List<Table> tableList = []
		List tableNameList = getTableNameList(target.tablePrefix, target.allowPattern, target.denyPattern, target.isIncludeView)
		def DB db = createDB()
		// �e�[�u���̃R�����g���擾
		def tableComment = getTableComments(tableNameList)
		log.debug("Table comments : $tableComment")

		// ��̃R�����g���擾
		def columnComments = getColumnComment(tableNameList)
		log.debug("Column comments : $columnComments")


		tableNameList.each { tableName ->

			Table table = createTable()
			table.name = tableName
			table.logicalName = tableComment[tableName]

			//			log.debug("${table.name}/${table.logicalName}")

			// ���^�f�[�^���擾
			db.connect { Sql sql ->
				String query = "select * from ${tableName} where rownum = 1"
				def result = sql.query(query) { ResultSet rs ->
					ResultSetMetaData metaData = rs.metaData
					// ����
					(1..metaData.columnCount).each { i ->
						assert columnComments[tableName]
						Column column = createColumn()
						MetaDataConvertor.convert(metaData, i, column, columnComments[tableName])

						// ���ڃ��X�g�ɒǉ�
						table.columnList.add(column)
					}
				}
			}
			table.initColumnMap()

			// ��L�[���擾
			log.debug("tableName=$tableName")
			List pkList = getPkList(JdbcConfig.instance.schemaName, tableName, table.columnList)
			//			assert pkList
			table.pkList = pkList
			log.debug("pkList :" + table.pkList)

			// �ǉ��̐ݒ�
			setAdditionalOption(table)

			tableList.add(table)
		}

		//		assert tableList[0].pkList
		return tableList;
	}

	protected void setAdditionalOption(Table table) {
		// TODO �����Ńe�[�u���̐ݒ��ǉ�
	}

	/**
	 * �e�[�u�����̈ꗗ���擾
	 *
	 * @param prefix �v���t�B�b�N�X
	 * @param isIncludeView �r���[���܂ނ��H
	 * @param allow ������e�[�u�����̃p�^�[���i�ȗ��j
	 * @param deny ���O����e�[�u�����̃p�^�[���i�ȗ��j
	 * @return
	 */
	def List getTableNameList(String prefix, String allow = null, String deny = null, boolean isIncludeView) {
		String query = getFindTableNamesQuery(prefix);
		List tableNameList = getObjectNameList(query, 'table_name', prefix, allow, deny);

		if (isIncludeView) {
			String viewQuery = getFindViewNamesQuery(prefix);
			if (viewQuery) {
				List viewNameList = getObjectNameList(viewQuery, 'view_name', prefix, allow, deny);
				if (viewNameList) {
					tableNameList.addAll(viewNameList)
				}
			}
		}
		return tableNameList
	}

	/**
	 * �I�u�W�F�N�g���̈ꗗ���擾
	 * �i�e�[�u���A�r���[�Ȃǁj
	 *
	 * @param query SQL�N�G��
	 * @param columnName �e�[�u�����̗�
	 * @param prefix �v���t�B�b�N�X
	 * @param allow ������e�[�u�����̃p�^�[���i�ȗ��j
	 * @param deny ���O����e�[�u�����̃p�^�[���i�ȗ��j
	 *
	 * @return �I�u�W�F�N�g���̈ꗗ
	 */
	def List getObjectNameList(String query, String columnName, String prefix, String allow = null, String deny = null) {
		def DB db = createDB()
		def result = db.connect { Sql sql ->
			sql.rows(query)
		}
		List list = result.findAll {
			String tableName = it[columnName]
			boolean isAllow = true
			if (allow) {
				isAllow = (tableName ==~ allow)
			}
			if (deny) {
				// TODO �o�b�N�A�b�v�e�[�u���̃p�^�[���͗v����
				isAllow = !(tableName ==~ deny)
			}
			return isAllow
		}
		return list[columnName]
	}

	/**
	 * ��L�[�̈ꗗ���擾
	 *
	 * @param schema �X�L�[�}��
	 * @param tableName �e�[�u����
	 * @param columnList ����̈ꗗ
	 *
	 * @return ��L�[�̈ꗗ
	 */
	def List<String> getPkList(String schema, String tableName, List<Column> columnList) {
		def DB db = createDB()
		List primaryKeyList = db.getPrimaryKeyList(schema, tableName)
		List pkNameList = primaryKeyList.columnName
		List pkList = pkNameList.collect { String name ->
			Column pk = columnList.find { Column column ->
				return column.columnName == name
			}
			pk.isPk = true
			return pk
		}
		return pkList
	}
}
