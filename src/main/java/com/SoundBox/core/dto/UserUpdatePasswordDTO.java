package com.SoundBox.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdatePasswordDTO {
	private Integer id;
	private String password;
	private String newPassword;
}
