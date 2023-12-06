package com.SoundBox.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.SoundBox.core.dto.MusicDTO;
import com.SoundBox.core.model.AlreadyHeard;
import com.SoundBox.core.model.Music;
import com.SoundBox.core.model.MusicPlaylist;
import com.SoundBox.core.repository.AlreadyHeardRepository;
import com.SoundBox.core.repository.MusicPlaylistRepository;
import com.SoundBox.core.repository.MusicRepository;
import com.SoundBox.core.repository.PlaylistRepository;

@Service
public class MusicService extends AbstractService<Music, MusicDTO, Integer> {
  
	@Autowired
	private MusicRepository musicRepository;
	
	@Autowired
	private AlreadyHeardRepository alreadyHeardRepository;
	
	@Autowired
	private MusicPlaylistRepository musicPlaylistRepository;

	@Override
	public JpaRepository<Music, Integer> buscarRepositorio() {
		return musicRepository;
	}

	@Override
	public MusicDTO toDTO(Music entity) {
		MusicDTO dto = new MusicDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setAlbumName(entity.getAlbumName());
		dto.setArtistName(entity.getArtistName());
		dto.setDescription(entity.getDescription());
		dto.setIdImgAlbum(entity.getIdImgAlbum());
		dto.setIdImgArtist(entity.getIdImgArtist());
		dto.setIdImgMusic(entity.getIdImgMusic());
		dto.setIdTrack(entity.getIdTrack());
		dto.setMusicAlreadyHeard(entity.getMusicAlreadyHeard());
		return dto;
	}

	@Override
	public Music toEntity(MusicDTO dto) {
		Music entity = new Music();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setAlbumName(dto.getAlbumName());
		entity.setArtistName(dto.getArtistName());
		entity.setDescription(dto.getDescription());
		entity.setIdImgAlbum(dto.getIdImgAlbum());
		entity.setIdImgArtist(dto.getIdImgArtist());
		entity.setIdImgMusic(dto.getIdImgMusic());
		entity.setIdTrack(dto.getIdTrack());
		entity.setMusicAlreadyHeard(dto.getMusicAlreadyHeard());
		return entity;
	}
	
	@Override
	public MusicDTO findById(Integer id) {
		
		Music entity = this.musicRepository.findById(id).get();
		if(!entity.equals(null)) {			
			MusicDTO dto = toDTO(entity);
			return dto;
		}
		return null;
	}
	
	public MusicDTO getById(Integer id, Integer userId) {
		
		Music entity = this.musicRepository.findById(id).get();
		if(!entity.equals(null)) {
			Boolean status = this.getStatusMusicAlreadyHeard(entity.getId(), userId);
			entity.setMusicAlreadyHeard(status);
			Boolean inPlaylist = this.inPlaylist(entity.getId(), userId);
			MusicDTO dto = toDTO(entity);
			dto.setInPlaylist(inPlaylist);
			return dto;
		}
		return null;
	}
	

	public List<MusicDTO> findMusicByName(String name, Integer userId){
		List<MusicDTO> musicList = new ArrayList<MusicDTO>();
		
		Stream<Music> result = this.musicRepository.findByNameFree(name);
		
		result.forEach(x -> {
			MusicDTO dto = this.toDTO(x);	
			Boolean status = this.getStatusMusicAlreadyHeard(dto.getId(), userId);
			Boolean inPlaylist = this.inPlaylist(dto.getId(), userId);
			dto.setMusicAlreadyHeard(status);
			dto.setInPlaylist(inPlaylist);
			musicList.add(dto);
		});
		
		return musicList;
	}

	public List<MusicDTO> findNeverHeardSongsByStyle(List<Integer> styles, Integer userId){

		List<MusicDTO> musicList = new ArrayList<MusicDTO>();
		
		Stream<Music> result = this.musicRepository.findByStyleAndNeverAlreadyHeard(styles, userId);
		
		result.forEach(x -> {
			MusicDTO dto = this.toDTO(x);				
			musicList.add(dto);
		});
		
		return musicList;
	}
	
	public List<MusicDTO> findPlaylistByIdUser(Integer userId){
		List<MusicDTO> musicList = new ArrayList<MusicDTO>();
		
		Stream<Music> result = this.musicRepository.findByPlaylistId(userId);
		
		result.forEach(x -> {
			MusicDTO dto = this.toDTO(x);	
			dto.setMusicAlreadyHeard(true);
			dto.setInPlaylist(true);
			musicList.add(dto);
		});
		
		return musicList;
	} 
	
	public List<MusicDTO> findRadomPlaylist(Integer userId){
		List<MusicDTO> musicList = new ArrayList<MusicDTO>();
		
		Stream<Music> result = this.musicRepository.findRandom();
		
		result.forEach(x -> {
			MusicDTO dto = this.toDTO(x);	
			Boolean status = this.getStatusMusicAlreadyHeard(dto.getId(), userId);
			dto.setMusicAlreadyHeard(status);
			musicList.add(dto);
		});
		
		return musicList;
	} 
	
	public List<MusicDTO> findRadomAlreadyHeardPlaylist(Integer userId){
		List<MusicDTO> musicList = new ArrayList<MusicDTO>();
		
		Stream<Music> result = this.musicRepository.findRandomAlreadyHeardList(userId);
		
		result.forEach(x -> {
			MusicDTO dto = this.toDTO(x);
			dto.setMusicAlreadyHeard(true);
			musicList.add(dto);
		});
		
		return musicList;
	} 
	
	private Boolean getStatusMusicAlreadyHeard(Integer idMusic, Integer idUser) {
		Boolean status = false;		
		AlreadyHeard entity = this.alreadyHeardRepository.findByIdMusicAndIdUser(idMusic, idUser);
		
		if(entity != null)
			status  = true;
		
		return status;
	}
		
	private Boolean inPlaylist(Integer idMusic, Integer idUser) {
		Boolean status = false;		
		MusicPlaylist entity = this.musicPlaylistRepository.findByMusicIdAndUserId(idMusic, idUser);
		
		if(entity != null)
			status  = true;
		
		return status;
	}
}
