package com.SoundBox.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SoundBox.core.model.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
	
}
