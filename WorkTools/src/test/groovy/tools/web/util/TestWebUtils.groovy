/**
 *
 */
package tools.web.util;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tools.web.util.WebUtils;

/**
 *
 *
 */
class TestWebUtils {

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
//	 * {@link jp.co.melco.mei.fa.tools.web.util.WebUtils#WebUtils()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testWebUtils() {
//		fail("�܂���������Ă��܂���");
//	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.web.util.WebUtils#toQueryString(java.util.Map, boolean, java.lang.String)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	public void testToQueryStringMapBooleanString() {
		def params = [:]
		params['denshi'] = '1'
		//		params['backdoc'] = null
		params['author'] = '��| �ꐢ�@�o�k�l�Z�Q'
		params['createdate'] = new Date().format('yyyy/MM/dd')
		params['remark'] = '�O���[�v���ʔF�ؑΉ��̎����f�[�^'
		def result = WebUtils.toQueryString(params, true , 'Shift_JIS')

		def before = '�O���[�v���ʔF�ؑΉ��̎����f�[�^'
		def encodeVal = URLEncoder.encode(before, 'Shift_JIS')
		def decodeVal = URLDecoder.decode(encodeVal, 'Shift_JIS')

		assert before == decodeVal
		assert decodeVal == '�O���[�v���ʔF�ؑΉ��̎����f�[�^'

		def encodeVal2 = URLEncoder.encode(before, 'UTF-8')


		fail("�܂���������Ă��܂���");
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.web.util.WebUtils#toQueryString(java.util.Map)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	public void testToQueryStringMap() {
		def params = [:]
		params['denshi'] = '1'
		//		params['backdoc'] = null
		params['author'] = '��| �ꐢ�@�o�k�l�Z�Q'
		params['createdate'] = new Date().format('yyyy/MM/dd')
		params['remark'] = '�O���[�v���ʔF�ؑΉ��̎����f�[�^'
		def result = WebUtils.toQueryString(params)


		fail("�܂���������Ă��܂���");
	}


}
