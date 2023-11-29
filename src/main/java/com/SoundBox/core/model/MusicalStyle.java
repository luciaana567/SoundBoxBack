package com.SoundBox.core.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Musical_Style", schema = "db_sound_box")
public class MusicalStyle extends BaseModel<Integer> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1837740209223773663L;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "style_id")
    private Style style;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "music_id")
    private Music music; 
}