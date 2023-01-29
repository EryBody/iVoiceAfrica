package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.ivoiceafrica.ivoiceafrica.entity.DeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;

public interface DeliveryAttachmentService {

	public List<DeliveryAttachment> findAll();

	public Optional<DeliveryAttachment> findById(String theId);

	public void save(DeliveryAttachment theDeliveryAttachment);

	public void deleteById(String theId);
	
	List<DeliveryAttachment> findDeliveryAttachmentByWorkOrderDelivery(WorkOrdersDelivery workOrderDelivery);
	

}
