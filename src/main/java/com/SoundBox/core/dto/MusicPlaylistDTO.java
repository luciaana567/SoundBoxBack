package com.SoundBox.core.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicPlaylistDTO {
	private int id;
	private MusicDTO music;
	private PlaylistDTO playList;	
}
