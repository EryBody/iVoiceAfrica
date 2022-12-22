package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.entity.DurationType;


public interface DurationTypeService {

	public List<DurationType> findAll();

	public Optional<DurationType> findById(int theId);

	public void save(DurationType theDuration);

	public void deleteById(int theId);
	
	Optional<DurationType> findDurationTypeByDurationId(int durationId);
}
