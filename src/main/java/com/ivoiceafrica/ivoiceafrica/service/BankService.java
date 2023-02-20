package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.entity.Bank;


public interface BankService {

	public List<Bank> findAll();
	
	public Optional<Bank> findById(int theId);

	public void save(Bank bank);

	public void deleteById(int theId);	
	
}
