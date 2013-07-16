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
public class Column implements Serializable {

	/**
	 *
	 */
	public Column() {
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L

	def String logicalName
	def boolean isPk

	/** ���ږ� */
	def String columnName
	/** Java�̌^�� */
	def String columnClassName
	def columnType
	/** DB�̌^�� */
	def String columnTypeName
	def columnDisplaySize
	/** ���ڕʖ� */
	def String columnLabel
	/** ���ڃT�C�Y */
	def Integer precision
	/** �����_�ȉ��̌��� */
	def Integer scale
	/** �����̔ԗL�� */
	def boolean autoIncrement
	def boolean caseSensitive
	def boolean currency
	def boolean definitelyWritable
	/** NULL�� */
	def boolean nullable
	def boolean readOnly
	def boolean searchable
	def boolean signed
	def boolean writable
	def String catalogName
	/** �X�L�[�}�� */
	def String schemaName
	/** �e�[�u���� */
	def String tableName

	def String remarks

	public String getFieldName() {
		return columnName.toLowerCase()
	}

	/**
	 * logicalName���擾���܂��B
	 *
	 * @return logicalName
	 */
	public String getLogicalName() {
		return logicalName ?: columnName
	}

	public void setLogicalName(String logicalName) {
		// �󔒈ȍ~�͔��l
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

	/**
	 * lengthInteger��ݒ肵�܂��B
	 *
	 * @param precision
	 *            precision
	 */
	public void setPrecision(Double precision) {
		this.precision = precision?.intValue()
	}

	/**
	 * lengthDecimal��ݒ肵�܂��B
	 *
	 * @param scale
	 *            scale
	 */
	public void setScale(Double scale) {
		this.scale = scale?.intValue()
	}
}
