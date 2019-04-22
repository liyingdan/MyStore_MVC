package service;

import dao.AdminDao;
import domain.admin;

public class AdminService {

	public admin login(String name, String pwd) throws Exception {
		//调用dao层到数据库当中查询
		AdminDao adminDao = new AdminDao();
		admin admin = adminDao.checkAdmin(name,pwd);
		if(admin != null) {
			return admin;
		}
		else {
			throw new Exception("用户名或密码错误");
		}
	}

}
