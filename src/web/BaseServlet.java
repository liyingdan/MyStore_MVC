package web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ղ���
		String action = request.getParameter("action");
		try {
			//��ȡ��ǰ������ֽ�
			Class clazz = this.getClass();
			//��������action�ķ������˷����������������������ͺ���.class
			Method method = clazz.getMethod(action, HttpServletRequest.class,HttpServletResponse.class);
			//�ж���û�д���ķ���
			if(method != null) {
				//����о͵���(invokeֻ�ܵ��ù����ķ���)
				String desPath = (String)method.invoke(this, request,response);
				//ת��
				if(desPath != null){
					request.getRequestDispatcher(desPath).forward(request, response);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}


}