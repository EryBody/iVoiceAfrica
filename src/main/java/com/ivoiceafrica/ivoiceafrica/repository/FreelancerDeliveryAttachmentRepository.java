package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.entity.DeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerDeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;


public interface FreelancerDeliveryAttachmentRepository extends JpaRepository<FreelancerDeliveryAttachment, String> {
		
	List<FreelancerDeliveryAttachment> findFreelancerDeliveryAttachmentByDeliveryAttachmentOrderByEntryDateDesc(DeliveryAttachment deliveryAttachment);
	
	List<FreelancerDeliveryAttachment> findFreelancerDeliveryAttachmentByWorkOrderDeliveryOrderByEntryDateDesc(WorkOrdersDelivery workOrderDelivery);
	
}
