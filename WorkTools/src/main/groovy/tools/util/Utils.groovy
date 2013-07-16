package tools.util
/**
 *
 */

/**
 *
 *
 */
class Utils {

	/**
	 * �A���_�[�o�[��؂�̕�������p�X�J���P�[�X�ɕϊ�
	 * @param value �ϊ��Ώۂ̕�����
	 * @return �p�X�J���P�[�X�ɕϊ����ꂽ������
	 */
	static String toPascalCase(String value) {
		if (!value) return value
		def list = value.split('_').collect {
			return it.toLowerCase().capitalize()
		}
		def result = list.join('')
		return result
	}

	/**
	 * �A���_�[�o�[��؂�̕�������L�������P�[�X�ɕϊ�
	 * @param value �ϊ��Ώۂ̕�����
	 * @return �L�������P�[�X�ɕϊ����ꂽ������
	 */
	static String toCamelCase(String value) {
		if (!value) return value
		String pascal = toPascalCase(value)
		String result = Character.toLowerCase(pascal.charAt(0)) + pascal.substring(1);
		return result
	}

	static String toSnakeCase(String value) {
		if (!value) return value
		def result = value.replaceAll(/([A-Z][^A-Z])/) { all, String g1 ->
			return "_${g1.toLowerCase()}"
		}
		result = result.replaceFirst(/^_/, '').toLowerCase()

		return result
	}
}
