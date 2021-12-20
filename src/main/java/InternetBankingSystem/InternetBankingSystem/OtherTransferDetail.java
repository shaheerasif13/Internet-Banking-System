package InternetBankingSystem.InternetBankingSystem;

import javax.persistence.Entity;

@Entity
public class OtherTransferDetail extends TransactionDetail {

	private String phoneNumber; // To phone number
	private int fromAccountNumber;

	// Default constructor
	public OtherTransferDetail() {
		super();
		this.phoneNumber = null;
		this.fromAccountNumber = -1;
	}

	// Parameterized constructor
	public OtherTransferDetail(int transactionID, String type, double amount, String date, String time,
			String phoneNumber, int fromAccountNumber) {
		super(transactionID, type, amount, date, time);
		this.phoneNumber = phoneNumber;
		this.fromAccountNumber = fromAccountNumber;
	}
	// *************************
	// Getter and setter methods
	// *************************

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public int getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setFromAccountNumber(int fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	// ***************
	// Utility methods
	// ***************

	@Override
	public String toString() {
		return String.format(this.transactionID + "\t" + this.type + "\t" + this.amount + "\t" + this.phoneNumber + "\t"
				+ this.date + "\t" + this.time);
	}
}