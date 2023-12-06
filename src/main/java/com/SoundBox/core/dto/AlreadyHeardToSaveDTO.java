package com.SoundBox.core.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlreadyHeardToSaveDTO {
	private Integer id;
	private Integer musicId;
	private Integer userId;	
}
