package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderStatus;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, String> {

	Optional<WorkOrder> findFirstWorkOrderByUserOrderByPostingDateDesc(User User);
	
	List<WorkOrder> findWorkOrderByUserOrderByPostingDate(User user);
	
	List<WorkOrder> findWorkOrderByUserAndWorkOrderStatusOrderByPostingDate(User user, WorkOrderStatus workOrderStatus);
	
	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE work_orders w set w.wo_status_id = :workOrderStatusId where w.work_id = :workId", nativeQuery = true)
	public int updateWorkOrderStatus(@Param("workOrderStatusId")int workOrderStatusId, @Param("workId")String workId);

}
