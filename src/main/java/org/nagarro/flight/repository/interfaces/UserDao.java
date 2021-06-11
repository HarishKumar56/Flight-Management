package org.nagarro.flight.repository.interfaces;

import org.nagarro.flight.model.User;

public interface UserDao {

	void saveUser(User user);

	User getUserByUserName(String userName);

}