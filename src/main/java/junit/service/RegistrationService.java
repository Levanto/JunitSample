package junit.service;

import junit.entity.User;

/**
 * @author GovindSingh
 */
public interface RegistrationService {

	/**
	 * Register user profile with given details
	 * 
	 * @param user User object with all required fields
	 * @return success if registration is successful else return error message
	 */
	public String registerUser(User user);
}
