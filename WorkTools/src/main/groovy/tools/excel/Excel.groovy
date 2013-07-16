/**
 *
 */
package tools.excel

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.CreationHelper
import org.apache.poi.ss.usermodel.FormulaEvaluator
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory

/**
 *
 *
 */
class Excel {

	public enum ExcelType {
		/**
		 * OLE2�`��
		 */
		HSSF,

		/**
		 * OOXML�`��
		 */
		XSSF
	}

	/**
	 * �R���X�g���N�^
	 * @param book ���[�N�u�b�N
	 */
	def Excel(Workbook book) {
		this.workbook = book
	}

	/**
	 * �R���X�g���N�^
	 * @param inStream Excel�t�@�C��
	 */
	def Excel(InputStream inStream) {
		this.workbook = WorkbookFactory.create(inStream)
	}

	/**
	 * �R���X�g���N�^
	 * @param inFile Excel�t�@�C��
	 */
	def Excel(File inFile) {
		inFile.withInputStream { InputStream inStream ->
			this.workbook = WorkbookFactory.create(inStream)
		}
	}

	/**
	 * �R���X�g���N�^
	 */
	def Excel(ExcelType type = ExcelType.HSSF) {
		switch(type) {
			case ExcelType.HSSF:
				this.workbook = new org.apache.poi.hssf.usermodel.HSSFWorkbook()
				break;
			case ExcelType.XSSF:
				this.workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook()
				break;
			default:
				throw new IllegalArgumentException("Invalid ExcelType. $type")
		}
	}

	/** ���[�N�u�b�N */
	def Workbook workbook

	/**
	 * �t�@�C���ɏo��
	 * @param file
	 */
	def void write(File outFile) {
		outFile.withOutputStream {OutputStream outStream ->
			this.workbook.write(outStream);
		}
	}

	/**
	 * �V�[�g���擾
	 *
	 * @param sheetName �V�[�g���i�ȗ������ꍇ�̓A�N�e�B�u�V�[�g�j
	 * @return �V�[�g
	 */
	def Sheet getSheet(String sheetName = null) {
		sheetName ? workbook.getSheet(sheetName) : workbook.getSheetAt(workbook.activeSheetIndex)
	}
	def Sheet getSheet(Integer sheetIndex) {
		workbook.getSheetAt(sheetIndex)
	}

	/**
	 * �V�[�g�̃f�[�^��ǂݍ���
	 *
	 * @return �V�[�g�̃f�[�^
	 */
	def List load(String sheetName = null) {
		def sheet = getSheet(sheetName)
		def rowList = []
		getRowRange(sheet)?.each { rowNum ->
			def row = sheet?.getRow(rowNum)
			getColRange(row)?.each { cellNum ->
				def colList = []
				def value = getCellValue(row, cellNum)
				colList + value
				rowList + colList
			}
		}
		return rowList
	}

	/**
	 * �V�[�g�̃f�[�^��ǂݍ���<br>
	 * �P�s�ڂ��w�b�_�[�s�Ƃ݂Ȃ��āA���ږ��ƒl��Map��List��Ԃ��B
	 *
	 * @return �V�[�g�̃f�[�^
	 */
	List<Map> loadWithColumnName(String sheetName = null) {

		List rowList = []
		Sheet sheet = workbook.getSheet(sheetName)

		def headerList = []
		def headerRow = sheet.getRow(0)
		getColRange(headerRow).each { colIdx ->
			def value = getCellValue(headerRow, colIdx)
			headerList.add(value)
		}

		getRowRange().eachWithIndex { rowIdx, idx ->
			if (idx == 0) {
				return
			}
			def row = sheet.getRow(rowIdx)
			if (!row) {
				return
			}

			def colList = [:]
			getColRange(row).each { colIdx ->
				def colName = headerList[colIdx]
				def value = getCellValue(row, colIdx)
				value = value != null ? value : ''
				colList[colName] = value
			}
			rowList.add(colList)
		}
		return rowList
	}

	/**
	 * �L���ȍs�͈͂��擾
	 *
	 * @param sheet �V�[�g
	 * @return �L���ȍs�͈�
	 */
	def Range getRowRange(Sheet sheet) {
		if (sheet == null) {
			return null
		}
		sheet.firstRowNum..sheet.lastRowNum
	}

