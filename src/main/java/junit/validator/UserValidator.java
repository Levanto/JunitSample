/**
 * 
 */
package junit.validator;

import junit.entity.User;
import junit.util.Constants;

/**
 * @author GovindSingh
 */
public class UserValidator {

	public static String validateUser(final User user) {

		final String userId = user.getUserId();
		final String password = user.getPassword();

		if (userId == null || userId.isEmpty()) {
			return Constants.USER_ID_INVALID;
		}

		if (userId.length() < 6 || userId.length() > 10) {
			return Constants.USER_ID_LENGTH_INVALID;
		}

		if (password == null || password.isEmpty()) {
			return Constants.PASSWORD_INVALID;
		}
		if (password.length() < 5 || password.length() > 20) {
			return Constants.PASSWORD_LENGTH_INVALID;
		}

		return Constants.SUCCESS;

	}

}
