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
@Table(name = "work_payments_status")
public class WorkPaymentStatus {

	@Id
	@GeneratedValue(generator = "payment_status_id", strategy = GenerationType.AUTO)
	@Column(name = "payment_status_id")
	private Integer paymentStatusId;

	@Column(name = "status")
	private String status;

	@OneToMany(mappedBy = "paymentStatus", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JsonIgnore
	private Set<WorkPayments> workPayments;

	@OneToMany(mappedBy = "paymentStatus", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JsonIgnore
	private Set<WorkFreelancerPayment> freelancerPayments;

	public WorkPaymentStatus() {

	}

	public WorkPaymentStatus(Integer paymentStatusId, String status, Set<WorkPayments> workPayments,
			Set<WorkFreelancerPayment> freelancerPayments) {
		this.paymentStatusId = paymentStatusId;
		this.status = status;
		this.workPayments = workPayments;
		this.freelancerPayments = freelancerPayments;
	}

	public Integer getPaymentStatusId() {
		return paymentStatusId;
	}

	public void setPaymentStatusId(Integer paymentStatusId) {
		this.paymentStatusId = paymentStatusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<WorkPayments> getWorkPayments() {
		return workPayments;
	}

	public void setWorkPayments(Set<WorkPayments> workPayments) {
		this.workPayments = workPayments;
	}

	public Set<WorkFreelancerPayment> getFreelancerPayments() {
		return freelancerPayments;
	}

	public void setFreelancerPayments(Set<WorkFreelancerPayment> freelancerPayments) {
		this.freelancerPayments = freelancerPayments;
	}

	public WorkPaymentStatus(Integer paymentStatusId, String status) {
		this.paymentStatusId = paymentStatusId;
		this.status = status;
	}

}
