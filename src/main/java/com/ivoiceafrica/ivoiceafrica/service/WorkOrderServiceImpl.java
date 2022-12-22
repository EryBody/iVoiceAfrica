package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderStatus;
import com.ivoiceafrica.ivoiceafrica.repository.WorkOrderRepository;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {

	@Autowired
	WorkOrderRepository workOrderRepository;

	@Override
	public List<WorkOrder> findAll() {
		return workOrderRepository.findAll();
	}

	@Override
	public Optional<WorkOrder> findById(String theId) {

		Optional<WorkOrder> result = workOrderRepository.findById(theId);

		if (result.isPresent()) {
			return result;
		} else {
			throw new RuntimeException("Did not find Record - " + theId);
		}
	}

	@Override
	public void deleteById(String theId) {
		workOrderRepository.deleteById(theId);

	}

	@Override
	public void save(WorkOrder theService) {
		workOrderRepository.save(theService);
	}

	@Override
	public Optional<WorkOrder> findFirstWorkOrderByUserOrderByPostingDateDesc(User user) {
		
		Optional<WorkOrder> result = workOrderRepository.findFirstWorkOrderByUserOrderByPostingDateDesc(user);

		if (result.isPresent()) {
			return result;
		} else {
			throw new RuntimeException("Did not find Record - " + user);
		}
	}

	@Override
	public List<WorkOrder> findWorkOrderByUserOrderByPostingDate(User user) {
		return workOrderRepository.findWorkOrderByUserOrderByPostingDate(user);
	}

	@Override
	public List<WorkOrder> findWorkOrderByUserAndWorkOrderStatusOrderByPostingDate(User user,
			WorkOrderStatus workOrderStatus) {
		return workOrderRepository.findWorkOrderByUserAndWorkOrderStatusOrderByPostingDate(user, workOrderStatus);
	}

	@Override
	public int updateWorkOrderStatus(int workOrderStatusId, String workId) {
		return workOrderRepository.updateWorkOrderStatus(workOrderStatusId, workId);
	}

}
