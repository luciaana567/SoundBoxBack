package com.SoundBox.core.model;

import java.io.Serializable;
import java.util.Date;

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
	
	@Column(name="created_at", updatable = false)
	public Date createdAt;
	
	@Column(name="update_at")
	public Date updateAt;
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getUpdateAt() {
		return updateAt;
	}
	
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
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
