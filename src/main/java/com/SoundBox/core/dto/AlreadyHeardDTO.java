package com.SoundBox.core.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlreadyHeardDTO {
	private int id;
	private MusicDTO music;
	private UserDTO user;	
}
