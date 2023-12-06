package com.SoundBox.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.SoundBox.core.dto.MusicPlaylistDTO;
import com.SoundBox.core.dto.MusicPlaylistToSaveDTO;
import com.SoundBox.core.model.MusicPlaylist;
import com.SoundBox.core.repository.MusicPlaylistRepository;

@Service
public class MusicPlaylistService extends AbstractService<MusicPlaylist, MusicPlaylistDTO, Integer> {
  
	@Autowired
	private MusicPlaylistRepository musicPlaylistRepository;
	
	@Autowired
	private MusicService musicService;
	
	@Autowired
	private PlaylistService playlistService;

	@Override
	public JpaRepository<MusicPlaylist, Integer> buscarRepositorio() {
		return musicPlaylistRepository;
	}

	@Override
	public MusicPlaylistDTO toDTO(MusicPlaylist entity) {
		MusicPlaylistDTO dto = new MusicPlaylistDTO();
		dto.setId(entity.getId());
		dto.setMusic(musicService.toDTO(entity.getMusic()));
		dto.setPlayList(playlistService.toDTO(entity.getPlaylist()));
		return dto;
	}

	@Override
	public MusicPlaylist toEntity(MusicPlaylistDTO dto) {
		MusicPlaylist entity = new MusicPlaylist();
		entity.setId(dto.getId());
		entity.setMusic(musicService.toEntity(dto.getMusic()));
		entity.setPlaylist(playlistService.toEntity(dto.getPlayList()));
		return entity;
	}	
	
	public MusicPlaylistDTO saveNewMusic(MusicPlaylistToSaveDTO dto) {		
		MusicPlaylistDTO dtoToSave = new MusicPlaylistDTO();
		dtoToSave.setMusic(musicService.findById(dto.getMusicId()));
		dtoToSave.setPlayList(playlistService.findByUserId(dto.getUserId()));
		
		this.musicPlaylistRepository.save(this.toEntity(dtoToSave));		
		return dtoToSave;
	}
	
	public void deleteByIdMusicAndIdPlaylist(MusicPlaylistToSaveDTO dto) {
		this.musicPlaylistRepository.removeByMusicIdAndUserId(dto.getMusicId(), dto.getUserId());
	}

}
