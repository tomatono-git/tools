/**
 *
 */
package tools.daogen.config.roules

import groovy.transform.ToString
import groovy.util.slurpersupport.GPathResult

/**
 *
 *
 */
@ToString(includeNames = true, includeFields = true)
class DdlConfig extends ClassConfig {
	/** DDLの出力先 */
	def String outputDir

	/** 履歴テーブルのDDL出力先 */
	def String historyTableOutput

	/** シーケンスのDDL出力先 */
	def String sequenceOutput

	/** トリガーのDDL出力先 */
	def String triggerOutput

	/** 履歴テーブルのテンプレート名 */
	def String historyTableTemplate

	/** シーケンスのテンプレート名 */
	def String sequenceTemplate

	/** トリガーのテンプレート名 */
	def String triggerTemplate

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.config.roules.ConfigRoules#load(groovy.util.slurpersupport.GPathResult)
	 */
	@Override
	def void load(GPathResult xml) {
		outputDir = xml.table.ddl.outputDir
		historyTableTemplate = xml.table.ddl.history.template
		sequenceTemplate = xml.table.ddl.sequence.template
		triggerTemplate = xml.table.ddl.trigger.template
		triggerOutput = xml.table.ddl.trigger.output
		historyTableOutput = xml.table.ddl.history.output
		sequenceOutput = xml.table.ddl.sequence.output
	}
}
