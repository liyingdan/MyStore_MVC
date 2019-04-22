package domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class goods {
	private Integer id;
	private String name;
	private Double price;
	private String image;
	private String gdesc;
	private Integer is_hot;
	private Integer cid;
	@Override
	public String toString() {
		return "goods [id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", gdesc=" + gdesc
				+ ", is_hot=" + is_hot + ", cid=" + cid + "]";
	}
	public String getSavepath() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	

}
