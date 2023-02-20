package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.Bank;
import com.ivoiceafrica.ivoiceafrica.entity.BankDetail;
import com.ivoiceafrica.ivoiceafrica.repository.BankDetailRepository;
import com.ivoiceafrica.ivoiceafrica.repository.BankRepository;


@Service
public class BankDetailServiceImpl implements BankDetailService{

	@Autowired
	BankDetailRepository bankDetailRepository;

	@Override
	public List<BankDetail> findAll() {
		return bankDetailRepository.findAll();
	}

	@Override
	public Optional<BankDetail> findById(int theId) {
		
		Optional<BankDetail> result = bankDetailRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			throw new RuntimeException("Did not find Record - "+theId);
		}
	}

	@Override
	public void save(BankDetail theStatus) {
		bankDetailRepository.save(theStatus);
	}

	@Override
	public void deleteById(int theId) {
		bankDetailRepository.deleteById(theId);
		
	}

	@Override
	public BankDetail findBankDetailsWithUserId(int userId) {
		return bankDetailRepository.findBankDetailsWithUserId(userId);
	}
	
}
