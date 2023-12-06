package com.SoundBox.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoundBox.core.dto.StyleDTO;
import com.SoundBox.core.model.Style;
import com.SoundBox.core.repository.StyleRepository;

@Service
public class StyleService  {
  
	@Autowired
	private StyleRepository styleRepository;

	public StyleDTO toDTO(Style entity) {
		StyleDTO dto = new StyleDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}


	public Style toEntity(StyleDTO dto) {
		Style entity = new Style();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}

	public List<StyleDTO> findAll() {
		List<StyleDTO> list = new ArrayList<StyleDTO>();		
		List<Style> result = this.styleRepository.findAll();
		
		result.forEach(x -> {
			StyleDTO dto = this.toDTO(x);					
			list.add(dto);
		});
		
		return list;
	}
	
}