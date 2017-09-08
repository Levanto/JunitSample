/**
 * 
 */
package junit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import junit.dao.RegistrationDao;
import junit.entity.User;
import junit.util.Constants;

/**
 * @author GovindSingh
 */
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	@Qualifier("registrationDao")
	private RegistrationDao registrationDao;

	public void setRegistrationDao(RegistrationDao registrationDao) {
		this.registrationDao = registrationDao;
	}

	public String registerUser(User user) {

		User existingUser = registrationDao.getUser(user.getUserId());
		if (existingUser != null) {
			return Constants.USER_ID_ALREADY_EXIST;
		}

		registrationDao.saveUser(user);

		return Constants.SUCCESS;
	}
}
