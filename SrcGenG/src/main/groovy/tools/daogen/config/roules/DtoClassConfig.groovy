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
class DtoClassConfig extends ClassConfig {

	/* (”ñ Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.config.roules.ClassConfig#load(groovy.util.slurpersupport.GPathResult)
	 */
	@Override
	public void load(GPathResult xml) {
		suffix = xml.src.dto.suffix
		basePackage = xml.src.basePackageName
		packageSuffix = xml.src.dto.packageName
		template = xml.src.dto.template
		baseClassName = xml.src.dto.baseClassName
		interfaceList = getInterfaceList(xml.src.dto.interface)
		enableGenerics = xml.src.dto.generics.text() as boolean
		enableAnnotation = xml.src.dto.annotation.text() as boolean
	}
}
