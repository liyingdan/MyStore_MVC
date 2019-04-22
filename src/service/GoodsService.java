package service;

import java.sql.SQLException;
import java.util.List;

import dao.GoodDao;
import domain.PageBean;
import domain.goods;

public class GoodsService {
	GoodDao goodDao = new GoodDao();
	public List<goods> getAllGoods() throws SQLException {
		//从数据库中获取所有的数据
		List<goods> allGoods = goodDao.getAllGoods();
		return allGoods;
		
	}

	public void deleteGoods(String id) throws Exception{
		//调用dao让其删除商品
		goodDao.delGoods(Integer.parseInt(id));

	}

	public void addGoods(goods goods) throws Exception {
		// 调用dao 插入操作
		goodDao.addGoods(goods);
		
	}

	public goods getGoodsWithId(String id) throws Exception {
		// 调用dao 根据id查询一个商品 
		goods goods = goodDao.getGoodsWithId(id);
		return goods;
	}

	public void updateGoods(goods goods) throws Exception {
		// 调用dao 根据id更新商品
		goodDao.updateGoods(goods);
		
	}

	public PageBean getPageBean(Integer currentPage) throws Exception {
		PageBean pageBean = new PageBean();
		//设置当前页
		pageBean.setCurrentPage(currentPage);
		//获取有多少条记录
		//从数据库中查询
		Long count = goodDao.getCount();
		pageBean.setTotalCount(count.intValue());
		//一页展示多少条数据
		Integer pageCount = 10;
		//总页数
		double totalPage = Math.ceil(1.0 * pageBean.getTotalCount() / pageCount);//此方法向上取整
		pageBean.setTotalPage((int)totalPage);
		//当前页查询的角标
		Integer index = (pageBean.getCurrentPage() - 1)*pageCount;
		List<goods> list = goodDao.getPageData(index,pageCount);
		pageBean.setGoodsList(list);
		return pageBean;
	}


	
	
	
}
