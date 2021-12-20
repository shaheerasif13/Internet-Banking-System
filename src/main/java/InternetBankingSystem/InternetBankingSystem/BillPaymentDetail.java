package InternetBankingSystem.InternetBankingSystem;

import javax.persistence.Entity;

@Entity
public class BillPaymentDetail extends TransactionDetail {

	private String consumerName;
	private String paidByName;

	// Default constructor
	public BillPaymentDetail() {
		super();
		this.consumerName = null;
		this.paidByName = null;
	}

	// Parameterized constructor
	public BillPaymentDetail(int transactionID, String type, double amount, String date, String time,
			String consumerName, String paidByName) {
		super(transactionID, type, amount, date, time);
		this.consumerName = consumerName;
		this.paidByName = paidByName;
	}

	// *************************
	// Getter and setter methods
	// *************************

	public String getConsumerName() {
		return consumerName;
	}

	public String getPaidByName() {
		return paidByName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public void setPaidByName(String paidByName) {
		this.paidByName = paidByName;
	}

	// ***************
	// Utility methods
	// ***************

	@Override
	public String toString() {
		return String.format(this.transactionID + "\t" + this.type + "\t" + this.amount + "\t" + this.consumerName
				+ "\t" + this.date + "\t" + this.time);
	}
}