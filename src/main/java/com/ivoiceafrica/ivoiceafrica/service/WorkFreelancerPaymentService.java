package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkFreelancerPayment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;


public interface WorkFreelancerPaymentService {

	public List<WorkFreelancerPayment> findAll();
	
	public Optional<WorkFreelancerPayment> findById(int theId);

	public void save(WorkFreelancerPayment payment);

	public void deleteById(int theId);	
	
	List<WorkFreelancerPayment> findWorkFreelancerPaymentByFreelancerIdOrderByEntryDateDesc(User user);
	
	public int updateWorkFreelancerPaymentStatus(int paymentStatusId, String workId);
	
	Optional<WorkFreelancerPayment> findWorkFreelancerPaymentByfreelancerId(String freelancerId);
	
	Optional<WorkFreelancerPayment> findWorkFreelancerPaymentByworkOrder(WorkOrder workOrder);
	
}
