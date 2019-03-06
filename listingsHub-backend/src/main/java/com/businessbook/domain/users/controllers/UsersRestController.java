package com.businessbook.domain.users.controllers;

import static com.businessbook.domain.common.WebServicesConstants.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.businessbook.domain.users.model.User;
import com.businessbook.domain.users.services.UsersService;

@RestController
@CrossOrigin
public class UsersRestController {
	private UsersService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UsersRestController(UsersService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userService = userService;
		this.bCryptPasswordEncoder= bCryptPasswordEncoder;
	}



	@PostMapping(USERS_SIGN_UP_URI)
	@ResponseStatus(code=HttpStatus.OK)
	public void signUp(@RequestBody User newUser) {
		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
		userService.addUser(newUser);
	}
	
	@GetMapping("/account/details")
	public User getAccount(@RequestParam("username") String username){
		return userService.findByUsername(username);
	}
}
