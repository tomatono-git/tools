/**
 *
 */
package tools.db

import groovy.util.logging.Log4j
import tools.resources.PropertiesSupport

public enum DatabaseType {
	Oracle, H2, UNKNOWN
}

/**
 *
 *
 */
@Singleton(lazy = true)
@Log4j
class JdbcConfigFactory extends PropertiesSupport {
	/**
	 *
	 */
	public JdbcConfigFactory() {
		def driverClassName = get('jdbc.driver.class.name')
		def url =  get('jdbc.url')
		def userName = get('jdbc.user.name')
		def password = get('jdbc.password')
		def schemaName = getSchemaNameValue()

		config = new JdbcConfig(
			driverClassName : driverClassName,
			url : url,
			userName : userName,
			password : password,
			schemaName : schemaName)
	}

	private final JdbcConfig config

	public JdbcConfig getConfig() {
		return config
	}

	/* (�� Javadoc)
	 * @see jp.co.melco.mei.fa.tools.resources.PropertiesSupport#getBundleName()
	 */
	@Override
	protected String getBundleName() {
		return 'jdbc';
	}

	private String getSchemaNameValue() {
		String schemaName = ''
		try {
			// PK�̎擾�Ɏg�p
			schemaName = get('jdbc.schema.name')
			if (schemaName) {
				schemaName = schemaName.toUpperCase()
			}

		} catch (Exception e) {
			log.warn(e.getMessage(), e)
		}
		return schemaName
	}

	/* (�� Javadoc)
	 * @see jp.co.melco.mei.fa.tools.resources.PropertiesSupport#initialize()
	 */
	@Override
	protected void initialize() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

}
