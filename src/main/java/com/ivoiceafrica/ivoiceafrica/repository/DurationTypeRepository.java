package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.entity.DurationType;

public interface DurationTypeRepository extends JpaRepository<DurationType, Integer> {

	Optional<DurationType> findDurationTypeByDurationId(int durationId);
	
}
