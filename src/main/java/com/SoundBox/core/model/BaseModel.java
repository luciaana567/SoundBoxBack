package com.SoundBox.core.model;

import java.io.Serializable;
import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseModel<ID extends Serializable> implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique=true, updatable = false, nullable = false)
	protected ID id;
	
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	@Column(name="created_at")
	protected Timestamp createdAt;
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name="update_at")
	protected Timestamp updateAt;
	
	public Timestamp getUpdateAt() {
		return updateAt;
	}

	public void seUpdateAt(Timestamp updateAt) {
		this.updateAt = updateAt;
	}
	
	@Override
	public boolean equals(Object obj) {
		BaseModel<?> anotherObj = (BaseModel<?>) obj;
		
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass() || !id.equals(anotherObj.id) || (id == null && anotherObj.id != null))
			return false;	
		
		return true;
	}
}
