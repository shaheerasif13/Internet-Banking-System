package InternetBankingSystem.InternetBankingSystem;

public class PersonException extends Exception {

	// Name exceptions
	public static class NameException extends BaseException {

		public NameException(String message) {
			super(message);
		}
	}

	// Gender exceptions
	public static class GenderException extends BaseException {

		public GenderException(String message) {
			super(message);
		}
	}

	// Age exceptions
	public static class AgeException extends BaseException {

		public AgeException(String message) {
			super(message);
		}
	}

	// Address exceptions
	public static class AddressException extends BaseException {

		public AddressException(String message) {
			super(message);
		}
	}

	// Phone number exceptions
	public static class PhoneNumberException extends BaseException {

		public PhoneNumberException(String message) {
			super(message);
		}
	}

	// CNIC exceptions
	public static class CNICException extends BaseException {

		public CNICException(String message) {
			super(message);
		}
	}
}