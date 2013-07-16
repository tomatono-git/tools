/**
 *
 */
package tools.daogen.generator;

import tools.daogen.exception.DaogenException
import tools.daogen.loader.ExcelObjectGenConfigLoader
import tools.daogen.loader.h2.H2TableGenConfigLoader
import tools.daogen.loader.oracle.OracleQueryGenConfigLoader
import tools.daogen.loader.oracle.OracleTableGenConfigLoader
import tools.db.DatabaseType
import tools.db.loader.BaseObjectConfigLoader
import tools.util.Utils

/**
 *
 *
 */
public class GeneratorFactory {

	public enum GeneratorType {
		TableDao, QueryDao, DDL
	}

	public enum LoaderType {
		Excel, DB, QUERY
	}

	public BaseGenerator create(GeneratorType generatorType, LoaderType loaderType, DatabaseType databaseType) {
		// TODO String Ç…ÉÅÉ\ÉbÉhÇí«â¡
		String.metaClass.toPascalCase = { Utils.toPascalCase(delegate) }

		// Create Loader
		BaseObjectConfigLoader loader = null;
		switch (loaderType) {
			case LoaderType.Excel:
				loader = new ExcelObjectGenConfigLoader();
				break;
			case LoaderType.DB:
				switch (databaseType) {
					case DatabaseType.Oracle:
						loader = new OracleTableGenConfigLoader();
						break;
					case DatabaseType.H2:
						loader = new H2TableGenConfigLoader();
						break;
					default:
						throw new DaogenException("Invalid DatabaseType. DatabaseType=$databaseType");
						break;
				}
				break;
			case LoaderType.QUERY:
				loader = new OracleQueryGenConfigLoader();
				break;

			default:
				throw new IllegalArgumentException("Invalid LoaderType. type :" + loaderType);
		}

		// Create Generator
		assert ((generatorType != GeneratorType.QueryDao)
		|| ((generatorType == GeneratorType.QueryDao)
		&& (loaderType == LoaderType.QUERY)))
		BaseGenerator generator = null;
		switch (generatorType) {
			case GeneratorType.TableDao:
				generator = new DaoGenerator(loader);
				break;
			case GeneratorType.QueryDao:
				generator = new QueryDaoGenerator(loader);
				break;
			case GeneratorType.DDL:
				generator = new DdlGenerator(loader);
				break;
			default:
				throw new IllegalArgumentException("Invalid GeneratorType. type :" + generatorType);
		}
		return generator;
	}
}
