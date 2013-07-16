/**
 *
 */
package tools.daogen.config

import groovy.util.slurpersupport.GPathResult

/**
 *
 *
 */
class DaogenConfigFactory {
	private DaogenConfigFactory() {
	}

	private static volatile DaogenConfig config;

	public static DaogenConfig getInstance() {
		if (config) {
			config
		} else {
			synchronized(DaogenConfig) {
				if (config) {
					config
				} else {
					config = newDocConfig()
				}
			}
		}
	}

	private static DaogenConfig newDocConfig() {
		String filePath = getFilePath()
		File file = new File(filePath);
		GPathResult xml = new XmlSlurper().parse(file)

		DaogenConfig config = new DaogenConfig();
		config.load(xml)

		return config
	}

	public static String getFilePath() {
		return ResourceBundle.getBundle("daogen").getString("daogen.file.path")
	}
}
