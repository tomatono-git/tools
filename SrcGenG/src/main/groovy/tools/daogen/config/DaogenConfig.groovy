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

	/** �v���W�F�N�g�� */
	def String projectName

	/** �o�͕����R�[�h */
	def String outputEncoding

	/** �\�[�X�̃p�X */
	def String srcPath

	/** �e�[�u����`��Excel�t�@�C���p�X */
	def String excelFilePath

	/** �I���W�i��Dao�̃p�b�P�[�W�� */
	def String queryPackage

	/** INSERT�ŏ��O���鍀�� */
	def String excludeInsertColumnList
	/** UPDATE�ŏ��O���鍀�� */
	def String excludeUpdateColumnList

	/** �o�^�̗� */
	def String createDateColumnName
	/** �X�V�̗� */
	def String updateDateColumnName

	/** Dao�������N�G�� */
	def String generateQuery
	/** �N�G���� */
	def String generateQueryName

	/** Dao�N���X��` */
	def DaoClassConfig dao = new DaoClassConfig()
	/** Dto�N���X��` */
	def DtoClassConfig dto = new DtoClassConfig()
	/** �Ώۂ̒�` */
	def TargetConfig target = new TargetConfig()
	/** DDL��` */
	def DdlConfig ddl = new DdlConfig()
	/** �N���X�̒ǉ���` */
	def DaoAddition addition = new DaoAddition()

	/* (�� Javadoc)
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
	 * �N���X�����擾
	 * @param tableName �e�[�u����
	 * @param suffix �T�t�B�b�N�X
	 * @return �N���X��
	 */
	def String getClassName(String tableName, ClassConfig classConf) {
		return classConf.getClassName(tableName, target.tablePrefix)
	}

	/**
	 * Dto�N���X�����擾
	 * @param tableName �e�[�u����
	 * @return Dto�N���X��
	 */
	def String getDtoClassName(String tableName) {
		return getClassName(tableName, dto)
	}

	/**
	 * Dao�N���X�����擾
	 * @param tableName �e�[�u����
	 * @return Dao�N���X��
	 */
	def String getDaoClassName(String tableName) {
		return getClassName(tableName, dao)
	}
}
