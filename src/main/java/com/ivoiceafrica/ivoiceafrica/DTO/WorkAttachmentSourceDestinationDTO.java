package com.ivoiceafrica.ivoiceafrica.DTO;

import java.util.List;

public class WorkAttachmentSourceDestinationDTO {

	private List<String> sourceLanguage;
	private List<String> destinationLanguage;
	
	public List<String> getSourceLanguage() {
		return sourceLanguage;
	}
	
	public void setSourceLanguage(List<String> sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	public List<String> getDestinationLanguage() {
		return destinationLanguage;
	}

	public void setDestinationLanguage(List<String> destinationLanguage) {
		this.destinationLanguage = destinationLanguage;
	}

	@Override
	public String toString() {
		return "WorkAttachmentSourceDestinationDTO [sourceLanguage=" + sourceLanguage + ", destinationLanguage="
				+ destinationLanguage + "]";
	}
	
	
	
}
