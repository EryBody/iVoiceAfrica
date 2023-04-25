package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerServicePricing;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceTypePricing;


public interface FreelancerServicePricingRepository extends JpaRepository<FreelancerServicePricing, String> {

	Optional<FreelancerServicePricing> findFreelancerServicePricingByServiceTypePricing(ServiceTypePricing serviceTypePricing);
	
	Optional<FreelancerServicePricing> findFreelancerServicePricingByServiceTypePricingAndUser(ServiceTypePricing serviceTypePricing, User user);
	
	Optional<FreelancerServicePricing> findFirstFreelancerServicePricingByServiceTypePricingAndUser(ServiceTypePricing serviceTypePricing, User user);
	
	List<FreelancerServicePricing> findFreelancerServicePricingListByServiceTypePricingAndUser(ServiceTypePricing serviceTypePricing, User user);

}
