/**
 * 
 */
package junit.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import junit.dao.RegistrationDao;
import junit.entity.User;
import junit.util.Constants;

/**
 * @author GovindSingh
 * @category Test
 * @since 2017 This is test class for RegistrationServiceImpl which is used to test all public method with Junit.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-test-context.xml" })
@Transactional
@Rollback(true)
public class RegistrationServiceImplTest {
	@Autowired
	@Qualifier("registrationDao")
	private RegistrationDao registrationDao;

	private User user;
	private RegistrationServiceImpl registrationServiceImpl = new RegistrationServiceImpl();

	@Before
	public void setUp() {
		registrationServiceImpl.setRegistrationDao(registrationDao);
	}

	@Test
	public void testRegisterSuccess() throws Exception {
		// GIVEN
		User user = createUser("12345678", "ABCDEFGH");

		// WHEN
		String result = registrationServiceImpl.registerUser(user);

		// THEN
		assertThat("The user registration should not fail", result, is(Constants.SUCCESS));
	}

	@Test
	public void testRegisterUserFailureWhenUserIdAlreadyExist() throws Exception {
		// GIVEN
		User user = createUser("12345678", "ABCDEFGH");
		registrationServiceImpl.registerUser(user);

		// WHEN
		String result = registrationServiceImpl.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.USER_ID_ALREADY_EXIST));
	}

	private User createUser(String userId, String password) {
		user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		return user;
	}

	public void tearDown() throws Exception {
		// TODO
	}

}
