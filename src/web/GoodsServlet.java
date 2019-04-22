package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import domain.Category;
import domain.PageBean;
import domain.goods;
import service.CategoryService;
import service.GoodsService;

@WebServlet("/GoodsServlet")
public class GoodsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	// 获取所有商品
	public String getListGoods(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 1.调用服务层
		GoodsService goodsService = new GoodsService();
		try {
			List<goods> allGoods = goodsService.getAllGoods();
			// 对集合进行翻转
			Collections.reverse(allGoods);
			// 把数据写到request域
			PageBean pageBean = new PageBean();
			pageBean.setGoodsList(allGoods);
			pageBean.setTotalCount(10);
			pageBean.setTotalPage(10);
			request.setAttribute("pageBean", pageBean);
			// 转发
			return "admin/main.jsp";

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//页码处理
	public String getPageDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取当前页码
			String currentPage = request.getParameter("currentPage");
			//2.把页码给业务层，根据页码给我一个pageBean
			GoodsService goodsService = new GoodsService();
			PageBean pageBean = goodsService.getPageBean(Integer.parseInt(currentPage));
			//3.把pageBean写到域中
			request.setAttribute("pageBean", pageBean);
			//4.转发
			return "admin/main.jsp";
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	

	// 删除商品（调整格式：Ctrl+Shift+f）
	public String delGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.接收参数id
		String id = request.getParameter("id");
		// 2.调用服务层，让其删除商品
		GoodsService goodsService = new GoodsService();
		try {
			goodsService.deleteGoods(id);
			return "/GoodsServlet?action=getListGoods";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 修改商品前期工作
	public String EditUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取当前商品的id
		String id = request.getParameter("id");
		System.out.println("GoodsEditUIServlet===" + id);

		try {
			// 1.获取当前商品
			GoodsService goodsService = new GoodsService();
			goods goods = goodsService.getGoodsWithId(id);

			// 把商品写到域
			request.setAttribute("goods", goods);

			// 2.获取所有的分类
			CategoryService categoryService = new CategoryService();
			List<Category> allCategory = categoryService.findCategory();
			request.setAttribute("allCategory", allCategory);

			// 3.转发到edit.jsp
			return "admin/edit.jsp";
			// request.getRequestDispatcher("/admin/edit.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 修改商品
	 public String editGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {request.setCharacterEncoding("utf-8");
	 	request.setCharacterEncoding("utf-8");
	  	//response.setContentType("text/html;charset=utf-8");
	 	//1.获取所有的参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		goods goods = new goods(); //Ctrl+Shift+o : 自动导包
		//2.封装成goods对象
		try {
			BeanUtils.populate(goods, parameterMap);
			response.setContentType("text/html;charset=utf-8");
			//3.根据id更新数据
			System.out.println(goods);
			//4.调用服务层，更新数据
			GoodsService goodsService = new GoodsService();
			goods.setImage("goods_001.png");
			goodsService.updateGoods(goods);
			//5.跳转回main.jsp列表
			return "/GoodsServlet?action=getListGoods";
			//request.getRequestDispatcher("/GoodsServlet?action=getListGoods").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	  }

	// 新增商品前期
	public String AddUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.取出所有的分类
		CategoryService categoryService = new CategoryService();
		try {
			List<Category> allCategory = categoryService.findCategory();
			// 2.把分类存域当中
			request.setAttribute("allCategory", allCategory);
			System.out.println(allCategory);
			// 3.转发edit.jsp
			return "admin/add.jsp";
			//request.getRequestDispatcher("admin/add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*// 新增商品
	public String AddGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GoodsAddServlet");
		request.setCharacterEncoding("utf-8");
		//获取所有参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		System.out.println("parameterMap"+parameterMap);
		//把参数分装成对象（使用BeanUtils可以直接封装成对象，有两个参数，第一个参数是封装成什么对象，第二个是接收map）
		goods goods = new goods();
		try {
			BeanUtils.populate(goods, parameterMap);
			//添加图片暂时这样写
			goods.setImage("goods_001.png");
			System.out.println(goods);
			//调用服务层
			GoodsService goodsService = new GoodsService();
			goodsService.addGoods(goods);
			//跳转列表, 取出最新的数据GoodsListServlet
			return "/GoodsServlet?action=getListGoods";
			//request.getRequestDispatcher("/GoodsServlet?action=getListGoods").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	*/
	
	
	
	

}
