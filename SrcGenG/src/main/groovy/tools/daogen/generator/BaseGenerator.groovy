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

	/** テーブル定義のLoader */
	protected BaseObjectConfigLoader loader;
	/** ソース生成の設定 */
	protected DaogenConfig config = DaogenConfigFactory.getInstance();

	/**
	 * コンストラクタ
	 * @param loader テーブル定義のLoader
	 */
	public BaseGenerator(BaseObjectConfigLoader loader) {
		this.loader = loader;
	}

	/**
	 * ソース生成
	 */
	public abstract void generate(Map map = [:]);

	/**
	 * 出力先ディレクトリを取得
	 * @return 出力先ディレクトリ
	 */
	protected abstract File getOutFileDir();

	/**
	 * テーブル定義を読み込む
	 * @return テーブル定義の一覧
	 */
	public List<TableBindings> load(Map map = [:]) {
		List<TableBindings> tableList = loader.load(map);
		return tableList;
	}

	/**
	 * テンプレートファイルを取得
	 * @param name テンプレートファイル名
	 * @return テンプレートファイル
	 */
	protected File getTemplateFile(String name) {
		assert name
		return new File('template', "${name}")
	}

	/**
	 * 出力
	 * @param srcFile ソースファイル
	 * @param binding バインドパラメーター
	 * @param tmplFile テンプレートファイル
	 */
	protected void write(File srcFile, Map binding, File tmplFile) {
		log.debug("srcFile=$srcFile, tmplFile : $tmplFile")
		write(srcFile, tmplFile) { tmpl, src ->
			TemplateUtils.write(tmpl, binding, src)
		}
	}

	/**
	 * 出力
	 * @param srcFile ソースファイル
	 * @param tmplFile テンプレートファイル
	 * @param writeClos 出力処理（クロージャ）
	 * @return
	 */
	protected write(File srcFile, File tmplFile, Closure writeClos) {

		File parent = srcFile.getParentFile();
		if (parent.exists()) {
			//			parent.deleteDir()
			//			parent.mkdir();
		} else {
			// フォルダを生成
			parent.mkdirs();
		}

		if (srcFile.exists()) {
			// 既存ファイルを削除
			srcFile.delete()
		}

		writeClos(tmplFile, srcFile)

		log.info("out :${srcFile}")
	}
}