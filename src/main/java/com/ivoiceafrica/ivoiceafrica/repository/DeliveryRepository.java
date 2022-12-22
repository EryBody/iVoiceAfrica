package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;

public interface DeliveryRepository extends JpaRepository<WorkOrdersDelivery, String> {

	public Optional<WorkOrdersDelivery> findFirstWorkOrdersDeliveryByUserOrderByCreatedDateDesc(User user);

	List<WorkOrdersDelivery> findWorkOrdersDeliveryByDeliveryStatusOrderByCreatedDateDesc(DeliveryStatus deliveryStatus);
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByUserOrderByCreatedDateDesc(User user);

	public Optional<WorkOrdersDelivery> findFirstWorkOrdersDeliveryByWorkOrderOrderByCreatedDateDesc(
			WorkOrder workOrder);
	
	@Query(value = "select * from wo_delivery w where :currentDate > w.end_date AND w.user_id = :userId", nativeQuery = true)
	public List<WorkOrdersDelivery> findOverdueWorkOrdersDelivery(@Param("currentDate")String startDate, @Param("userId")int userId);
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(DeliveryStatus deliveryStatus, User user);
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByUserAndDeliveryStatusOrderByCreatedDateDesc(User user, DeliveryStatus deliveryStatus);

	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE wo_delivery w set w.delivery_status_id = :deliveryStatusId where w.delivery_id = :deliveryId", nativeQuery = true)
	public int updateWorkDeliveryStatus(@Param("deliveryStatusId")int deliveryStatusId, @Param("deliveryId")String deliveryId);

}
