package com.SoundBox.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.SoundBox.core.dto.PersonDTO;
import com.SoundBox.core.model.Person;
import com.SoundBox.core.repository.PersonRepository;

@Service
public class PersonService extends AbstractService<Person, PersonDTO, Integer>{

	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public JpaRepository<Person, Integer> buscarRepositorio() {
		return personRepository;
	}

	@Override
	public PersonDTO toDTO(Person entity) {
		PersonDTO dto = new PersonDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setBirthday(entity.getBirthday());
		dto.setGender(entity.getGender());
		dto.setCountry(entity.getCountry());
		return dto;
	}

	@Override
	public Person toEntity(PersonDTO dto) {
		Person entity = new Person();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setBirthday(dto.getBirthday());
		entity.setGender(dto.getGender());
		entity.setCountry(dto.getCountry());
		return entity;
	}

}
