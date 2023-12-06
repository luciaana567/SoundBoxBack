package com.SoundBox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoundBox.business.PlaylistService;
import com.SoundBox.business.UserService;
import com.SoundBox.core.dto.LoginDTO;
import com.SoundBox.core.dto.UserDTO;
import com.SoundBox.core.model.User;
import com.SoundBox.utils.UtilSecuriry;

@RestController
@RequestMapping("/api/user")
public class UserController{

	@Autowired
	private UserService userService;
	
	@Autowired
	private UtilSecuriry utilSecutity; 
	
	@Autowired
	private PlaylistService playlistService;

	@Transactional
	@PostMapping("/save")
	public ResponseEntity<?> salve(@RequestBody UserDTO dto) throws Exception {	
		try {
			// Realiza o BCrypt do password
			dto.setPassword(utilSecutity.enconde(dto.getPassword()));
			UserDTO user = userService.saveUser(dto);
			playlistService.createPlaylist(user);
		}catch (Exception e) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_ACCEPTABLE).badRequest().body(e.getMessage());
		}
		return new ResponseEntity<UserDTO>(HttpStatus.CREATED);		
	}	
	
	@Transactional
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody UserDTO dto) throws Exception {	
		try {
			dto.setPassword(utilSecutity.enconde(dto.getPassword()));
			userService.updateUser(dto);
		}catch (Exception e) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_ACCEPTABLE).badRequest().body(e.getMessage());
		}
		return new ResponseEntity<UserDTO>(HttpStatus.OK);		
	}
	
	@Transactional
	@PostMapping("/get")
	public ResponseEntity<?> update(@RequestBody LoginDTO dto) throws Exception {	
		UserDTO user;
		try {
			dto.setPassword(utilSecutity.enconde(dto.getPassword()));
			user = userService.findByEmail(dto.getEmail());
		}catch (Exception e) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_ACCEPTABLE).badRequest().body(e.getMessage());
		}
		return new ResponseEntity<Integer>(user.getId(),HttpStatus.OK);		
	}
}
