/**
 *
 */
package tools.excel;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tools.excel.Excel;

/**
 *
 *
 */
class TestExcel {

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
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#Excel(org.apache.poi.hssf.usermodel.HSSFWorkbook)} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testExcelHSSFWorkbook() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#Excel(java.io.InputStream)} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testExcelInputStream() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#Excel()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testExcel() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#write(java.io.File)} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testWrite() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getSheet()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testGetSheetString() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getSheet()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testGetSheet() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#load()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testLoadString() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#load()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testLoad() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#loadWithColumnName()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testLoadWithColumnNameString() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#loadWithColumnName()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testLoadWithColumnName() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getRowRange(org.apache.poi.hssf.usermodel.HSSFSheet)} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testGetRowRangeHSSFSheet() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getRowRange()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testGetRowRangeString() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getRowRange()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testGetRowRange() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getColRange(org.apache.poi.hssf.usermodel.HSSFRow)} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testGetColRange() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#createCellStyle()} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testCreateCellStyle() {
//		fail("�܂���������Ă��܂���");
//	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getCellValue(java.lang.Object, java.lang.Object)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	public void testGetCellValueStringObjectObject() {
		def excel = new Excel(new File('data/TestExcel.xls'))
		// �l�̎擾
		assert excel.getCellValue(2, 4) == '2-4'
	}

//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getCellValue(java.lang.Object, java.lang.Object)} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testGetCellValueObjectObject() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getCellValue(org.apache.poi.hssf.usermodel.HSSFSheet, java.lang.Object, java.lang.Object)} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testGetCellValueHSSFSheetObjectObject() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getCellValue(org.apache.poi.hssf.usermodel.HSSFRow, java.lang.Object)} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testGetCellValueHSSFRowObject() {
//		fail("�܂���������Ă��܂���");
//	}
//
//	/**
//	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#getCellValue(org.apache.poi.hssf.usermodel.HSSFCell)} �̂��߂̃e�X�g�E���\�b�h�B
//	 */
//	@Test
//	public void testGetCellValueHSSFCell() {
//		fail("�܂���������Ă��܂���");
//	}
//
	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#setCellValue(org.apache.poi.hssf.usermodel.HSSFRow, java.lang.Object, java.lang.Object)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	public void testSetCellValueHSSFRowObjectObjectHSSFCellStyle() {
		def excel = new Excel()
		excel.workbook.createSheet()
		def sheet = excel.getSheet()

		String value = '2-4'
		def style = excel.createCellStyle()

		// �l�̐ݒ�
		excel.setCellValue(sheet, 2, 4, value, style)
		// �l�̎擾
		assert excel.getCellValue(2, 4) == value
		excel.write(new File('data/TestExcel.xls'))
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#setCellValue(org.apache.poi.hssf.usermodel.HSSFRow, java.lang.Object, java.lang.Object)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	public void testSetCellValueHSSFRowObjectObject() {
		def excel = new Excel()
		excel.workbook.createSheet()
		def sheet = excel.getSheet()
		def row = sheet.createRow(2)
		String value = '2-4'

		// �l�̐ݒ�
		excel.setCellValue(row, 4, value)
		// �l�̎擾
		assert excel.getCellValue(2, 4) == value
		excel.write(new File('data/TestExcel.xls'))
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#setCellValue(org.apache.poi.hssf.usermodel.HSSFSheet, java.lang.Object, java.lang.Object, java.lang.Object)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	public void testSetCellValueHSSFSheetObjectObjectObjectHSSFCellStyle() {
		def excel = new Excel()
		excel.workbook.createSheet()
		def sheet = excel.getSheet()
		String value = '2-4'

		// �l�̐ݒ�
		excel.setCellValue(sheet, 2, 4, value)
		// �l�̎擾
		assert excel.getCellValue(2, 4) == value
		excel.write(new File('data/TestExcel.xls'))
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#setCellValue(org.apache.poi.hssf.usermodel.HSSFSheet, java.lang.Object, java.lang.Object, java.lang.Object)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	public void testSetCellValueHSSFSheetObjectObjectObject() {
		def excel = new Excel()
		excel.workbook.createSheet()
		def sheet = excel.getSheet()
		String value = '2-4'
		def style = excel.createCellStyle()

		// �l�̐ݒ�
		excel.setCellValue(sheet, 2, 4, value, style)
		// �l�̎擾
		assert excel.getCellValue(2, 4) == value
		excel.write(new File('data/TestExcel.xls'))
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#setCellValue(org.apache.poi.hssf.usermodel.HSSFCell, java.lang.Object)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	public void testSetCellValueHSSFCellObjectHSSFCellStyle() {
		def excel = new Excel()
		excel.workbook.createSheet()
		def sheet = excel.getSheet()
		String value = '2-4'
		def row = sheet.createRow(2)
		def cell = row.createCell(4)
		def style = excel.createCellStyle()

		// �l�̐ݒ�
		excel.setCellValue(cell, value, style)
		// �l�̎擾
		assert excel.getCellValue(2, 4) == value
		excel.write(new File('data/TestExcel.xls'))
	}

	/**
	 * {@link jp.co.melco.mei.fa.tools.excel.Excel#setCellValue(org.apache.poi.hssf.usermodel.HSSFCell, java.lang.Object)} �̂��߂̃e�X�g�E���\�b�h�B
	 */
	@Test
	public void testSetCellValueHSSFCellObject() {
		def excel = new Excel()
		excel.workbook.createSheet()
		def sheet = excel.getSheet()
		String value = '2-4'
		def row = sheet.createRow(2)
		def cell = row.createCell(4)

		// �l�̐ݒ�
		excel.setCellValue(cell, value)
		// �l�̎擾
		assert excel.getCellValue(2, 4) == value
		excel.write(new File('data/TestExcel.xls'))
	}

}
