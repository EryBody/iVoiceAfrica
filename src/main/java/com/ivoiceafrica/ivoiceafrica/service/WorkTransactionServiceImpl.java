package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkEscrowTransaction;
import com.ivoiceafrica.ivoiceafrica.entity.WorkFreelancerPayment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPaymentStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPayments;
import com.ivoiceafrica.ivoiceafrica.entity.WorkTransactions;
import com.ivoiceafrica.ivoiceafrica.repository.WorkEscrowTransactionRepository;
import com.ivoiceafrica.ivoiceafrica.repository.WorkFreelancerPaymentRepository;
import com.ivoiceafrica.ivoiceafrica.repository.WorkPaymentRepository;
import com.ivoiceafrica.ivoiceafrica.repository.WorkPaymentStatusRepository;
import com.ivoiceafrica.ivoiceafrica.repository.WorkTransactionRepository;


@Service
public class WorkTransactionServiceImpl implements WorkTransactionService{

	@Autowired
	WorkTransactionRepository workTransactionRepository;

	@Override
	public List<WorkTransactions> findAll() {
		return workTransactionRepository.findAll();
	}

	@Override
	public Optional<WorkTransactions> findById(int theId) {
		
		Optional<WorkTransactions> result = workTransactionRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			throw new RuntimeException("Did not find Record - "+theId);
		}
	}

	
	@Override
	public void deleteById(int theId) {
		workTransactionRepository.deleteById(theId);
		
	}

	@Override
	public void save(WorkTransactions payment) {
		workTransactionRepository.save(payment);
	}

	@Override
	public List<WorkTransactions> findWorkTransactionsByUserOrderByEntryDateDesc(User user) {
		return workTransactionRepository.findWorkTransactionsByUserOrderByEntryDateDesc(user);
	}

	@Override
	public List<WorkTransactions> findWorkTransactionsByWorkOrderOrderByEntryDateDesc(WorkOrder workOrder) {
		return workTransactionRepository.findWorkTransactionsByWorkOrderOrderByEntryDateDesc(workOrder);
	}

	@Override
	public List<WorkTransactions> findWorkTransactionsByWorkOrderAndIsInFlowOrderByEntryDateDesc(WorkOrder workOrder,
			Boolean isInFlow) {
		return workTransactionRepository.findWorkTransactionsByWorkOrderAndIsInFlowOrderByEntryDateDesc(workOrder, isInFlow);
	}

	@Override
	public List<WorkTransactions> findWorkTransactionsByUserAndIsInFlowOrderByEntryDateDesc(User user,
			Boolean isInFlow) {
		return workTransactionRepository.findWorkTransactionsByUserAndIsInFlowOrderByEntryDateDesc(user, isInFlow);
	}

	@Override
	public Optional<WorkTransactions> findLastWorkTransactions(int userId) {
		return workTransactionRepository.findLastWorkTransactions(userId);
	}
	
}
