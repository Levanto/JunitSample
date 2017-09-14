/**
 * 
 */
package junit.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.SessionFactory;
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

/**
 * @author GovindSingh
 * @category Test
 * @since 2017 This is test class for RegistrationDaoImpl which is used to test all public method with Junit.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-test-context.xml" })
@Transactional
@Rollback(true)
public class RegistrationDaoImplTest {
	private final String USER_ID = "12345678";
	private final String PASSWORD = "ABCDEFGH";

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	private User user;
	private RegistrationDaoImpl registrationDaoImpl = new RegistrationDaoImpl();

	@Before
	public void setUp() {
		registrationDaoImpl.setSessionFactory(sessionFactory);
	}

	@Test
	public void testSaveUserSuccess() throws Exception {
		// GIVEN
		User user = createUser(USER_ID, PASSWORD);

		// WHEN
		registrationDaoImpl.saveUser(user);

		// THEN
		User retrieivedUser = registrationDaoImpl.getUser(user.getUserId());
		assertThat("User is not saved in database", retrieivedUser.getUserId(), is(user.getUserId()));

	}

	@Test(expected = NonUniqueObjectException.class)
	public void testSaveUserFailureWhenUserAlreadyExist() throws Exception {
		// GIVEN
		User user = createUser(USER_ID, PASSWORD);
		registrationDaoImpl.saveUser(user);

		// WHEN
		User existingUser = createUser(USER_ID, PASSWORD);
		registrationDaoImpl.saveUser(existingUser);
	}

	@Test
	public void testGetUserSuccess() throws Exception {
		// GIVEN
		User user = createUser(USER_ID, PASSWORD);
		registrationDaoImpl.saveUser(user);
		// WHEN
		User retrieivedUser = registrationDaoImpl.getUser(user.getUserId());

		// THEN
		assertThat("User is not saved in database", retrieivedUser.getUserId(), is(user.getUserId()));

	}

	@Test
	public void testGetUserWhenUserDoesNotExist() throws Exception {
		// GIVEN
		User user = createUser(USER_ID, PASSWORD);
		registrationDaoImpl.deleteUser(user);
		// WHEN
		User retrieivedUser = registrationDaoImpl.getUser(USER_ID);

		// THEN
		assertThat("User is not saved in database", retrieivedUser, is(nullValue()));
	}

	@Test
	public void testDeleteUserSuccess() throws Exception {
		// GIVEN
		User user = createUser(USER_ID, PASSWORD);
		registrationDaoImpl.saveUser(user);

		// WHEN
		registrationDaoImpl.deleteUser(user);

		// THEN
		User retrieivedUser = registrationDaoImpl.getUser(USER_ID);
		assertThat("User is not saved in database", retrieivedUser, is(nullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteUserFailureWithNull() throws Exception {

		// WHEN
		registrationDaoImpl.deleteUser(null);
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
