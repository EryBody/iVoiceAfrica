package com.ivoiceafrica.ivoiceafrica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "service_industries")
public class Industry {

	@Id
    @GeneratedValue(generator = "industry_id", strategy = GenerationType.AUTO)  
    @Column(name="industry_id")
	private Integer industryId;
	
	@Column(name="industry")
	private String industry;
	
	public Industry() {
		
	}

	public Industry(Integer industryId, String industry) {
		this.industryId = industryId;
		this.industry = industry;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	
	
}
