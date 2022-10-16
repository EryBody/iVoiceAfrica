package com.ivoiceafrica.ivoiceafrica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;

@Entity
@Table(name = "freelancer_service_pricing")
public class FreelancerServicePricing {

	@Id
    @GenericGenerator(name = "fpricing_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.FreelancerServicePricingGenerator")
    @GeneratedValue(generator = "fpricing_id", strategy = GenerationType.SEQUENCE)  
    @Column(name="fpricing_id")
	private String fpricingId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "pricing_id")
    private ServiceTypePricing serviceTypePricing;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "user_id")
    private User user;
	
	@JoinColumn(name = "min_price")
	private double minPrice;
	
	@JoinColumn(name = "max_price")
	private double maxPrice;
	
	

	public FreelancerServicePricing() {
	}

	public FreelancerServicePricing(String fpricingId, ServiceTypePricing serviceTypePricing, User user,
			double minPrice, double maxPrice) {
		this.fpricingId = fpricingId;
		this.serviceTypePricing = serviceTypePricing;
		this.user = user;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public String getFpricingId() {
		return fpricingId;
	}

	public void setFpricingId(String fpricingId) {
		this.fpricingId = fpricingId;
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
	
	
	
}
