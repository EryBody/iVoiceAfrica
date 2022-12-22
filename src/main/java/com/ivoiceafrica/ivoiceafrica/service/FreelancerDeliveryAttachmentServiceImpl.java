package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryStatus;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerDeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerServicePricing;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;
import com.ivoiceafrica.ivoiceafrica.repository.FreelancerDeliveryAttachmentRepository;
import com.ivoiceafrica.ivoiceafrica.repository.FreelancerServicePricingRepository;

@Service
public class FreelancerDeliveryAttachmentServiceImpl implements FreelancerDeliveryAttachmentService {

	@Autowired
	FreelancerDeliveryAttachmentRepository attachmentRepository;

	@Override
	public List<FreelancerDeliveryAttachment> findAll() {
		return attachmentRepository.findAll();
	}

	@Override
	public void save(FreelancerDeliveryAttachment theFile) {
		attachmentRepository.save(theFile);
	}

	@Override
	public void deleteById(String theId) {
		attachmentRepository.deleteById(theId);
	}

	@Override
	public Optional<FreelancerDeliveryAttachment> findById(String theId) {

		Optional<FreelancerDeliveryAttachment> result = attachmentRepository.findById(theId);

		if (result.isPresent()) {
			return result;
		} else {
			// we didn't find the Role
			throw new RuntimeException("Did not find Record id - " + theId);
		}
	}


}
