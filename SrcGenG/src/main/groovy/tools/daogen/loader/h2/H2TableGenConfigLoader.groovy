/**
 *
 */
package tools.daogen.loader.h2;

import tools.daogen.binding.ColumnBindings
import tools.daogen.binding.TableBindings
import tools.daogen.config.DaogenConfig
import tools.daogen.config.DaogenConfigFactory
import tools.db.bean.Column
import tools.db.bean.Table
import tools.db.loader.h2.H2TableConfigLoader



/**
 *
 *
 */
class H2TableGenConfigLoader extends H2TableConfigLoader {

	/**
	 * コンストラクタ
	 */
	public H2TableGenConfigLoader(){
		DaogenConfig config = DaogenConfigFactory.getInstance()
		this.target= config.target
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
