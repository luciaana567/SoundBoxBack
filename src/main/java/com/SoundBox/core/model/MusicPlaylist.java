package com.SoundBox.core.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Music_Playlist")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "music_playlist", schema = "db_sound_box")
public class MusicPlaylist extends BaseModel<Integer> implements Serializable{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1950129815640887249L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "music_id")
    private Music music;

}
