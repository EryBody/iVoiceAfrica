package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.Bank;
import com.ivoiceafrica.ivoiceafrica.repository.BankRepository;


@Service
public class BankServiceImpl implements BankService{

	@Autowired
	BankRepository bankRepository;

	@Override
	public List<Bank> findAll() {
		return bankRepository.findAll();
	}

	@Override
	public Optional<Bank> findById(int theId) {
		
		Optional<Bank> result = bankRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			throw new RuntimeException("Did not find Record - "+theId);
		}
	}

	@Override
	public void save(Bank theStatus) {
		bankRepository.save(theStatus);
		
	}

	@Override
	public void deleteById(int theId) {
		bankRepository.deleteById(theId);
		
	}
	
}
