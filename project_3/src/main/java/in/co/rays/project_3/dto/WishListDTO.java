package in.co.rays.project_3.dto;

import java.util.Date;

public class WishListDTO extends BaseDTO {
	private String username;
	private String product;
	private Date date;
	private String remark;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return product + "";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return product + "";
	}

}
