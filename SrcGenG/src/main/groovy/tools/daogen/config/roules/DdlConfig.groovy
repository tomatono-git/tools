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
	/** DDL�̏o�͐� */
	def String outputDir

	/** �����e�[�u����DDL�o�͐� */
	def String historyTableOutput

	/** �V�[�P���X��DDL�o�͐� */
	def String sequenceOutput

	/** �g���K�[��DDL�o�͐� */
	def String triggerOutput

	/** �����e�[�u���̃e���v���[�g�� */
	def String historyTableTemplate

	/** �V�[�P���X�̃e���v���[�g�� */
	def String sequenceTemplate

	/** �g���K�[�̃e���v���[�g�� */
	def String triggerTemplate

	/* (�� Javadoc)
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
