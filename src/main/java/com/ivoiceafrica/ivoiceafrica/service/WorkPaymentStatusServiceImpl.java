package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.WorkEscrowTransaction;
import com.ivoiceafrica.ivoiceafrica.entity.WorkFreelancerPayment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPaymentStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPayments;
import com.ivoiceafrica.ivoiceafrica.repository.WorkEscrowTransactionRepository;
import com.ivoiceafrica.ivoiceafrica.repository.WorkFreelancerPaymentRepository;
import com.ivoiceafrica.ivoiceafrica.repository.WorkPaymentRepository;
import com.ivoiceafrica.ivoiceafrica.repository.WorkPaymentStatusRepository;


@Service
public class WorkPaymentStatusServiceImpl implements WorkPaymentStatusService{

	@Autowired
	WorkPaymentStatusRepository workPaymentStatusRepository;

	@Override
	public List<WorkPaymentStatus> findAll() {
		return workPaymentStatusRepository.findAll();
	}

	@Override
	public Optional<WorkPaymentStatus> findById(int theId) {
		
		Optional<WorkPaymentStatus> result = workPaymentStatusRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			throw new RuntimeException("Did not find Record - "+theId);
		}
	}

	@Override
	public void save(WorkPaymentStatus theStatus) {
		workPaymentStatusRepository.save(theStatus);
		
	}

	@Override
	public void deleteById(int theId) {
		workPaymentStatusRepository.deleteById(theId);
		
	}
	
}
