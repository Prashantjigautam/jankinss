package in.co.rays.project_3.dto;

import java.util.Date;

public class ContactDTO extends BaseDTO {
	private String name;
	private Date dob;
	private String mobile;
	private String city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return city + "";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return city + "";
	}

}
