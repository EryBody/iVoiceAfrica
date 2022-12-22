package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.entity.Industry;

public interface IndustryService {

	public List<Industry> findAll();

	public Optional<Industry> findById(int theId);

	public void save(Industry theIdustry);

	public void deleteById(int theId);
	
	Optional<Industry> findIndustryByIndustryId(int industry);
	
}
