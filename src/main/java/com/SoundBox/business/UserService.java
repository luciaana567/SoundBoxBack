package com.SoundBox.business;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.SoundBox.core.dto.*;
import com.SoundBox.core.model.Person;
import com.SoundBox.core.model.User;
import com.SoundBox.core.repository.UserRepository;
import com.SoundBox.utils.EmailValid;
@Service
public class UserService extends AbstractService<User, UserDTO, Integer>{

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PersonService personService; 

	@Override
	public JpaRepository<User, Integer> buscarRepositorio() {
		return this.userRepository;
	}

	@Override
	public UserDTO toDTO(User entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setEmail(entity.getEmail());
		dto.setUsername(entity.getUsername());
		dto.setPassword(entity.getPassword());
		dto.setPerson(personService.toDTO(entity.getPerson()));		
		return dto;
	}
	
	@Override
	public User toEntity(UserDTO dto) {
		User entity = new User();
		entity.setId(dto.getId());
		entity.setEmail(dto.getEmail());
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
		entity.setPerson(personService.toEntity(dto.getPerson()));
		return entity;
	}
	
	public User EntitieFindById(Integer id) {
		return this.userRepository.findById(id).get();
	}
		
	public UserDTO findByEmail(String email) {
		return toDTO(this.userRepository.findByEmail(email));
	}
	
 	public UserDTO saveUser(UserDTO dto) throws Exception {
		 		
		if(!EmailValid.isValidEmailAddressRegex(dto.getEmail())) {
			throw new Exception("Email inválido!");
		}
		if(VerifyExistsEmail(dto)) {
			throw new Exception("Email já cadastrado!");
		}
		if(VerifyExistsLogin(dto)) {
			throw new Exception("Login já esta sendo usado!");
		}		
		
		User user = toEntity(dto);		
		user.setCreatedAt(new Date());	
		user.setUpdateAt(new Date());
		User userSaved =  this.userRepository.save(user);		
		return toDTO(userSaved);
	}
	
 	
	public UserDTO updateUser(UserDTO dto) throws Exception {

 		User entity = this.EntitieFindById(dto.getId());
 		
		if(!EmailValid.isValidEmailAddressRegex(dto.getEmail())) {
			throw new Exception("Email inválido!");
		}
		if(VerifyExistsEmail(dto) && !dto.getEmail().equals(entity.getEmail())) {
			throw new Exception("Email já cadastrado!");
		}
		if(VerifyExistsLogin(dto) && !dto.getUsername().equals(entity.getUsername())) {
			throw new Exception("Login já esta sendo usado!");
		}	
		
		User user = toEntity(dto);
		user.setUpdateAt(new Date());
		user.getPerson().setUpdateAt(new Date());
		User userSaved =  this.userRepository.save(user);
		PersonDTO person = this.personService.save(dto.getPerson());
		UserDTO result = toDTO(userSaved);
		result.setPerson(person);
		return result;
	}


	public Boolean VerifyExistsEmail(UserDTO dto) {
		return this.userRepository.existsByEmail(dto.getEmail());
	}
	
	public Boolean VerifyExistsLogin(UserDTO dto) {
		return this.userRepository.existsByUsername(dto.getUsername());
	}	
	
	public String uuid() throws Exception{
		return UUID.randomUUID().toString();
	}
	
	public boolean exists(String username, String email) throws Exception {

		if (username != null && email != null) {
			if (this.userRepository.existsByEmail(email) || this.userRepository.existsByUsername(username)) {
				return true;
			}
		} else if (username == null) {
			if (this.userRepository.existsByEmail(email)) {
				return true;
			}
		} else if (email == null) {
			if (this.userRepository.existsByUsername(username)) {
				return true;
			}
		}
		return false;
	}
	
}
