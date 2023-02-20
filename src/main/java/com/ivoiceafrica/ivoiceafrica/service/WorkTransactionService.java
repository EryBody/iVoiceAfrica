package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkFreelancerPayment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPayments;
import com.ivoiceafrica.ivoiceafrica.entity.WorkTransactions;


public interface WorkTransactionService {

	public List<WorkTransactions> findAll();
	
	public Optional<WorkTransactions> findById(int theId);

	public void save(WorkTransactions payment);

	public void deleteById(int theId);	
	
	List<WorkTransactions> findWorkTransactionsByUserOrderByEntryDateDesc(User user);

	List<WorkTransactions> findWorkTransactionsByWorkOrderOrderByEntryDateDesc(WorkOrder workOrder);
}
