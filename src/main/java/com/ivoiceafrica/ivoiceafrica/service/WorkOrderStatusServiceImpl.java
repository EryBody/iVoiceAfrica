package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderStatus;
import com.ivoiceafrica.ivoiceafrica.repository.WorkOrderStatusRepository;

@Service
public class WorkOrderStatusServiceImpl implements WorkOrderStatusService {

	
	@Autowired
	WorkOrderStatusRepository workOrderStatusRepository;

	@Override
	public List<WorkOrderStatus> findAll() {
		return workOrderStatusRepository.findAll();
	}

	@Override
	public Optional<WorkOrderStatus> findById(int theId) {
		
		Optional<WorkOrderStatus> result = workOrderStatusRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			throw new RuntimeException("Did not find Record - "+theId);
		}
	}

	@Override
	public void deleteById(int theId) {
		workOrderStatusRepository.deleteById(theId);
		
	}

	@Override
	public void save(WorkOrderStatus theIndustry) {
		workOrderStatusRepository.save(theIndustry);
	}

	
}
