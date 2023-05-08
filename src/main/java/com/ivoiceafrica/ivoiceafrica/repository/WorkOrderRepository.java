package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderStatus;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, String> {

	Optional<WorkOrder> findFirstWorkOrderByUserOrderByPostingDateDesc(User User);
	
	Optional<WorkOrder> findFirstWorkOrderByWorkIdAndWorkOrderStatus(String workId, WorkOrderStatus workOrderStatus);
	
	Optional<WorkOrder> findFirstWorkOrderByWorkId(String workId);
	
	List<WorkOrder> findWorkOrderByUserOrderByPostingDateDesc(User user);
	
	List<WorkOrder> findWorkOrderByWorkIdOrderByPostingDate(String workId);
	
	List<WorkOrder> findWorkOrderByUserAndWorkOrderStatusOrderByPostingDate(User user, WorkOrderStatus workOrderStatus);
	
	List<WorkOrder> findWorkOrderByWorkOrderStatusOrderByPostingDate(WorkOrderStatus workOrderStatus);
	
	List<WorkOrder> findWorkOrderByServiceTypeOrderByPostingDate(ServiceType serviceType);
	
	List<WorkOrder> findWorkOrderByServiceTypeAndUserOrderByPostingDate(ServiceType serviceType, User user);
	
	List<WorkOrder> findWorkOrderByUserAndWorkIdOrderByPostingDate(User user, String workId);
	
	
	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE work_orders w set w.wo_status_id = :workOrderStatusId where w.work_id = :workId", nativeQuery = true)
	public int updateWorkOrderStatus(@Param("workOrderStatusId")int workOrderStatusId, @Param("workId")String workId);

	@Query(value = "select * from work_orders order by posting_date DESC LIMIT :limit", nativeQuery = true)
	List<WorkOrder> findWorkOrderByLimit(@Param("limit")int limit);
	
	@Query(value = "select * from work_orders order by posting_date DESC", nativeQuery = true)
	List<WorkOrder> findWorkOrders();
	
	@Query(value = "select * from work_orders order by posting_date DESC LIMIT :limit", nativeQuery = true)
	WorkOrder findLastWorkOrder(@Param("limit")int limit);
	
}
