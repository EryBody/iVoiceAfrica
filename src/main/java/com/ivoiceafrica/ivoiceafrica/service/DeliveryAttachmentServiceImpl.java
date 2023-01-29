package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.DeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;
import com.ivoiceafrica.ivoiceafrica.repository.DeliveryAttachmentRepository;

@Service
public class DeliveryAttachmentServiceImpl implements DeliveryAttachmentService {

	
	@Autowired
	DeliveryAttachmentRepository deliveryAttachmentRepository;

	@Override
	public List<DeliveryAttachment> findAll() {
		return deliveryAttachmentRepository.findAll();
	}

	@Override
	public Optional<DeliveryAttachment> findById(String theId) {

		Optional<DeliveryAttachment> result = deliveryAttachmentRepository.findById(theId);

		if (result.isPresent()) {
			return result;
		} else {
			throw new RuntimeException("Did not find Record - " + theId);
		}
	}

	@Override
	public void deleteById(String theId) {
		deliveryAttachmentRepository.deleteById(theId);

	}

	@Override
	public void save(DeliveryAttachment theDeliveryAttachment) {
		deliveryAttachmentRepository.save(theDeliveryAttachment);
	}

	@Override
	public List<DeliveryAttachment> findDeliveryAttachmentByWorkOrderDelivery(WorkOrdersDelivery workOrderDelivery) {
		return deliveryAttachmentRepository.findDeliveryAttachmentByWorkOrderDelivery(workOrderDelivery);
	}

	
}
