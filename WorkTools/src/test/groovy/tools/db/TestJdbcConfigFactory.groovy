/**
 *
 */
package tools.db;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tools.db.JdbcConfig;
import tools.db.JdbcConfigFactory;

/**
 *
 *
 */
class TestJdbcConfigFactory {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

//	/**
//	 * {@link jp.co.melco.mei.fa.tools.db.JdbcConfigFactory#initialize()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testInitialize() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.db.JdbcConfigFactory#getBundleName()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testGetBundleName() {
//		fail("�܂���������Ă��܂���");
//	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.JdbcConfigFactory#JdbcConfigFactory()} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	public void testJdbcConfigFactory() {
//		JdbcConfigFactory factory = JdbcConfigFactory.instance
		assert JdbcConfigFactory.instance.config == JdbcConfig.instance

		def config = JdbcConfig.instance

		assert config
		assert config.driverClassName == 'oracle.jdbc.OracleDriver'
		assert config.url == 'jdbc:oracle:thin:@meia2fdc:1521:ORCL'
		assert config.userName == 'oracle'
		assert config.password == 'PDMORA'
		assert config.schemaName == 'ORACLE'
	}

//	/**
//	 * {@link jp.co.melco.mei.fa.tools.db.JdbcConfigFactory#getConfig()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testGetConfig() {
//		fail("�܂���������Ă��܂���");
//	}

}
