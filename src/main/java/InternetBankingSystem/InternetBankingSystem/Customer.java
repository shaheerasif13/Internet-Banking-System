package InternetBankingSystem.InternetBankingSystem;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer extends Person {

	private String username;
	private String password;
	private Account account;
	private String SMSAlerts;
	private Set<BillPaymentDetail> billPaymentDetails;
	private Set<BankTransferDetail> bankTransferDetails;
	private Set<OtherTransferDetail> otherTransferDetails;

	// Default constructor
	public Customer() {
		super();
		this.username = null;
		this.password = null;
		this.account = null;
		this.SMSAlerts = "OFF";
		this.billPaymentDetails = new HashSet<BillPaymentDetail>();
		this.bankTransferDetails = new HashSet<BankTransferDetail>();
		this.otherTransferDetails = new HashSet<OtherTransferDetail>();
	}

	// Parameterized constructor
	public Customer(String CNIC, String name, char gender, int age, String address, String phoneNumber, String username,
			String password, Account account) {
		super(CNIC, name, gender, age, address, phoneNumber);
		this.username = username;
		this.password = password;
		this.account = account;
		this.SMSAlerts = "OFF";
		this.billPaymentDetails = new HashSet<BillPaymentDetail>();
		this.bankTransferDetails = new HashSet<BankTransferDetail>();
		this.otherTransferDetails = new HashSet<OtherTransferDetail>();
	}

	// *************************
	// Getter and setter methods
	// *************************

	@Id
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_number")
	public Account getAccount() {
		return account;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Set<BillPaymentDetail> getBillPaymentDetails() {
		return billPaymentDetails;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Set<BankTransferDetail> getBankTransferDetails() {
		return bankTransferDetails;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Set<OtherTransferDetail> getOtherTransferDetails() {
		return otherTransferDetails;
	}

	public String getSMSAlerts() {
		return SMSAlerts;
	}

	public void setUsername(String username) throws CustomerException.UsernameException {

		if (username == null) {
			throw new CustomerException.UsernameException("Username can not be null!");
		}

		else if (username.length() < 5) {
			throw new CustomerException.UsernameException("Username too short!");
		}

		else if (username.length() > 15) {
			throw new CustomerException.UsernameException("Username too long!");
		}

		this.username = username;
	}

	public void setPassword(String password) throws CustomerException.PasswordException {

		if (password == null) {
			throw new CustomerException.PasswordException("Password can not be null!");
		}

		else if (password.length() < 8) {
			throw new CustomerException.PasswordException("Password too short!");
		}

		this.password = password;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setBillPaymentDetails(Set<BillPaymentDetail> billPaymentDetails) {
		this.billPaymentDetails = billPaymentDetails;
	}

	public void setBankTransferDetails(Set<BankTransferDetail> bankTransferDetails) {
		this.bankTransferDetails = bankTransferDetails;
	}

	public void setOtherTransferDetails(Set<OtherTransferDetail> otherTransferDetails) {
		this.otherTransferDetails = otherTransferDetails;
	}

	public void setSMSAlerts(String sMSAlerts) {
		SMSAlerts = sMSAlerts;
	}

	// ***************
	// Utility methods
	// ***************

	// Method to generate new transaction id
	public int generateTransactionID() {
		Random random = new Random();
		int transactionID = random.ints(10000000, 100000000).findFirst().getAsInt();

		while (!isValidNewTransactionID(transactionID)) {
			transactionID = random.ints(10000000, 100000000).findFirst().getAsInt();
		}

		return transactionID;
	}

	// Method to validate new account number for duplication
	private boolean isValidNewTransactionID(int targetTransactionID) {

		for (BillPaymentDetail i : this.billPaymentDetails) {

			if (i.getTransactionID() == targetTransactionID) {
				return false;
			}
		}

		for (BankTransferDetail i : this.bankTransferDetails) {

			if (i.getTransactionID() == targetTransactionID) {
				return false;
			}
		}

		for (OtherTransferDetail i : this.otherTransferDetails) {

			if (i.getTransactionID() == targetTransactionID) {
				return false;
			}
		}

		return true;
	}

	// Method to add new bill payment detail
	public void addBillPaymentDetail(BillPaymentDetail detail) {
		this.billPaymentDetails.add(detail);
	}

	// Method to add new bank transfer detail
	public void addBankTransferDetail(BankTransferDetail detail) {
		this.bankTransferDetails.add(detail);
	}

	// Method to add new other transfer detail
	public void addOtherTransferDetail(OtherTransferDetail detail) {
		this.otherTransferDetails.add(detail);
	}

	public void setSMSAlertsUtil(boolean sMSAlerts) {

		if (sMSAlerts) {
			this.SMSAlerts = "ON";
		}

		else {
			this.SMSAlerts = "OFF";
		}
	}

	@Override
	public String toString() {
		return String.format(
				super.toString() + "\n" + this.username + "\n" + this.password + "\n" + this.account.toString());
	}
}