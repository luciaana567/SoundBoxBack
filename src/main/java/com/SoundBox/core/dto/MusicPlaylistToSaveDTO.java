package com.SoundBox.core.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicPlaylistToSaveDTO {
	private Integer id;
	private Integer musicId;
	private Integer playListId;	
	private Integer userId;	
	
}
