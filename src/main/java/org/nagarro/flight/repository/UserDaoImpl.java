package org.nagarro.flight.repository;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.nagarro.flight.model.User;
import org.nagarro.flight.repository.interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	/* (non-Javadoc)
	 * @see org.nagarro.flight.repository.UserDao#saveUser(org.nagarro.flight.model.User)
	 */
	@Override
	public void saveUser(User user) {
		getSession().saveOrUpdate(user);
		
	}
	
	/* (non-Javadoc)
	 * @see org.nagarro.flight.repository.UserDao#getUserByUserName(java.lang.String)
	 */
	@Override
	public User getUserByUserName(String userName) {
		User user = new User();
		try {
			
			Query query = getSession().createQuery("from User where userName = :userName");
			query.setParameter("userName", userName);
			if (query.getResultList().size() > 0) {
				user = (User) query.getResultList().get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
