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

@Entity
@Table(name = "voice_capabilities")
public class VoiceCapability {

	@Id
    @GeneratedValue(generator = "voice_id", strategy = GenerationType.AUTO)  
    @Column(name="voice_id")
	private Integer voiceId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "render_id")
    private ServiceRendered serviceRendered;
	
	@Column(name="v_capability")
	private String vCapability;
	
	@OneToMany(mappedBy = "voiceCapability",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<ServiceVoiceCapability> serviceVoiceCapabilities;
	
	
	public VoiceCapability() {
		
	}


	public VoiceCapability(Integer voiceId, ServiceRendered serviceRendered, String vCapability,
			Set<ServiceVoiceCapability> serviceVoiceCapabilities) {
		this.voiceId = voiceId;
		this.serviceRendered = serviceRendered;
		this.vCapability = vCapability;
		this.serviceVoiceCapabilities = serviceVoiceCapabilities;
	}


	public Integer getVoiceId() {
		return voiceId;
	}


	public void setVoiceId(Integer voiceId) {
		this.voiceId = voiceId;
	}


	public ServiceRendered getServiceRendered() {
		return serviceRendered;
	}


	public void setServiceRendered(ServiceRendered serviceRendered) {
		this.serviceRendered = serviceRendered;
	}


	public String getvCapability() {
		return vCapability;
	}


	public void setvCapability(String vCapability) {
		this.vCapability = vCapability;
	}


	public Set<ServiceVoiceCapability> getServiceVoiceCapabilities() {
		return serviceVoiceCapabilities;
	}


	public void setServiceVoiceCapabilities(Set<ServiceVoiceCapability> serviceVoiceCapabilities) {
		this.serviceVoiceCapabilities = serviceVoiceCapabilities;
	}


}
