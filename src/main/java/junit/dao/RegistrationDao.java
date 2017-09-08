package junit.dao;

import junit.entity.User;

/**
 * @author GovindSingh
 */
public interface RegistrationDao {

	/**
	 * Get the User for given user ID
	 * 
	 * @param userId
	 * @return User object if user exist else return null
	 */
	public User getUser(String userId);

	/**
	 * Save User object to database
	 * 
	 * @param user User object with all required fields
	 */
	public void saveUser(User user);

	/**
	 * Delete the User for given user ID
	 * 
	 * @param userId
	 */
	public void deleteUser(User user);

}
