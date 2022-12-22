package com.ivoiceafrica.ivoiceafrica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;

@Entity
@Table(name = "proposals")
public class Proposal {

	@Id
	@GenericGenerator(name = "proposal_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.ProposalGenerator")
	@GeneratedValue(generator = "proposal_id", strategy = GenerationType.SEQUENCE)
	@Column(name = "proposal_id")
	private String proposalId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "work_id")
    private WorkOrder workOrder;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "proposal_status_id")
    private ProposalStatus proposalStatus;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "modified_date")
	private String modifiedDate;
	
	@Column(name = "created_date")
	private String createdDate;
	
	public Proposal() {
		
	}

	public Proposal(String proposalId, WorkOrder workOrder, User user, ProposalStatus proposalStatus, double amount,
			String modifiedDate, String createdDate) {
		this.proposalId = proposalId;
		this.workOrder = workOrder;
		this.user = user;
		this.proposalStatus = proposalStatus;
		this.amount = amount;
		this.modifiedDate = modifiedDate;
		this.createdDate = createdDate;
	}

	public String getProposalId() {
		return proposalId;
	}

	public void setProposalId(String proposalId) {
		this.proposalId = proposalId;
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

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Proposal [proposalId=" + proposalId + ", workOrder=" + workOrder + ", user=" + user
				+ ", proposalStatus=" + proposalStatus + ", amount=" + amount + ", modifiedDate=" + modifiedDate
				+ ", createdDate=" + createdDate + "]";
	}

	
	
}
