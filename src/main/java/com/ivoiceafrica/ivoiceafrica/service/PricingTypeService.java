package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.entity.PricingType;


public interface PricingTypeService {

	public List<PricingType> findAll();

	public Optional<PricingType> findById(String theId);

	public void save(PricingType theIdustry);

	public void deleteById(String theId);
}
