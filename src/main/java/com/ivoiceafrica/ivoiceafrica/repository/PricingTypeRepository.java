package com.ivoiceafrica.ivoiceafrica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.entity.PricingType;

public interface PricingTypeRepository extends JpaRepository<PricingType, String> {
	
}
