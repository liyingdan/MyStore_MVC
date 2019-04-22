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
	
	//1.�����ݿ��в�ѯ���е���Ʒ�б�
	public List<goods> getAllGoods() throws SQLException{
		//1)��ѯ����
		//QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "select * from goods";
		//2)ִ��sql
		List<goods> allGoods = null;
		allGoods = qr.query(sql, new BeanListHandler<goods>(goods.class));
		return allGoods;
	}
	
	
	//2.�����Ʒ�����ݿ���
	public void addGoods(goods goods) throws SQLException {
		//�������
		String sql ="insert into goods(name,price,image,gdesc,is_hot,cid) value(?,?,?,?,?,?)";
		qr.update(sql,goods.getName(),goods.getPrice(),goods.getImage(),goods.getGdesc(),goods.getIs_hot(),goods.getCid());
	}
	
	
	//3.����idɾ��һ����Ʒ
	public void delGoods(int id) throws SQLException {
		//ɾ������
		String sql = "delete from goods where id=?";
		qr.update(sql,id);
		
	}
	
	//4.����һ����Ʒ�������ݿ��и���id���¸���Ʒ
	public void updateGoods(goods goods) throws SQLException {
		//���²���
		String sql = "update goods set name=?,price=?,image=?, gdesc=?,is_hot=?,cid=? where id=?";
		qr.update(sql,goods.getName(),goods.getPrice(),goods.getImage(),goods.getGdesc(),goods.getIs_hot(),goods.getCid(),goods.getId());
	}

	//����id��ѯһ����Ʒ
	public goods getGoodsWithId(String id) throws Exception {
		String sql = "select * from goods where id=?";
		//sql�ǽ�������������ѯ��һ����������Ϊֻ��һ����дBeanHandler
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
