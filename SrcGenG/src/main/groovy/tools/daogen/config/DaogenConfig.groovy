/**
 *
 */
package tools.daogen.config

import groovy.util.slurpersupport.GPathResult
import tools.daogen.config.roules.ClassConfig
import tools.daogen.config.roules.Configuration
import tools.daogen.config.roules.DaoClassConfig
import tools.daogen.config.roules.DdlConfig
import tools.daogen.config.roules.DtoClassConfig
import tools.daogen.config.roules.TargetConfig
import tools.daogen.config.roules.additoin.DaoAddition

/**
 *
 *
 */
def final class DaogenConfig implements Configuration {

	/** プロジェクト名 */
	def String projectName

	/** 出力文字コード */
	def String outputEncoding

	/** ソースのパス */
	def String srcPath

	/** テーブル定義書Excelファイルパス */
	def String excelFilePath

	/** オリジナルDaoのパッケージ名 */
	def String queryPackage

	/** INSERTで除外する項目 */
	def String excludeInsertColumnList
	/** UPDATEで除外する項目 */
	def String excludeUpdateColumnList

	/** 登録の列名 */
	def String createDateColumnName
	/** 更新の列名 */
	def String updateDateColumnName

	/** Dao生成元クエリ */
	def String generateQuery
	/** クエリ名 */
	def String generateQueryName

	/** Daoクラス定義 */
	def DaoClassConfig dao = new DaoClassConfig()
	/** Dtoクラス定義 */
	def DtoClassConfig dto = new DtoClassConfig()
	/** 対象の定義 */
	def TargetConfig target = new TargetConfig()
	/** DDL定義 */
	def DdlConfig ddl = new DdlConfig()
	/** クラスの追加定義 */
	def DaoAddition addition = new DaoAddition()

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.config.roules.Configuration#load(groovy.util.slurpersupport.GPathResult)
	 */
	@Override
	public void load(GPathResult xml) {
		target.load(xml)
		ddl.load(xml)
		dto.load(xml)
		dao.load(xml)
		addition.load(xml)

		projectName = xml.project
		outputEncoding = xml.outputEncoding
		srcPath = xml.src.path
		excelFilePath = xml.excel.filePath

		queryPackage = xml.src.queryPackageName

		createDateColumnName = xml.table.columnName.createDate
		updateDateColumnName = xml.table.columnName.updateDate

		excludeInsertColumnList = xml.table.columnName.create ?: ''
		excludeUpdateColumnList = xml.table.columnName.update ?: ''

		generateQuery = xml.table.query
		generateQueryName = xml.table.query.@name
	}

	/**
	 * クラス名を取得
	 * @param tableName テーブル名
	 * @param suffix サフィックス
	 * @return クラス名
	 */
	def String getClassName(String tableName, ClassConfig classConf) {
		return classConf.getClassName(tableName, target.tablePrefix)
	}

	/**
	 * Dtoクラス名を取得
	 * @param tableName テーブル名
	 * @return Dtoクラス名
	 */
	def String getDtoClassName(String tableName) {
		return getClassName(tableName, dto)
	}

	/**
	 * Daoクラス名を取得
	 * @param tableName テーブル名
	 * @return Daoクラス名
	 */
	def String getDaoClassName(String tableName) {
		return getClassName(tableName, dao)
	}
}
