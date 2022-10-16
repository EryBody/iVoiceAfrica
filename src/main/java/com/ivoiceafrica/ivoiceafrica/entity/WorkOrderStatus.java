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
@Table(name = "wo_status_type")
public class WorkOrderStatus {

	@Id
    @GeneratedValue(generator = "wo_status_id", strategy = GenerationType.AUTO)  
    @Column(name="wo_status_id")
	private Integer woStatusId;
	
	@Column(name="status")
	private String status;
	
	@OneToMany(mappedBy = "workOrderStatus",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<WorkOrder> workOrders;
	
	
	public WorkOrderStatus() {
		
	}


	public WorkOrderStatus(Integer woStatusId, String status, Set<WorkOrder> workOrders) {
		this.woStatusId = woStatusId;
		this.status = status;
		this.workOrders = workOrders;
	}


	public Integer getWoStatusId() {
		return woStatusId;
	}


	public void setWoStatusId(Integer woStatusId) {
		this.woStatusId = woStatusId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Set<WorkOrder> getWorkOrders() {
		return workOrders;
	}


	public void setWorkOrders(Set<WorkOrder> workOrders) {
		this.workOrders = workOrders;
	}
	
}
