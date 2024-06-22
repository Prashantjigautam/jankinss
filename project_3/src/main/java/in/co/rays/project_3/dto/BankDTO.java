package in.co.rays.project_3.dto;

public class BankDTO extends BaseDTO {
	private String accountNo;
	private String name;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getKey() {

		return id + "";
	}

	@Override
	public String getValue() {

		return name + "";
	}

}
