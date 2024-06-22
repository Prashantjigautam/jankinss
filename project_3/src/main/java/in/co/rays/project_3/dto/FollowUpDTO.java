package in.co.rays.project_3.dto;

import java.util.Date;

public class FollowUpDTO extends BaseDTO {
	private String patient;

	private Date dov;
	private String doctor;
	private String fees;

	public String getPatient() {
		return patient;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public Date getDov() {
		return dov;
	}

	public void setDov(Date dov) {
		this.dov = dov;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return doctor + "";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return doctor + "";
	}

}
