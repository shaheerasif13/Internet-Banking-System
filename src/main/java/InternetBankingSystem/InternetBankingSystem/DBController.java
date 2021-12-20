package InternetBankingSystem.InternetBankingSystem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DBController {

	private Configuration connection;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	// Default constructor
	public DBController() {
		this.connection = new Configuration();
		this.connection.configure("hibernate.cfg.xml").addAnnotatedClass(Person.class);
		this.connection.configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
		this.connection.configure("hibernate.cfg.xml").addAnnotatedClass(Account.class);
		this.connection.configure("hibernate.cfg.xml").addAnnotatedClass(ElectricityBill.class);
		this.connection.configure("hibernate.cfg.xml").addAnnotatedClass(GasBill.class);
		this.connection.configure("hibernate.cfg.xml").addAnnotatedClass(TransactionDetail.class);
		this.connection.configure("hibernate.cfg.xml").addAnnotatedClass(BillPaymentDetail.class);
		this.connection.configure("hibernate.cfg.xml").addAnnotatedClass(BankTransferDetail.class);
		this.connection.configure("hibernate.cfg.xml").addAnnotatedClass(OtherTransferDetail.class);
		this.connection.configure("hibernate.cfg.xml").addAnnotatedClass(EasypaisaAccount.class);
		this.connection.configure("hibernate.cfg.xml").addAnnotatedClass(CheckbookRequest.class);
		this.sessionFactory = connection.buildSessionFactory();
	}

	// **********************
	// Methods to signup user
	// **********************

	// Method to check if new user is unique or not
	public boolean verifyNewUser(String username, String CNIC) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		Customer temp = (Customer) this.session.get(Customer.class, username);

		if (temp == null) {
			temp = (Customer) this.session.get(Customer.class, CNIC);

			if (temp == null) {
				this.session.close();
				return true;
			}
		}

		this.session.close();
		return false;
	}

	// Method to save new customer in database
	public void saveNewCustomer(Customer targetCustomer) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		this.session.save(targetCustomer);
		this.transaction.commit();
		this.session.close();
	}

	// ********************
	// Method to login user
	// ********************

	public Customer loginUser(String username, String password) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		Customer activeCustomer = (Customer) this.session.get(Customer.class, username);

		if (activeCustomer != null) {

			if (!password.equals(activeCustomer.getPassword())) {
				activeCustomer = null;
			}
		}

		this.session.close();
		return activeCustomer;
	}

	// *******************************
	// Method to process bank transfer
	// *******************************

	// Method to get account from database
	public Account getAccount(int accountNumber) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		Account targetAccount = (Account) this.session.get(Account.class, accountNumber);
		this.session.close();
		return targetAccount;
	}

	// Method to save account
	public void saveAccount(Account targetAccount) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		this.session.update(targetAccount);
		this.transaction.commit();
		this.session.close();
	}

	// *******************************
	// Methods to pay electricity bill
	// *******************************

	// Method to get electricity bill from database
	public ElectricityBill getElecticityBill(int billID) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		ElectricityBill targetBill = (ElectricityBill) this.session.get(ElectricityBill.class, billID);
		this.session.close();
		return targetBill;
	}

	// Method to save electricity bill in database
	public void saveElectricityBill(ElectricityBill targetBill) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		this.session.update(targetBill);
		this.transaction.commit();
		this.session.close();
	}

	// ***********************
	// Methods to pay gas bill
	// ***********************

	// Method to get electricity bill from database
	public GasBill getGasBill(int billID) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		GasBill targetBill = (GasBill) this.session.get(GasBill.class, billID);
		this.session.close();
		return targetBill;
	}

	// Method to save electricity bill in database
	public void saveGasBill(GasBill targetBill) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		this.session.update(targetBill);
		this.transaction.commit();
		this.session.close();
	}

	// ************************************
	// Method to process easypaisa transfer
	// ************************************

	public EasypaisaAccount getEasypaisaAccount(String phoneNumber) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		EasypaisaAccount targetAccount = (EasypaisaAccount) this.session.get(EasypaisaAccount.class, phoneNumber);
		this.session.close();
		return targetAccount;
	}

	// Method to save easypaisa account
	public void saveEasypaisaAccount(EasypaisaAccount targetAccount) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		this.session.update(targetAccount);
		this.transaction.commit();
		this.session.close();
	}

	// ********************************
	// Method to save checkbook request
	// ********************************

	public void saveCheckbookRequest(CheckbookRequest request) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		this.session.save(request);
		this.transaction.commit();
		this.session.close();
	}

	// *************************************
	// Method to update customer in database
	// *************************************

	public void updateCustomer(Customer targetCustomer) {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();
		this.session.update(targetCustomer);
		this.transaction.commit();
		this.session.close();
	}

	//////////////////////////////////////////////////////////////////////////////////
	public void populateDatabase() {
		this.session = sessionFactory.openSession();
		this.transaction = session.beginTransaction();

		// Inserting some bank accounts
		Account a1 = new Account(11111111, 10000, "21/10/2011");
		this.session.save(a1);
		Account a2 = new Account(22222222, 20000, "22/12/2012");
		this.session.save(a2);
		Account a3 = new Account(33333333, 30000, "23/12/2013");
		this.session.save(a3);

		// Inserting some easypaisa accounts
		EasypaisaAccount easy1 = new EasypaisaAccount("03001111111", 1000, "21/10/2011");
		this.session.save(easy1);
		EasypaisaAccount easy2 = new EasypaisaAccount("03002222222", 2000, "22/12/2012");
		this.session.save(easy2);
		EasypaisaAccount easy3 = new EasypaisaAccount("03003333333", 3000, "23/12/2013");
		this.session.save(easy3);

		// Inserting some customers
		Customer c1 = new Customer("3420111111111", "Muhammad Shaheer", 'M', 20, "Gujrat", "03000000000",
				"shaheerasif13", "11111111", a1);
		this.session.save(c1);
		Customer c2 = new Customer("3420122222222", "Faizan Shabbir", 'M', 20, "Islamabad", "03009999999",
				"lokalpotato", "22222222", a2);
		this.session.save(c2);

		// Inserting some electricity bills
		ElectricityBill e1 = new ElectricityBill(10101010, "Peter Parker", 2500.0);
		this.session.save(e1);
		ElectricityBill e2 = new ElectricityBill(20202020, "Gwen Stacy", 4000.0);
		this.session.save(e2);

		// Inserting some gas bills
		GasBill g1 = new GasBill(30303030, "Doctor Ock", 3000.0);
		this.session.save(g1);
		GasBill g2 = new GasBill(40404040, "Green Goblin", 850.0);
		this.session.save(g2);

		this.transaction.commit();
		this.session.close();
	}
	//////////////////////////////////////////////////////////////////////////////////
}