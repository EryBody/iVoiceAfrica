package com.ivoiceafrica.ivoiceafrica.models;

public class SkillUploadModel {

	private int language;
	private String descriptionOfVoice;
	private String documentName;
	
	public SkillUploadModel() {
		
	}
	
	public SkillUploadModel(int language, String descriptionOfVoice) {
		this.language = language;
		this.descriptionOfVoice = descriptionOfVoice;
	}

	public SkillUploadModel(int language, String descriptionOfVoice, String documentName) {
		this.language = language;
		this.descriptionOfVoice = descriptionOfVoice;
		this.documentName = documentName;
	}

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public String getDescriptionOfVoice() {
		return descriptionOfVoice;
	}

	public void setDescriptionOfVoice(String descriptionOfVoice) {
		this.descriptionOfVoice = descriptionOfVoice;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	@Override
	public String toString() {
		return "SkillUploadModel [language=" + language + ", descriptionOfVoice=" + descriptionOfVoice
				+ ", documentName=" + documentName + "]";
	}
	
}
