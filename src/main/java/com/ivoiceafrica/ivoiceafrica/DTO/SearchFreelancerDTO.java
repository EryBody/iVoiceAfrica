package com.ivoiceafrica.ivoiceafrica.DTO;

public class SearchFreelancerDTO {

	private String username;
	private String workId;
	private String workOrderStatus;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getWorkOrderStatus() {
		return workOrderStatus;
	}

	public void setWorkOrderStatus(String workOrderStatus) {
		this.workOrderStatus = workOrderStatus;
	}

	@Override
	public String toString() {
		return "SearchFreelancerDTO [username=" + username + ", workId=" + workId + ", workOrderStatus="
				+ workOrderStatus + "]";
	}

}
