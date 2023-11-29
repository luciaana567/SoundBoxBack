package com.SoundBox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoundBox.business.UserService;
import com.SoundBox.core.dto.UserDTO;
import com.SoundBox.core.dto.UserUpdatePasswordDTO;
import com.SoundBox.utils.UtilSecuriry;

@RestController
@RequestMapping("/api")
public class LoginController{

	@Autowired
	private UserService userService;
	
	@Autowired
	private UtilSecuriry utilSecutity; 

	@PutMapping("/login")
	private ResponseEntity<?> changePassword(@RequestBody UserUpdatePasswordDTO userUpdatePasswordDTO)throws Exception{	
			
		UserDTO user = this.userService.findById(userUpdatePasswordDTO.getId());
		
		if(utilSecutity.match(user.getPassword(), userUpdatePasswordDTO.getPassword())) {
			user.setPassword(utilSecutity.enconde(userUpdatePasswordDTO.getNewPassword()));
			this.userService.update(user);
			return ResponseEntity.status(HttpStatus.OK).body("SUCESS");
		}			
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
