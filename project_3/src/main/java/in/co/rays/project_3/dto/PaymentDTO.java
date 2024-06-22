package in.co.rays.project_3.dto;

public class PaymentDTO extends BaseDTO {
	private String bank;
	private String balance;
	private String PaymentMode;

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getPaymentMode() {
		return PaymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		PaymentMode = paymentMode;
	}

	@Override
	public String getKey() {

		return id + "";
	}

	@Override
	public String getValue() {

		return bank + "";

	}

}
