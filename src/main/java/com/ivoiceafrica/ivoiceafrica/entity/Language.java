package com.ivoiceafrica.ivoiceafrica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "service_languages")
public class Language {

	@Id
    @GeneratedValue(generator = "language_id", strategy = GenerationType.AUTO)  
    @Column(name="language_id")
	private Integer languageId;
	
	@Column(name="language")
	private String language;
	
	

	public Language() {
	}

	public Language(Integer languageId, String language) {
		this.languageId = languageId;
		this.language = language;
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
	
	
}
