package com.ivoiceafrica.ivoiceafrica.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pricing_types")
public class PricingType {

	@Id
    @GenericGenerator(name = "pricing_type_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.PricingTypeGenerator")
    @GeneratedValue(generator = "pricing_type_id", strategy = GenerationType.SEQUENCE)  
    @Column(name="pricing_type_id")
	private String pricingTypeId;
	
	@Column(name="pricing_type")
	private String pricingType;
	
	@Column(name="min_price")
	private double minPrice;
	
	@Column(name="max_price")
	private double maxPrice;
	
	@OneToMany(mappedBy = "pricingType",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<ServiceTypePricing> serviceTypePricings;
	
	
	public PricingType() {
		
	}


	public String getPricingTypeId() {
		return pricingTypeId;
	}


	public void setPricingTypeId(String pricingTypeId) {
		this.pricingTypeId = pricingTypeId;
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


	public Set<ServiceTypePricing> getServiceTypePricings() {
		return serviceTypePricings;
	}


	public void setServiceTypePricings(Set<ServiceTypePricing> serviceTypePricings) {
		this.serviceTypePricings = serviceTypePricings;
	}


	@Override
	public String toString() {
		return "PricingType [pricingTypeId=" + pricingTypeId + ", pricingType=" + pricingType + ", minPrice=" + minPrice
				+ ", maxPrice=" + maxPrice + "]";
	}

	
}
