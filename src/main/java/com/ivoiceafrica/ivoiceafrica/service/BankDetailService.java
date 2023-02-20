package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.ivoiceafrica.ivoiceafrica.entity.Bank;
import com.ivoiceafrica.ivoiceafrica.entity.BankDetail;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;


public interface BankDetailService {

	public List<BankDetail> findAll();
	
	public Optional<BankDetail> findById(int theId);

	public void save(BankDetail bank);

	public void deleteById(int theId);
	
	BankDetail findBankDetailsWithUserId(int userId);
	
}
