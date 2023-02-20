package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkFreelancerPayment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPayments;


public interface WorkPaymentService {

	public List<WorkPayments> findAll();
	
	public Optional<WorkPayments> findById(int theId);

	public void save(WorkPayments payment);

	public void deleteById(int theId);	
	
	List<WorkPayments> findWorkPaymentsByClientIdOrderByEntryDateDesc(User user);
	
	public int updateWorkPaymentStatus(int paymentStatusId, String workId);
}
