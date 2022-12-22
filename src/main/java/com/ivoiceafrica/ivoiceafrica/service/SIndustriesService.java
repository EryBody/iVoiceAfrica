package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.entity.ServiceIndustries;


public interface SIndustriesService {

	public List<ServiceIndustries> findAll();
	
	public Optional<ServiceIndustries> findById(String theId);

	public void save(ServiceIndustries theRole);

	public void deleteById(String theId);

}
