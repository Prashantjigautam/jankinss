package in.co.rays.project_3.dto;

import java.util.Date;

public class PatientDTO extends BaseDTO{
	private String name;
	private Date dov;
	private String mobile;
	private String decease;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDov() {
		return dov;
	}
	public void setDov(Date dov) {
		this.dov = dov;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getDecease() {
		return decease;
	}
	public void setDecease(String decease) {
		this.decease = decease;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return decease+"";
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return decease+"";
	}
	

}
