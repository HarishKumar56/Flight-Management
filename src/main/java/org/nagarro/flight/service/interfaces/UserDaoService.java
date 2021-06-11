package org.nagarro.flight.service.interfaces;

import org.nagarro.flight.model.User;

public interface UserDaoService {

	void saveUser(User user);

	User getUserByUserName(String User);

}