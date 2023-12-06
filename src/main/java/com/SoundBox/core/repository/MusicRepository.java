package com.SoundBox.core.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SoundBox.core.model.Music;

public interface MusicRepository extends JpaRepository<Music, Integer> {
	
	@Query(value = "select m.* from music m where lower(m.name) like lower(concat('', :nameToFind,'%')) Or lower(m.artist_name) like lower(concat('', :nameToFind ,'%'))", nativeQuery = true)
	public Stream<Music> findByNameFree(@Param("nameToFind") String name);
	
	@Query(value="select m.* from musical_style ms Inner Join music m on m.id =ms.music_id WHERE ms.style_id in(:codStyle) AND m.id NOT IN (SELECT a.music_id FROM already_heard a WHERE a.user_id =:idUser) ORDER BY RAND() LIMIT 10", nativeQuery = true)
	public Stream<Music> findByStyleAndNeverAlreadyHeard(@Param("codStyle") List<Integer> codStyle,  @Param("idUser") Integer idUser);
	
	@Query(value="select m.* from music_playlist mp Inner Join music m on m.id = mp.music_id Inner Join playlist p on p.id = mp.playlist_id  WHERE p.user_id = :idUser", nativeQuery = true)
	public Stream<Music> findByPlaylistId( @Param("idUser") Integer idUser);
	
	@Query(value = "select * from music m ORDER BY RAND() LIMIT 10", nativeQuery = true)
	public Stream<Music> findRandom();
	
	@Query(value="select * from music m INNER JOIN already_heard a on a.music_id = m.id WHERE a.user_id =:idUser ORDER BY RAND() LIMIT 10", nativeQuery = true)
	public Stream<Music> findRandomAlreadyHeardList( @Param("idUser") Integer idUser);
}
