package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.entity.DeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;

public interface DeliveryAttachmentRepository extends JpaRepository<DeliveryAttachment, String>  {

	List<DeliveryAttachment> findDeliveryAttachmentByWorkOrderDelivery(WorkOrdersDelivery workOrderDelivery);
}
