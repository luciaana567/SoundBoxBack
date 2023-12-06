package com.SoundBox.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SoundBox.core.model.MusicPlaylist;

public interface MusicPlaylistRepository extends JpaRepository<MusicPlaylist, Integer> {
	
	@Modifying
	@Query(value="delete m from music_playlist m inner join playlist p on p.id = m.playlist_id where m.music_id =:musicId and p.user_id =:userId", nativeQuery = true)
	void removeByMusicIdAndUserId(@Param("musicId") Integer musicId, @Param("userId")  Integer userId);
	
	
	@Query(value="select * from music_playlist m inner join playlist p on p.id = m.playlist_id where m.music_id =:musicId and p.user_id =:userId", nativeQuery = true)
	MusicPlaylist findByMusicIdAndUserId(@Param("musicId") Integer musicId, @Param("userId")  Integer userId);
}
