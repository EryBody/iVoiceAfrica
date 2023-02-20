package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.entity.WorkFreelancerPayment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPaymentStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPayments;


public interface WorkPaymentStatusService {

	public List<WorkPaymentStatus> findAll();
	
	public Optional<WorkPaymentStatus> findById(int theId);

	public void save(WorkPaymentStatus payment);

	public void deleteById(int theId);	
	
}
