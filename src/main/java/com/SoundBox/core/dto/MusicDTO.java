package com.SoundBox.core.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicDTO {
	private int id;
	private String name;
	private String artistName;
	private String albumName;
	private String description;
	private String idImgArtist;
	private String idImgAlbum;
	private String idImgMusic;
    private String idTrack;
	private Boolean musicAlreadyHeard;
	private Boolean inPlaylist;
}
