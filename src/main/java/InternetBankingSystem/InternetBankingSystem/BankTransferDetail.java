package InternetBankingSystem.InternetBankingSystem;

import javax.persistence.Entity;

@Entity
public class BankTransferDetail extends TransactionDetail {

	private int toAccountNumber;
	private int fromAccountNumber;

	// Default constructor
	public BankTransferDetail() {
		super();
		this.toAccountNumber = -1;
		this.fromAccountNumber = -1;
	}

	// Parameterized constructor
	public BankTransferDetail(int transactionID, String type, double amount, String date, String time,
			int toAccountNumber, int fromAccountNumber) {
		super(transactionID, type, amount, date, time);
		this.toAccountNumber = toAccountNumber;
		this.fromAccountNumber = fromAccountNumber;
	}

	// *************************
	// Getter and setter methods
	// *************************

	public int getToAccountNumber() {
		return toAccountNumber;
	}

	public int getFromAccountNumber() {
		return fromAccountNumber;
	}

	public void setToAccountNumber(int toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public void setFromAccountNumber(int fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	// ***************
	// Utility methods
	// ***************

	@Override
	public String toString() {
		return String.format(this.transactionID + "\t" + this.type + "\t" + this.amount + "\t" + this.toAccountNumber
				+ "\t" + this.date + "\t" + this.time);
	}
}