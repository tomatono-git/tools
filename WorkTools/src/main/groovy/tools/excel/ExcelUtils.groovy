/**
 *
 */
package tools.excel

/**
 *
 *
 */
class ExcelUtils {

	/**
	 * �V�[�g��ǂݍ���ŏ�������
	 * @param file Excel�t�@�C��
	 * @param clos ����
	 */
	static void read(File file, Closure<Excel> clos) {
		def excel = new Excel(file)
		clos(excel)
	}

	/**
	 * �V�[�g�̃f�[�^��ǂݍ���
	 * @param file Excel�t�@�C��
	 * @param sheetName �V�[�g��
	 * @return �V�[�g�̃f�[�^
	 */
	static List load(File file, String sheetName) {
		read(file) { Excel excel ->
			excel.load(sheetName)
		}
	}

	/**
	 * �V�[�g�̃f�[�^��ǂݍ���
	 *
	 * @param file Excel�t�@�C��
	 * @return �V�[�g�̃f�[�^
	 */
	static List<Map> loadWithColumnName(File file, String sheetName = 'Sheet1') {
		file.withInputStream { InputStream inStream ->
			def excel = new Excel(inStream)
			excel.loadWithColumnName(sheetName)
		}
	}

	static void write(File file, Closure<Excel> clos) {
		file.withOutputStream {OutputStream outStream ->
			def excel = new Excel()
			clos(excel)
			excel.workbook.write(outStream);
		}
	}
}
