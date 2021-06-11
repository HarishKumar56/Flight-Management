package org.nagarro.flight.controller;

import org.nagarro.flight.model.User;
import org.nagarro.flight.service.interfaces.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	
	@Autowired
	UserDaoService userDaoService;
	
	@GetMapping("/register")
	public String registerPage(@ModelAttribute("user")User user)
	{
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user")User user)
	{
		userDaoService.saveUser(user);
		return "redirect:login";
	}
	
	

}
