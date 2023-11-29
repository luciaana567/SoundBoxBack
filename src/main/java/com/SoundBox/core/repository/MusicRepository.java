package com.SoundBox.core.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SoundBox.core.model.Music;

public interface MusicRepository extends JpaRepository<Music, Integer> {
	
	@Query("select m from Music m where lower(m.name) like lower(concat('%', :nameToFind,'%')) Or lower(m.artistName) like lower(concat('%', :nameToFind,'%'))")
	public Stream<Music> findByNameFree(@Param("nameToFind") String name);
	
	@Query("select m from MusicalStyle ms Inner Join ms.music m WHERE ms.style.id IN :codStyle AND m.musicAlreadyHeard = false")
	public Stream<Music> findByStyleAndNeverAlreadyHeard(@Param("codStyle") List<Integer> codStyle);
	
	@Query("select m from MusicPlaylist mp Inner Join mp.music m WHERE mp.playlist.id = :idPlaylist")
	public Stream<Music> findByPlaylistId(@Param("idPlaylist") Integer idPlaylist);
	
	@Query(value = "select * from Music m ORDER BY RAND() LIMIT 6", nativeQuery = true)
	public Stream<Music> findRandom();
	
	@Query(value="select * from Music m WHERE m.musicAlreadyHeard = true ORDER BY RAND() LIMIT 6", nativeQuery = true)
	public Stream<Music> findRandomAlreadyHeardList();
}
