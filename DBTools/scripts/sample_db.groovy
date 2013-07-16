import jp.co.melco.mei.fa.tools.db.JdbcConfig
import jp.co.melco.mei.fa.tools.db.OracleDB

def config = new JdbcConfig(
	driverClassName : 'oracle.jdbc.OracleDriver',
	url : 'jdbc:oracle:thin:@meia2fdc:1521:ORCL',
	userName : 'oracle',
	password : 'pdmora')
def db = new OracleDB(config)
// ※/src/main/resources/jdbc.properties で接続設定する場合は以下で良い。
//def db = new OracleDB()
// デフォルトコンストラクタ（OracleDB()）は勝手にjdbc.propertiesの設定を読み込む。

String query = '''
select
    *
from
    KIREKI
where
    rownum < 5
'''
def result = db.connect() { groovy.sql.Sql sql ->
	sql.rows(query)
}

result.eachWithIndex { Map row, i ->
	if (i == 0) {
		println row.keySet().join('\t')
	}
	// 'null' は '' に置換して表示
	println row.values().join('\t').replaceAll(/\bnull\b/, '')
}
