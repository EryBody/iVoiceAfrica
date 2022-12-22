package com.ivoiceafrica.ivoiceafrica.models;

public class ClientUploadModel {

	private String languageUpload;
	private String languageSource;
	private String languageDestination;
	
	public ClientUploadModel() {
	}

	public ClientUploadModel(String languageUpload, String languageSource, String languageDestination) {
		this.languageUpload = languageUpload;
		this.languageSource = languageSource;
		this.languageDestination = languageDestination;
	}

	public String getLanguageUpload() {
		return languageUpload;
	}

	public void setLanguageUpload(String languageUpload) {
		this.languageUpload = languageUpload;
	}

	public String getLanguageSource() {
		return languageSource;
	}

	public void setLanguageSource(String languageSource) {
		this.languageSource = languageSource;
	}

	public String getLanguageDestination() {
		return languageDestination;
	}

	public void setLanguageDestination(String languageDestination) {
		this.languageDestination = languageDestination;
	}

	@Override
	public String toString() {
		return "ClientUploadModel [languageUpload=" + languageUpload + ", languageSource=" + languageSource
				+ ", languageDestination=" + languageDestination + "]";
	}
	
}
