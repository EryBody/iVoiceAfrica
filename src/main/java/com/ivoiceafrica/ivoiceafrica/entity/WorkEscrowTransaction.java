package com.ivoiceafrica.ivoiceafrica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;

@Entity
@Table(name = "work_escrow_transactions")
public class WorkEscrowTransaction {

	@Id
	@GeneratedValue(generator = "trans_id", strategy = GenerationType.AUTO)
	@Column(name = "trans_id")
	private Integer transId;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private User clientId;

	@ManyToOne
	@JoinColumn(name = "freelancer_id")
	private User freelancerId;

	@ManyToOne
	@JoinColumn(name = "work_id")
	private WorkOrder workOrder;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "currency")
	private String currency;

	@Column(name = "isReleased")
	private Boolean isReleased;

	@Column(name = "released_date")
	private String releasedDate;

	@Column(name = "entry_date")
	private String entryDate;

	public WorkEscrowTransaction() {

	}

	public WorkEscrowTransaction(Integer transId, User clientId, User freelancerId, WorkOrder workOrder, Double amount,
			String currency, Boolean isReleased, String releasedDate, String entryDate) {
		this.transId = transId;
		this.clientId = clientId;
		this.freelancerId = freelancerId;
		this.workOrder = workOrder;
		this.amount = amount;
		this.currency = currency;
		this.isReleased = isReleased;
		this.releasedDate = releasedDate;
		this.entryDate = entryDate;
	}

	public Integer getTransId() {
		return transId;
	}

	public void setTransId(Integer transId) {
		this.transId = transId;
	}

	public User getClientId() {
		return clientId;
	}

	public void setClientId(User clientId) {
		this.clientId = clientId;
	}

	public User getFreelancerId() {
		return freelancerId;
	}

	public void setFreelancerId(User freelancerId) {
		this.freelancerId = freelancerId;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Boolean getIsReleased() {
		return isReleased;
	}

	public void setIsReleased(Boolean isReleased) {
		this.isReleased = isReleased;
	}

	public String getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(String releasedDate) {
		this.releasedDate = releasedDate;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	@Override
	public String toString() {
		return "WorkEscrowTransaction [transId=" + transId + ", clientId=" + clientId + ", freelancerId=" + freelancerId
				+ ", workOrder=" + workOrder + ", amount=" + amount + ", currency=" + currency + ", isReleased="
				+ isReleased + ", releasedDate=" + releasedDate + ", entryDate=" + entryDate + "]";
	}

}
