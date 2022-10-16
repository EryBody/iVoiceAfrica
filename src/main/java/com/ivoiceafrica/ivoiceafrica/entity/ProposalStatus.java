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
@Table(name = "proposal_status")
public class ProposalStatus {

	@Id
    @GeneratedValue(generator = "proposal_status_id", strategy = GenerationType.AUTO)  
    @Column(name="proposal_status_id")
	private Integer proposalStatusId;
	
	@Column(name="status")
	private String status;
	
	@OneToMany(mappedBy = "proposalStatus",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<Proposal> proposals;
	
	
	public ProposalStatus() {
		
	}

	public ProposalStatus(Integer proposalStatusId, String status, Set<Proposal> proposals) {
		this.proposalStatusId = proposalStatusId;
		this.status = status;
		this.proposals = proposals;
	}

	public Integer getProposalStatusId() {
		return proposalStatusId;
	}

	public void setProposalStatusId(Integer proposalStatusId) {
		this.proposalStatusId = proposalStatusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Proposal> getProposals() {
		return proposals;
	}

	public void setProposals(Set<Proposal> proposals) {
		this.proposals = proposals;
	}
	
	
	
}
