package com.ivoiceafrica.ivoiceafrica.models;

public class FreelancerPricingModel {

	private String pricingType;
	private double minPrice;
	private double maxPrice;
	
	public FreelancerPricingModel() {
		
	}
	
	public FreelancerPricingModel(String pricingType, double minPrice, double maxPrice) {
		this.pricingType = pricingType;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public FreelancerPricingModel(double minPrice, double maxPrice) {
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	
	public String getPricingType() {
		return pricingType;
	}

	public void setPricingType(String pricingType) {
		this.pricingType = pricingType;
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
		return "FreelancerPricingModel [pricingType=" + pricingType + ", minPrice=" + minPrice + ", maxPrice="
				+ maxPrice + "]";
	}

}
