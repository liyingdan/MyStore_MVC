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

	// ��ȡ������Ʒ
	public String getListGoods(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 1.���÷����
		GoodsService goodsService = new GoodsService();
		try {
			List<goods> allGoods = goodsService.getAllGoods();
			// �Լ��Ͻ��з�ת
			Collections.reverse(allGoods);
			// ������д��request��
			PageBean pageBean = new PageBean();
			pageBean.setGoodsList(allGoods);
			pageBean.setTotalCount(10);
			pageBean.setTotalPage(10);
			request.setAttribute("pageBean", pageBean);
			// ת��
			return "admin/main.jsp";

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//ҳ�봦��
	public String getPageDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.��ȡ��ǰҳ��
			String currentPage = request.getParameter("currentPage");
			//2.��ҳ���ҵ��㣬����ҳ�����һ��pageBean
			GoodsService goodsService = new GoodsService();
			PageBean pageBean = goodsService.getPageBean(Integer.parseInt(currentPage));
			//3.��pageBeanд������
			request.setAttribute("pageBean", pageBean);
			//4.ת��
			return "admin/main.jsp";
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	

	// ɾ����Ʒ��������ʽ��Ctrl+Shift+f��
	public String delGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.���ղ���id
		String id = request.getParameter("id");
		// 2.���÷���㣬����ɾ����Ʒ
		GoodsService goodsService = new GoodsService();
		try {
			goodsService.deleteGoods(id);
			return "/GoodsServlet?action=getListGoods";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// �޸���Ʒǰ�ڹ���
	public String EditUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ��ǰ��Ʒ��id
		String id = request.getParameter("id");
		System.out.println("GoodsEditUIServlet===" + id);

		try {
			// 1.��ȡ��ǰ��Ʒ
			GoodsService goodsService = new GoodsService();
			goods goods = goodsService.getGoodsWithId(id);

			// ����Ʒд����
			request.setAttribute("goods", goods);

			// 2.��ȡ���еķ���
			CategoryService categoryService = new CategoryService();
			List<Category> allCategory = categoryService.findCategory();
			request.setAttribute("allCategory", allCategory);

			// 3.ת����edit.jsp
			return "admin/edit.jsp";
			// request.getRequestDispatcher("/admin/edit.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// �޸���Ʒ
	 public String editGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {request.setCharacterEncoding("utf-8");
	 	request.setCharacterEncoding("utf-8");
	  	//response.setContentType("text/html;charset=utf-8");
	 	//1.��ȡ���еĲ���
		Map<String, String[]> parameterMap = request.getParameterMap();
		goods goods = new goods(); //Ctrl+Shift+o : �Զ�����
		//2.��װ��goods����
		try {
			BeanUtils.populate(goods, parameterMap);
			response.setContentType("text/html;charset=utf-8");
			//3.����id��������
			System.out.println(goods);
			//4.���÷���㣬��������
			GoodsService goodsService = new GoodsService();
			goods.setImage("goods_001.png");
			goodsService.updateGoods(goods);
			//5.��ת��main.jsp�б�
			return "/GoodsServlet?action=getListGoods";
			//request.getRequestDispatcher("/GoodsServlet?action=getListGoods").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	  }

	// ������Ʒǰ��
	public String AddUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.ȡ�����еķ���
		CategoryService categoryService = new CategoryService();
		try {
			List<Category> allCategory = categoryService.findCategory();
			// 2.�ѷ��������
			request.setAttribute("allCategory", allCategory);
			System.out.println(allCategory);
			// 3.ת��edit.jsp
			return "admin/add.jsp";
			//request.getRequestDispatcher("admin/add.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*// ������Ʒ
	public String AddGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GoodsAddServlet");
		request.setCharacterEncoding("utf-8");
		//��ȡ���в���
		Map<String, String[]> parameterMap = request.getParameterMap();
		System.out.println("parameterMap"+parameterMap);
		//�Ѳ�����װ�ɶ���ʹ��BeanUtils����ֱ�ӷ�װ�ɶ�����������������һ�������Ƿ�װ��ʲô���󣬵ڶ����ǽ���map��
		goods goods = new goods();
		try {
			BeanUtils.populate(goods, parameterMap);
			//���ͼƬ��ʱ����д
			goods.setImage("goods_001.png");
			System.out.println(goods);
			//���÷����
			GoodsService goodsService = new GoodsService();
			goodsService.addGoods(goods);
			//��ת�б�, ȡ�����µ�����GoodsListServlet
			return "/GoodsServlet?action=getListGoods";
			//request.getRequestDispatcher("/GoodsServlet?action=getListGoods").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	*/
	
	
	
	

}
