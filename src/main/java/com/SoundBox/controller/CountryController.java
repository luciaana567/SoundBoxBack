package com.SoundBox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoundBox.business.AbstractService;
import com.SoundBox.business.CountryService;
import com.SoundBox.core.dto.CountryDTO;
import com.SoundBox.core.model.Country;

@RestController
@RequestMapping("/api/country")
public class CountryController extends AbstractController<Country, CountryDTO, Integer> {

	@Autowired
	private CountryService countryService;

	@Override
	public AbstractService<Country, CountryDTO, Integer> service() {
		return countryService;
	}
}
