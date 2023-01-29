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
@Table(name = "work_payments")
public class WorkPayments {

	@Id
	@GeneratedValue(generator = "trans_id", strategy = GenerationType.AUTO)
	@Column(name = "trans_id")
	private Integer transId;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private User clientId;

	@ManyToOne
	@JoinColumn(name = "work_id")
	private WorkOrder workOrder;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "currency")
	private String currency;

	@ManyToOne
	@JoinColumn(name = "payment_status_id")
	private WorkPaymentStatus paymentStatus;

	@Column(name = "payment_gateway")
	private String paymentGateway;

	@Column(name = "entry_date")
	private String entryDate;

	public WorkPayments() {

	}

	public WorkPayments(Integer transId, User clientId, WorkOrder workOrder, Double amount, String currency,
			WorkPaymentStatus paymentStatus, String paymentGateway, String entryDate) {
		this.transId = transId;
		this.clientId = clientId;
		this.workOrder = workOrder;
		this.amount = amount;
		this.currency = currency;
		this.paymentStatus = paymentStatus;
		this.paymentGateway = paymentGateway;
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

	public WorkPaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(WorkPaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentGateway() {
		return paymentGateway;
	}

	public void setPaymentGateway(String paymentGateway) {
		this.paymentGateway = paymentGateway;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	@Override
	public String toString() {
		return "WorkPayments [transId=" + transId + ", clientId=" + clientId + ", workOrder=" + workOrder + ", amount="
				+ amount + ", currency=" + currency + ", paymentStatus=" + paymentStatus + ", paymentGateway="
				+ paymentGateway + ", entryDate=" + entryDate + "]";
	}

}
