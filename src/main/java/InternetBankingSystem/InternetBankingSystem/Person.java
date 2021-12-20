package InternetBankingSystem.InternetBankingSystem;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {

	protected String CNIC;
	protected String name;
	protected char gender;
	protected int age;
	protected String address;
	protected String phoneNumber;

	// Default constructor
	public Person() {
		this.CNIC = null;
		this.name = null;
		this.gender = '\0';
		this.age = 0;
		this.address = null;
		this.phoneNumber = null;
	}

	// Parameterized constructor
	public Person(String CNIC, String name, char gender, int age, String address, String phoneNumber) {
		this.CNIC = CNIC;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	// *************************
	// Getter and setter methods
	// *************************

	public String getName() {
		return name;
	}

	public void setName(String name) throws PersonException.NameException {

		if (name == null) {
			throw new PersonException.NameException("Name can not be null!");
		}

		else if (name.length() < 3) {
			throw new PersonException.NameException("Name too short!");
		}

		else if (name.length() > 20) {
			throw new PersonException.NameException("Name too long!");
		}

		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) throws PersonException.GenderException {

		if (gender == '\0') {
			throw new PersonException.GenderException("Gender can not be null!");
		}

		else if (gender != 'M' && gender != 'F' && gender != 'm' && gender != 'f') {
			throw new PersonException.GenderException("Invalid gender!");
		}

		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) throws PersonException.AgeException {

		if (age <= 0) {
			throw new PersonException.AgeException("Invalid age!");
		}

		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws PersonException.AddressException {

		if (address == null) {
			throw new PersonException.AddressException("Address can not be null!");
		}

		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws PersonException.PhoneNumberException {

		if (phoneNumber == null) {
			throw new PersonException.PhoneNumberException("Phone number can not be null!");
		}

		else if (phoneNumber.length() != 11) {
			throw new PersonException.PhoneNumberException("Invalid phone number!");
		}

		this.phoneNumber = phoneNumber;
	}

	public String getCNIC() {
		return CNIC;
	}

	public void setCNIC(String CNIC) throws PersonException.CNICException {

		if (CNIC == null) {
			throw new PersonException.CNICException("CNIC can not be null!");
		}

		else if (CNIC.length() != 13) {
			throw new PersonException.CNICException("Invalid CNIC!");
		}

		this.CNIC = CNIC;
	}

	// ***************
	// Utility methods
	// ***************

	@Override
	public String toString() {
		return String.format(this.CNIC + "\n" + this.name + "\n" + this.gender + "\n" + this.age + "\n" + this.address
				+ "\n" + this.phoneNumber);
	}
}