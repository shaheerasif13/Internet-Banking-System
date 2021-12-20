package InternetBankingSystem.InternetBankingSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import InternetBankingSystem.InternetBankingSystem.CustomerException.PasswordException;
import InternetBankingSystem.InternetBankingSystem.CustomerException.UsernameException;
import InternetBankingSystem.InternetBankingSystem.PersonException.AddressException;
import InternetBankingSystem.InternetBankingSystem.PersonException.AgeException;
import InternetBankingSystem.InternetBankingSystem.PersonException.CNICException;
import InternetBankingSystem.InternetBankingSystem.PersonException.GenderException;
import InternetBankingSystem.InternetBankingSystem.PersonException.NameException;
import InternetBankingSystem.InternetBankingSystem.PersonException.PhoneNumberException;

public class InternetBankingSystem {

	private Customer activeCustomer;
	private DBController dbController;

	// Default constructor
	public InternetBankingSystem() {
		this.activeCustomer = new Customer();
		this.dbController = new DBController();

		//////////////////////////////////////////////////////////////////////////////////
		this.dbController.populateDatabase();
		//////////////////////////////////////////////////////////////////////////////////
	}

	// *************************
	// Getter and setter methods
	// *************************

	public Customer getActiveCustomer() {
		return activeCustomer;
	}

	public DBController getDbController() {
		return dbController;
	}

	public void setActiveCustomer(Customer activeCustomer) {
		this.activeCustomer = activeCustomer;
	}

	public void setDbController(DBController dbController) {
		this.dbController = dbController;
	}

	// ************************************
	// Methods to get current date and time
	// ************************************

	public String getCurrentDate() {
		DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String currentDate = formatDateTime.format(LocalDateTime.now());
		return currentDate;
	}

	public String getCurrentTime() {
		DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("hh:mm:ss");
		String currentTime = formatDateTime.format(LocalDateTime.now());
		return currentTime;
	}

	// *********************
	// Method to signup user
	// *********************

	public int signupUser(String firstName, String lastName, String CNIC, char gender, int age, String address,
			String phoneNumber, String username, String password, int accountNumber) {

		if (this.dbController.verifyNewUser(username, CNIC)) {
			Account targetAccount = this.dbController.getAccount(accountNumber);

			if (targetAccount != null) {
				Customer newCustomer = new Customer();

				try {
					newCustomer.setCNIC(CNIC);
				} catch (CNICException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}

				try {
					newCustomer.setName(firstName + " " + lastName);
				} catch (NameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}

				try {
					newCustomer.setGender(gender);
				} catch (GenderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}

				try {
					newCustomer.setAge(age);
				} catch (AgeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}

				try {
					newCustomer.setAddress(address);
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}

				try {
					newCustomer.setPhoneNumber(phoneNumber);
				} catch (PhoneNumberException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}

				try {
					newCustomer.setUsername(username);
				} catch (UsernameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}

				try {
					newCustomer.setPassword(password);
				} catch (PasswordException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}

				newCustomer.setAccount(targetAccount);
				this.dbController.saveNewCustomer(newCustomer);
				this.activeCustomer = newCustomer;
				return 1;
			}
		}

		return -1;
	}

	// ********************
	// Method to login user
	// ********************

	public boolean loginUser(String username, String password) {
		Customer c = this.dbController.loginUser(username, password);

		if (c != null) {
			this.activeCustomer = c;
			return true;
		}

		return false;
	}

	// *******************************
	// Method to process bank transfer
	// *******************************

	public BankTransferDetail bankAccountTransfer(int accountNumber, double amount) {

		if (accountNumber != this.activeCustomer.getAccount().getAccountNumber()) {

			if (amount <= this.activeCustomer.getAccount().getBalance()) {
				Account targetAccount = this.dbController.getAccount(accountNumber);

				if (targetAccount != null) {
					targetAccount.setBalance(targetAccount.getBalance() + amount);
					this.activeCustomer.getAccount().setBalance(this.activeCustomer.getAccount().getBalance() - amount);
					BankTransferDetail newDetail = new BankTransferDetail(this.activeCustomer.generateTransactionID(),
							"Bank Transfer", amount, this.getCurrentDate(), this.getCurrentTime(),
							targetAccount.getAccountNumber(), this.activeCustomer.getAccount().getAccountNumber());
					this.activeCustomer.addBankTransferDetail(newDetail);
					this.dbController.saveAccount(targetAccount);
					this.dbController.updateCustomer(activeCustomer);
					return newDetail;
				}
			}
		}

		return null;
	}

	// ******************************
	// Method to pay electricity bill
	// ******************************

