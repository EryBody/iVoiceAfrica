package com.ivoiceafrica.ivoiceafrica.DTO;

public class FreelancerWorkDeliveryForAdmin {

	private int activeDelivery;
	private int completedDelivery;
	private int finishedDelivery;
	private int allRequest;
	public int getActiveDelivery() {
		return activeDelivery;
	}
	public void setActiveDelivery(int activeDelivery) {
		this.activeDelivery = activeDelivery;
	}
	public int getCompletedDelivery() {
		return completedDelivery;
	}
	public void setCompletedDelivery(int completedDelivery) {
		this.completedDelivery = completedDelivery;
	}
	public int getFinishedDelivery() {
		return finishedDelivery;
	}
	public void setFinishedDelivery(int finishedDelivery) {
		this.finishedDelivery = finishedDelivery;
	}
	public int getAllRequest() {
		return allRequest;
	}
	public void setAllRequest(int allRequest) {
		this.allRequest = allRequest;
	}
	@Override
	public String toString() {
		return "FreelancerWorkDeliveryForAdmin [activeDelivery=" + activeDelivery + ", completedDelivery="
				+ completedDelivery + ", finishedDelivery=" + finishedDelivery + ", allRequest=" + allRequest + "]";
	}
	
	
}
	
