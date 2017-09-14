package junit.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import junit.entity.User;
import junit.service.RegistrationService;
import junit.util.Constants;

/**
 * @author GovindSingh
 * @category Test
 * @since 2017 This is test class for RegistrationController which is used to test all public method with Junit and
 *        mockito
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerMockTest {

	@Mock
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
		when(registrationService.registerUser(user)).thenReturn(Constants.SUCCESS);

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not fail", result, is(Constants.SUCCESS));
		verify(registrationService, times(1)).registerUser(user);
	}

	@Test
	public void testRegisterUserFailureWhenUserIdIsNull() throws Exception {
		// GIVEN
		User user = createUser(null, "ABCDEFGH");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.USER_ID_INVALID));
		verify(registrationService, times(0)).registerUser(user);
	}

	@Test
	public void testRegisterUserFailureWhenUserIdIsEmpty() throws Exception {
		// GIVEN
		User user = createUser("", "ABCDEFGH");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.USER_ID_INVALID));
		verify(registrationService, times(0)).registerUser(user);
	}

	@Test
	public void testRegisterUserFailureWhenUserIdlengthLessThan6() throws Exception {
		// GIVEN
		User user = createUser("1", "ABCDEFGH");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.USER_ID_LENGTH_INVALID));
		verify(registrationService, times(0)).registerUser(user);
	}

	@Test
	public void testRegisterUserFailureWhenUserIdlengthGreaterThan10() throws Exception {
		// GIVEN
		User user = createUser("12345678901", "ABCDEFGH");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.USER_ID_LENGTH_INVALID));
		verify(registrationService, times(0)).registerUser(user);
	}

	@Test
	public void testRegisterUserFailureWhenPasswordIsNull() throws Exception {
		// GIVEN
		User user = createUser("12345678", null);

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.PASS_WORD_INVALID));
		verify(registrationService, times(0)).registerUser(user);
	}

	@Test
	public void testRegisterUserFailureWhenPasswordIsEmpty() throws Exception {
		// GIVEN
		User user = createUser("12345678", "");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.PASS_WORD_INVALID));
		verify(registrationService, times(0)).registerUser(user);
	}

	@Test
	public void testRegisterUserFailureWhenPasswordLengthLessThan5() throws Exception {
		// GIVEN
		User user = createUser("12345678", "ABCD");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.PASS_WORD_LENGTH_INVALID));
		verify(registrationService, times(0)).registerUser(user);
	}

	@Test
	public void testRegisterUserFailureWhenPasswordLengthGreaterThan20() throws Exception {
		// GIVEN
		User user = createUser("12345678", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.PASS_WORD_LENGTH_INVALID));
		verify(registrationService, times(0)).registerUser(user);
	}

	@Test
	public void testRegisterUserFailureWhenUserIdAlreadyExist() throws Exception {
		// GIVEN
		User user = createUser("12345678", "ABCDEFGH");
		when(registrationService.registerUser(user)).thenReturn(Constants.USER_ID_ALREADY_EXIST);

		// WHEN
		String result = registrationController.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.USER_ID_ALREADY_EXIST));
		verify(registrationService, times(1)).registerUser(user);
	}
	private User createUser(String userId, String password) {
		user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		return user;
	}
}
