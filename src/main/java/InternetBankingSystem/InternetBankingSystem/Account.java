package InternetBankingSystem.InternetBankingSystem;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Entity
public class Account {

	private int accountNumber;
	private double balance;
	private String dateCreated;

	// Default constructor
	public Account() {
		this.accountNumber = -1;
		this.dateCreated = null;
		this.balance = 0.0;
	}

	// Parameterized constructor
	public Account(int accountNumber, double balance, String dateCreated) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.dateCreated = dateCreated;

		// Getting current date and time (Setting date of creation of account)
		// DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy
		// hh:mm:ss");
		// dateCreated = formatDateTime.format(LocalDateTime.now());
	}

	// *************************
	// Getter and setter methods
	// *************************

	@Id
	public int getAccountNumber() {
		return accountNumber;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public double getBalance() {
		return balance;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	// ***************
	// Utility methods
	// ***************

	@Override
	public String toString() {
		return String.format(this.accountNumber + "\n" + this.balance + "\n" + this.dateCreated);
	}
}