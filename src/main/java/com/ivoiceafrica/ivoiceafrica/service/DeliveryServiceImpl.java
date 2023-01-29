package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;
import com.ivoiceafrica.ivoiceafrica.repository.DeliveryRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService {
	
	@Autowired
	DeliveryRepository deliveryRepository;

	@Override
	public List<WorkOrdersDelivery> findAll() {
		return deliveryRepository.findAll();
	}

	@Override
	public Optional<WorkOrdersDelivery> findById(String theId) {

		Optional<WorkOrdersDelivery> result = deliveryRepository.findById(theId);

		if (result.isPresent()) {
			return result;
		} else {
			throw new RuntimeException("Did not find Record - " + theId);
		}
	}

	@Override
	public void deleteById(String theId) {
		deliveryRepository.deleteById(theId);
	}

	
	@Override
	public void save(WorkOrdersDelivery workDelivery) {
		deliveryRepository.save(workDelivery);
	}

	
	@Override
	public Optional<WorkOrdersDelivery> findFirstWorkOrdersDeliveryByUserOrderByCreatedDateDesc(User user) {
		Optional<WorkOrdersDelivery> result = deliveryRepository.findFirstWorkOrdersDeliveryByUserOrderByCreatedDateDesc(user);

		if (result.isPresent()) {
			return result;
		} else {
			throw new RuntimeException("Did not find Record - " + user);
		}
	}

	
	@Override
	public List<WorkOrdersDelivery> findWorkOrdersDeliveryByDeliveryStatusOrderByCreatedDateDesc(
			DeliveryStatus deliveryStatus) {
		return deliveryRepository.findWorkOrdersDeliveryByDeliveryStatusOrderByCreatedDateDesc(deliveryStatus);
	}

	
	@Override
	public Optional<WorkOrdersDelivery> findFirstWorkOrdersDeliveryByWorkOrderOrderByCreatedDateDesc(
			WorkOrder workOrder) {
		
		Optional<WorkOrdersDelivery> result = deliveryRepository.findFirstWorkOrdersDeliveryByWorkOrderOrderByCreatedDateDesc(workOrder);

		return result;
	}

	
	@Override
	public List<WorkOrdersDelivery> findWorkOrdersDeliveryByUserOrderByCreatedDateDesc(User user) {
		
		List<WorkOrdersDelivery> result = deliveryRepository.findWorkOrdersDeliveryByUserOrderByCreatedDateDesc(user);
		
		return result;
	}

	@Override
	public List<WorkOrdersDelivery> findOverdueWorkOrdersDelivery(String currentDate, int userId) {
		return deliveryRepository.findOverdueWorkOrdersDelivery(currentDate, userId);
	}

	@Override
	public List<WorkOrdersDelivery> findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(
			DeliveryStatus deliveryStatus, User user) {
		return deliveryRepository.findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(deliveryStatus, user);
	}

	@Override
	public List<WorkOrdersDelivery> findWorkOrdersDeliveryByUserAndDeliveryStatusOrderByCreatedDateDesc(User user,
			DeliveryStatus deliveryStatus) {
		
		return deliveryRepository.findWorkOrdersDeliveryByUserAndDeliveryStatusOrderByCreatedDateDesc(user, deliveryStatus);
	}

	@Override
	public int updateWorkDeliveryStatus(int deliveryStatusId, String deliveryId) {
		return deliveryRepository.updateWorkDeliveryStatus(deliveryStatusId, deliveryId);
	}

	@Override
	public Optional<WorkOrdersDelivery> findFirstWorkOrdersDeliveryByWorkOrderAndDeliveryStatus(WorkOrder workOrder,
			DeliveryStatus deliveryStatus) {
		return deliveryRepository.findFirstWorkOrdersDeliveryByWorkOrderAndDeliveryStatus(workOrder, deliveryStatus);
	}

	@Override
	public List<WorkOrdersDelivery> findWorkOrdersDeliveryByClientUserIdAndDeliveryStatusOrderByCreatedDateDesc(
			int clientUserId, DeliveryStatus deliveryStatus) {
		return deliveryRepository.findWorkOrdersDeliveryByClientUserIdAndDeliveryStatusOrderByCreatedDateDesc(clientUserId, deliveryStatus);
	}

	@Override
	public List<WorkOrdersDelivery> findWorkOrdersDeliveryByClientUserIdOrderByCreatedDateDesc(int clientUserId) {
		return deliveryRepository.findWorkOrdersDeliveryByClientUserIdOrderByCreatedDateDesc(clientUserId);
	}

	@Override
	public List<WorkOrdersDelivery> findWorkOrdersDeliveryByClientUserIdAndWorkOrderOrderByCreatedDateDesc(int user,
			WorkOrder workOrder) {
		return deliveryRepository.findWorkOrdersDeliveryByClientUserIdAndWorkOrderOrderByCreatedDateDesc(user, workOrder);
	}

	@Override
	public List<WorkOrdersDelivery> findWorkOrdersDeliveryByUserAndWorkOrderOrderByCreatedDateDesc(User user,
			WorkOrder workOrder) {
		return deliveryRepository.findWorkOrdersDeliveryByUserAndWorkOrderOrderByCreatedDateDesc(user, workOrder);
	}

	@Override
	public int updateworkDeliveryAmount(double amount, String deliveryId) {
		return deliveryRepository.updateworkDeliveryAmount(amount, deliveryId);
	}

}
