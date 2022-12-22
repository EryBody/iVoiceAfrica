package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.PricingType;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceTypePricing;
import com.ivoiceafrica.ivoiceafrica.repository.ServiceTypePricingRepository;

@Service
public class STypePricingServiceImpl implements STypePricingService {

	@Autowired
	ServiceTypePricingRepository serviceTypePricingRepository;

	@Override
	public List<ServiceTypePricing> findAll() {
		return serviceTypePricingRepository.findAll();
	}

	@Override
	public void save(ServiceTypePricing thePricing) {
		serviceTypePricingRepository.save(thePricing);
	}

	@Override
	public void deleteById(String theId) {
		serviceTypePricingRepository.deleteById(theId);
	}

	@Override
	public Optional<ServiceTypePricing> findById(String theId) {

		Optional<ServiceTypePricing> result = serviceTypePricingRepository.findById(theId);

		if (result.isPresent()) {
			return result;
		} else {
			// we didn't find the Role
			throw new RuntimeException("Did not find record id - " + theId);
		}
	}

	@Override
	public Optional<ServiceTypePricing> findServiceTypePricingByServiceType(ServiceType serviceType) {

		Optional<ServiceTypePricing> result = serviceTypePricingRepository
				.findServiceTypePricingByServiceType(serviceType);

		if (result.isPresent()) {
			return result;
		} else {
			// we didn't find the Role
			throw new RuntimeException("Did not find record id - " + serviceType);
		}
	}

	@Override
	public Optional<ServiceTypePricing> findServiceTypePricingByPricingType(PricingType pricingType) {
		Optional<ServiceTypePricing> result = serviceTypePricingRepository
				.findServiceTypePricingByPricingType(pricingType);

		if (result.isPresent()) {
			return result;
		} else {
			// we didn't find the Role
			throw new RuntimeException("Did not find record id - " + pricingType);
		}
	}

	@Override
	public List<ServiceTypePricing> findServiceTypePricingListByServiceType(ServiceType serviceType) {
		return serviceTypePricingRepository.findServiceTypePricingListByServiceType(serviceType);
	}

}
