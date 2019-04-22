package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import domain.admin;
import utils.JdbcUtil;

public class AdminDao {

	public admin checkAdmin(String name, String pwd) throws SQLException {
		//�����ݿ⵱�в�ѯ
		//1.����
		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		//2.��ѯ
		String sql = "select * from admin where username=? and password=?";
		//3.ִ�в�ѯ
		admin admin = null;
		admin = qr.query(sql, new BeanHandler<admin>(admin.class),name,pwd);
		//���ز�ѯ���
		return admin;
		
		
		
	}

}
