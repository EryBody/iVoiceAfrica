package com.ivoiceafrica.ivoiceafrica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.entity.WorkPaymentStatus;

public interface WorkPaymentStatusRepository extends JpaRepository<WorkPaymentStatus, Integer> {
	
}
