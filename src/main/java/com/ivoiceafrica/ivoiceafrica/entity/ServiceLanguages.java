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
@Table(name = "service_languages")
public class ServiceLanguages {


	@Id
    @GenericGenerator(name = "sl_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.ServiceLanguageGenerator")
    @GeneratedValue(generator = "sl_id", strategy = GenerationType.SEQUENCE)  
    @Column(name="sl_id")
	private String serviceLanguageId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "language_id")
    private Language languageId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "render_id")
    private ServiceRendered serviceRendered;
	
	@Column(name="language_desc")
	private String languageDesc;
	
	@Column(name="voice_type")
	private String voiceType;
	
	@Column(name="language_upload")
	private String languageUpload;
	
	@OneToMany(mappedBy = "serviceLanguage",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<VoiceCapability> voiceCapabilities;
	
	
	public ServiceLanguages() {
		
	}


	public ServiceLanguages(String serviceLanguageId, Language languageId, ServiceRendered serviceRendered,
			String languageDesc, String voiceType, String languageUpload, Set<VoiceCapability> voiceCapabilities) {
		this.serviceLanguageId = serviceLanguageId;
		this.languageId = languageId;
		this.serviceRendered = serviceRendered;
		this.languageDesc = languageDesc;
		this.voiceType = voiceType;
		this.languageUpload = languageUpload;
		this.voiceCapabilities = voiceCapabilities;
	}


	public String getServiceLanguageId() {
		return serviceLanguageId;
	}


	public void setServiceLanguageId(String serviceLanguageId) {
		this.serviceLanguageId = serviceLanguageId;
	}


	public Language getLanguageId() {
		return languageId;
	}


	public void setLanguageId(Language languageId) {
		this.languageId = languageId;
	}


	public ServiceRendered getServiceRendered() {
		return serviceRendered;
	}


	public void setServiceRendered(ServiceRendered serviceRendered) {
		this.serviceRendered = serviceRendered;
	}
	

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getLanguageDesc() {
		return languageDesc;
	}


	public void setLanguageDesc(String languageDesc) {
		this.languageDesc = languageDesc;
	}

	
	public String getVoiceType() {
		return voiceType;
	}


	public void setVoiceType(String voiceType) {
		this.voiceType = voiceType;
	}


	public String getLanguageUpload() {
		return languageUpload;
	}


	public void setLanguageUpload(String languageUpload) {
		this.languageUpload = languageUpload;
	}


	public Set<VoiceCapability> getVoiceCapabilities() {
		return voiceCapabilities;
	}


	public void setVoiceCapabilities(Set<VoiceCapability> voiceCapabilities) {
		this.voiceCapabilities = voiceCapabilities;
	}


	@Override
	public String toString() {
		return "ServiceLanguages [serviceLanguageId=" + serviceLanguageId + ", languageId=" + languageId
				+ ", languageDesc=" + languageDesc + ", voiceType=" + voiceType + ", languageUpload=" + languageUpload
				+ ", voiceCapabilities=" + voiceCapabilities + "]";
	}


}
