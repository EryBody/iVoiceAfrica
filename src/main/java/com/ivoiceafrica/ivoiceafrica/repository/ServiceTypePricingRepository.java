package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.entity.PricingType;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceTypePricing;


public interface ServiceTypePricingRepository extends JpaRepository<ServiceTypePricing, String> {

	Optional<ServiceTypePricing> findServiceTypePricingByServiceType(ServiceType serviceType);
	
	Optional<ServiceTypePricing> findServiceTypePricingByPricingType(PricingType pricingType);
	
	List<ServiceTypePricing> findServiceTypePricingListByServiceType(ServiceType serviceType);
}
