package com.SoundBox.core.dto;

import java.util.List;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListStyleToGetMusicNeverAlreadyHeardDTO {
	private List<Integer> stylesId;
}
