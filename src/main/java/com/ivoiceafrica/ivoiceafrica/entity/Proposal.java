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
	
	@JoinColumn(name = "amount")
	private double amount;
	
	@JoinColumn(name = "modified_date")
	private String modifiedDate;
	
	@JoinColumn(name = "created_date")
	private String createddate;
	
	
	
	
}
