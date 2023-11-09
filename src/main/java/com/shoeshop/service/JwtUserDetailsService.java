package com.shoeshop.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shoeshop.models.UserOne;
import com.shoeshop.repositories.UserDAO;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserOne userOne = new UserDAO().getUserByUsername(username);
		return new User(userOne.getUsername(), userOne.getPassword(),
				new ArrayList<>());
	}

}