/**
 *
 */
package tools.daogen.loader.oracle;

import groovy.util.logging.Log4j
import tools.daogen.binding.ColumnBindings
import tools.daogen.binding.TableBindings
import tools.daogen.config.DaogenConfig
import tools.daogen.config.DaogenConfigFactory
import tools.db.bean.Column
import tools.db.bean.Table
import tools.db.loader.oracle.OracleTableConfigLoader


/**
 *
 *
 */
@Log4j
public class OracleTableGenConfigLoader extends OracleTableConfigLoader {

	/**
	 * �R���X�g���N�^
	 */
	public OracleTableConfigLoader(){
		DaogenConfig config = DaogenConfigFactory.getInstance()
		this.target= config.target
	}

	/* (�� Javadoc)
	 * @see jp.co.melco.mei.fa.tools.db.loader.BaseObjectConfigLoader#createTable()
	 */
	@Override
	protected Table createTable() {
		Table table = new TableBindings()()
		return table
	}

	/* (�� Javadoc)
	 * @see jp.co.melco.mei.fa.tools.db.loader.BaseObjectConfigLoader#createColumn()
	 */
	@Override
	protected Column createColumn() {
		Column column = new ColumnBindings()
	}
}
