/**
 *
 */
package tools.db.loader.oracle;

import groovy.util.logging.Log4j
import tools.db.DB
import tools.db.OracleDB
import tools.db.bean.TargetToExtract
import tools.db.loader.BaseTableConfigLoader


/**
 *
 *
 */
@Log4j
public class OracleTableConfigLoader extends BaseTableConfigLoader {

	/**
	 * �R���X�g���N�^
	 *
	 * @param tablePrefix �e�[�u���v���t�B�b�N�X
	 * @param allowPattern ������e�[�u�����̃p�^�[���i�ȗ��j
	 * @param denyPattern ���O����e�[�u�����̃p�^�[���i�ȗ��j
	 * @param isIncludeView isIncludeView �r���[���܂ނ��H
	 */
	public OracleTableConfigLoader(String tablePrefix, String allowPattern, String denyPattern, String isIncludeView) {
		super(tablePrefix, allowPattern, denyPattern, isIncludeView)
	}

	/**
	 * �R���X�g���N�^
	 *
	 * @param target �ǂݍ��ݑΏ�
	 */
	public OracleTableConfigLoader(TargetToExtract target) {
		super(target)
	}

	/* (�� Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.loader.BaseTableConfigLoader#getQuery()
	 */
	@Override
	protected String getFindTableNamesQuery(String prefix) {
		return "select table_name from user_tables where table_name like '${prefix}%'";
	}

	/* (�� Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.loader.BaseTableConfigLoader#newDb()
	 */
	@Override
	protected DB createDB() {
		return new OracleDB();
	}

	/* (�� Javadoc)
	 * @see jp.co.melco.mei.fa.tools.daogen.loader.BaseTableConfigLoader#getFindViewNamesQuery(java.lang.String)
	 */
	@Override
	protected String getFindViewNamesQuery(String prefix) {
		return "select * from user_views where view_name like '${prefix}%'";
	}
}
