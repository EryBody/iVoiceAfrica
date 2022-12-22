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


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;

@Entity
@Table(name = "voice_capabilities")
public class VoiceCapability {

	@Id
    @GeneratedValue(generator = "voice_id", strategy = GenerationType.AUTO)  
    @Column(name="voice_id")
	private Integer voiceId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "sl_id")
    private ServiceLanguages serviceLanguage;
	
	@Column(name="voice_document")
	private String voiceDocument;
	
	@Column(name="voice_desc")
	private String voiceDesc;
	
	@OneToMany(mappedBy = "voiceCapability",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<ServiceVoiceCapability> serviceVoiceCapabilities;
	
	
	public VoiceCapability() {
		
	}


	public VoiceCapability(Integer voiceId, User user, ServiceLanguages serviceLanguage, String voiceDocument,
			String voiceDesc, Set<ServiceVoiceCapability> serviceVoiceCapabilities) {
		this.voiceId = voiceId;
		this.user = user;
		this.serviceLanguage = serviceLanguage;
		this.voiceDocument = voiceDocument;
		this.voiceDesc = voiceDesc;
		this.serviceVoiceCapabilities = serviceVoiceCapabilities;
	}


	public Integer getVoiceId() {
		return voiceId;
	}


	public void setVoiceId(Integer voiceId) {
		this.voiceId = voiceId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public ServiceLanguages getServiceLanguage() {
		return serviceLanguage;
	}


	public void setServiceLanguage(ServiceLanguages serviceLanguage) {
		this.serviceLanguage = serviceLanguage;
	}


	public String getVoiceDocument() {
		return voiceDocument;
	}


	public void setVoiceDocument(String voiceDocument) {
		this.voiceDocument = voiceDocument;
	}


	public String getVoiceDesc() {
		return voiceDesc;
	}


	public void setVoiceDesc(String voiceDesc) {
		this.voiceDesc = voiceDesc;
	}


	public Set<ServiceVoiceCapability> getServiceVoiceCapabilities() {
		return serviceVoiceCapabilities;
	}


	public void setServiceVoiceCapabilities(Set<ServiceVoiceCapability> serviceVoiceCapabilities) {
		this.serviceVoiceCapabilities = serviceVoiceCapabilities;
	}


	


}
