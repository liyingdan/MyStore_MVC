package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.goods;
import utils.JdbcUtil;

public class GoodDao {
	
	private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	
	//1.从数据库中查询所有的商品列表
	public List<goods> getAllGoods() throws SQLException{
		//1)查询操作
		//QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from goods";
		//2)执行sql
		List<goods> allGoods = null;
		allGoods = qr.query(sql, new BeanListHandler<goods>(goods.class));
		return allGoods;
	}
	
	
	//2.添加商品到数据库中
	public void addGoods(goods goods) throws SQLException {
		//插入操作
		String sql ="insert into goods(name,price,image,gdesc,is_hot,cid) value(?,?,?,?,?,?)";
		qr.update(sql,goods.getName(),goods.getPrice(),goods.getImage(),goods.getGdesc(),goods.getIs_hot(),goods.getCid());
	}
	
	
	//3.根据id删除一个商品
	public void delGoods(int id) throws SQLException {
		//删除操作
		String sql = "delete from goods where id=?";
		qr.update(sql,id);
		
	}
	
	//4.传入一个商品，到数据库中根据id更新该商品
	public void updateGoods(goods goods) throws SQLException {
		//更新操作
		String sql = "update goods set name=?,price=?,image=?, gdesc=?,is_hot=?,cid=? where id=?";
		qr.update(sql,goods.getName(),goods.getPrice(),goods.getImage(),goods.getGdesc(),goods.getIs_hot(),goods.getCid(),goods.getId());
	}

	//根据id查询一个商品
	public goods getGoodsWithId(String id) throws Exception {
		String sql = "select * from goods where id=?";
		//sql是结果集，后面跟查询的一个参数，因为只有一个，写BeanHandler
		goods goods = qr.query(sql, new BeanHandler<goods>(goods.class),Integer.parseInt(id));
		return goods;
	}


	public Long getCount() throws SQLException {
		String sql = "select count(*) from goods";
		Long count = (Long)qr.query(sql, new ScalarHandler());
		return count;
	}


	public List<goods> getPageData(Integer index, Integer pageCount) throws Exception {
		String sql = "select * from goods limit ?,?";
		List<goods> pageGoods = qr.query(sql, new BeanListHandler<goods>(goods.class),index,pageCount);
		return pageGoods;
	}
	
	
	
	
	
	
	
	
	
	
	
}
