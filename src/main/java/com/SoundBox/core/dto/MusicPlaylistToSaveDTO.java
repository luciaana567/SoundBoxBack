package com.SoundBox.core.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicPlaylistToSaveDTO {
	private int id;
	private int musicId;
	private int playListId;	
}
