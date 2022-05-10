package com.genpact.shoppingcart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.genpact.shoppingcart.model.CustomUserDetail;
import com.genpact.shoppingcart.model.User;
import com.genpact.shoppingcart.repository.UserRepository;


@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userOptional=userRepository.findUserByEmail(username);
		userOptional.orElseThrow(()->new UsernameNotFoundException("NO USER FOUND"));
		return userOptional.map(CustomUserDetail::new).get();
		
	}

}