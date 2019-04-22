package domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter@Getter
public class PageBean {
	//当前是哪一页
	private Integer currentPage;
	//共多少页
	private Integer totalPage;
	//共多少条记录
	private Integer totalCount;
	//当前页商品
	private List<goods> goodsList = new ArrayList<>();
}
