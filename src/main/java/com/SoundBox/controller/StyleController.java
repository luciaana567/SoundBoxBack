package com.SoundBox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoundBox.business.StyleService;
import com.SoundBox.core.dto.StyleDTO;

@RestController
@RequestMapping("/api/style")
public class StyleController  {

	@Autowired
	private StyleService styleService;
	
	@GetMapping(path ="/get")
	public ResponseEntity<List<StyleDTO>>  buscar() {
		List<StyleDTO> list = this.styleService.findAll();
		return new ResponseEntity<List<StyleDTO>>(list,  HttpStatus.OK);
	}
	
}
