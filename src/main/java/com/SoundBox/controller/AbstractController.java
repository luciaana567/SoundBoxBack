package com.SoundBox.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.SoundBox.business.AbstractService;
import com.SoundBox.core.model.BaseModel;

public abstract class AbstractController<Entity extends BaseModel<Integer>, DTO, ID extends Serializable> {

	public abstract AbstractService<Entity, DTO, ID> service();
	
	@GetMapping()
	public ResponseEntity<List<DTO>>  buscar() {
		List<DTO> lista = service().findAll();
		return new ResponseEntity<List<DTO>>(lista,  HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<DTO>  buscar(@PathVariable ID id) {
		DTO dto = service().findById(id);
		return new ResponseEntity<DTO>(dto,  HttpStatus.OK);
	}
	
	@PostMapping("/alvar")
	public ResponseEntity<DTO> salvar(@RequestBody DTO dto) {		
		DTO dtoSaved = service().save(dto);
		return new ResponseEntity<DTO>(dtoSaved, HttpStatus.CREATED);		
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<DTO> atualizar(@RequestBody DTO dto) {		
		DTO dtoUpdated = service().update(dto);
		return new ResponseEntity<DTO>(dtoUpdated, HttpStatus.OK);		
	}
	
	@DeleteMapping("/apagar/{id}")
	public ResponseEntity<?> excluir(@PathVariable ID id) {		
		service().deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);	
	}
}
