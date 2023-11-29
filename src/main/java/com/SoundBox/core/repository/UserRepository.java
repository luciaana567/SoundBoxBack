package com.SoundBox.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SoundBox.core.model.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	
	Boolean existsByEmail(String email);
	
	Boolean existsByUsername(String username);
	
	User findByEmail(String email);
}
