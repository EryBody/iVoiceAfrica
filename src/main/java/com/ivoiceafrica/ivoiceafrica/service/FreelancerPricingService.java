package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerServicePricing;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceTypePricing;

public interface FreelancerPricingService {

	public List<FreelancerServicePricing> findAll();
	
	public Optional<FreelancerServicePricing> findById(String theId);

	public void save(FreelancerServicePricing theRole);

	public void deleteById(String theId);
	
	Optional<FreelancerServicePricing> findFreelancerServicePricingByServiceTypePricing(ServiceTypePricing serviceTypePricing);
	
	Optional<FreelancerServicePricing> findFreelancerServicePricingByServiceTypePricingAndUser(ServiceTypePricing serviceTypePricing, User user);
	
	Optional<FreelancerServicePricing> findFirstFreelancerServicePricingByServiceTypePricingAndUser(ServiceTypePricing serviceTypePricing, User user);

	List<FreelancerServicePricing> findFreelancerServicePricingListByServiceTypePricingAndUser(ServiceTypePricing serviceTypePricing, User user);

}
