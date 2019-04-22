package service;

import java.sql.SQLException;
import java.util.List;

import dao.GoodDao;
import domain.PageBean;
import domain.goods;

public class GoodsService {
	GoodDao goodDao = new GoodDao();
	public List<goods> getAllGoods() throws SQLException {
		//�����ݿ��л�ȡ���е�����
		List<goods> allGoods = goodDao.getAllGoods();
		return allGoods;
		
	}

	public void deleteGoods(String id) throws Exception{
		//����dao����ɾ����Ʒ
		goodDao.delGoods(Integer.parseInt(id));

	}

	public void addGoods(goods goods) throws Exception {
		// ����dao �������
		goodDao.addGoods(goods);
		
	}

	public goods getGoodsWithId(String id) throws Exception {
		// ����dao ����id��ѯһ����Ʒ 
		goods goods = goodDao.getGoodsWithId(id);
		return goods;
	}

	public void updateGoods(goods goods) throws Exception {
		// ����dao ����id������Ʒ
		goodDao.updateGoods(goods);
		
	}

	public PageBean getPageBean(Integer currentPage) throws Exception {
		PageBean pageBean = new PageBean();
		//���õ�ǰҳ
		pageBean.setCurrentPage(currentPage);
		//��ȡ�ж�������¼
		//�����ݿ��в�ѯ
		Long count = goodDao.getCount();
		pageBean.setTotalCount(count.intValue());
		//һҳչʾ����������
		Integer pageCount = 10;
		//��ҳ��
		double totalPage = Math.ceil(1.0 * pageBean.getTotalCount() / pageCount);//�˷�������ȡ��
		pageBean.setTotalPage((int)totalPage);
		//��ǰҳ��ѯ�ĽǱ�
		Integer index = (pageBean.getCurrentPage() - 1)*pageCount;
		List<goods> list = goodDao.getPageData(index,pageCount);
		pageBean.setGoodsList(list);
		return pageBean;
	}


	
	
	
}
