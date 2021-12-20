package InternetBankingSystem.InternetBankingSystem;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Bill {

	@Id
	protected int billID;
	protected String consumerName;
	protected double amount;
	protected String status;

	// Default constructor
	public Bill() {
		this.billID = -1;
		this.consumerName = null;
		this.amount = 0;
		this.status = "Unpaid";
	}

	// Parameterized constructor
	public Bill(int billID, String consumerName, double amount) {
		this.billID = billID;
		this.consumerName = consumerName;
		this.amount = amount;
		this.status = "Unpaid";
	}

	// *************************
	// Getter and setter methods
	// *************************

	public int getBillID() {
		return billID;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public double getAmount() {
		return amount;
	}

	public String isStatus() {
		return status;
	}

	public void setBillID(int billID) {
		this.billID = billID;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setStatus(boolean status) {

		if (status) {
			this.status = "Paid";
		}
	}
}