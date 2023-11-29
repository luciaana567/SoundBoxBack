package com.SoundBox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoundBox.config.TokenSecurity;
import com.SoundBox.core.dto.LoginDTO;
import com.SoundBox.core.dto.TokenDTO;

@RestController
@RequestMapping("/api")
public class AuthenticateController {
		
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenSecurity tokenSecurity;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate (@RequestBody LoginDTO dto){
		try {			
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getPassword()));
			String token = tokenSecurity.tokenCreate(authentication);
			TokenDTO tokenDTO = new TokenDTO();
			tokenDTO.setToken("Bearer "+token);
			return ResponseEntity.status(HttpStatus.OK).body(tokenDTO);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}

