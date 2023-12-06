package com.SoundBox.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoundBox.core.dto.CountryDTO;
import com.SoundBox.core.model.Country;
import com.SoundBox.core.repository.CountryRepository;

@Service
public class CountryService  {
  
	@Autowired
	private CountryRepository countryRepository;

	public CountryDTO toDTO(Country entity) {
		CountryDTO dto = new CountryDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setSlg(entity.getSlg());
		return dto;
	}


	public Country toEntity(CountryDTO dto) {
		Country entity = new Country();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setSlg(dto.getSlg());
		return entity;
	}

	public List<CountryDTO> findAll() {
		List<CountryDTO> list = new ArrayList<CountryDTO>();		
		List<Country> result = this.countryRepository.findAll();
		
		result.forEach(x -> {
			CountryDTO dto = this.toDTO(x);					
			list.add(dto);
		});
		
		return list;
	}
	
}
