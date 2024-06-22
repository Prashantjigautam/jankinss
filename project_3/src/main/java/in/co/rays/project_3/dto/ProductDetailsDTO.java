package in.co.rays.project_3.dto;

import java.util.Date;

public class ProductDetailsDTO extends BaseDTO {
	private String name;
	private String decription;
	private String price;
	private String category;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getDop() {
		return dop;
	}
	public void setDop(Date dop) {
		this.dop = dop;
	}
	private Date dop;
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return category+"";
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return category+"";
	}
	

}
