package com.businessbook.domain.users.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.businessbook.domain.users.model.User;
import com.businessbook.domain.users.repository.UsersRepository;

@Service
public class UsersService implements UserDetailsService{
	@Autowired
	private UsersRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User appUser = repository.findByUsername(username);
		if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
		return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), new ArrayList<>());
	}
	
	/**
	 * Add new user
	 * @param newUser
	 * @return
	 */
	public User addUser(User newUser) {
		return repository.save(newUser);
	}
	
	/**
	 * Find user by user name
	 * @param username
	 * @return AppUser class or null
	 */
	public User findByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	/**
	 * Returns list of all users in the system
	 * @return
	 */
	public List<User> findAll(){
		return repository.findAll();
	}
}
