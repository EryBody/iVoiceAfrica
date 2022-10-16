package com.ivoiceafrica.ivoiceafrica.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;

@Entity
@Table(name = "portfolios")
public class Portfolio {

	@Id
	@GenericGenerator(name = "portfolio_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.PortfolioGenerator")
	@GeneratedValue(generator = "portfolio_id", strategy = GenerationType.SEQUENCE)
	@Column(name = "portfolio_id")
	private String portfolioId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "render_id")
    private ServiceRendered serviceRendered;
	
	@OneToMany(mappedBy = "portfolio",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<PortfolioAttachment> attachments;
	
	public Portfolio() {
		
	}

	public Portfolio(String portfolioId, User user, ServiceRendered serviceRendered,
			Set<PortfolioAttachment> attachments) {
		this.portfolioId = portfolioId;
		this.user = user;
		this.serviceRendered = serviceRendered;
		this.attachments = attachments;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ServiceRendered getServiceRendered() {
		return serviceRendered;
	}

	public void setServiceRendered(ServiceRendered serviceRendered) {
		this.serviceRendered = serviceRendered;
	}

	public Set<PortfolioAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<PortfolioAttachment> attachments) {
		this.attachments = attachments;
	}
	
	
	
}
