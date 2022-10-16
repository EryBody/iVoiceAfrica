package com.ivoiceafrica.ivoiceafrica.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;

@Entity
@Table(name = "wo_delivery")
public class WorkOrdersDelivery {
	
	@Id
    @GenericGenerator(name = "delivery_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.WorkOrderDeliveryGenerator")
    @GeneratedValue(generator = "delivery_id", strategy = GenerationType.SEQUENCE)  
    @Column(name="delivery_id")
	private String deliveryId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "work_id")
    private WorkOrder workOrder;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "delivery_status_id")
    private DeliveryStatus deliveryStatus;
	
	@Column(name="start_date")
	private String startDate;
	
	@Column(name="end_date")
	private String endDate;
	
	@OneToMany(mappedBy = "workOrderDelivery",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<DeliveryAttachment> deliveryAttachment;
	

	public WorkOrdersDelivery() {
		
	}
	

	public WorkOrdersDelivery(String deliveryId, WorkOrder workOrder, User user, DeliveryStatus deliveryStatus,
			String startDate, String endDate, Set<DeliveryAttachment> deliveryAttachment) {
		this.deliveryId = deliveryId;
		this.workOrder = workOrder;
		this.user = user;
		this.deliveryStatus = deliveryStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deliveryAttachment = deliveryAttachment;
	}


	public String getDeliveryId() {
		return deliveryId;
	}


	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}


	public WorkOrder getWorkOrder() {
		return workOrder;
	}


	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}


	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public Set<DeliveryAttachment> getDeliveryAttachment() {
		return deliveryAttachment;
	}


	public void setDeliveryAttachment(Set<DeliveryAttachment> deliveryAttachment) {
		this.deliveryAttachment = deliveryAttachment;
	}
	
	
}


