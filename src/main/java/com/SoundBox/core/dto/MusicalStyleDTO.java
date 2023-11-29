package com.SoundBox.core.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MusicalStyleDTO {
	private int id;
	private MusicDTO music;
	private StyleDTO style;	
}
