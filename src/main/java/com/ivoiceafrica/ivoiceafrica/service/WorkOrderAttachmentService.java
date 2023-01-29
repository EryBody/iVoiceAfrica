package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderAttachment;


public interface WorkOrderAttachmentService {

	
	public List<WorkOrderAttachment> findAll();

	public Optional<WorkOrderAttachment> findById(String theId);

	public void save(WorkOrderAttachment theAttached);

	public void deleteById(String theId);
	
	List<WorkOrderAttachment> findWorkOrderAttachmentByWorkOrder(WorkOrder workOrder);
	
	Optional<WorkOrderAttachment> findFirstWorkOrderAttachmentByWorkOrder(WorkOrder workOrder);
	
	List<WorkOrderAttachment> findWorkOrderAttachmentByWorkOrderWithLimit(String workOrderId, int limitOfAttachment);
	
	public int updateJobCounts(int wordCount,int pageCount,String workDeliveryId);

	
}
