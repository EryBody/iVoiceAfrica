package com.ivoiceafrica.ivoiceafrica.DTO;

import com.ivoiceafrica.ivoiceafrica.entity.Language;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;

public class FreelancerProfileLanguageDTO {

	private Language languageId;
	private ServiceRendered serviceRendered;
	private String languageDesc;
	private String languageUpload;

	public FreelancerProfileLanguageDTO() {

	}

	public FreelancerProfileLanguageDTO(Language languageId, ServiceRendered serviceRendered, String languageDesc,
			String languageUpload) {
		this.languageId = languageId;
		this.serviceRendered = serviceRendered;
		this.languageDesc = languageDesc;
		this.languageUpload = languageUpload;
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

	public String getLanguageDesc() {
		return languageDesc;
	}

	public void setLanguageDesc(String languageDesc) {
		this.languageDesc = languageDesc;
	}

	public String getLanguageUpload() {
		return languageUpload;
	}

	public void setLanguageUpload(String languageUpload) {
		this.languageUpload = languageUpload;
	}

	@Override
	public String toString() {
		return "FreelancerProfileLanguageDTO [languageId=" + languageId + ", serviceRendered=" + serviceRendered
				+ ", languageDesc=" + languageDesc + ", languageUpload=" + languageUpload + "]";
	}

}
