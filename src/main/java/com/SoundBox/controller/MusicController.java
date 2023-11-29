package com.SoundBox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoundBox.business.MusicService;
import com.SoundBox.core.dto.MusicDTO;

@RestController
@RequestMapping("/api/music")
public class MusicController  {

	@Autowired
	private MusicService musicService;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<MusicDTO>  buscar(@PathVariable Integer id) {
		MusicDTO dto = this.musicService.findById(id);
		return new ResponseEntity<MusicDTO>(dto,  HttpStatus.OK);
	}
	
}
