package com.ivoiceafrica.ivoiceafrica.DTO;

public class FreelancerOfferDeclineDTO {

	private String workOrderId;
	private String userId;
	
	
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
	
	@Override
	public String toString() {
		return "FreelancerOfferDeclineDTO [workOrderId=" + workOrderId + ", userId=" + userId + "]";
	}
	
	
}
