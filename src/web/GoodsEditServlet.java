package web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.goods;
import service.GoodsService;

@WebServlet("/GoodsEditServlet")
public class GoodsEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("GoodsEditServlet");
		request.setCharacterEncoding("utf-8");
		//1.��ȡ���еĲ���
		Map<String, String[]> parameterMap = request.getParameterMap();
		goods goods = new goods(); //Ctrl+Shift+o : �Զ�����
		//2.��װ��goods����
		try {
			BeanUtils.populate(goods, parameterMap);
			//3.����id��������
			System.out.println(goods);
			//4.���÷���㣬��������
			GoodsService goodsService = new GoodsService();
			goods.setImage("goods_001.png");
			goodsService.updateGoods(goods);
			//5.��ת��main.jsp�б�
			request.getRequestDispatcher("/GoodsServlet?action=getListGoods").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
