package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.WorkEscrowTransaction;
import com.ivoiceafrica.ivoiceafrica.repository.WorkEscrowTransactionRepository;


@Service
public class WorkEscrowTransactionServiceImpl implements WorkEscrowTransactionService{

	@Autowired
	WorkEscrowTransactionRepository workEscrowTransactionRepository;

	@Override
	public List<WorkEscrowTransaction> findAll() {
		return workEscrowTransactionRepository.findAll();
	}

	@Override
	public Optional<WorkEscrowTransaction> findById(int theId) {
		
		Optional<WorkEscrowTransaction> result = workEscrowTransactionRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			throw new RuntimeException("Did not find Record - "+theId);
		}
	}

	@Override
	public void save(WorkEscrowTransaction theStatus) {
		workEscrowTransactionRepository.save(theStatus);
		
	}

	@Override
	public void deleteById(int theId) {
		workEscrowTransactionRepository.deleteById(theId);
		
	}

	@Override
	public int updateWorkEscrowIsReleasedAndDate(boolean isReleased, String releasedDate, String workId) {
		return workEscrowTransactionRepository.updateWorkEscrowIsReleasedAndDate(isReleased, releasedDate, workId);
	}
	
}
