/**
 * 
 */
package junit.controller;

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

import junit.entity.User;
import junit.service.RegistrationService;
import junit.util.Constants;

/**
 * @author GovindSingh
 * @category Test
 * @since 2017 This is test class for RegistrationController which is used to test all public method with Junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-test-context.xml" })
@Transactional
@Rollback(true)
public class RegistrationControllerTest {
	@Autowired
	@Qualifier("registrationService")
	private RegistrationService registrationService;

	private User user;
	private RegistrationController registrationController = new RegistrationController();

	@Before
	public void setup() {
		registrationController.setRegistrationService(registrationService);
	}

	@Test
	public void testRegisterUserSuccess() throws Exception {
		// GIVEN
		User user = createUser("12345678", "ABCDEFGH");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not fail", result, is(Constants.SUCCESS));
	}

	@Test
	public void testRegisterUserFailureWhenUserIdIsNull() throws Exception {
		// GIVEN
		User user = createUser(null, "ABCDEFGH");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.USER_ID_INVALID));
	}

	@Test
	public void testRegisterUserFailureWhenUserIdIsEmpty() throws Exception {
		// GIVEN
		User user = createUser("", "ABCDEFGH");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.USER_ID_INVALID));
	}

	@Test
	public void testRegisterUserFailureWhenUserIdlengthLessThan6() throws Exception {
		// GIVEN
		User user = createUser("1", "ABCDEFGH");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.USER_ID_LENGTH_INVALID));
	}

	@Test
	public void testRegisterUserFailureWhenUserIdlengthGreaterThan10() throws Exception {
		// GIVEN
		User user = createUser("12345678901", "ABCDEFGH");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.USER_ID_LENGTH_INVALID));
	}

	@Test
	public void testRegisterUserFailureWhenPasswordIsNull() throws Exception {
		// GIVEN
		User user = createUser("12345678", null);

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.PASSWORD_INVALID));
	}

	@Test
	public void testRegisterUserFailureWhenPasswordIsEmpty() throws Exception {
		// GIVEN
		User user = createUser("12345678", "");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.PASSWORD_INVALID));
	}

	@Test
	public void testRegisterUserFailureWhenPasswordLengthLessThan5() throws Exception {
		// GIVEN
		User user = createUser("12345678", "ABCD");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.PASSWORD_LENGTH_INVALID));
	}

	@Test
	public void testRegisterUserFailureWhenPasswordLengthGreaterThan20() throws Exception {
		// GIVEN
		User user = createUser("12345678", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.PASSWORD_LENGTH_INVALID));
	}

	@Test
	public void testRegisterUserFailureWhenUserIdAlreadyExist() throws Exception {
		// GIVEN
		User user = createUser("12345678", "ABCDEFGH");
		registrationController.registerUser(user);

		// WHEN
		String result = registrationController.registerUser(user);

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
