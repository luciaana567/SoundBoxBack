package com.SoundBox.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SoundBox.core.model.User;
import com.SoundBox.core.repository.UserRepository;

@Service
public class AuthenticateService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userAccess = null;
		try {			
			userAccess = userRepository.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userAccess;
	}
}
