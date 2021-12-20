package InternetBankingSystem.InternetBankingSystem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CheckbookRequest {

	@Id
	@GeneratedValue
	private int requestID;
	private int accountNumber;
	private String address;
	private String Status;

	// Default constructor
	public CheckbookRequest() {
		this.requestID = -1;
		this.accountNumber = -1;
		this.address = null;
		this.Status = "FALSE";
	}

	// Parameterized constructor
	public CheckbookRequest(int accountNumber, String address) {
		this.requestID = -1;
		this.accountNumber = accountNumber;
		this.address = address;
		this.Status = "FALSE";
	}

	// *************************
	// Getter and setter methods
	// *************************

	public int getRequestID() {
		return requestID;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public String getAddress() {
		return address;
	}

	public String getStatus() {
		return Status;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setStatus(String status) {
		Status = status;
	}
}