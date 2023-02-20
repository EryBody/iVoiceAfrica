package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkEscrowTransaction;
import com.ivoiceafrica.ivoiceafrica.entity.WorkFreelancerPayment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPayments;
import com.ivoiceafrica.ivoiceafrica.repository.WorkEscrowTransactionRepository;
import com.ivoiceafrica.ivoiceafrica.repository.WorkFreelancerPaymentRepository;
import com.ivoiceafrica.ivoiceafrica.repository.WorkPaymentRepository;


@Service
public class WorkPaymentServiceImpl implements WorkPaymentService{

	@Autowired
	WorkPaymentRepository workPaymentRepository;

	@Override
	public List<WorkPayments> findAll() {
		return workPaymentRepository.findAll();
	}

	@Override
	public Optional<WorkPayments> findById(int theId) {
		
		Optional<WorkPayments> result = workPaymentRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			throw new RuntimeException("Did not find Record - "+theId);
		}
	}

	@Override
	public void save(WorkPayments theStatus) {
		workPaymentRepository.save(theStatus);
		
	}

	@Override
	public void deleteById(int theId) {
		workPaymentRepository.deleteById(theId);
		
	}

	@Override
	public List<WorkPayments> findWorkPaymentsByClientIdOrderByEntryDateDesc(User user) {
		return workPaymentRepository.findWorkPaymentsByClientIdOrderByEntryDateDesc(user);
	}

	@Override
	public int updateWorkPaymentStatus(int paymentStatusId, String workId) {
		return workPaymentRepository.updateWorkPaymentStatus(paymentStatusId, workId);
	}
	
}
