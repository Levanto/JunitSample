/**
 * 
 */
package junit.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import junit.entity.User;

/**
 * @author GovindSingh
 */
@Transactional
public class RegistrationDaoImpl implements RegistrationDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public User getUser(String userId) {
		return sessionFactory.getCurrentSession().get(User.class, userId);
	}

	public void saveUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	public void deleteUser(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

}
