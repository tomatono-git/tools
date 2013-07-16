/**
 *
 */
package tools.daogen.binding

import tools.db.bean.Column


/**
 *
 *
 */
public class ColumnBindings extends Column {

	/**
	 *
	 */
	public ColumnBindings() {
	}

	public String getGetterName() {
		return "get" + fieldName.capitalize()
	}

	public String getSetterName() {
		return "set" + fieldName.capitalize()
	}

	/** CREATE•¶‚Ì—ñ’è‹`•”•ª */
	public String getDefine() {
		def define = "${columnName} ${columnTypeName}"
		if (precision) {
			boolean isNumber = (columnTypeName.equalsIgnoreCase("NUMBER"))
			define << "(${precision}${isNumber ? ',' + scale : ''})"
		}
		if (!nullable) {
			define << " NOT NULL"
		}

		return define
	}
}
