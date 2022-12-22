package com.ivoiceafrica.ivoiceafrica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "service_industries")
public class ServiceIndustries {

	@Id
    @GenericGenerator(name = "si_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.ServiceIndustryGenerator")
    @GeneratedValue(generator = "si_id", strategy = GenerationType.SEQUENCE)  
    @Column(name="si_id")
	private String serviceIndustryId;	
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "industry_id")
    private Industry industryId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "render_id")
    private ServiceRendered serviceRendered;
	
	@Column(name="industry")
	private String industry;
	
	public ServiceIndustries() {
		
	}

	public ServiceIndustries(String serviceIndustryId, Industry industryId, ServiceRendered serviceRendered,
			String industry) {
		this.serviceIndustryId = serviceIndustryId;
		this.industryId = industryId;
		this.serviceRendered = serviceRendered;
		this.industry = industry;
	}

	public String getServiceIndustryId() {
		return serviceIndustryId;
	}

	public void setServiceIndustryId(String serviceIndustryId) {
		this.serviceIndustryId = serviceIndustryId;
	}

	public Industry getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Industry industryId) {
		this.industryId = industryId;
	}

	public ServiceRendered getServiceRendered() {
		return serviceRendered;
	}

	public void setServiceRendered(ServiceRendered serviceRendered) {
		this.serviceRendered = serviceRendered;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
}
