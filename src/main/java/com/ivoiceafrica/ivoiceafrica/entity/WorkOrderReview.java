package com.ivoiceafrica.ivoiceafrica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;

@Entity
@Table(name = "job_reviews")
public class WorkOrderReview {

	@Id
    @GeneratedValue(generator = "review_id", strategy = GenerationType.AUTO)  
	private Integer reviewId;
	
	@ManyToOne 
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne
    @JoinColumn(name = "work_id")
    private WorkOrder workOrder;
	
	@Column(name = "review")
	private String review;
	
	@Column(name = "entryDate")
	private String entryDate;

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	@Override
	public String toString() {
		return "WorkOrderReview [reviewId=" + reviewId + ", user=" + user + ", workOrder=" + workOrder + ", review="
				+ review + ", entryDate=" + entryDate + "]";
	}
	
	
}
