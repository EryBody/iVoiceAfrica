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
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByUserOrderByCreatedDateDesc(User user);
	
	List<WorkOrdersDelivery> findOverdueWorkOrdersDelivery(String currentDate, int userId);
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(DeliveryStatus deliveryStatus, User user);
	
	List<WorkOrdersDelivery> findWorkOrdersDeliveryByUserAndDeliveryStatusOrderByCreatedDateDesc(User user, DeliveryStatus deliveryStatus);

	public int updateWorkDeliveryStatus(int deliveryStatusId, String deliveryId);
}
