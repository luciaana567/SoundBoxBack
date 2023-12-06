package com.SoundBox.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SoundBox.core.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
	
}
