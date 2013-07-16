/**
 *
 */
package tools.daogen.generator;

import groovy.util.logging.Log4j
import tools.daogen.binding.TableBindings
import tools.daogen.config.DaogenConfig
import tools.daogen.config.DaogenConfigFactory
import tools.db.loader.BaseObjectConfigLoader
import tools.util.TemplateUtils

/**
 *
 *
 */
@Log4j
public abstract class BaseGenerator {

	/** �e�[�u����`��Loader */
	protected BaseObjectConfigLoader loader;
	/** �\�[�X�����̐ݒ� */
	protected DaogenConfig config = DaogenConfigFactory.getInstance();

	/**
	 * �R���X�g���N�^
	 * @param loader �e�[�u����`��Loader
	 */
	public BaseGenerator(BaseObjectConfigLoader loader) {
		this.loader = loader;
	}

	/**
	 * �\�[�X����
	 */
	public abstract void generate(Map map = [:]);

	/**
	 * �o�͐�f�B���N�g�����擾
	 * @return �o�͐�f�B���N�g��
	 */
	protected abstract File getOutFileDir();

	/**
	 * �e�[�u����`��ǂݍ���
	 * @return �e�[�u����`�̈ꗗ
	 */
	public List<TableBindings> load(Map map = [:]) {
		List<TableBindings> tableList = loader.load(map);
		return tableList;
	}

	/**
	 * �e���v���[�g�t�@�C�����擾
	 * @param name �e���v���[�g�t�@�C����
	 * @return �e���v���[�g�t�@�C��
	 */
	protected File getTemplateFile(String name) {
		assert name
		return new File('template', "${name}")
	}

	/**
	 * �o��
	 * @param srcFile �\�[�X�t�@�C��
	 * @param binding �o�C���h�p�����[�^�[
	 * @param tmplFile �e���v���[�g�t�@�C��
	 */
	protected void write(File srcFile, Map binding, File tmplFile) {
		log.debug("srcFile=$srcFile, tmplFile : $tmplFile")
		write(srcFile, tmplFile) { tmpl, src ->
			TemplateUtils.write(tmpl, binding, src)
		}
	}

	/**
	 * �o��
	 * @param srcFile �\�[�X�t�@�C��
	 * @param tmplFile �e���v���[�g�t�@�C��
	 * @param writeClos �o�͏����i�N���[�W���j
	 * @return
	 */
	protected write(File srcFile, File tmplFile, Closure writeClos) {

		File parent = srcFile.getParentFile();
		if (parent.exists()) {
			//			parent.deleteDir()
			//			parent.mkdir();
		} else {
			// �t�H���_�𐶐�
			parent.mkdirs();
		}

		if (srcFile.exists()) {
			// �����t�@�C�����폜
			srcFile.delete()
		}

		writeClos(tmplFile, srcFile)

		log.info("out :${srcFile}")
	}
}