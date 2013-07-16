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
class DaoClassConfig extends ClassConfig {

	def String findMasterPattern

	/* (”ñ Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.config.roules.ClassConfig#load(groovy.util.slurpersupport.GPathResult)
	 */
	@Override
	public void load(GPathResult xml) {
		suffix = xml.src.dao.suffix
		basePackage = xml.src.basePackageName
		packageSuffix = xml.src.dao.packageName
		template = xml.src.dao.template
		baseClassName = xml.src.dao.baseClassName
		interfaceList = getInterfaceList(xml.src.dao.interface)
		enableGenerics = xml.src.dao.generics.text() as boolean
		enableAnnotation = xml.src.dao.annotation.text() as boolean
	}
}
