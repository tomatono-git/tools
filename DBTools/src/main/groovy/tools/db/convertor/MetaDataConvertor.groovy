/**
 *
 */
package tools.db.convertor;

import java.sql.ResultSetMetaData
import java.sql.Timestamp

import tools.db.bean.Column

/**
 *
 *
 */
final class MetaDataConvertor {

	/**
	 *
	 */
	private MetaDataConvertor() {
	}

	/**
	 * ���t�^�̕ϊ����@
	 */
	public enum DateConvertMethod {
		/**
		 * �Ȃ��i�ϊ����Ȃ��j
		 */
		None
		,

		/**
		 * Date -> Timestamp
		 */
		DateToTimestamp
		,

		/**
		 * Timestamp -> Date
		 */
		TimestampToDate
	}

	public static void convert(
			ResultSetMetaData metaData,
			int index,
			Column column,
			Map columnComments = null,
			DateConvertMethod typeConvertMethod = DateConvertMethod.DateToTimestamp) {

		column.columnName = metaData.getColumnName(index)
		// Java�̌^��
		column.columnClassName = metaData.getColumnClassName(index)

		column.columnType = metaData.getColumnType(index)
		// DB�̌^��
		column.columnTypeName = metaData.getColumnTypeName(index)
		column.columnDisplaySize = metaData.getColumnDisplaySize(index)
		// ���ڕʖ�
		column.columnLabel = metaData.getColumnLabel(index)
		// ���ڃT�C�Y
		column.precision = metaData.getPrecision(index)
		// �����_�ȉ��̌���
		column.scale = metaData.getScale(index)
		column.autoIncrement = metaData.isAutoIncrement(index)
		column.caseSensitive = metaData.isCaseSensitive(index)
		column.currency = metaData.isCurrency(index)
		column.definitelyWritable = metaData.isDefinitelyWritable(index)
		// NULL��
		column.nullable = metaData.isNullable(index)
		column.readOnly = metaData.isReadOnly(index)
		column.searchable = metaData.isSearchable(index)
		column.signed = metaData.isSigned(index)
		column.writable = metaData.isWritable(index)
		// �J�^���O��
		column.catalogName = metaData.getCatalogName(index)
		// �X�L�[�}�[��
		column.schemaName = metaData.getSchemaName(index)
		// �e�[�u����
		column.tableName = metaData.getTableName(index)
		// �_����
		column.logicalName = columnComments ? columnComments[column.columnName] : null

		// Java�̌^�𒲐�
		if (column.columnClassName == BigDecimal.class.name) {
			if (!column.scale) {
				if (column.precision < Integer.MAX_VALUE.toString().length()) {
					// Integer
					column.columnClassName = Integer.class.name
				} else if (column.precision < Long.MAX_VALUE.toString().length()) {
					// Long
					column.columnClassName = Long.class.name
				}
			}
		} else if(column.columnClassName == Date.class.name) {
			if (typeConvertMethod == DateConvertMethod.DateToTimestamp) {
				// Date �� Timestamp �ɂ���
				column.columnClassName = Timestamp.class.name

			}
		} else if(column.columnClassName == Timestamp.class.name) {
			if (typeConvertMethod == DateConvertMethod.TimestampToDate) {
				// Timestamp �� Date �ɂ���
				column.columnClassName = Date.class.name
			}
		}
		column.columnClassName = column.columnClassName.replaceFirst(/^java\.lang\./, '')

//		return column
	}
}
