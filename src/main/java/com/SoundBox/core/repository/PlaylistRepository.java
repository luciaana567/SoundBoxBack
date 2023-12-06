package com.SoundBox.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SoundBox.core.model.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
	
	@Query(value = "select * from playlist p where p.user_id = :userId", nativeQuery = true)
	public Playlist findByUserId(@Param("userId") Integer id);
}
