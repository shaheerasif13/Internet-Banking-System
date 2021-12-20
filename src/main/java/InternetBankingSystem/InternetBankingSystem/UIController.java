package InternetBankingSystem.InternetBankingSystem;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class UIController implements Initializable {

	private static InternetBankingSystem system;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		if (UIController.system == null) {
			UIController.system = new InternetBankingSystem();
		}

		// Setting state of combo box before showing signup page
		if (genderComboBox != null) {
			genderComboBox.setItems(FXCollections.observableArrayList("M", "F"));
		}

		// Maintaining state of SMS Alerts toggle before displaying on settings screen
		if (UIController.system != null && SMSAlertToggle != null) {
			SMSAlertToggle.setSelected(UIController.system.getSMSAlerts());
		}

		// Maintaining state of data fields of user for edit profile page
		if (UIController.system != null && nameField != null && phoneNumberField != null && addressField != null
				&& passwordField != null && genderComboBox != null && ageField != null) {
			Customer tempCustomer = UIController.system.getActiveCustomer();
			nameField.setText(tempCustomer.getName());
			phoneNumberField.setText(tempCustomer.getPhoneNumber());
			addressField.setText(tempCustomer.getAddress());
			passwordField.setText(tempCustomer.getPassword());
			genderComboBox.setValue("" + tempCustomer.getGender());
			ageField.setText((String.format("" + tempCustomer.getAge())));
		}

		// Displaying dashboard by default after login or signup
		if (contentArea != null && displayNameText == null) {
			this.displayDashboard(null);
		}

		// Displaying customers name and balance on dashboard
		if (UIController.system != null && displayNameText != null && displayBalanceText != null) {
			Customer tempCustomer = UIController.system.getActiveCustomer();
			displayNameText.setText(tempCustomer.getName());
			displayBalanceText.setText(String.format("" + tempCustomer.getAccount().getBalance()));
		}

		// Getting details into tables before displaying E-Statement
		if (UIController.system != null && amountTransferDetailTable != null) {
			Customer tempCustomer = UIController.system.getActiveCustomer();

			// Initializing bank transfer details
			Set<BankTransferDetail> bankTransferDetails = tempCustomer.getBankTransferDetails();
			ObservableList<BankTransferDetail> bankTransferDetailsList = FXCollections
					.observableArrayList(bankTransferDetails);
			ATID.setCellValueFactory(new PropertyValueFactory<BankTransferDetail, Integer>("transactionID"));
			ATType.setCellValueFactory(new PropertyValueFactory<BankTransferDetail, String>("type"));
			ATAmount.setCellValueFactory(new PropertyValueFactory<BankTransferDetail, Double>("amount"));
			ATRecipient.setCellValueFactory(new PropertyValueFactory<BankTransferDetail, Integer>("toAccountNumber"));
			ATDate.setCellValueFactory(new PropertyValueFactory<BankTransferDetail, String>("date"));
			ATTime.setCellValueFactory(new PropertyValueFactory<BankTransferDetail, String>("time"));
			amountTransferDetailTable.setItems(bankTransferDetailsList);

			// Initializing Other transfer details
			Set<OtherTransferDetail> otherTransferDetails = tempCustomer.getOtherTransferDetails();
			ObservableList<OtherTransferDetail> otherTransferDetailsList = FXCollections
					.observableArrayList(otherTransferDetails);
			OTID.setCellValueFactory(new PropertyValueFactory<OtherTransferDetail, Integer>("transactionID"));
			OTType.setCellValueFactory(new PropertyValueFactory<OtherTransferDetail, String>("type"));
			OTAmount.setCellValueFactory(new PropertyValueFactory<OtherTransferDetail, Double>("amount"));
			OTRecipient.setCellValueFactory(new PropertyValueFactory<OtherTransferDetail, String>("phoneNumber"));
			OTDate.setCellValueFactory(new PropertyValueFactory<OtherTransferDetail, String>("date"));
			OTTime.setCellValueFactory(new PropertyValueFactory<OtherTransferDetail, String>("time"));
			otherTransferDetailTable.setItems(otherTransferDetailsList);

			// Initializing bill payment details
			Set<BillPaymentDetail> billPaymentDetails = tempCustomer.getBillPaymentDetails();
			ObservableList<BillPaymentDetail> billPaymentDetailsList = FXCollections
					.observableArrayList(billPaymentDetails);
			BPID.setCellValueFactory(new PropertyValueFactory<BillPaymentDetail, Integer>("transactionID"));
			BPType.setCellValueFactory(new PropertyValueFactory<BillPaymentDetail, String>("type"));
			BPAmount.setCellValueFactory(new PropertyValueFactory<BillPaymentDetail, Double>("amount"));
			BPConsumer.setCellValueFactory(new PropertyValueFactory<BillPaymentDetail, String>("consumerName"));
			BPDate.setCellValueFactory(new PropertyValueFactory<BillPaymentDetail, String>("date"));
			BPTime.setCellValueFactory(new PropertyValueFactory<BillPaymentDetail, String>("time"));
			BillPaymentDetailTable.setItems(billPaymentDetailsList);
		}
	}

	// *********************************
	// Login page attributes and methods
	// *********************************

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Text invalidMessage;

	@FXML
	private Text emptyMessage;

	@FXML
	void loginUser(ActionEvent event) throws IOException {

		// If any field is empty
		if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
			invalidMessage.setVisible(false);
			emptyMessage.setVisible(true);
		}

		else {
			boolean loggedIn = UIController.system.loginUser(usernameField.getText(), passwordField.getText());

			// If invalid user name or password is entered
			if (!loggedIn) {
				emptyMessage.setVisible(false);
				invalidMessage.setVisible(true);
			}

			// Everything is ok
			else {
				emptyMessage.setVisible(false);
				invalidMessage.setVisible(false);
				App.setRoot("Application");
			}
		}
	}

	@FXML
	public void showSignupPage(ActionEvent event) throws IOException {
		App.setRoot("Signup");
	}

	// **********************************
	// Signup page attributes and methods
	// **********************************

	@FXML
	private TextField CNICField;

	@FXML
	private TextField accountNumberField;

	@FXML
	private TextField addressField;

	@FXML
	private TextField ageField;

	@FXML
	private TextField firstNameField;

	@FXML
	private ComboBox<String> genderComboBox;

	@FXML
	private TextField lastNameField;

	@FXML
	private TextField phoneNumberField;

	@FXML
	private Text alreadyExistsMessage;

	@FXML
	void showLoginPage(ActionEvent event) throws IOException {
		App.setRoot("Login");
	}

	@FXML
	void signupUser(ActionEvent event) throws IOException {

		// If any field is empty
		if (firstNameField.getText().equals("") || lastNameField.getText().equals("") || CNICField.getText().equals("")
				|| ageField.getText().equals("") || genderComboBox.getPromptText().equals("")
				|| addressField.getText().equals("") || phoneNumberField.getText().equals("")
				|| usernameField.getText().equals("") || passwordField.getText().equals("")
				|| accountNumberField.getText().equals("")) {
			alreadyExistsMessage.setVisible(false);
			invalidMessage.setVisible(false);
			emptyMessage.setVisible(true);
		}

		else {
			int signedUp = UIController.system.signupUser(firstNameField.getText(), lastNameField.getText(),
					CNICField.getText(), genderComboBox.getValue().charAt(0), Integer.parseInt(ageField.getText()),
					addressField.getText(), phoneNumberField.getText(), usernameField.getText(),
					passwordField.getText(), Integer.parseInt(accountNumberField.getText()));

			// If account already exists
			if (signedUp == -1) {
				invalidMessage.setVisible(false);
				emptyMessage.setVisible(false);
				alreadyExistsMessage.setVisible(true);
			}

			else if (signedUp == 0) {
				emptyMessage.setVisible(false);
				alreadyExistsMessage.setVisible(false);
				invalidMessage.setVisible(true);
			}

			// Everything is ok
			else {
				App.setRoot("Application");
				alreadyExistsMessage.setVisible(false);
				emptyMessage.setVisible(false);
				invalidMessage.setVisible(false);
			}
		}
	}

	// *********************************************************************
	// Main application page attributes and methods (After login or sign up)
	// *********************************************************************

	@FXML
	private StackPane contentArea;

	@FXML
	void displayDashboard(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void displayEditProfile(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("EditProfile.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void displaySettings(ActionEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
			contentArea.getChildren().removeAll();
			contentArea.getChildren().setAll(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ************************************
	// Settings page attributes and methods
	// ************************************

	@FXML
	private JFXToggleButton SMSAlertToggle;

	@FXML
	void setSMSAlerts(ActionEvent event) {
		UIController.system.setSMSAlerts(SMSAlertToggle.isSelected());
	}

	// ****************************************
	// Edit profile page attributes and methods
	// ****************************************

	@FXML
	private TextField nameField;

	@FXML
	private Text profileUpdatedMessage;

	@FXML
	void updateUserInfo(ActionEvent event) {

		// If fields are empty
		if (nameField.getText().equals("") || ageField.getText().equals("") || genderComboBox.getPromptText().equals("")
				|| addressField.getText().equals("") || phoneNumberField.getText().equals("")
				|| passwordField.getText().equals("")) {
			profileUpdatedMessage.setVisible(false);
			invalidMessage.setVisible(false);
			emptyMessage.setVisible(true);
		}

		else {
			boolean updated = UIController.system.updateUserInfo(nameField.getText(), phoneNumberField.getText(),
					passwordField.getText(), addressField.getText(), genderComboBox.getValue().charAt(0),
					Integer.parseInt(ageField.getText()));

			if (!updated) {
				profileUpdatedMessage.setVisible(false);
				emptyMessage.setVisible(false);
				invalidMessage.setVisible(true);
			}

			else {
				emptyMessage.setVisible(false);
				invalidMessage.setVisible(false);
				profileUpdatedMessage.setVisible(true);
			}
		}
	}

	// ********************************
	// Dashboard attributes and methods
	// ********************************

	@FXML
	private Text displayBalanceText;

	@FXML
	private Text displayNameText;

	@FXML
	void processBankTransfer(ActionEvent event) throws IOException {
		App.setRoot("BankTransfer");
	}

	@FXML
	void processCheckbook(ActionEvent event) throws IOException {
		App.setRoot("Checkbook");
	}

	@FXML
	void processEStatement(ActionEvent event) throws IOException {
		App.setRoot("EStatement");
	}

	@FXML
	void processEasypaisa(ActionEvent event) throws IOException {
		App.setRoot("Easypaisa");
	}

	@FXML
	void processElecticityBill(ActionEvent event) throws IOException {
		App.setRoot("ElectricityBill");
	}

	@FXML
	void processGasBill(ActionEvent event) throws IOException {
		App.setRoot("GasBill");
	}

	@FXML
	void backToApplication(ActionEvent event) throws IOException {
		App.setRoot("Application");
	}

	// ************************************
	// Bank transfer attributes and methods
	// ************************************

	@FXML
	private TextField amountField;

	@FXML
	private Text transferSuccessMessage;

	@FXML
	void sendMoney(ActionEvent event) {

		if (accountNumberField.getText().equals("") || amountField.getText().equals("")) {
			transferSuccessMessage.setVisible(false);
			invalidMessage.setVisible(false);
			emptyMessage.setVisible(true);
		}

		else {
			BankTransferDetail detail = UIController.system.bankAccountTransfer(
					Integer.parseInt(accountNumberField.getText()), Double.parseDouble(amountField.getText()));

			if (detail == null) {
				emptyMessage.setVisible(false);
				transferSuccessMessage.setVisible(false);
				invalidMessage.setVisible(true);
			}

			else {
				invalidMessage.setVisible(false);
				emptyMessage.setVisible(false);
				transferSuccessMessage.setVisible(true);
			}
		}
	}

	// ******************************************
	// Easypaisa transfer attributes and methods
	// ******************************************

	@FXML
	void sendMoneyEasypaisa(ActionEvent event) {

		if (accountNumberField.getText().equals("") || amountField.getText().equals("")) {
			transferSuccessMessage.setVisible(false);
			invalidMessage.setVisible(false);
			emptyMessage.setVisible(true);
		}

		else {
			OtherTransferDetail detail = UIController.system.easypaisaTransfer(accountNumberField.getText(),
					Double.parseDouble(amountField.getText()));

			if (detail == null) {
				emptyMessage.setVisible(false);
				transferSuccessMessage.setVisible(false);
				invalidMessage.setVisible(true);
			}

			else {
				invalidMessage.setVisible(false);
				emptyMessage.setVisible(false);
				transferSuccessMessage.setVisible(true);
			}
		}
	}

	// *******************************************************
	// Electricity and gas bill payment attributes and methods
	// *******************************************************

	@FXML
	private TextField billIDField;

	@FXML
	void payElectricityBill(ActionEvent event) {

		if (billIDField.getText().equals("")) {
			transferSuccessMessage.setVisible(false);
			invalidMessage.setVisible(false);
			emptyMessage.setVisible(true);
		}

		else {
			BillPaymentDetail detail = UIController.system.payElectricityBill(Integer.parseInt(billIDField.getText()));

			if (detail == null) {
				emptyMessage.setVisible(false);
				transferSuccessMessage.setVisible(false);
				invalidMessage.setVisible(true);
			}

			else {
				invalidMessage.setVisible(false);
				emptyMessage.setVisible(false);
				transferSuccessMessage.setVisible(true);
			}
		}
	}

	@FXML
	void payGasBill(ActionEvent event) {

		if (billIDField.getText().equals("")) {
			transferSuccessMessage.setVisible(false);
			invalidMessage.setVisible(false);
			emptyMessage.setVisible(true);
		}

		else {
			BillPaymentDetail detail = UIController.system.payGasBill(Integer.parseInt(billIDField.getText()));

			if (detail == null) {
				emptyMessage.setVisible(false);
				transferSuccessMessage.setVisible(false);
				invalidMessage.setVisible(true);
			}

			else {
				invalidMessage.setVisible(false);
				emptyMessage.setVisible(false);
				transferSuccessMessage.setVisible(true);
			}
		}
	}

	// *********************************
	// EStatement attributes and methods
	// *********************************

	@FXML
	private TableColumn<BankTransferDetail, Double> ATAmount;

	@FXML
	private TableColumn<BankTransferDetail, String> ATDate;

	@FXML
	private TableColumn<BankTransferDetail, Integer> ATID;

	@FXML
	private TableColumn<BankTransferDetail, Integer> ATRecipient;

	@FXML
	private TableColumn<BankTransferDetail, String> ATTime;

	@FXML
	private TableColumn<BankTransferDetail, String> ATType;

	@FXML
	private TableColumn<BillPaymentDetail, Double> BPAmount;

	@FXML
	private TableColumn<BillPaymentDetail, String> BPConsumer;

	@FXML
	private TableColumn<BillPaymentDetail, String> BPDate;

	@FXML
	private TableColumn<BillPaymentDetail, Integer> BPID;

	@FXML
	private TableColumn<BillPaymentDetail, String> BPTime;

	@FXML
	private TableColumn<BillPaymentDetail, String> BPType;

	@FXML
	private TableColumn<OtherTransferDetail, Double> OTAmount;

	@FXML
	private TableColumn<OtherTransferDetail, String> OTDate;

	@FXML
	private TableColumn<OtherTransferDetail, Integer> OTID;

	@FXML
	private TableColumn<OtherTransferDetail, String> OTRecipient;

	@FXML
	private TableColumn<OtherTransferDetail, String> OTTime;

	@FXML
	private TableColumn<OtherTransferDetail, String> OTType;

	@FXML
	private TableView<BillPaymentDetail> BillPaymentDetailTable;

	@FXML
	private TableView<BankTransferDetail> amountTransferDetailTable;

	@FXML
	private TableView<OtherTransferDetail> otherTransferDetailTable;

	// ****************************************
	// Request checkbook attributes and methods
	// ****************************************

	@FXML
	void requestCheckbook(ActionEvent event) {

		if (addressField.getText().equals("")) {
			transferSuccessMessage.setVisible(false);
			emptyMessage.setVisible(true);
		}

		else {
			UIController.system.saveCheckbookRequest(addressField.getText());
			emptyMessage.setVisible(false);
			transferSuccessMessage.setVisible(true);
		}
	}
}
