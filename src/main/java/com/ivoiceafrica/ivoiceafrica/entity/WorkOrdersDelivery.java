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
	@Column(name = "delivery_id")
	private String deliveryId;

	@ManyToOne // Mapping the column of this table
	@JoinColumn(name = "work_id")
	private WorkOrder workOrder;

	@ManyToOne // Mapping the column of this table
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "amount")
	private String amount;

	@ManyToOne // Mapping the column of this table
	@JoinColumn(name = "delivery_status_id")
	private DeliveryStatus deliveryStatus;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "completed_date")
	private String completedDate;

	@OneToMany(mappedBy = "workOrderDelivery", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JsonIgnore
	private Set<DeliveryAttachment> deliveryAttachment;
	
	@OneToMany(mappedBy = "workOrderDelivery", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JsonIgnore
	private Set<FreelancerDeliveryAttachment> freelancerDeliveryAttachment;

	public WorkOrdersDelivery() {

	}

	public WorkOrdersDelivery(String deliveryId, WorkOrder workOrder, User user, String amount,
			DeliveryStatus deliveryStatus, String startDate, String endDate, String createdDate, String completedDate,
			Set<DeliveryAttachment> deliveryAttachment,
			Set<FreelancerDeliveryAttachment> freelancerDeliveryAttachment) {
		this.deliveryId = deliveryId;
		this.workOrder = workOrder;
		this.user = user;
		this.amount = amount;
		this.deliveryStatus = deliveryStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdDate = createdDate;
		this.completedDate = completedDate;
		this.deliveryAttachment = deliveryAttachment;
		this.freelancerDeliveryAttachment = freelancerDeliveryAttachment;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}

	public Set<DeliveryAttachment> getDeliveryAttachment() {
		return deliveryAttachment;
	}

	public void setDeliveryAttachment(Set<DeliveryAttachment> deliveryAttachment) {
		this.deliveryAttachment = deliveryAttachment;
	}

	public Set<FreelancerDeliveryAttachment> getFreelancerDeliveryAttachment() {
		return freelancerDeliveryAttachment;
	}

	public void setFreelancerDeliveryAttachment(Set<FreelancerDeliveryAttachment> freelancerDeliveryAttachment) {
		this.freelancerDeliveryAttachment = freelancerDeliveryAttachment;
	}

	@Override
	public String toString() {
		return "WorkOrdersDelivery [deliveryId=" + deliveryId + ", workOrder=" + workOrder + ", user=" + user
				+ ", amount=" + amount + ", deliveryStatus=" + deliveryStatus + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", createdDate=" + createdDate + ", completedDate=" + completedDate
				+ ", deliveryAttachment=" + deliveryAttachment + ", freelancerDeliveryAttachment="
				+ freelancerDeliveryAttachment + "]";
	}

}
