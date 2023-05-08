package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderReview;

public interface WorkOrderReviewService {

	public List<WorkOrderReview> findAll();

	public Optional<WorkOrderReview> findById(int theId);

	public void save(WorkOrderReview theReview);

	public void deleteById(int theId);
	
	List<WorkOrderReview> findWorkOrderReviewByuserOrderByEntryDateDesc(User user);
	
	List<WorkOrderReview> findWorkOrderReviewByworkOrderOrderByEntryDateDesc(WorkOrder workId);	
	
	List<WorkOrderReview> findWorkOrderReviewByworkOrderAndUserOrderByEntryDateDesc(WorkOrder workId, User user);
}
