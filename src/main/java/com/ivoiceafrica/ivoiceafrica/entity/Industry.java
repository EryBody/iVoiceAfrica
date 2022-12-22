package com.ivoiceafrica.ivoiceafrica.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "industries")
public class Industry {

	@Id
    @GeneratedValue(generator = "industry_id", strategy = GenerationType.AUTO)  
    @Column(name="industry_id")
	private Integer industryId;
	
	@Column(name="industry")
	private String industry;
	
	@OneToMany(mappedBy = "industryId",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<ServiceIndustries> serviceIndustries;
	
	
	public Industry() {
		
	}


	public Industry(Integer industryId, String industry, Set<ServiceIndustries> serviceIndustries) {
		this.industryId = industryId;
		this.industry = industry;
		this.serviceIndustries = serviceIndustries;
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


	public Set<ServiceIndustries> getServiceIndustries() {
		return serviceIndustries;
	}


	public void setServiceIndustries(Set<ServiceIndustries> serviceIndustries) {
		this.serviceIndustries = serviceIndustries;
	}


	@Override
	public String toString() {
		return "Industry [industryId=" + industryId + ", industry=" + industry + "]";
	}
	
}
