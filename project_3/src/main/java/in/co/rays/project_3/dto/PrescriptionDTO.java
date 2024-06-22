package in.co.rays.project_3.dto;

import java.util.Date;

public class PrescriptionDTO extends BaseDTO {
	private String name;
	private String decease;
	private Date date;
	private String capacity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDecease() {
		return decease;
	}

	public void setDecease(String decease) {
		this.decease = decease;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return decease + "";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return decease + "";
	}

}
