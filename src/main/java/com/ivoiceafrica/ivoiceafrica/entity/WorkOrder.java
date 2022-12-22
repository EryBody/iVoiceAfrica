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
@Table(name = "work_orders")
public class WorkOrder {

	@Id
	@GenericGenerator(name = "work_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.WorkOrderGenerator")
	@GeneratedValue(generator = "work_id", strategy = GenerationType.SEQUENCE)
	@Column(name = "work_id")
	private String workId;

	@Column(name = "work_title")
	private String workTitle;

	@ManyToOne // Mapping the column of this table
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne // Mapping the column of this table
	@JoinColumn(name = "type_id")
	private ServiceType serviceType;

	@ManyToOne // Mapping the column of this table
	@JoinColumn(name = "duration_id")
	private DurationType duration;

	@ManyToOne // Mapping the column of this table
	@JoinColumn(name = "wo_status_id")
	private WorkOrderStatus workOrderStatus;

	@Column(name = "description")
	private String description;

	@Column(name = "min_amount")
	private double minAmount;

	@Column(name = "max_amount")
	private double maxAmount;

	@Column(name = "modifiedDate")
	private String modifiedDate;

	@Column(name = "postingDate")
	private String postingDate;

	@OneToMany(mappedBy = "workOrder", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JsonIgnore
	private Set<Proposal> proposals;

	@OneToMany(mappedBy = "workOrder", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JsonIgnore
	private Set<WorkOrderAttachment> workOrderAttachments;

	@OneToMany(mappedBy = "workOrder", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JsonIgnore
	private Set<WorkOrdersDelivery> workOrdersDeliveries;


	public WorkOrder() {

	}

	public WorkOrder(String workId, String workTitle, User user, ServiceType serviceType, DurationType duration,
			WorkOrderStatus workOrderStatus, String description, double minAmount, double maxAmount,
			String modifiedDate, String postingDate, Set<Proposal> proposals,
			Set<WorkOrderAttachment> workOrderAttachments, Set<WorkOrdersDelivery> workOrdersDeliveries) {
		this.workId = workId;
		this.workTitle = workTitle;
		this.user = user;
		this.serviceType = serviceType;
		this.duration = duration;
		this.workOrderStatus = workOrderStatus;
		this.description = description;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.modifiedDate = modifiedDate;
		this.postingDate = postingDate;
		this.proposals = proposals;
		this.workOrderAttachments = workOrderAttachments;
		this.workOrdersDeliveries = workOrdersDeliveries;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getWorkTitle() {
		return workTitle;
	}

	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public DurationType getDuration() {
		return duration;
	}

	public void setDuration(DurationType duration) {
		this.duration = duration;
	}

	public WorkOrderStatus getWorkOrderStatus() {
		return workOrderStatus;
	}

	public void setWorkOrderStatus(WorkOrderStatus workOrderStatus) {
		this.workOrderStatus = workOrderStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	public double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}

	public Set<Proposal> getProposals() {
		return proposals;
	}

	public void setProposals(Set<Proposal> proposals) {
		this.proposals = proposals;
	}

	public Set<WorkOrderAttachment> getWorkOrderAttachments() {
		return workOrderAttachments;
	}

	public void setWorkOrderAttachments(Set<WorkOrderAttachment> workOrderAttachments) {
		this.workOrderAttachments = workOrderAttachments;
	}

	public Set<WorkOrdersDelivery> getWorkOrdersDeliveries() {
		return workOrdersDeliveries;
	}

	public void setWorkOrdersDeliveries(Set<WorkOrdersDelivery> workOrdersDeliveries) {
		this.workOrdersDeliveries = workOrdersDeliveries;
	}

	@Override
	public String toString() {
		return "WorkOrder [workId=" + workId + ", workTitle=" + workTitle + ", description=" + description
				+ ", minAmount=" + minAmount + ", maxAmount=" + maxAmount + ", modifiedDate=" + modifiedDate
				+ ", postingDate=" + postingDate + "]";
	}

}
