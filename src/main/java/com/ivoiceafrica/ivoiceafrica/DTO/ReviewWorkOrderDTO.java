package com.ivoiceafrica.ivoiceafrica.DTO;

public class ReviewWorkOrderDTO {

	private String deliveryId;
	private String workOrderId;
	private int userId;
	private String review;

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "ReviewWorkOrderDTO [deliveryId=" + deliveryId + ", workOrderId=" + workOrderId + ", userId=" + userId
				+ ", review=" + review + "]";
	}

}