	public BillPaymentDetail payElectricityBill(int billID) {
		ElectricityBill targetBill = this.dbController.getElecticityBill(billID);

		if (targetBill != null) {

			if (!targetBill.isStatus().equals("Paid")) {

				if (targetBill.getAmount() <= this.activeCustomer.getAccount().getBalance()) {
					targetBill.setStatus(true);
					this.activeCustomer.getAccount()
							.setBalance(this.activeCustomer.getAccount().getBalance() - targetBill.getAmount());
					BillPaymentDetail newDetail = new BillPaymentDetail(this.activeCustomer.generateTransactionID(),
							"Electricity Bill Payment", targetBill.getAmount(), this.getCurrentDate(),
							this.getCurrentTime(), targetBill.getConsumerName(), this.activeCustomer.getName());
					System.out.println(newDetail);
					this.activeCustomer.addBillPaymentDetail(newDetail);
					this.dbController.saveElectricityBill(targetBill);
					this.dbController.updateCustomer(activeCustomer);
					return newDetail;
				}
			}
		}

		return null;
	}

	// **********************
	// Method to pay gas bill
	// **********************

	public BillPaymentDetail payGasBill(int billID) {
		GasBill targetBill = this.dbController.getGasBill(billID);

		if (targetBill != null) {

			if (targetBill.getAmount() <= this.activeCustomer.getAccount().getBalance()) {
				targetBill.setStatus(true);
				this.activeCustomer.getAccount()
						.setBalance(this.activeCustomer.getAccount().getBalance() - targetBill.getAmount());
				BillPaymentDetail newDetail = new BillPaymentDetail(this.activeCustomer.generateTransactionID(),
						"Gas Bill Payment", targetBill.getAmount(), this.getCurrentDate(), this.getCurrentTime(),
						targetBill.getConsumerName(), this.activeCustomer.getName());
				System.out.println(newDetail);
				this.activeCustomer.addBillPaymentDetail(newDetail);
				this.dbController.saveGasBill(targetBill);
				this.dbController.updateCustomer(activeCustomer);
				return newDetail;
			}
		}

		return null;
	}

	// ************************************
	// Method to process easypaisa transfer
	// ************************************

	public OtherTransferDetail easypaisaTransfer(String phoneNumber, double amount) {

		if (amount <= this.activeCustomer.getAccount().getBalance()) {
			EasypaisaAccount targetAccount = this.dbController.getEasypaisaAccount(phoneNumber);

			if (targetAccount != null) {
				targetAccount.setBalance(targetAccount.getBalance() + amount);
				this.activeCustomer.getAccount().setBalance(this.activeCustomer.getAccount().getBalance() - amount);
				OtherTransferDetail newDetail = new OtherTransferDetail(this.activeCustomer.generateTransactionID(),
						"Easypaisa Transfer", amount, this.getCurrentDate(), this.getCurrentTime(),
						targetAccount.getPhoneNumber(), this.activeCustomer.getAccount().getAccountNumber());
				this.activeCustomer.addOtherTransferDetail(newDetail);
				this.dbController.saveEasypaisaAccount(targetAccount);
				this.dbController.updateCustomer(activeCustomer);
				return newDetail;
			}
		}

		return null;
	}

	// ***********************************
	// Methods to set SMS Alerts on and of
	// ***********************************

	public void setSMSAlerts(boolean flag) {
		this.activeCustomer.setSMSAlertsUtil(flag);
		this.dbController.updateCustomer(activeCustomer);
	}

	public boolean getSMSAlerts() {

		if (this.activeCustomer.getSMSAlerts().equals("ON")) {
			return true;
		}

		else {
			return false;
		}
	}

	// *********************************
	// Method to update user information
	// *********************************

	public boolean updateUserInfo(String name, String phoneNumber, String password, String address, char gender,
			int age) {

		try {
			this.activeCustomer.setName(name);
		} catch (NameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		try {
			this.activeCustomer.setPhoneNumber(phoneNumber);
		} catch (PhoneNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		try {
			this.activeCustomer.setPassword(password);
		} catch (PasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			this.activeCustomer.setAddress(address);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		try {
			this.activeCustomer.setGender(gender);
		} catch (GenderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		try {
			this.activeCustomer.setAge(age);
		} catch (AgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		this.dbController.updateCustomer(activeCustomer);
		return true;
	}

	// **********************************
	// Method to handle checkbook request
	// **********************************

	public void saveCheckbookRequest(String address) {
		CheckbookRequest request = new CheckbookRequest(this.activeCustomer.getAccount().getAccountNumber(), address);
		this.dbController.saveCheckbookRequest(request);
	}
}