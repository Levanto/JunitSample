package junit.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import junit.dao.RegistrationDao;
import junit.entity.User;
import junit.util.Constants;

/**
 * @author GovindSingh
 * @category Test
 * @since 2017 This is test class for RegistrationServiceImpl which is used to test all public method with Junit and
 *        mockito
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceImplMockTest {
	@Mock
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
		when(registrationDao.getUser("12345678")).thenReturn(null);
		doNothing().when(registrationDao).saveUser(user);

		// WHEN
		String result = registrationServiceImpl.registerUser(user);

		// THEN
		assertThat("The user registration should not fail", result, is(Constants.SUCCESS));
		verify(registrationDao, times(1)).getUser("12345678");
		verify(registrationDao, times(1)).saveUser(user);
	}

	@Test
	public void testRegisterUserFailureWhenUserIdAlreadyExist() throws Exception {
		// GIVEN
		User user = createUser("12345678", "ABCDEFGH");
		when(registrationDao.getUser("12345678")).thenReturn(user);

		// WHEN
		String result = registrationServiceImpl.registerUser(user);

		// THEN
		assertThat("The user registration should not pass", result, is(Constants.USER_ID_ALREADY_EXIST));
		verify(registrationDao, times(1)).getUser("12345678");
		verify(registrationDao, times(0)).saveUser(user);
	}
	private User createUser(String userId, String password) {
		user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		return user;
	}
}
