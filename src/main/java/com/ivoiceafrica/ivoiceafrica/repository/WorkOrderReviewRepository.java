package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderReview;

public interface WorkOrderReviewRepository extends JpaRepository<WorkOrderReview, Integer>{

	List<WorkOrderReview> findWorkOrderReviewByuserOrderByEntryDateDesc(User user);
	
	List<WorkOrderReview> findWorkOrderReviewByworkOrderOrderByEntryDateDesc(WorkOrder workId);
	
	List<WorkOrderReview> findWorkOrderReviewByworkOrderAndUserOrderByEntryDateDesc(WorkOrder workId, User user);
}
