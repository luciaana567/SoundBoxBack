package com.SoundBox.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SoundBox.core.model.AlreadyHeard;

public interface AlreadyHeardRepository extends JpaRepository<AlreadyHeard, Integer> {
	
	@Query(value = "SELECT * FROM already_heard a WHERE a.music_id =:idMusic AND a.user_id =:idUser ", nativeQuery = true)
	public AlreadyHeard findByIdMusicAndIdUser(@Param("idMusic") Integer idMusic, @Param("idUser") Integer idUser);
}
