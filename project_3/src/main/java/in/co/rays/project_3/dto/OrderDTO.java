package in.co.rays.project_3.dto;

import java.util.Date;

public class OrderDTO extends BaseDTO {
	private String quantity;
	private String product;
	private Date date;
	private String ammount;

	@Override
	public String getKey() {

		return product + "";
	}

	@Override
	public String getValue() {

		return product + "";
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
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

	public String getAmmount() {
		return ammount;
	}

	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}

}
