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
@Table(name = "service_vc")
public class ServiceVoiceCapability {

	@Id
    @GenericGenerator(name = "vc_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.VoiceCapabilityGenerator")
    @GeneratedValue(generator = "vc_id", strategy = GenerationType.SEQUENCE)  
    @Column(name="vc_id")
	private String vcId;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "voice_id")
    private VoiceCapability voiceCapability;
	 
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "render_id")
    private ServiceRendered serviceRendered;
	
    @Column(name = "v_capability")
    private String vCapability;
	
	public ServiceVoiceCapability() {
		
	}

	public ServiceVoiceCapability(String vcId, VoiceCapability voiceCapability, ServiceRendered serviceRendered,
			String vCapability) {
		this.vcId = vcId;
		this.voiceCapability = voiceCapability;
		this.serviceRendered = serviceRendered;
		this.vCapability = vCapability;
	}

	public String getVcId() {
		return vcId;
	}

	public void setVcId(String vcId) {
		this.vcId = vcId;
	}

	public VoiceCapability getVoiceCapability() {
		return voiceCapability;
	}

	public void setVoiceCapability(VoiceCapability voiceCapability) {
		this.voiceCapability = voiceCapability;
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

	
	
}
