package com.ivoiceafrica.ivoiceafrica.DTO;

public class FreelancerAcceptanceDTO {

	private String userId;
	private double freelancerAmount;
	private String workOrderId;
	private String proposalId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getFreelancerAmount() {
		return freelancerAmount;
	}

	public void setFreelancerAmount(double freelancerAmount) {
		this.freelancerAmount = freelancerAmount;
	}

	public String getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getProposalId() {
		return proposalId;
	}

	public void setProposalId(String proposalId) {
		this.proposalId = proposalId;
	}

	@Override
	public String toString() {
		return "FreelancerAcceptanceDTO [userId=" + userId + ", freelancerAmount=" + freelancerAmount + ", workOrderId="
				+ workOrderId + ", proposalId=" + proposalId + "]";
	}

}
