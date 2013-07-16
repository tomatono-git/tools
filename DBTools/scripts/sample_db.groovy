import jp.co.melco.mei.fa.tools.db.JdbcConfig
import jp.co.melco.mei.fa.tools.db.OracleDB

def config = new JdbcConfig(
	driverClassName : 'oracle.jdbc.OracleDriver',
	url : 'jdbc:oracle:thin:@meia2fdc:1521:ORCL',
	userName : 'oracle',
	password : 'pdmora')
def db = new OracleDB(config)
// ��/src/main/resources/jdbc.properties �Őڑ��ݒ肷��ꍇ�͈ȉ��ŗǂ��B
//def db = new OracleDB()
// �f�t�H���g�R���X�g���N�^�iOracleDB()�j�͏����jdbc.properties�̐ݒ��ǂݍ��ށB

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
	// 'null' �� '' �ɒu�����ĕ\��
	println row.values().join('\t').replaceAll(/\bnull\b/, '')
}
