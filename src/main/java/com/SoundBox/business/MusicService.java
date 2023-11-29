package com.SoundBox.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.SoundBox.core.dto.MusicDTO;
import com.SoundBox.core.model.Music;
import com.SoundBox.core.repository.MusicRepository;

@Service
public class MusicService extends AbstractService<Music, MusicDTO, Integer> {
  
	@Autowired
	private MusicRepository musicRepository;

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
	
	public MusicDTO getById(Integer id) {
		
		Music entity = this.musicRepository.findById(id).get();
		if(!entity.equals(null)) {
			return toDTO(entity);
		}
		return null;
	}
	

	public List<MusicDTO> findMusicByName(String name){
		List<MusicDTO> musicList = new ArrayList<MusicDTO>();
		
		Stream<Music> result = this.musicRepository.findByNameFree(name);
		
		result.forEach(x -> {
			MusicDTO dto = this.toDTO(x);						
			musicList.add(dto);
		});
		
		return musicList;
	}
	
	public MusicDTO alreadyHerad(Integer id) {
		Music entity = this.musicRepository.findById(id).get(); 
		entity.setUpdateAt(new Date());
		entity.setMusicAlreadyHeard(true);
		Music entitySaved =  this.musicRepository.save(entity);
		return toDTO(entitySaved);
	}
	
	public List<MusicDTO> findNeverHeardSongsByStyle(List<Integer> styles){

		List<MusicDTO> musicList = new ArrayList<MusicDTO>();
		
		Stream<Music> result = this.musicRepository.findByStyleAndNeverAlreadyHeard(styles);
		
		result.forEach(x -> {
			MusicDTO dto = this.toDTO(x);				
			musicList.add(dto);
		});
		
		return musicList;
	}
	
	public List<MusicDTO> findByIdPlaylist(Integer id){
		List<MusicDTO> musicList = new ArrayList<MusicDTO>();
		
		Stream<Music> result = this.musicRepository.findByPlaylistId(id);
		
		result.forEach(x -> {
			MusicDTO dto = this.toDTO(x);					
			musicList.add(dto);
		});
		
		return musicList;
	} 
	
	public List<MusicDTO> findRadomPlaylist(){
		List<MusicDTO> musicList = new ArrayList<MusicDTO>();
		
		Stream<Music> result = this.musicRepository.findRandom();
		
		result.forEach(x -> {
			MusicDTO dto = this.toDTO(x);					
			musicList.add(dto);
		});
		
		return musicList;
	} 
	
	public List<MusicDTO> findRadomAlreadyHeardPlaylist(){
		List<MusicDTO> musicList = new ArrayList<MusicDTO>();
		
		Stream<Music> result = this.musicRepository.findRandomAlreadyHeardList();
		
		result.forEach(x -> {
			MusicDTO dto = this.toDTO(x);					
			musicList.add(dto);
		});
		
		return musicList;
	} 
		
}
