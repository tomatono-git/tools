/**
 *
 */
package tools.daogen.config.roules.additoin

import groovy.transform.ToString


/**
 *
 *
 */
@ToString(includeNames = true, includeFields = true)
class DaoMethodAdditon {

	def String tableName
	def String type
//	def String column
//	def String condition
//	def String order

	def columnNameList = []
	def conditionNameList = []
	def orderNameList = []

	def columnList = []
	def conditionList = []
	def orderList = []

	def void setColumnNameList(value) {
		columnNameList = toList(value)
	}

	def void setConditionNameList(value) {
		conditionNameList = toList(value)
	}

	def void setOrderNameList(value) {
		orderNameList = toList(value)
	}

//	def List getColumnNameList() {
//		return toList(column)
//	}
//	def List getConditionNameList() {
//		return toList(condition)
//	}
//	def List getOrderNameList() {
//		return toList(order)
//	}

	public String getOrder() {
		return orderNameList.join(', ')
	}

	public String getName() {
		def buff = ''<<''
		switch (type) {
			case 'find':
			case 'delete':
				buff = '' << "${type}By"
				conditionNameList.eachWithIndex { String colName, i ->
					if (i != 0) {
						buff << 'And'
					}
					buff << colName.toLowerCase().capitalize()
				}
				break;
			case 'master':
				buff = '' << "findMaster"
				break;
			default:
				break;
		}
		return buff
	}

	private List toList(String value) {
		if (!value) {
			return []
		}
		List list = value.toUpperCase().split(/\s*,\s*/)
		assert list
		return list
	}
}
