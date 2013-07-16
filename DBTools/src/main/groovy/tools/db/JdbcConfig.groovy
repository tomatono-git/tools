/**
 *
 */
package tools.db

import groovy.transform.AutoClone;
import groovy.transform.Immutable

@Immutable
public class JdbcConfig {
	def String driverClassName
	def String url
	def String userName
	def String password
	def String schemaName

	/**
	 * DB�ڑ��ݒ���v���p�e�B�t�@�C������擾
	 * @return
	 */
	public static JdbcConfig getInstance() {
		def config = JdbcConfigFactory.instance.config
	}
}
