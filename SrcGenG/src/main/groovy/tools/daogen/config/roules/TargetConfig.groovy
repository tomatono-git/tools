/**
 *
 */
package tools.daogen.config.roules

import groovy.transform.ToString
import groovy.util.slurpersupport.GPathResult
import tools.db.bean.TargetToExtract

/**
 *
 *
 */
@ToString(includeNames = true, includeFields = true)
class TargetConfig extends TargetToExtract implements Configuration {

	/* (”ñ Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.config.roules.ConfigRoules#load(groovy.util.slurpersupport.GPathResult)
	 */
	@Override
	def void load(GPathResult xml) {
		tablePrefix = xml.table.target.prefix
		denyPattern = xml.table.target.deny
		allowPattern = xml.table.target.allow
		isIncludeView = xml.table.target.view as boolean
	}
}
