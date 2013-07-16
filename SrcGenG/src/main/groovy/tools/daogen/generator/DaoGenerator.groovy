/**
 *
 */
package tools.daogen.generator;

import groovy.util.logging.Log4j
import tools.daogen.binding.TableBindings
import tools.db.loader.BaseObjectConfigLoader



/**
 *
 *
 */
@Log4j
public class DaoGenerator extends BaseGenerator {

	/**
	 * @param loader
	 */
	public DaoGenerator(BaseObjectConfigLoader loader) {
		super(loader);
	}

	@Override
	public void generate(Map map = [:]) {
		List<TableBindings> tableList = load(map);

		File outDir = getOutFileDir();
		writeDao(outDir, tableList);
		writeDto(outDir, tableList);
	}

	/**
	 * Dao���o��
	 *
	 * @param baseOutDir
	 *            �o�̓x�[�X�f�B���N�g��
	 */
	private void writeDao(File baseOutDir, List<TableBindings> tableList) {
		File outDir = new File(baseOutDir, config.dao.packageSuffix);
		File template = getDaoTemplateFile()
		tableList.each { TableBindings table ->
			File outFile = getSrcFile(outDir, table.daoClassName);
			Map binding = ['table' : table, 'config' : config]
			write(outFile, binding, template);
		}
	}

	/**
	 * Dto���o��
	 *
	 * @param baseOutDir
	 *            �o�̓x�[�X�f�B���N�g��
	 * @throws VelocityWrapperException
	 */
	private void writeDto(File baseOutDir, List<TableBindings> tableList) {
		File outDir = new File(baseOutDir, config.dto.packageSuffix);
		File template = getDtoTemplateFile()
		tableList.each { TableBindings table ->
			File outFile = getSrcFile(outDir, table.dtoClassName);
			Map binding = ['table' : table, 'config' : config]
			write(outFile, binding, template);
		}
	}

	private File getDaoTemplateFile() {
		def path = config.dao.template
		File file = null
		if (path) {
			file = new File(path)
		} else {
			file = new File('template', 'dao.template')
		}
		return file;
	}

	private File getDtoTemplateFile() {
		def path = config.dto.template
		File file = null
		if (path) {
			file = new File(path)
		} else {
			file = new File('template', 'dto.template')
		}
		return file;
	}

	/**
	 * �\�[�X�t�@�C�����擾
	 * @param dir �f�B���N�g��
	 * @param className �t�@�C����
	 * @return
	 */
	protected File getSrcFile(File dir, String className) {
		assert className
		return new File(dir, "${className}.java")
	}

	/**
	 * Java�̃\�[�X�t�@�C�����擾
	 * @param dir �f�B���N�g��
	 * @param className �N���X��
	 * @return
	 */
	private File getJavaFile(File dir, String className) {
		File file = new File(dir, "${className}.java");
		return file;
	}

	/*
	 * (�� Javadoc)
	 *
	 * @see jp.co.melco.mei.fa.daogen.SourceGenerator#getOutFileDir()
	 */
	@Override
	protected File getOutFileDir() {
		String srcPath = config.srcPath;
		String basePath = getRootPackage()?.split(/\./)?.join('/')
		assert basePath
		File dir = new File(srcPath, basePath);
		return dir;
	}

	/**
	 * ���[�g�p�b�P�[�W���擾<br>
	 * �iDao��Dto�̏�ʃp�b�P�[�W�j
	 * @return ���[�g�p�b�P�[�W
	 */
	protected String getRootPackage() {
		assert config.dao.basePackage
		assert config.dto.basePackage
		// TODO Dao��Dto�̋�ʂ��ł���悤�ɂ���
		return config.dao.basePackage  ?: config.dto.basePackage
	}
}