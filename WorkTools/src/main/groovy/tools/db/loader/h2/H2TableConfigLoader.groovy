

/**
 *
 */
package tools.db.loader.h2;

import tools.db.DB
import tools.db.H2DB
import tools.db.bean.TargetToExtract
import tools.db.loader.BaseTableConfigLoader



/**
 *
 *
 */
class H2TableConfigLoader extends BaseTableConfigLoader {

	/**
	 * �R���X�g���N�^
	 *
	 * @param tablePrefix �e�[�u���v���t�B�b�N�X
	 * @param allowPattern ������e�[�u�����̃p�^�[���i�ȗ��j
	 * @param denyPattern ���O����e�[�u�����̃p�^�[���i�ȗ��j
	 * @param isIncludeView isIncludeView �r���[���܂ނ��H
	 */
	public H2TableConfigLoader(String tablePrefix, String allowPattern, String denyPattern, String isIncludeView) {
		super(tablePrefix, allowPattern, denyPattern, isIncludeView)
	}

	/**
	 * �R���X�g���N�^
	 *
	 * @param target �ǂݍ��ݑΏ�
	 */
	public H2TableConfigLoader(TargetToExtract target) {
		super(target)
	}

	/* (�� Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.loader.BaseTableConfigLoader#getQuery(java.lang.String)
	 */
	@Override
	protected String getFindTableNamesQuery(String prefix) {
		return "select * from INFORMATION_SCHEMA.tables where table_type = 'TABLE' and table_name like '${prefix}%'";
	}

	/* (�� Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.loader.BaseTableConfigLoader#createDB()
	 */
	@Override
	protected DB createDB() {
		return new H2DB();
	}

	/* (�� Javadoc)
	 * @see jp.co.melco.mei.fa.tools.daogen.loader.BaseTableConfigLoader#getFindViewNamesQuery(java.lang.String)
	 */
	@Override
	protected String getFindViewNamesQuery(String prefix) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}
}
