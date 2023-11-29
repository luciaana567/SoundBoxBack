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
@Table(name = "Countries", schema = "db_sound_box")
public class Country extends BaseModel<Integer> implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2200000402633999682L;

	@Column(name = "name")
    private String name;

    @Column(name = "slg")
    private String slg;  
}