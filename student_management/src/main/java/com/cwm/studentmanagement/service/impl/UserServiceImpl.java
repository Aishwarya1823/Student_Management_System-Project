package com.cwm.studentmanagement.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cwm.studentmanagement.model.Users;
import com.cwm.studentmanagement.repository.UserRepository;


@Service
public class UserServiceImpl implements UserDetailsService {
	
	private UserRepository usersRepository;
	
	public UserServiceImpl(UserRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = usersRepository.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException("Invalid username"));
		
		return User.withUsername(username)
				.password(users.getPassword())
				.disabled(!users.isActive())
				.build();
	}
	
}
