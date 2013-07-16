/**
 *
 */
package tools.resources

import groovy.transform.TypeChecked

/**
 * �v���p�e�B�t�@�C���̃w���p�[�N���X
 *
 * �p����ł́A@Singleton(lazy = true) ������Ɨǂ��B
 */
@TypeChecked
abstract class PropertiesSupport {

	/**
	 * String�z��Ŏ擾����ꍇ�̋�؂蕶��
	 */
	protected String delimiterStringArray = /\s*,\s*/

	/**
	 * �v���p�e�B�t�@�C���̖��O
	 * @return
	 */
	abstract protected String getBundleName()

	/**
	 * ResourceBundle���擾
	 * @return
	 */
	protected ResourceBundle getBundle() {
		ResourceBundle.getBundle(bundleName)
	}

	/**
	 * �v���p�e�B�̏����擾
	 *
	 * @param key �v���p�e�B�[�̃L�[��
	 * @return �v���p�e�B�̒l
	 */
	protected String get(String key) {
		bundle.getString(key)
	}

	/**
	 * �v���p�e�B�̏����擾
	 *
	 * @param key �v���p�e�B�[�̃L�[��
	 * @return �v���p�e�B�̒l
	 */
	protected int getInt(String key) {
		Object value = bundle.getObject(key)
		int result = 0
		switch(value) {
			case int :
				result = (int)value
				break
			case String :
				result = value ? Integer.valueOf((String)value) : 0
				break
			default:
			// String�ōĎ擾
				String str = bundle.getString(key)
				result = value ? Integer.valueOf(str) : 0
		}
		return result
	}

	/**
	 * �v���p�e�B�̒l��String�z��Ŏ擾
	 *
	 * @param key �v���p�e�B�[�̃L�[��
	 * @return �v���p�e�B�̒l
	 */
	protected String[] getStringArray(String key) {
		String value = bundle.getString(key)
		String[] values = value ? value?.split(delimiterStringArray) : [].toArray()
		return values
	}

	/**
	 * �v���p�e�B�̒l��List�Ŏ擾
	 *
	 * @param key �v���p�e�B�[�̃L�[��
	 * @return �v���p�e�B�̒l
	 */
	protected List<?> getList(String key) {
		def values = this.getStringArray(key)
		def result = values.collect { it }
		return result
	}

	protected Date getDate(String key) {
		String value = bundle.getString(key)
		Date date
		if (value) {
			// GDK��Date#parse(String format, String input) ���Ăяo���Ă���B
			// JDK��Date#parse(String source)�ł͂Ȃ��B
			// @Deprecated ���t���Ă���̂�JDK�̕��BEclipse���������F���ł��Ă��Ȃ��B
			date = Date.parse('yyyy/MM/dd', value)
		}
		return date
	}
}
