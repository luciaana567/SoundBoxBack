package com.SoundBox.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SoundBox.core.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{
}
