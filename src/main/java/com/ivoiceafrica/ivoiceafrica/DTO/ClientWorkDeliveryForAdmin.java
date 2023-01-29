package com.ivoiceafrica.ivoiceafrica.DTO;

public class ClientWorkDeliveryForAdmin {

	private int activeDelivery;
	private int completedDelivery;
	private int finishedDelivery;
	private int clientProposals;

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

	public int getClientProposals() {
		return clientProposals;
	}

	public void setClientProposals(int clientProposals) {
		this.clientProposals = clientProposals;
	}

}
