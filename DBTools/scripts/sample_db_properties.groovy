import groovy.sql.Sql
import jp.co.melco.mei.fa.tools.db.OracleDB

def db = new OracleDB()
// DBからデータを検索
def result = db.connect() { Sql sql -> sql.rows('select * from KIREKI where rownum < 5') }

// 検索結果を表示
result.each { println it }
