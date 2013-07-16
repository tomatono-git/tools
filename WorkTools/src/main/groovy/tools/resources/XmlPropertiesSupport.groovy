/**
 *
 */
package tools.resources

import tools.properties.XMLResourceBundle


/**
 *
 *
 */
abstract class XmlPropertiesSupport extends PropertiesSupport {

	/**
	 *
	 */
	public XmlPropertiesSupport() {
	}

	/* (�� Javadoc)
	 * @see jp.co.melco.mei.fa.tools.resources.PropertiesSupport#getBundle()
	 */
	@Override
	protected ResourceBundle getBundle() {
		XMLResourceBundle.getBundleFromXml(bundleName)
	}
}
