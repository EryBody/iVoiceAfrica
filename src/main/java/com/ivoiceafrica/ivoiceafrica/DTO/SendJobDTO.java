package com.ivoiceafrica.ivoiceafrica.DTO;

public class SendJobDTO {

	private String jobStatus;
	private String message;
	private String userId;
	private String workId;
	private String deliveryId;
	private String freelancerUserId;

	public SendJobDTO() {

	}

	public SendJobDTO(String jobStatus, String message, String userId, String workId, String deliveryId,
			String freelancerUserId) {
		this.jobStatus = jobStatus;
		this.message = message;
		this.userId = userId;
		this.workId = workId;
		this.deliveryId = deliveryId;
		this.freelancerUserId = freelancerUserId;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getFreelancerUserId() {
		return freelancerUserId;
	}

	public void setFreelancerUserId(String freelancerUserId) {
		this.freelancerUserId = freelancerUserId;
	}

	@Override
	public String toString() {
		return "SendJobDTO [jobStatus=" + jobStatus + ", message=" + message + ", userId=" + userId + ", workId="
				+ workId + ", deliveryId=" + deliveryId + ", freelancerUserId=" + freelancerUserId + "]";
	}

}
