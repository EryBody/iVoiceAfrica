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
@Table(name = "work_freelancer_payments")
public class WorkFreelancerPayment {

	@Id
	@GeneratedValue(generator = "trans_id", strategy = GenerationType.AUTO)
	@Column(name = "trans_id")
	private Integer transId;

	@ManyToOne
	@JoinColumn(name = "work_id")
	private WorkOrder workOrder;

	@ManyToOne
	@JoinColumn(name = "freelancer_id")
	private User freelancerId;

	@Column(name = "amount")
	private Double amount;

	@ManyToOne
	@JoinColumn(name = "payment_status_id")
	private WorkPaymentStatus paymentStatus;

	@Column(name = "currency")
	private String currency;

	@Column(name = "payment_gateway")
	private String paymentGateway;

	@Column(name = "entry_date")
	private String entryDate;

	public WorkFreelancerPayment() {

	}

	public WorkFreelancerPayment(Integer transId, WorkOrder workOrder, User freelancerId, Double amount,
			WorkPaymentStatus paymentStatus, String currency, String paymentGateway, String entryDate) {
		this.transId = transId;
		this.workOrder = workOrder;
		this.freelancerId = freelancerId;
		this.amount = amount;
		this.paymentStatus = paymentStatus;
		this.currency = currency;
		this.paymentGateway = paymentGateway;
		this.entryDate = entryDate;
	}

	public Integer getTransId() {
		return transId;
	}

	public void setTransId(Integer transId) {
		this.transId = transId;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public User getFreelancerId() {
		return freelancerId;
	}

	public void setFreelancerId(User freelancerId) {
		this.freelancerId = freelancerId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public WorkPaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(WorkPaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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
		return "WorkFreelancerPayment [transId=" + transId + ", workOrder=" + workOrder + ", freelancerId="
				+ freelancerId + ", amount=" + amount + ", paymentStatus=" + paymentStatus + ", currency=" + currency
				+ ", paymentGateway=" + paymentGateway + ", entryDate=" + entryDate + "]";
	}

}
