package InternetBankingSystem.InternetBankingSystem;

import javax.persistence.Entity;

@Entity
public class ElectricityBill extends Bill {

	private String type;

	// Default constructor
	public ElectricityBill() {
		super();
		this.type = "Electicity";
	}

	// Parameterized constructor
	public ElectricityBill(int billID, String consumerName, double amount) {
		super(billID, consumerName, amount);
		this.type = "Electricity";
	}

	// *************************
	// Getter and setter methods
	// *************************

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// ***************
	// Utility methods
	// ***************

	@Override
	public String toString() {
		return String.format("ID: " + this.billID + "\nType: " + this.type + "\nConsumer: " + this.consumerName
				+ "\nAmount: " + this.amount + "\nStatus: " + this.status);
	}
}