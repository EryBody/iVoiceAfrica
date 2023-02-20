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
@Table(name = "work_transactions")
public class WorkTransactions {

	@Id
	@GeneratedValue(generator = "trans_id", strategy = GenerationType.AUTO)
	@Column(name = "trans_id")
	private Integer transId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "work_id")
	private WorkOrder workOrder;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "currency")
	private String currency;

	@Column(name = "is_in_Flow")
	private Boolean isInFlow;

	@Column(name = "entry_date")
	private String entryDate;

	public WorkTransactions() {

	}

	public WorkTransactions(Integer transId, User user, WorkOrder workOrder, Double amount, String currency,
			Boolean isInFlow, String entryDate) {
		this.transId = transId;
		this.user = user;
		this.workOrder = workOrder;
		this.amount = amount;
		this.currency = currency;
		this.isInFlow = isInFlow;
		this.entryDate = entryDate;
	}

	public Integer getTransId() {
		return transId;
	}

	public void setTransId(Integer transId) {
		this.transId = transId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Boolean getIsInFlow() {
		return isInFlow;
	}

	public void setIsInFlow(Boolean isInFlow) {
		this.isInFlow = isInFlow;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	@Override
	public String toString() {
		return "WorkTransactions [transId=" + transId + ", user=" + user + ", workOrder=" + workOrder + ", amount="
				+ amount + ", currency=" + currency + ", isInFlow=" + isInFlow + ", entryDate=" + entryDate + "]";
	}

}
