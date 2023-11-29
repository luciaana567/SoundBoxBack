package com.SoundBox.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.SoundBox.core.model.BaseModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractService<Entity extends BaseModel<Integer>, DTO, ID extends Serializable> {

	public abstract JpaRepository<Entity, ID> buscarRepositorio();
	
	public abstract DTO toDTO(Entity entity);
	
	public abstract Entity toEntity(DTO dto);
	
	@Transactional(readOnly = true)
	public List<DTO> findAll() {
		List<DTO> listDTO = new ArrayList<DTO>();
		List<Entity> listEntity = buscarRepositorio().findAll();
		
		listEntity.forEach(entity -> {
			listDTO.add(toDTO(entity));
		});
		
		return listDTO;	
	}
	
	@Transactional(readOnly = true)
	public DTO findById(ID id) {
		Entity entity = buscarRepositorio().findById(id).get();
		if(!entity.equals(null)) {
			return toDTO(entity);
		}
		return null;
	}
	
	@Transactional
	public DTO save(DTO dto) {		
		Entity entity = toEntity(dto);
		entity.setCreatedAt(new Date());
		entity.setUpdateAt(new Date());
		Entity modelSaved =  buscarRepositorio().save(entity);
		return toDTO(modelSaved);
	}
	
	@Transactional
	public DTO update(DTO dto) {		
		Entity entity = toEntity(dto);
		entity.setUpdateAt(new Date());
		Entity entitySaved =  buscarRepositorio().save(entity);
		return toDTO(entitySaved);
	}
	
	@Transactional
	public void delete(DTO dto) {		
		Entity entity = toEntity(dto);
		buscarRepositorio().delete(entity);
	}
	
	@Transactional
	public void deleteById(ID id) {		
		buscarRepositorio().deleteById(id);
	}
	

}
