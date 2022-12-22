package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerServicePricing;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceTypePricing;
import com.ivoiceafrica.ivoiceafrica.repository.FreelancerServicePricingRepository;

@Service
public class FreelancerPricingServiceImpl implements FreelancerPricingService {

	@Autowired
	FreelancerServicePricingRepository pricingRepository;

	@Override
	public List<FreelancerServicePricing> findAll() {
		return pricingRepository.findAll();
	}

	@Override
	public void save(FreelancerServicePricing theService) {
		pricingRepository.save(theService);
	}

	@Override
	public void deleteById(String theId) {
		pricingRepository.deleteById(theId);
	}

	@Override
	public Optional<FreelancerServicePricing> findById(String theId) {

		Optional<FreelancerServicePricing> result = pricingRepository.findById(theId);

		if (result.isPresent()) {
			return result;
		} else {
			// we didn't find the Role
			throw new RuntimeException("Did not find Record id - " + theId);
		}
	}

	@Override
	public Optional<FreelancerServicePricing> findFreelancerServicePricingByServiceTypePricing(
			ServiceTypePricing serviceTypePricing) {
		Optional<FreelancerServicePricing> result = pricingRepository
				.findFreelancerServicePricingByServiceTypePricing(serviceTypePricing);

		if (result.isPresent()) {
			return result;
		} else {
			// we didn't find the Role
			throw new RuntimeException("Did not find Record id - " + serviceTypePricing);
		}
	}

	@Override
	public Optional<FreelancerServicePricing> findFirstFreelancerServicePricingByServiceTypePricingAndUser(ServiceTypePricing serviceTypePricing, User user) {
		
		Optional<FreelancerServicePricing> result = pricingRepository
				.findFirstFreelancerServicePricingByServiceTypePricingAndUser(serviceTypePricing , user);
		
		return result;
	}

	@Override
	public List<FreelancerServicePricing> findFreelancerServicePricingListByServiceTypePricingAndUser(
			ServiceTypePricing serviceTypePricing, User user) {
		List<FreelancerServicePricing> pricings =  findFreelancerServicePricingListByServiceTypePricingAndUser(serviceTypePricing, user);

		return pricings;
	}

}
