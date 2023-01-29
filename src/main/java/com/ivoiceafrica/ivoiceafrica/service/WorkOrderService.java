package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderStatus;


public interface WorkOrderService {

	public List<WorkOrder> findAll();

	public Optional<WorkOrder> findById(String theId);

	public void save(WorkOrder theJob);

	public void deleteById(String theId);
	
	Optional<WorkOrder> findFirstWorkOrderByUserOrderByPostingDateDesc(User User);
	
	List<WorkOrder> findWorkOrderByUserOrderByPostingDate(User user);
	
	List<WorkOrder> findWorkOrderByUserAndWorkOrderStatusOrderByPostingDate(User user, WorkOrderStatus workOrderStatus);
	
	public int updateWorkOrderStatus(int workOrderStatusId,String workId);

	List<WorkOrder> findWorkOrderByWorkOrderStatusOrderByPostingDate(WorkOrderStatus workOrderStatus);
	
	List<WorkOrder> findWorkOrderByLimit(int limit);
	
	Optional<WorkOrder> findFirstWorkOrderByWorkIdAndWorkOrderStatus(String workId, WorkOrderStatus workOrderStatus);
	
	Optional<WorkOrder> findFirstWorkOrderByWorkId(String workId);
	
	List<WorkOrder> findWorkOrderByServiceTypeOrderByPostingDate(ServiceType serviceType);
	
	List<WorkOrder> findWorkOrderByWorkIdOrderByPostingDate(String workId);
	
	List<WorkOrder> findWorkOrderByUserAndWorkIdOrderByPostingDate(User user, String workId);
	
	List<WorkOrder> findWorkOrderByServiceTypeAndUserOrderByPostingDate(ServiceType serviceType, User user);
	
}
