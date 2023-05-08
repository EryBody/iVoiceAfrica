package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderReview;
import com.ivoiceafrica.ivoiceafrica.repository.WorkOrderReviewRepository;

@Service
public class WorkOrderReviewServiceImpl implements WorkOrderReviewService {

	@Autowired
	WorkOrderReviewRepository workOrderReviewRepository;

	@Override
	public List<WorkOrderReview> findAll() {
		return workOrderReviewRepository.findAll();
	}

	@Override
	public void save(WorkOrderReview theReview) {
		workOrderReviewRepository.save(theReview);
	}

	@Override
	public void deleteById(int theId) {
		workOrderReviewRepository.deleteById(theId);
	}

	@Override
	public Optional<WorkOrderReview> findById(int theId) {
		return workOrderReviewRepository.findById(theId);
	}

	@Override
	public List<WorkOrderReview> findWorkOrderReviewByuserOrderByEntryDateDesc(User user) {
		return workOrderReviewRepository.findWorkOrderReviewByuserOrderByEntryDateDesc(user);
	}

	@Override
	public List<WorkOrderReview> findWorkOrderReviewByworkOrderOrderByEntryDateDesc(WorkOrder workId) {
		return workOrderReviewRepository.findWorkOrderReviewByworkOrderOrderByEntryDateDesc(workId);
	}

	@Override
	public List<WorkOrderReview> findWorkOrderReviewByworkOrderAndUserOrderByEntryDateDesc(WorkOrder workId,
			User user) {
		return workOrderReviewRepository.findWorkOrderReviewByworkOrderAndUserOrderByEntryDateDesc(workId, user);
	}
}