	/**
	 * �L���ȍs�͈͂��擾
	 *
	 * @param sheetName �V�[�g��
	 * @return �L���ȍs�͈�
	 */
	def Range getRowRange(String sheetName = null) {
		def sheet = getSheet(sheetName)
		getRowRange(sheet)
	}

	/**
	 * �L���ȗ�͈͂��擾
	 *
	 * @param row �s
	 * @return �L���ȗ�͈�
	 */
	def Range getColRange(Row row) {
		if (row == null) {
			return null
		}
		row.firstCellNum..<row.lastCellNum
	}

	public Cell getCell(Sheet sheet, int rowIndex, int colIndex, boolean isCreate = false) {
		assert sheet

		Cell cell = null
		if (isCreate) {
			Row row = sheet.getRow(rowIndex) ?: sheet.createRow(rowIndex)
			cell = row.getCell(colIndex) ?: row.createCell(colIndex)
		} else {
			cell = sheet?.getRow(rowIndex)?.getCell(colIndex)
		}

		return cell
	}

	def CellStyle createCellStyle() {
		return workbook.createCellStyle()
	}

	/**
	 * �Z���̒l���擾
	 *
	 * @param rowIndex �s�C���f�b�N�X
	 * @param colIndex ��C���f�b�N�X
	 * @return �Z���̒l
	 */
	def getCellValue(String sheetName = null, rowIndex, colIndex) {
		def sheet = getSheet(sheetName)
		def cell = getCell(sheet, rowIndex, colIndex)
		return getCellValue(cell)
	}

	/**
	 * �Z���̒l���擾
	 *
	 * @param sheet �V�[�g
	 * @param rowIndex �s�C���f�b�N�X
	 * @param colIndex ��C���f�b�N�X
	 * @return �Z���̒l
	 */
	def getCellValue(Sheet sheet, rowIndex, colIndex) {
		def cell = getCell(sheet, rowIndex, colIndex)
		return getCellValue(cell)
	}

	/**
	 * �Z���̒l���擾
	 *
	 * @param row �s
	 * @param colIndex ��C���f�b�N�X
	 * @return �Z���̒l
	 */
	def getCellValue(Row row, colIndex) {
		def cell = row?.getCell(colIndex)
		return getCellValue(cell)
	}

	/**
	 * �Z���̒l���擾
	 *
	 * @param cell �Z��
	 * @return �Z���̒l
	 */
	def getCellValue(Cell cell) {
		if (!cell) {
			return null
		}
		def value = null;
		switch (cell.cellType) {
			case Cell.CELL_TYPE_NUMERIC:
				value = (int)cell.getNumericCellValue()
				break;
			case Cell.CELL_TYPE_STRING:
				value = cell.getStringCellValue()
				break;
			case Cell.CELL_TYPE_BLANK:
				value = ''
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue()
				break;
			case Cell.CELL_TYPE_ERROR:
				value = cell.getErrorCellValue()
				break;

			case Cell.CELL_TYPE_FORMULA:
			// �����͌��ʂ̒l�ɕϊ�
				CreationHelper crateHelper = cell.row.sheet.workbook.getCreationHelper();
				FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
				value = getCellValue(evaluator.evaluateInCell(cell));
				break;

			default:
				throw new RuntimeException("cellType=${cell.cellType}");
				break;
		}
		return value
	}

	def void setCellValue(Row row, int colIndex, value, CellStyle style = null) {
		assert row

		def cell = row.getCell(colIndex)
		if (cell == null) {
			cell = row.createCell(colIndex)
		}
		setCellValue(cell, value, style)
	}
	def void setCellValue(Sheet sheet, rowIndex, colIndex, value, CellStyle style = null) {
		assert sheet

		def cell = this.getCell(sheet, rowIndex, colIndex)
		Row row = sheet.getRow(rowIndex)
		if (row == null) {
			row = sheet.createRow(rowIndex)
		}
		setCellValue(row, colIndex, value, style)
	}
	def void setCellValue(Cell cell, value, CellStyle style = null) {
		assert cell

		cell.setCellValue(value);
		if (style) {
			cell.setCellStyle(style)
		}
	}
}
