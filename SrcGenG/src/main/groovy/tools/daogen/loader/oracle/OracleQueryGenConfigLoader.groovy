/**
 *
 */
package tools.daogen.loader.oracle;

import groovy.util.logging.Log4j
import tools.daogen.binding.ColumnBindings
import tools.daogen.binding.TableBindings
import tools.db.bean.Column
import tools.db.bean.Table
import tools.db.loader.oracle.OracleQueryConfigLoader

/**
 *
 *
 */
@Log4j
public class OracleQueryGenConfigLoader extends OracleQueryConfigLoader {

	/**
	 * コンストラクタ
	 */
	public OracleQueryGenConfigLoader() {
	}

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.tools.db.loader.BaseObjectConfigLoader#createTable()
	 */
	@Override
	protected Table createTable() {
		Table table = new TableBindings()()
		return table
	}

	/* (非 Javadoc)
	 * @see jp.co.melco.mei.fa.tools.db.loader.BaseObjectConfigLoader#createColumn()
	 */
	@Override
	protected Column createColumn() {
		Column column = new ColumnBindings()
	}
}
