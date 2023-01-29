package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;


import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryStatus;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerDeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;

public interface FreelancerDeliveryAttachmentService {

	public List<FreelancerDeliveryAttachment> findAll();

	public Optional<FreelancerDeliveryAttachment> findById(String theId);

	public void save(FreelancerDeliveryAttachment theWorkDelivery);

	public void deleteById(String theId);
	
	List<FreelancerDeliveryAttachment> findFreelancerDeliveryAttachmentByDeliveryAttachmentOrderByEntryDateDesc(DeliveryAttachment deliveryAttachment);
	
	List<FreelancerDeliveryAttachment> findFreelancerDeliveryAttachmentByWorkOrderDeliveryOrderByEntryDateDesc(WorkOrdersDelivery workOrderDelivery);
	
}
