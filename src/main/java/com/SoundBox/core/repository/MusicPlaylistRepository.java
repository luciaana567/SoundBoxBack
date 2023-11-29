package com.SoundBox.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SoundBox.core.model.MusicPlaylist;

public interface MusicPlaylistRepository extends JpaRepository<MusicPlaylist, Integer> {
	
	MusicPlaylist removeByMusicIdAndPlaylistId(Integer musicId, Integer playlistId);
}
