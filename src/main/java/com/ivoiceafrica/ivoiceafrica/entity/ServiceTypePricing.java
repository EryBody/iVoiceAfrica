package com.ivoiceafrica.ivoiceafrica.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "service_type_pricing")
public class ServiceTypePricing {

	@Id
    @GenericGenerator(name = "pricing_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.ServiceTypePricingGenerator")
    @GeneratedValue(generator = "pricing_id", strategy = GenerationType.SEQUENCE)  
    @Column(name="pricing_id")
	private String pricingId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "pricing_type_id")
    private PricingType pricingType;
	

	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "type_id")
    private ServiceType serviceType;
	
	@OneToMany(mappedBy = "serviceTypePricing",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<FreelancerServicePricing> freelancerServicePricings;
	
	public ServiceTypePricing() {
		
	}
	
	public ServiceTypePricing(String pricingId, PricingType pricingType, ServiceType serviceType,
			Set<FreelancerServicePricing> freelancerServicePricings) {
		this.pricingId = pricingId;
		this.pricingType = pricingType;
		this.serviceType = serviceType;
		this.freelancerServicePricings = freelancerServicePricings;
	}



	public String getPricingId() {
		return pricingId;
	}

	public void setPricingId(String pricingId) {
		this.pricingId = pricingId;
	}

	public PricingType getPricingType() {
		return pricingType;
	}

	public void setPricingType(PricingType pricingType) {
		this.pricingType = pricingType;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public Set<FreelancerServicePricing> getFreelancerServicePricings() {
		return freelancerServicePricings;
	}

	public void setFreelancerServicePricings(Set<FreelancerServicePricing> freelancerServicePricings) {
		this.freelancerServicePricings = freelancerServicePricings;
	}
	
	
}
