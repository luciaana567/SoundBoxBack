package com.SoundBox.core.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDTO {
	private int id;
	private String name;
	private UserDTO user;
}
