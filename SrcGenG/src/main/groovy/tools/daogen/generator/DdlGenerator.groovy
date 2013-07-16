/**
 *
 */
package tools.daogen.generator

import groovy.util.logging.Log4j
import tools.daogen.binding.TableBindings
import tools.db.loader.BaseObjectConfigLoader


/**
 *
 *
 */
@Log4j
class DdlGenerator extends BaseGenerator {

	/**
	 * @param loader
	 */
	public DdlGenerator(BaseObjectConfigLoader loader) {
		super(loader);
	}

	/*
	 * (”ñ Javadoc)
	 *
	 * @see jp.co.melco.mei.fa.daogen.SourceGenerator#generate()
	 */
	@Override
	public void generate(Map map = [:]) {
		List<TableBindings> tableList = load(map);
		writeHistoryTableDdl(tableList);
		writeSequenceDdl(tableList);
		writeTriggerDdl(tableList);
	}

	private void writeHistoryTableDdl(List<TableBindings> tableList) {
		String inFileName = config.ddl.historyTableTemplate;
		String outFileName = config.ddl.historyTableOutput;

		writeSql(tableList, inFileName, outFileName)
	}

	private void writeSequenceDdl(List<TableBindings> tableList) {
		String inFileName = config.ddl.sequenceTemplate;
		String outFileName = config.ddl.sequenceOutput;

		writeSql(tableList, inFileName, outFileName)
	}

	private void writeTriggerDdl(List<TableBindings> tableList) {
		String inFileName = config.ddl.triggerTemplate;
		String outFileName = config.ddl.triggerOutput;

		writeSql(tableList, inFileName, outFileName)
	}

	private writeSql(List tableList, String templateFileName, String outFileName) {
		File templateFile = getTemplateFile(templateFileName)
		if (!templateFile.exists()) {
			throw new FileNotFoundException(templateFile.path);
		}
		log.debug("inFile :${templateFile}");

		File baseOutDir = getOutFileDir();
		File outDir = new File(baseOutDir, "CREATE");
		if (!outDir.exists()) {
			outDir.mkdirs();
		}
		File outFile = new File(outDir, outFileName);
		log.debug("outFile :${outFile.getPath()}");

		def binding = ['tableList' : tableList];

		write(outFile, binding, templateFile)
	}

	/*
	 * (”ñ Javadoc)
	 *
	 * @see jp.co.melco.mei.fa.daogen.SourceGenerator#getOutFileDir()
	 */
	@Override
	protected File getOutFileDir() {
		String outDir = config.ddl.outputDir;
		File dir = new File(outDir);
		return dir;
	}
}
