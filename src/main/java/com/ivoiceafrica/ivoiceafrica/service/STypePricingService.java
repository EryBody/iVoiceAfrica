package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.entity.PricingType;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceTypePricing;


public interface STypePricingService {

	public List<ServiceTypePricing> findAll();

	public Optional<ServiceTypePricing> findById(String theId);

	public void save(ServiceTypePricing theIndustry);

	public void deleteById(String theId);
	
	Optional<ServiceTypePricing> findServiceTypePricingByServiceType(ServiceType serviceType);
	
	Optional<ServiceTypePricing> findServiceTypePricingByPricingType(PricingType pricingType);
	
	List<ServiceTypePricing> findServiceTypePricingListByServiceType(ServiceType serviceType);
}
