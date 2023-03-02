package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderAttachment;


public interface WorkOrderAttachmentRepository extends JpaRepository<WorkOrderAttachment, String>  {

	List<WorkOrderAttachment> findWorkOrderAttachmentByWorkOrder(WorkOrder workOrder);
	
	Optional<WorkOrderAttachment> findFirstWorkOrderAttachmentByWorkOrder(WorkOrder workOrder);
	
	
	@Query(value = "select * from wo_attachments w where w.work_id = :workOrderId LIMIT :limitOfAttachment", nativeQuery = true)
	List<WorkOrderAttachment> findWorkOrderAttachmentByWorkOrderWithLimit(@Param("workOrderId")String workOrderId, @Param("limitOfAttachment")int limitOfAttachment);
	

	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE wo_attachments w set w.word_count = :wordCount, w.page_count = :pageCount, w.timer_count = :timerCount where w.wo_attach_id = :workDeliveryId", nativeQuery = true)
	public int updateJobCounts(@Param("wordCount")int wordCount, @Param("pageCount")int pageCount,  @Param("timerCount")String timerCount, @Param("workDeliveryId")String workDeliveryId);

	
	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE wo_attachments w set w.word_count = :wordCount where w.wo_attach_id = :workDeliveryId", nativeQuery = true)
	public int updateWordCounts(@Param("wordCount")int wordCount,  @Param("workDeliveryId")String workDeliveryId);

	
	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE wo_attachments w set w.page_count = :pageCount where w.wo_attach_id = :workDeliveryId", nativeQuery = true)
	public int updatePageCounts( @Param("pageCount")int pageCount, @Param("workDeliveryId")String workDeliveryId);

	
	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE wo_attachments w set w.timer_count = :timerCount where w.wo_attach_id = :workDeliveryId", nativeQuery = true)
	public int updateTimerCounts(@Param("timerCount")String timerCount, @Param("workDeliveryId")String workDeliveryId);

	
}
