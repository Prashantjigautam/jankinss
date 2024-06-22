package in.co.rays.project_3.dto;

import java.util.Date;

public class LeadDTO extends BaseDTO {
	private String name;
	private String mobile;
	private Date date;
	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return status + "";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return status + "";
	}

}
