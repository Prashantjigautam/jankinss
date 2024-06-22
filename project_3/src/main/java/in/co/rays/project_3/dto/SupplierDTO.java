package in.co.rays.project_3.dto;

import java.util.Date;

public class SupplierDTO extends BaseDTO {
	private String name;
	private String category;
	private Date date;
	private String payTerm;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPayTerm() {
		return payTerm;
	}

	public void setPayTerm(String payTerm) {
		this.payTerm = payTerm;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return category;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return category;
	}

}
