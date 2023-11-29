package com.SoundBox.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.SoundBox.core.dto.CountryDTO;
import com.SoundBox.core.model.Country;
import com.SoundBox.core.repository.CountryRepository;

@Service
public class CountryService extends AbstractService<Country, CountryDTO, Integer> {
  
	@Autowired
	private CountryRepository countryRepository;

	@Override
	public JpaRepository<Country, Integer> buscarRepositorio() {
		return countryRepository;
	}

	@Override
	public CountryDTO toDTO(Country entity) {
		CountryDTO dto = new CountryDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setSlg(entity.getSlg());
		return dto;
	}

	@Override
	public Country toEntity(CountryDTO dto) {
		Country entity = new Country();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setSlg(dto.getSlg());
		return entity;
	}

	
	
}
