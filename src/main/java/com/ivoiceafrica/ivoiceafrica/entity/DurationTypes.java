package com.ivoiceafrica.ivoiceafrica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "duration_types")
public class DurationTypes {

	@Id
    @GeneratedValue(generator = "duration_id", strategy = GenerationType.AUTO)  
    @Column(name="duration_id")
	private Integer durationId;
	
	@Column(name="duration")
	private String duration;
	
	public DurationTypes() {
		
	}

	public DurationTypes(Integer durationId, String duration) {
		this.durationId = durationId;
		this.duration = duration;
	}

	public Integer getDurationId() {
		return durationId;
	}

	public void setDurationId(Integer durationId) {
		this.durationId = durationId;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
	
}
