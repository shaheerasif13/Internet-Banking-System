package InternetBankingSystem.InternetBankingSystem;

public class CustomerException extends PersonException {

	// Username exceptions
	public static class UsernameException extends BaseException {

		public UsernameException(String message) {
			super(message);
		}
	}

	// Password exceptions
	public static class PasswordException extends BaseException {

		public PasswordException(String message) {
			super(message);
		}
	}
}