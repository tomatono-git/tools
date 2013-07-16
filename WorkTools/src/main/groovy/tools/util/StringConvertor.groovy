/**
 *
 */
package tools.util

import java.util.List;

/**
 *
 *
 */
final class StringConvertor {

	/**
	 *
	 */
	private StringConvertor() {
	}

	public static List<String> linesToList(String lines, boolean isTrim = true) {
		if (!lines) {
			// ��������Ȃ��̃��X�g��Ԃ�
			return []
		}

		String target
		if (isTrim) {
			target = lines.trim()
		} else {
			target = lines
		}

		if (!target) {
			// trim�������ʂ���Ȃ��̃��X�g��Ԃ�
			return []
		}

		// ���X�g�ɕϊ�
		List<String> list = []
		target.eachLine { list += it }

		return list
	}

}
