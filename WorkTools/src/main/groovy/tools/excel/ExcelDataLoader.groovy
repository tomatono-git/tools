/**
 *
 */
package tools.excel

import groovy.util.logging.Log4j



/**
 *
 *
 */
@Log4j
class ExcelDataLoader {

	/**
	 * Excel�̃f�[�^��ǂݍ���
	 *
	 * @param dir Excel�t�@�C���̑��݂���f�B���N�g��
	 * @param tableName �e�[�u����
	 * @return Excel�̃f�[�^
	 */
	def List<Map> loadTable(File dir, String tableName) {
		def bookName = "ORACLE.${tableName}"
		def inFile = new File (dir, "${bookName}.xls");
		return ExcelUtils.loadWithColumnName(inFile)
	}
}
