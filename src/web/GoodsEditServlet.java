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
		//1.获取所有的参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		goods goods = new goods(); //Ctrl+Shift+o : 自动导包
		//2.封装成goods对象
		try {
			BeanUtils.populate(goods, parameterMap);
			//3.根据id更新数据
			System.out.println(goods);
			//4.调用服务层，更新数据
			GoodsService goodsService = new GoodsService();
			goods.setImage("goods_001.png");
			goodsService.updateGoods(goods);
			//5.跳转回main.jsp列表
			request.getRequestDispatcher("/GoodsServlet?action=getListGoods").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
