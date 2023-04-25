package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;

public interface DeliveryService {

	public List<WorkOrdersDelivery> findAll();

	public Optional<WorkOrdersDelivery> findById(String theId);

	public void save(WorkOrdersDelivery theWorkDelivery);

	public void deleteById(String theId);
	
	public Optional<WorkOrdersDelivery> findFirstWorkOrdersDeliveryByUserOrderByCreatedDateDesc(User user);
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByDeliveryStatusOrderByCreatedDateDesc(DeliveryStatus deliveryStatus);
	

	public Optional<WorkOrdersDelivery> findFirstWorkOrdersDeliveryByWorkOrderOrderByCreatedDateDesc(
			WorkOrder workOrder);
	
	public Optional<WorkOrdersDelivery> findFirstWorkOrdersDeliveryByWorkOrderAndDeliveryStatus(
			WorkOrder workOrder, DeliveryStatus deliveryStatus);
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByUserOrderByCreatedDateDesc(User user);
	
	List<WorkOrdersDelivery> findOverdueWorkOrdersDelivery(String currentDate, int userId);
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(DeliveryStatus deliveryStatus, User user);
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByUserAndDeliveryStatusOrderByCreatedDateDesc(User user, DeliveryStatus deliveryStatus);
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByClientUserIdAndWorkOrderOrderByCreatedDateDesc(int user, WorkOrder workOrder);
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByUserAndWorkOrderOrderByCreatedDateDesc(User user, WorkOrder workOrder);
	

	public int updateWorkDeliveryStatus(int deliveryStatusId, String deliveryId);
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByClientUserIdAndDeliveryStatusOrderByCreatedDateDesc(int clientUserId, DeliveryStatus deliveryStatus);
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByClientUserIdOrderByCreatedDateDesc(int clientUserId);
	
	public int updateworkDeliveryAmount(double amount,String deliveryId);
	
	Optional<WorkOrdersDelivery> findWorkOrdersDeliveryByUserAndWorkOrder(User user, WorkOrder workOrder);
	
	public int updateCompletedDate(String completedDate,String deliveryId);
	

}
