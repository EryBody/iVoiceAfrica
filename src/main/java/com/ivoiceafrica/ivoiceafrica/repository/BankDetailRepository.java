package com.ivoiceafrica.ivoiceafrica.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ivoiceafrica.ivoiceafrica.entity.BankDetail;

public interface BankDetailRepository extends JpaRepository<BankDetail, Integer> {
	
	@Query(value = "select * from bank_details where user_id = :userId", nativeQuery = true)
	BankDetail findBankDetailsWithUserId(@Param("userId")int userId);
	
}
