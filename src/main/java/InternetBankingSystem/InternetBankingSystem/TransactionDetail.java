package InternetBankingSystem.InternetBankingSystem;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TransactionDetail {

	protected int transactionID;
	protected String type;
	protected double amount;
	protected String date;
	protected String time;

	// Default constructor
	public TransactionDetail() {
		this.transactionID = -1;
		this.type = null;
		this.amount = 0;
		this.date = null;
		this.time = null;
	}

	// Parameterized constructor
	public TransactionDetail(int transactionID, String type, double amount, String date, String time) {
		this.transactionID = transactionID;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.time = time;
	}

	// *************************
	// Getter and setter methods
	// *************************

	@Id
	public int getTransactionID() {
		return transactionID;
	}

	public String getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}
}