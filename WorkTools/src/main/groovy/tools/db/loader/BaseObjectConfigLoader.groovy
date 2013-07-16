/**
 *
 */
package tools.db.loader;

import tools.db.bean.Column
import tools.db.bean.Table
import tools.db.bean.TargetToExtract

/**
 *
 *
 */
public abstract class BaseObjectConfigLoader {

	/**
	 * �R���X�g���N�^
	 *
	 * @param tablePrefix �e�[�u���v���t�B�b�N�X
	 * @param allowPattern ������e�[�u�����̃p�^�[���i�ȗ��j
	 * @param denyPattern ���O����e�[�u�����̃p�^�[���i�ȗ��j
	 * @param isIncludeView isIncludeView �r���[���܂ނ��H
	 */
	protected BaseObjectConfigLoader(String tablePrefix, String allowPattern, String denyPattern, String isIncludeView) {
		this.target.tablePrefix= tablePrefix
		this.target.allowPattern= allowPattern
		this.target.denyPattern= denyPattern
		this.target.isIncludeView= isIncludeView
	}

	/**
	 * �R���X�g���N�^
	 *
	 * @param target �ǂݍ��ݑΏ�
	 */
	protected BaseObjectConfigLoader(TargetToExtract target) {
		this.target = target
	}

	protected TargetToExtract target = new TargetToExtract()

	protected Table createTable() {
		Table table = new Table()
		return table
	}

	protected Column createColumn() {
		Column column = new Column()
		return column
	}

	public abstract List<Table> load(Map map = [:]);
}
