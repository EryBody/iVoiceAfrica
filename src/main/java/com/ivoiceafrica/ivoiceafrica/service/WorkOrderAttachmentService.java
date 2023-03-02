package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
	
	public int updateJobCounts(int wordCount,int pageCount, String timerCount, String workDeliveryId);
	
	public int updateWordCounts(int wordCount, String workDeliveryId);

	public int updatePageCounts(int pageCount, String workDeliveryId);
	
	public int updateTimerCounts(String timerCount, String workDeliveryId);

}
