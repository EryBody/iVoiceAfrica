package com.ivoiceafrica.ivoiceafrica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderStatus;


public interface WorkOrderStatusRepository  extends JpaRepository<WorkOrderStatus, Integer> {

}
