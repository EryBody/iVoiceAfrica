package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkEscrowTransaction;
import com.ivoiceafrica.ivoiceafrica.entity.WorkFreelancerPayment;
import com.ivoiceafrica.ivoiceafrica.repository.WorkEscrowTransactionRepository;
import com.ivoiceafrica.ivoiceafrica.repository.WorkFreelancerPaymentRepository;


@Service
public class WorkFreelancerPaymentServiceImpl implements WorkFreelancerPaymentService{

	@Autowired
	WorkFreelancerPaymentRepository workFreelancerPaymentRepository;

	@Override
	public List<WorkFreelancerPayment> findAll() {
		return workFreelancerPaymentRepository.findAll();
	}

	@Override
	public Optional<WorkFreelancerPayment> findById(int theId) {
		
		Optional<WorkFreelancerPayment> result = workFreelancerPaymentRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			throw new RuntimeException("Did not find Record - "+theId);
		}
	}

	@Override
	public void save(WorkFreelancerPayment theStatus) {
		workFreelancerPaymentRepository.save(theStatus);
		
	}

	@Override
	public void deleteById(int theId) {
		workFreelancerPaymentRepository.deleteById(theId);
		
	}

	@Override
	public List<WorkFreelancerPayment> findWorkFreelancerPaymentByFreelancerIdOrderByEntryDateDesc(User user) {
		return workFreelancerPaymentRepository.findWorkFreelancerPaymentByFreelancerIdOrderByEntryDateDesc(user);
	}

	@Override
	public int updateWorkFreelancerPaymentStatus(int paymentStatusId, String workId) {
		return workFreelancerPaymentRepository.updateWorkFreelancerPaymentStatus(paymentStatusId, workId);
	}
	
}
