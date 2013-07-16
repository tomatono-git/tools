import jp.co.melco.mei.fa.tools.db.JdbcConfig
import groovy.sql.Sql
import jp.co.melco.mei.fa.tools.db.OracleDB

def config = new JdbcConfig(
    driverClassName : 'oracle.jdbc.OracleDriver',
    url : 'jdbc:oracle:thin:@meia2fdc:1521:ORCL',
    userName : 'oracle',
    password : 'pdmora')
def db = new OracleDB(config)
// DBからデータを検索
def result = db.connect() { Sql sql -> sql.rows('select * from KIREKI where rownum < 5') }

// 検索結果を表示
result.each { println it }
