package com.SoundBox.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.SoundBox.core.enums.GenderEnum;
import lombok.*;

@Entity(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "person", schema = "db_sound_box")
public class Person extends BaseModel<Integer> implements Serializable{

	/**
     
*/
  private static final long serialVersionUID = -4557524921115786948L;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    
    @Column(name = "country_id")
    private Integer country;

}
