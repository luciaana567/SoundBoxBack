package com.SoundBox.core.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "playlist")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "playlist", schema = "db_sound_box")
public class Playlist extends BaseModel<Integer> implements Serializable{
	
	private static final long serialVersionUID = 8112609118514535159L;	

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


}
