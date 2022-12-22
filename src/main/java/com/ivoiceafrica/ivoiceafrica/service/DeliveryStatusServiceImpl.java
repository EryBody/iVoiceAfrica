package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.DeliveryStatus;
import com.ivoiceafrica.ivoiceafrica.repository.DeliveryStatusRepository;

@Service
public class DeliveryStatusServiceImpl implements DeliveryStatusService {

	@Autowired
	DeliveryStatusRepository deliveryStatusRepository;

	@Override
	public List<DeliveryStatus> findAll() {
		return deliveryStatusRepository.findAll();
	}

	@Override
	public Optional<DeliveryStatus> findById(int theId) {

		Optional<DeliveryStatus> result = deliveryStatusRepository.findById(theId);

		if (result.isPresent()) {
			return result;
		} else {
			throw new RuntimeException("Did not find Record - " + theId);
		}
	}

	@Override
	public void deleteById(int theId) {
		deliveryStatusRepository.deleteById(theId);

	}

	@Override
	public void save(DeliveryStatus workDelivery) {
		deliveryStatusRepository.save(workDelivery);
	}
}
