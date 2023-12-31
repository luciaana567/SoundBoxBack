package com.SoundBox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoundBox.business.CountryService;
import com.SoundBox.core.dto.CountryDTO;

@RestController
@RequestMapping("/api/country")
public class CountryController  {

	@Autowired
	private CountryService countryService;


	@GetMapping("/get")
	public ResponseEntity<List<CountryDTO>> getAllCountries() {
		List<CountryDTO> list = this.countryService.findAll();
		return new ResponseEntity<List<CountryDTO>>(list,  HttpStatus.OK);
	}
}
