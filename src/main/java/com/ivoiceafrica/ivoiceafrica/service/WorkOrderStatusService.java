package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderStatus;

public interface WorkOrderStatusService {

	public List<WorkOrderStatus> findAll();

	public Optional<WorkOrderStatus> findById(int theId);

	public void save(WorkOrderStatus theStatus);

	public void deleteById(int theId);
}
