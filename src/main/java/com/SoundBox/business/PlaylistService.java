package com.SoundBox.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.SoundBox.core.dto.PlaylistDTO;
import com.SoundBox.core.model.Playlist;
import com.SoundBox.core.repository.PlaylistRepository;

@Service
public class PlaylistService extends AbstractService<Playlist, PlaylistDTO, Integer> {
  
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public JpaRepository<Playlist, Integer> buscarRepositorio() {
		return playlistRepository;
	}

	@Override
	public PlaylistDTO toDTO(Playlist entity) {
		PlaylistDTO dto = new PlaylistDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setUser(userService.toDTO(entity.getUser()));
		return dto;
	}

	@Override
	public Playlist toEntity(PlaylistDTO dto) {
		Playlist entity = new Playlist();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setUser(userService.toEntity(dto.getUser()));
		return entity;
	}

}
