/**
 *
 */
package tools.db.loader;

import static org.junit.Assert.*
import groovy.util.logging.Log4j

import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

import tools.db.bean.Table
import tools.db.bean.TargetToExtract
import tools.db.loader.oracle.OracleTableConfigLoader

/**
 *
 *
 */
@Log4j
class TestOracleTableConfigLoader {

	private OracleTableConfigLoader loader;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		TargetToExtract extract = new TargetToExtract(tablePrefix : "EFR_", isIncludeView: true)
//		PropertyResourceBundle bundle = PropertyResourceBundle.getBundle('log4j')
//		bundle.properties
//		def config = new ConfigSlurper().parse(props)
//		props = config.toProperties()
		loader = new OracleTableConfigLoader(new TargetToExtract(tablePrefix : "EFR_", isIncludeView: true))
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.OracleTableConfigLoader#getFindTableNamesQuery(java.lang.String)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testGetFindTableNamesQuery() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.OracleTableConfigLoader#getFindViewNamesQuery(java.lang.String)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testGetFindViewNamesQuery() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.OracleTableConfigLoader#createDB()} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testCreateDB() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.OracleTableConfigLoader#OracleTableConfigLoader(java.lang.String, java.lang.String, java.lang.String, java.lang.String)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testOracleTableConfigLoaderStringStringStringString() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.OracleTableConfigLoader#OracleTableConfigLoader(jp.co.melco.mei.fa.tools.db.bean.TargetToExtract)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testOracleTableConfigLoaderTargetToExtract() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#load()} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testLoadMap() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#load()} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	public void testLoad() {
		assert loader
		log.debug 'loader.load()'
		def result = loader.load()
		assert result.size()

//		assert log.debugEnabled

		def logger = log
		result.each { Table table ->
			assert table.name
			assert table.logicalName
			assert table.columnList
			assert table.columnMap
//			assert table.pkList
//			assert table.remarks

			def message = """
�e�[�u���������F${table.name}
�e�[�u���_�����F${table.logicalName}
��i�ꗗ�j�F${table.columnList}
��iMap�j�F${table.columnMap}
��L�[�F${table.pkList}
���l�F${table.remarks}
"""

//			assert logger.debugEnabled
			logger.debug(message)
		}
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getTableComments(java.util.List)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testGetTableComments() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getColumnComment(java.util.List)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testGetColumnComment() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#setAdditionalOption(jp.co.melco.mei.fa.tools.db.bean.Table)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testSetAdditionalOption() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getTableNameList(java.lang.String, boolean)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testGetTableNameListStringStringStringBoolean() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getTableNameList(java.lang.String, boolean)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testGetTableNameListStringStringBoolean() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getTableNameList(java.lang.String, boolean)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testGetTableNameListStringBoolean() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getObjectNameList(java.lang.String, java.lang.String, java.lang.String)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testGetObjectNameListStringStringStringStringString() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getObjectNameList(java.lang.String, java.lang.String, java.lang.String)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testGetObjectNameListStringStringStringString() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getObjectNameList(java.lang.String, java.lang.String, java.lang.String)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testGetObjectNameListStringStringString() {
		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.db.loader.BaseTableConfigLoader#getPkList(java.lang.String, java.lang.String, java.util.List)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	@Ignore
	public void testGetPkList() {
		fail("�܂���������Ă��܂���");
	}

}
