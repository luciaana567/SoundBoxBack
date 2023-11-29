package com.SoundBox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoundBox.business.UserService;
import com.SoundBox.core.dto.UserDTO;
import com.SoundBox.utils.UtilSecuriry;

@RestController
@RequestMapping("/api/user")
public class UserController{

	@Autowired
	private UserService userService;
	
	@Autowired
	private UtilSecuriry utilSecutity; 

	@PostMapping("/save")
	public ResponseEntity<?> salve(@RequestBody UserDTO dto) throws Exception {	
		try {
			// Realiza o BCrypt do password
			dto.setPassword(utilSecutity.enconde(dto.getPassword()));
			userService.saveUser(dto);
		}catch (Exception e) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_ACCEPTABLE).badRequest().body(e.getMessage());
		}
		return new ResponseEntity<UserDTO>(HttpStatus.CREATED);		
	}	
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody UserDTO dto) throws Exception {	
		try {
			userService.updateUser(dto);
		}catch (Exception e) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_ACCEPTABLE).badRequest().body(e.getMessage());
		}
		return new ResponseEntity<UserDTO>(HttpStatus.OK);		
	}
}
