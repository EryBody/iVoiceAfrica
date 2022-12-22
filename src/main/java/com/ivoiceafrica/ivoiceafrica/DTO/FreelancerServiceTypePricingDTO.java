package com.ivoiceafrica.ivoiceafrica.DTO;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceTypePricing;

public class FreelancerServiceTypePricingDTO {
	
    private ServiceTypePricing serviceTypePricing;
    private User user;
	private double minPrice;
	private double maxPrice;
	
	public FreelancerServiceTypePricingDTO() {
		
	}
	
	public FreelancerServiceTypePricingDTO(ServiceTypePricing serviceTypePricing, User user, double minPrice,
			double maxPrice) {
		this.serviceTypePricing = serviceTypePricing;
		this.user = user;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public ServiceTypePricing getServiceTypePricing() {
		return serviceTypePricing;
	}

	public void setServiceTypePricing(ServiceTypePricing serviceTypePricing) {
		this.serviceTypePricing = serviceTypePricing;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		return "FreelancerServiceTypePricingDTO [serviceTypePricing=" + serviceTypePricing + ", user=" + user
				+ ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + "]";
	}
	
	
}
