package junit.validator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.entity.User;
import junit.util.Constants;

/**
 * @author GovindSingh
 * @category Test
 * @since 2017 This is test class for UserValidator which is used to test all public method with Junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-test-context.xml" })
public class UserValidatorTest {

	private User user;

	@Test
	public void testValidateUserSuccess() throws Exception {
		// GIVEN
		User user = createUser("12345678", "ABCDEFGH");
		
		// WHEN
		String result = UserValidator.validateUser(user);
		
		//THEN
		assertThat("User validation should not fail", result, is(Constants.SUCCESS));
	}

	@Test
	public void testValidateUserFailureWhenUserIdIsNull() throws Exception {
		// GIVEN
		User user = createUser(null, "ABCDEFGH");

		// WHEN
		String result = UserValidator.validateUser(user);

		// THEN
		assertThat("The user validation should not pass", result, is(Constants.USER_ID_INVALID));
	}

	@Test
	public void testValidateUserFailureWhenUserIdIsEmpty() throws Exception {
		// GIVEN
		User user = createUser("", "ABCDEFGH");

		// WHEN
		String result = UserValidator.validateUser(user);

		// THEN
		assertThat("The user validation should not pass", result, is(Constants.USER_ID_INVALID));
	}

	@Test
	public void testValidateUserFailureWhenUserIdlengthLessThan6() throws Exception {
		// GIVEN
		User user = createUser("1", "ABCDEFGH");

		// WHEN
		String result = UserValidator.validateUser(user);

		// THEN
		assertThat("The user validation should not pass", result, is(Constants.USER_ID_LENGTH_INVALID));
	}

	@Test
	public void testValidateUserFailureWhenUserIdlengthGreaterThan10() throws Exception {
		// GIVEN
		User user = createUser("12345678901", "ABCDEFGH");

		// WHEN
		String result = UserValidator.validateUser(user);

		// THEN
		assertThat("The user validation should not pass", result, is(Constants.USER_ID_LENGTH_INVALID));
	}

	@Test
	public void testValidateUserFailureWhenPasswordIsNull() throws Exception {
		// GIVEN
		User user = createUser("12345678", null);

		// WHEN
		String result = UserValidator.validateUser(user);

		// THEN
		assertThat("The user validation should not pass", result, is(Constants.PASS_WORD_INVALID));
	}

	@Test
	public void testValidateUserFailureWhenPasswordIsEmpty() throws Exception {
		// GIVEN
		User user = createUser("12345678", "");

		// WHEN
		String result = UserValidator.validateUser(user);

		// THEN
		assertThat("The user validation should not pass", result, is(Constants.PASS_WORD_INVALID));
	}

	@Test
	public void testValidateUserFailureWhenPasswordLengthLessThan5() throws Exception {
		// GIVEN
		User user = createUser("12345678", "ABCD");

		// WHEN
		String result = UserValidator.validateUser(user);

		// THEN
		assertThat("The user validation should not pass", result, is(Constants.PASS_WORD_LENGTH_INVALID));
	}

	@Test
	public void testValidateUserFailureWhenPasswordLengthGreaterThan20() throws Exception {
		// GIVEN
		User user = createUser("12345678", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");

		// WHEN
		String result = UserValidator.validateUser(user);

		// THEN
		assertThat("The user validation should not pass", result, is(Constants.PASS_WORD_LENGTH_INVALID));
	}

	@Test
	public void testConstructorIsPrivate() throws Exception {
		Constructor<Constants> constructor = Constants.class.getDeclaredConstructor();
		assertTrue(Modifier.isPrivate(constructor.getModifiers()));
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	private User createUser(String userId, String password) {
		user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		return user;
	}
}
