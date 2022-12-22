package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderAttachment;


public interface WorkOrderAttachmentRepository extends JpaRepository<WorkOrderAttachment, String>  {

	List<WorkOrderAttachment> findWorkOrderAttachmentByWorkOrder(WorkOrder workOrder);
	
	Optional<WorkOrderAttachment> findFirstWorkOrderAttachmentByWorkOrder(WorkOrder workOrder);
	
	
	@Query(value = "select * from wo_attachments w where w.work_id = :workOrderId LIMIT :limitOfAttachment", nativeQuery = true)
	List<WorkOrderAttachment> findWorkOrderAttachmentByWorkOrderWithLimit(@Param("workOrderId")String workOrderId, @Param("limitOfAttachment")int limitOfAttachment);
	
}
