package com.ivoiceafrica.ivoiceafrica.DTO;

public class ClientAmountDTO {

	private String workOrderID;
	private String userID;
	private double clientAmount;
	private double totalMinAmount;
	private double totalMaxAmount;
	private String proposalId;
	

	public String getWorkOrderID() {
		return workOrderID;
	}

	public void setWorkOrderID(String workOrderID) {
		this.workOrderID = workOrderID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public double getClientAmount() {
		return clientAmount;
	}

	public void setClientAmount(double clientAmount) {
		this.clientAmount = clientAmount;
	}

	public double getTotalMinAmount() {
		return totalMinAmount;
	}

	public void setTotalMinAmount(double totalMinAmount) {
		this.totalMinAmount = totalMinAmount;
	}

	public double getTotalMaxAmount() {
		return totalMaxAmount;
	}

	public void setTotalMaxAmount(double totalMaxAmount) {
		this.totalMaxAmount = totalMaxAmount;
	}

	public String getProposalId() {
		return proposalId;
	}

	public void setProposalId(String proposalId) {
		this.proposalId = proposalId;
	}

	@Override
	public String toString() {
		return "ClientAmountDTO [workOrderID=" + workOrderID + ", userID=" + userID + ", clientAmount=" + clientAmount
				+ ", totalMinAmount=" + totalMinAmount + ", totalMaxAmount=" + totalMaxAmount + ", proposalId="
				+ proposalId + "]";
	}

	

}
