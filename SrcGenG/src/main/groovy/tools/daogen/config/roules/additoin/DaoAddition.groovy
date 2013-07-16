/**
 *
 */
package tools.daogen.config.roules.additoin

import groovy.transform.ToString
import groovy.util.logging.Log4j
import groovy.util.slurpersupport.GPathResult
import tools.daogen.binding.TableBindings
import tools.daogen.exception.DaogenException

/**
 *
 *
 */
@Log4j
@ToString(includeNames = true, includeFields = true)
class DaoAddition {

	/** テーブルごとの定義 */
	def Map<String, List<DaoMethodAdditon>> methodMap
	/** order by */
	def Map<String, String> orderMap

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.config.roules.ConfigRoules#load(groovy.util.slurpersupport.GPathResult)
	 */
	@Override
	def void load(GPathResult xml) {
		methodMap = [:]
		orderMap = [:]
		xml.src.addition.dao.table.each { tableXml ->
			String tableName = tableXml.@name
			List methodList = []
			tableXml.method.each { methodXml ->
				def type = methodXml.@type
				if (!(type ==~ /^(find|delete|master)$/)) {
					throw new DaogenException("オリジナルメソッドの種別が不正です。type=${methodConf.type}")
				}
				def method = new DaoMethodAdditon()
				method.tableName = tableName
				method.type = type
				method.columnNameList = methodXml.column.text()
				method.conditionNameList = methodXml.condition.text()
				method.orderNameList = methodXml.order.text()

				log.debug(method)
				methodList.add(method)
			}
			methodMap[tableName] = methodList
			String order = tableXml.order.text()
			order = order ? order.trim() : ''
			if (order) {
				orderMap[tableName] = order
			}
		}
	}

	def List<DaoMethodAdditon> getMethodAdditonList(TableBindings table) {

		if (table.columnList && !table.columnMap) {
			// columnMap の初期化
			table.initColumnMap()
		}

		String tableName = table.name
		List list = methodMap[tableName]
		if (!list) {
			// 追加のメソッドが無ければ処理しない
			return []
		}

		list.each { DaoMethodAdditon method ->
			method.columnList = method.columnNameList.collect {
				assert table.columnMap.containsKey(it)
				return table.columnMap[it]
			}
			method.conditionList = method.conditionNameList.collect {
				assert table.columnMap.containsKey(it)
				return table.columnMap[it]
			}
			method.orderList = method.orderNameList.collect {
				assert table.columnMap.containsKey(it)
				return table.columnMap[it]
			}
		}
	}

	def String getOrder(TableBindings table) {
		orderMap[table.name]
	}

	private String getMethodName(String tableName, String methodType, List<String> conditionList) {
		def buff = '' << "${methodType}By"
		conditionList.eachWithIndex { col, i ->
			if (i != 0) {
				buff << 'And'
			}
			buff << col.toLowerCase().capitalize()
		}
		return buff
	}
}
