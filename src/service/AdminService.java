package service;

import dao.AdminDao;
import domain.admin;

public class AdminService {

	public admin login(String name, String pwd) throws Exception {
		//����dao�㵽���ݿ⵱�в�ѯ
		AdminDao adminDao = new AdminDao();
		admin admin = adminDao.checkAdmin(name,pwd);
		if(admin != null) {
			return admin;
		}
		else {
			throw new Exception("�û������������");
		}
	}

}
