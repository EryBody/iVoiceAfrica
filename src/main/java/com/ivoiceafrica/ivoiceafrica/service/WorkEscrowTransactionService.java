package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.ivoiceafrica.ivoiceafrica.entity.WorkEscrowTransaction;


public interface WorkEscrowTransactionService {

	public List<WorkEscrowTransaction> findAll();
	
	public Optional<WorkEscrowTransaction> findById(int theId);

	public void save(WorkEscrowTransaction payment);

	public void deleteById(int theId);	
	
	public int updateWorkEscrowIsReleasedAndDate(boolean isReleased, String releasedDate,String workId);
	
	
}
