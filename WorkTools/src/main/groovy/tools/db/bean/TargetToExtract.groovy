/**
 *
 */
package tools.db.bean

import groovy.transform.ToString

/**
 *
 *
 */
@ToString(includeNames = true, includeFields = true)
class TargetToExtract {

	/** �e�[�u���̃v���t�B�b�N�X */
	def String tablePrefix
	/**�ΏۂƂ���e�[�u���̃p�^�[��  */
	def String allowPattern
	/** ���O����e�[�u���̃p�^�[�� */
	def String denyPattern
	/** VIEW���܂߂邩 */
	def boolean isIncludeView

	/**
	 * ���O����e�[�u���������肷��B
	 *
	 * @param tableName
	 * @return
	 */
	def boolean isDeny(String tableName) {
		String pattern = denyPattern
		if (!pattern) {
			return false
		}
		return (tableName ==~ pattern)
	}

	/**
	 * �ΏۂƂ���e�[�u���������肷��B
	 *
	 * @param tableName
	 * @return
	 */
	def boolean isAllow(String tableName) {
		String pattern = allowPattern
		if (!pattern) {
			return true
		}
		return (tableName ==~ pattern)
	}
}
