package com.SoundBox.core.dto;

import java.util.Date;

import com.SoundBox.core.enums.GenderEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
	private Integer id;
	private String name;
	private GenderEnum gender;
	private CountryDTO country;
	private Date birthday;	
}
