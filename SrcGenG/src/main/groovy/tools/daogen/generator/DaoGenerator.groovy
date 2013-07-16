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
	 * Daoを出力
	 *
	 * @param baseOutDir
	 *            出力ベースディレクトリ
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
	 * Dtoを出力
	 *
	 * @param baseOutDir
	 *            出力ベースディレクトリ
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
	 * ソースファイルを取得
	 * @param dir ディレクトリ
	 * @param className ファイル名
	 * @return
	 */
	protected File getSrcFile(File dir, String className) {
		assert className
		return new File(dir, "${className}.java")
	}

	/**
	 * Javaのソースファイルを取得
	 * @param dir ディレクトリ
	 * @param className クラス名
	 * @return
	 */
	private File getJavaFile(File dir, String className) {
		File file = new File(dir, "${className}.java");
		return file;
	}

	/*
	 * (非 Javadoc)
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
	 * ルートパッケージを取得<br>
	 * （DaoとDtoの上位パッケージ）
	 * @return ルートパッケージ
	 */
	protected String getRootPackage() {
		assert config.dao.basePackage
		assert config.dto.basePackage
		// TODO DaoとDtoの区別ができるようにする
		return config.dao.basePackage  ?: config.dto.basePackage
	}
}