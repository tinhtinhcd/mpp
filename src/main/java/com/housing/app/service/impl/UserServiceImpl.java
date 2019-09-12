package com.housing.app.service.impl;

import com.housing.app.exception.UserExistedException;
import com.housing.app.model.User;
import com.housing.app.repo.UserRepository;
import com.housing.app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) {
		User user = userRepository.findByEmail(userName);
		if (user == null) {
			throw new UsernameNotFoundException(userName);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				Collections.emptyList());
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void checkUserExisted(String email) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			throw new UserExistedException();
		}
	}

	@Override
	public User saveUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User findById(long id) {
		return userRepository.getOne(id);
	}
	
}
