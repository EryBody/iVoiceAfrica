package com.ivoiceafrica.ivoiceafrica.DTO;

public class FreelancerOfferDeclineDTO {

	private String workOrderId;
	private String userId;
	private String proposalId;

	public String getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProposalId() {
		return proposalId;
	}

	public void setProposalId(String proposalId) {
		this.proposalId = proposalId;
	}

	@Override
	public String toString() {
		return "FreelancerOfferDeclineDTO [workOrderId=" + workOrderId + ", userId=" + userId + ", proposalId="
				+ proposalId + "]";
	}

}
