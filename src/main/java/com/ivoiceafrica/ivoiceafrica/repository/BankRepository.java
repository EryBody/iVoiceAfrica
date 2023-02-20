package com.ivoiceafrica.ivoiceafrica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {
	
}
