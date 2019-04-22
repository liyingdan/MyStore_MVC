package test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import dao.GoodDao;
import domain.goods;

public class GoodsDaoTest {
	private GoodDao goodDao = new GoodDao();
	
	@Test
	public void testGetAllGoods() throws SQLException {
		List<goods> allGoods = goodDao.getAllGoods();
		System.out.println(allGoods);
	}
	
	@Test
	public void testAddGoods() throws SQLException {
		goods goods = new goods();
		goods.setName("∂¨ºæ–°∂Ã»π");
		goods.setPrice(55.2);
		goods.setImage("goods_10.png");
		goodDao.addGoods(goods);
	}
	
	@Test
	public void testDelGoods() throws SQLException {
		goodDao.delGoods(16);
	}
	
	@Test
	public void testupdateGoods() throws SQLException {
		goods goods = new goods();
		goods.setId(17);
		goods.setName("œƒºæ–°∂Ã»π");
		goods.setPrice(155.2);
		goods.setImage("goods_11.png");
		goodDao.updateGoods(goods);
	}
	
	
	
	
	
	
	
}
