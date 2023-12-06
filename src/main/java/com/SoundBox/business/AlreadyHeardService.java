package com.SoundBox.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.SoundBox.core.dto.AlreadyHeardDTO;
import com.SoundBox.core.dto.AlreadyHeardToSaveDTO;
import com.SoundBox.core.dto.MusicPlaylistDTO;
import com.SoundBox.core.dto.MusicPlaylistToSaveDTO;
import com.SoundBox.core.model.AlreadyHeard;
import com.SoundBox.core.model.MusicPlaylist;
import com.SoundBox.core.repository.AlreadyHeardRepository;
import com.SoundBox.core.repository.MusicPlaylistRepository;

@Service
public class AlreadyHeardService {
  
	@Autowired
	private AlreadyHeardRepository alreadyHeardRepository;
	
	@Autowired
	private MusicService musicService;
	
	@Autowired
	private UserService userService;

	public AlreadyHeardDTO toDTO(AlreadyHeard entity) {
		AlreadyHeardDTO dto = new AlreadyHeardDTO();
		dto.setId(entity.getId());
		dto.setMusic(musicService.toDTO(entity.getMusic()));
		dto.setUser(userService.toDTO(entity.getUser()));
		return dto;
	}
	
	public AlreadyHeard toEntity(AlreadyHeardDTO dto) {
		AlreadyHeard entity = new AlreadyHeard();
		entity.setId(dto.getId());
		entity.setMusic(musicService.toEntity(dto.getMusic()));
		entity.setUser(userService.toEntity(dto.getUser()));
		return entity;
	}	
	
	public AlreadyHeardDTO save(AlreadyHeardToSaveDTO dto) {		
		AlreadyHeardDTO dtoToSave = new AlreadyHeardDTO();
		dtoToSave.setMusic(musicService.findById(dto.getMusicId())); 
		dtoToSave.setUser(userService.findById(dto.getUserId()));
		this.alreadyHeardRepository.save(this.toEntity(dtoToSave));		
		return dtoToSave;
	}
	
	public Boolean getStatusMusicAlreadyHeard(Integer idMusic, Integer idUser) {
		Boolean status = false;		
		AlreadyHeard entity = this.alreadyHeardRepository.findByIdMusicAndIdUser(idMusic, idUser);
		
		if(entity != null)
			status  = true;
		
		return status;
	}
}
