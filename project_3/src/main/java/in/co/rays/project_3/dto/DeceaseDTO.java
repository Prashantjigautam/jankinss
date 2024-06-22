package in.co.rays.project_3.dto;

public class DeceaseDTO extends BaseDTO {
	private String decease;

	public String getDecease() {
		return decease;
	}

	public void setDecease(String decease) {
		this.decease = decease;
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
