package com.SoundBox.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "music")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "music", schema = "db_sound_box")
public class Music extends BaseModel<Integer> implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1561150326064433325L;

	@Column(name = "name")
    private String name;

	@Column(name = "artist_name")
    private String artistName;

	@Column(name = "album_name")
    private String albumName;

	@Column(name = "description")
    private String description;

	@Column(name = "id_img_artist")
    private String idImgArtist;

	@Column(name = "id_img_album")
    private String idImgAlbum;

	@Column(name = "id_img_music")
    private String idImgMusic;

	@Column(name = "id_track")
    private String idTrack;
 
	@Column(name="already_heard")
	private Boolean musicAlreadyHeard;

}
