package com.ivoiceafrica.ivoiceafrica.models;

public class FreelancerPriceModel {

	private double minPrice;
	private double maxPrice;
	
	public FreelancerPriceModel() {
		
	}
	
	public FreelancerPriceModel(double minPrice, double maxPrice) {
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	
	
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	@Override
	public String toString() {
		return "FreelancerPriceModel [minPrice=" + minPrice + ", maxPrice=" + maxPrice + "]";
	}
	
	
	
}
