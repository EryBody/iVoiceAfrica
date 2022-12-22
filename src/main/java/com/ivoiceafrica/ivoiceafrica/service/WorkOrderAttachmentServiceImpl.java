package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderAttachment;
import com.ivoiceafrica.ivoiceafrica.repository.WorkOrderAttachmentRepository;


@Service
public class WorkOrderAttachmentServiceImpl implements WorkOrderAttachmentService {
	
	@Autowired
	WorkOrderAttachmentRepository workOrderAttachmentRepository;
	
	@Override
	public List<WorkOrderAttachment> findAll() {
		return workOrderAttachmentRepository.findAll();
	}

	@Override
	public void save(WorkOrderAttachment theService) {
		workOrderAttachmentRepository.save(theService);
	}

	@Override
	public void deleteById(String theId) {
		workOrderAttachmentRepository.deleteById(theId);
	}

	@Override
	public Optional<WorkOrderAttachment> findById(String theId) {
		
		Optional<WorkOrderAttachment> result = workOrderAttachmentRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't find the Role
			throw new RuntimeException("Did not find record id - "+theId);
		}
	}

	@Override
	public List<WorkOrderAttachment> findWorkOrderAttachmentByWorkOrder(WorkOrder workOrder) {
		return workOrderAttachmentRepository.findWorkOrderAttachmentByWorkOrder(workOrder);
	}

	@Override
	public Optional<WorkOrderAttachment> findFirstWorkOrderAttachmentByWorkOrder(WorkOrder workOrder) {
		
		Optional<WorkOrderAttachment> result = workOrderAttachmentRepository.findFirstWorkOrderAttachmentByWorkOrder(workOrder);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't find the Role
			throw new RuntimeException("Did not find record id - "+workOrder);
		}
	}
	
	@Override
	public List<WorkOrderAttachment> findWorkOrderAttachmentByWorkOrderWithLimit(String workOrderId,
			int limitOfAttachment) {
		return workOrderAttachmentRepository.findWorkOrderAttachmentByWorkOrderWithLimit(workOrderId , limitOfAttachment);
	}
	

}
