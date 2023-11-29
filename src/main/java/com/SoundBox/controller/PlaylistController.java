package com.SoundBox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SoundBox.business.AbstractService;
import com.SoundBox.business.MusicPlaylistService;
import com.SoundBox.business.MusicService;
import com.SoundBox.business.PlaylistService;
import com.SoundBox.core.dto.ListStyleToGetMusicNeverAlreadyHeardDTO;
import com.SoundBox.core.dto.MusicDTO;
import com.SoundBox.core.dto.MusicPlaylistDTO;
import com.SoundBox.core.dto.MusicPlaylistToSaveDTO;
import com.SoundBox.core.dto.PlaylistDTO;
import com.SoundBox.core.dto.UserDTO;
import com.SoundBox.core.model.MusicPlaylist;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController extends AbstractController<MusicPlaylist, MusicPlaylistDTO, Integer> {

	@Autowired
	private MusicPlaylistService musicPlaylistService;
	
	@Autowired
	private PlaylistService playlistService;
	
	@Autowired
	private MusicService musicService;

	@Override
	public AbstractService<MusicPlaylist, MusicPlaylistDTO, Integer> service() {
		return musicPlaylistService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> salve(@RequestBody MusicPlaylistToSaveDTO playlist) throws Exception {	
		try {
			this.musicPlaylistService.saveNewMusic(playlist);
			
		}catch (Exception e) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_ACCEPTABLE).badRequest().body(e.getMessage());
		}
		return new ResponseEntity<UserDTO>(HttpStatus.CREATED);		
	}	
	
	@PutMapping("/update/name")
	public ResponseEntity<?> updateName(@RequestBody PlaylistDTO playlist) throws Exception {	
		try {
			this.playlistService.update(playlist);
			
		}catch (Exception e) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_ACCEPTABLE).badRequest().body(e.getMessage());
		}
		return new ResponseEntity<UserDTO>(HttpStatus.CREATED);		
	}	
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody MusicPlaylistToSaveDTO playlist) {		
		this.musicPlaylistService.deleteByIdMusicAndIdPlaylist(playlist);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);	
	}
	
	// pegar listagens 
	
	@GetMapping("/get/music")
	public ResponseEntity<List<MusicDTO>> getMusic(@PathVariable Integer id) {
		// mudar para pegar de acordo com id do usuário e trazer tbm a playlist
		List<MusicDTO> list = this.musicService.findByIdPlaylist(id);
		return new ResponseEntity<List<MusicDTO>>(list,  HttpStatus.OK);
	}
		
	@GetMapping("/get/MusicNeverAlreadyHeard")
	public ResponseEntity<List<MusicDTO>> GetMusicNeverAlreadyHeardByStyle(@RequestBody ListStyleToGetMusicNeverAlreadyHeardDTO list) {		
		List<MusicDTO> listMusic = this.musicService.findNeverHeardSongsByStyle(list.getStylesId());
		return new ResponseEntity<List<MusicDTO>>(listMusic,  HttpStatus.OK);
	}
	
	@GetMapping("/find/music")
	public ResponseEntity<List<MusicDTO>> findMusicByName(@PathVariable String name) {
		List<MusicDTO> list = this.musicService.findMusicByName(name);
		return new ResponseEntity<List<MusicDTO>>(list,  HttpStatus.OK);
	}
	
	@GetMapping("/get/MusicAlreadyHeardRadom")
	public ResponseEntity<List<MusicDTO>> GetMusicAlreadyHeardRadom(@RequestBody ListStyleToGetMusicNeverAlreadyHeardDTO list) {		
		List<MusicDTO> listMusic = this.musicService.findRadomAlreadyHeardPlaylist();
		return new ResponseEntity<List<MusicDTO>>(listMusic,  HttpStatus.OK);
	}
	
	@GetMapping("/get/MusicRadom")
	public ResponseEntity<List<MusicDTO>> GetMusicRadom(@RequestBody ListStyleToGetMusicNeverAlreadyHeardDTO list) {		
		List<MusicDTO> listMusic = this.musicService.findRadomPlaylist();
		return new ResponseEntity<List<MusicDTO>>(listMusic,  HttpStatus.OK);
	}
}
