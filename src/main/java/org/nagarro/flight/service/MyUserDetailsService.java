package org.nagarro.flight.service;

import org.nagarro.flight.model.MyUserDetails;
import org.nagarro.flight.model.User;
import org.nagarro.flight.service.interfaces.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserDaoService userDaoService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDaoService.getUserByUserName(username);
		UserDetails userDetails = new MyUserDetails(user);
		
		return userDetails;
	}

}
