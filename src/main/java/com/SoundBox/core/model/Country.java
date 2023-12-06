package com.SoundBox.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.yaml.snakeyaml.events.Event.ID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "countries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "countries", schema = "db_sound_box")
public class Country implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2200000402633999682L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", insertable = false)
	protected Integer id;	
	
	@Column(name="created_at", updatable = false)
	public Date createdAt;
	
	@Column(name="updated_at")
	public Date updateAt;
	
	@Column(name = "name")
    private String name;

    @Column(name = "slg")
    private String slg;  
}