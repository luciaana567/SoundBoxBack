package com.SoundBox.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Style", schema = "db_sound_box")
public class Style extends BaseModel<Integer> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1837740209223773663L;
	
	@Column(name = "name")
    private String name;   
}