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
@Table(name = "services_rendered")
public class ServiceRendered {

	@Id
	@GenericGenerator(name = "render_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.ServiceRenderedGenerator")
	@GeneratedValue(generator = "render_id", strategy = GenerationType.SEQUENCE)
	@Column(name = "render_id")
	private String renderId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "type_id")
    private ServiceType serviceType;
	
	@JoinColumn(name = "experience_in_years")
	private String experienceInYears;
	
	@JoinColumn(name = "other_info")
	private String otherInfo;
	
	@OneToMany(mappedBy = "serviceRendered",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<Portfolio> portfolios;
	
	
	@OneToMany(mappedBy = "serviceRendered",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<ServiceVoiceCapability> serviceVoiceCapabilities;
	
	@OneToMany(mappedBy = "serviceRendered",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<ServiceIndustries> serviceIndustries;
	
	@OneToMany(mappedBy = "serviceRendered",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<ServiceLanguages> serviceLanguages;


	public ServiceRendered() {
	}


	public ServiceRendered(String renderId, User user, ServiceType serviceType, String experienceInYears,
			String otherInfo, Set<Portfolio> portfolios, Set<ServiceVoiceCapability> serviceVoiceCapabilities,
			Set<ServiceIndustries> serviceIndustries, Set<ServiceLanguages> serviceLanguages) {
		this.renderId = renderId;
		this.user = user;
		this.serviceType = serviceType;
		this.experienceInYears = experienceInYears;
		this.otherInfo = otherInfo;
		this.portfolios = portfolios;
		this.serviceVoiceCapabilities = serviceVoiceCapabilities;
		this.serviceIndustries = serviceIndustries;
		this.serviceLanguages = serviceLanguages;
	}


	public String getRenderId() {
		return renderId;
	}


	public void setRenderId(String renderId) {
		this.renderId = renderId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public ServiceType getServiceType() {
		return serviceType;
	}


	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}


	public String getExperienceInYears() {
		return experienceInYears;
	}


	public void setExperienceInYears(String experienceInYears) {
		this.experienceInYears = experienceInYears;
	}


	public String getOtherInfo() {
		return otherInfo;
	}


	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}


	public Set<Portfolio> getPortfolios() {
		return portfolios;
	}


	public void setPortfolios(Set<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}


	public Set<ServiceVoiceCapability> getServiceVoiceCapabilities() {
		return serviceVoiceCapabilities;
	}


	public void setServiceVoiceCapabilities(Set<ServiceVoiceCapability> serviceVoiceCapabilities) {
		this.serviceVoiceCapabilities = serviceVoiceCapabilities;
	}


	public Set<ServiceIndustries> getServiceIndustries() {
		return serviceIndustries;
	}


	public void setServiceIndustries(Set<ServiceIndustries> serviceIndustries) {
		this.serviceIndustries = serviceIndustries;
	}


	public Set<ServiceLanguages> getServiceLanguages() {
		return serviceLanguages;
	}


	public void setServiceLanguages(Set<ServiceLanguages> serviceLanguages) {
		this.serviceLanguages = serviceLanguages;
	}


	@Override
	public String toString() {
		return "ServiceRendered [renderId=" + renderId + ", user=" + user + ", serviceType=" + serviceType
				+ ", experienceInYears=" + experienceInYears + ", otherInfo=" + otherInfo + ", portfolios=" + portfolios
				+ "]";
	}

	
	

	
}
