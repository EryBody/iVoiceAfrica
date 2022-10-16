package com.ivoiceafrica.ivoiceafrica.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "duration_types")
public class DurationType {
	
	@Id
    @GeneratedValue(generator = "duration_id", strategy = GenerationType.AUTO)  
    @Column(name="duration_id")
	private Integer durationId;
	
	@Column(name="duration")
	private String duration;

	
	@OneToMany(mappedBy = "duration",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<WorkOrder> workOrders;
	
	
	public DurationType() {
		
	}

	public DurationType(Integer durationId, String duration, Set<WorkOrder> workOrders) {
		this.durationId = durationId;
		this.duration = duration;
		this.workOrders = workOrders;
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


	public Set<WorkOrder> getWorkOrders() {
		return workOrders;
	}


	public void setWorkOrders(Set<WorkOrder> workOrders) {
		this.workOrders = workOrders;
	}
	
}
