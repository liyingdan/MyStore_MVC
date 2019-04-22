package domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter@Getter
public class PageBean {
	//��ǰ����һҳ
	private Integer currentPage;
	//������ҳ
	private Integer totalPage;
	//����������¼
	private Integer totalCount;
	//��ǰҳ��Ʒ
	private List<goods> goodsList = new ArrayList<>();
}
