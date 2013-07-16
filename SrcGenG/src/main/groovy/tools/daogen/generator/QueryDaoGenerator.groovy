/**
 *
 */
package tools.daogen.generator

import groovy.util.logging.Log4j
import tools.db.loader.BaseObjectConfigLoader

/**
 *
 *
 */
@Log4j
class QueryDaoGenerator extends DaoGenerator {

	/**
	 * @param loader
	 */
	public QueryDaoGenerator(BaseObjectConfigLoader loader) {
		super(loader);
	}

	/* (”ñ Javadoc)
	 * @see jp.co.melco.mei.fa.daogen.generator.DaoGenerator#getRootPackage()
	 */
	@Override
	protected String getRootPackage() {
		return config.getQueryPackage();
	}

}
