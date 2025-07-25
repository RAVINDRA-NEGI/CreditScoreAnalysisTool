package com.user.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.entity.UserPrincipal;
import com.user.repo.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
	 private final UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username ) throws UsernameNotFoundException {
		        User user = userRepo.findByEmailOrUserName(username)
		            .orElseThrow(() -> new UsernameNotFoundException("User not found with email or username: " + username));
		       
		        return new UserPrincipal(user);
		    
	}

}
