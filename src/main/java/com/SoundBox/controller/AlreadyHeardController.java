package com.SoundBox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoundBox.business.AlreadyHeardService;
import com.SoundBox.core.dto.AlreadyHeardDTO;
import com.SoundBox.core.dto.AlreadyHeardToSaveDTO;

@RestController
@RequestMapping("/api/AlreadyHeard")
public class AlreadyHeardController{

	@Autowired
	private AlreadyHeardService alreadyHeardService;	

	@Transactional
	@PostMapping("/save")
	public ResponseEntity<?> salve(@RequestBody AlreadyHeardToSaveDTO dto) throws Exception {			
		this.alreadyHeardService.save(dto);
		return new ResponseEntity<AlreadyHeardDTO>(HttpStatus.CREATED);		
	}	
}
