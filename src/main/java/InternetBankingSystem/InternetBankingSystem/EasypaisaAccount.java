package InternetBankingSystem.InternetBankingSystem;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EasypaisaAccount {

	private String phoneNumber;
	private double balance;
	private String dateCreated;

	// Default constructor
	public EasypaisaAccount() {
		this.phoneNumber = null;
		this.balance = 0;
		this.dateCreated = null;
	}

	// Parameterized constructor
	public EasypaisaAccount(String phoneNumber, double balance, String dateCreated) {
		this.phoneNumber = phoneNumber;
		this.balance = balance;
		this.dateCreated = dateCreated;
	}

	// *************************
	// Getter and setter methods
	// *************************

	@Id
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public double getBalance() {
		return balance;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
}