import groovy.sql.Sql
import jp.co.melco.mei.fa.tools.db.OracleDB

def db = new OracleDB()
// DB����f�[�^������
def result = db.connect() { Sql sql -> sql.rows('select * from KIREKI where rownum < 5') }

// �������ʂ�\��
result.each { println it }
