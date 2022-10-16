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
@Table(name = "delivery_status")
public class DeliveryStatus {

	@Id
    @GeneratedValue(generator = "delivery_status_id", strategy = GenerationType.AUTO)  
    @Column(name="delivery_status_id")
	private Integer deliveryStatusId;
	
	@Column(name="status")
	private String status;
	
	@OneToMany(mappedBy = "deliveryStatus",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<WorkOrdersDelivery> workOrdersDeliveries;
	
	public DeliveryStatus() {
		
	}

	public DeliveryStatus(Integer deliveryStatusId, String status) {
		this.deliveryStatusId = deliveryStatusId;
		this.status = status;
	}

	public Integer getDeliveryStatusId() {
		return deliveryStatusId;
	}

	public void setDeliveryStatusId(Integer deliveryStatusId) {
		this.deliveryStatusId = deliveryStatusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
