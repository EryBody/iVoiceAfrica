package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;

public interface STypeService {

	public List<ServiceType> findAll();

	public Optional<ServiceType> findById(String theId);

	public void save(ServiceType theRole);

	public void deleteById(String theId);

}
