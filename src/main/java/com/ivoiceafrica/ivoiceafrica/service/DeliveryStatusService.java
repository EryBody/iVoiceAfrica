package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.entity.DeliveryStatus;


public interface DeliveryStatusService {
	
	public List<DeliveryStatus> findAll();

	public Optional<DeliveryStatus> findById(int theId);

	public void save(DeliveryStatus theWorkDelivery);

	public void deleteById(int theId);
}
