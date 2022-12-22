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
@Table(name = "languages")
public class Language {

	@Id
    @GeneratedValue(generator = "language_id", strategy = GenerationType.AUTO)  
    @Column(name="language_id")
	private Integer languageId;
	
	@Column(name="language")
	private String language;
	
	@OneToMany(mappedBy = "languageId",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<ServiceLanguages> serviceLanguages;
	
	public Language() {
	}

	public Language(Integer languageId, String language) {
		this.languageId = languageId;
		this.language = language;
	}


	public Language(Integer languageId, String language, Set<ServiceLanguages> serviceLanguages) {
		this.languageId = languageId;
		this.language = language;
		this.serviceLanguages = serviceLanguages;
	}


	public Integer getLanguageId() {
		return languageId;
	}


	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public Set<ServiceLanguages> getServiceLanguages() {
		return serviceLanguages;
	}


	public void setServiceLanguages(Set<ServiceLanguages> serviceLanguages) {
		this.serviceLanguages = serviceLanguages;
	}


	@Override
	public String toString() {
		return "Language [languageId=" + languageId + ", language=" + language + "]";
	}


	
}
