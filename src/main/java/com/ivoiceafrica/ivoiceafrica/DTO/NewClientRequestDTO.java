package com.ivoiceafrica.ivoiceafrica.DTO;

import org.springframework.web.multipart.MultipartFile;

public class NewClientRequestDTO {

	private String serviceType;
	private String projectTitle;
	private String projectDescription;
	private String duration;
	private double minAmount;
	private double maxAMount;
	
	private MultipartFile languageUpload1;
	private String languageSource1;
	private String languageDestination1;
	
	private MultipartFile languageUpload2; 
	private String languageSource2;
	private String languageDestination2;
	
	private MultipartFile languageUpload3; 
	private String languageSource3;
	private String languageDestination3;
	
	private MultipartFile languageUpload4;
	private String languageSource4;
	private String languageDestination4;
	
	private MultipartFile languageUpload5; 
	private String languageSource5;
	private String languageDestination5;
	
	private MultipartFile languageUpload6; 
	private String languageSource6;
	private String languageDestination6;
	
	
	public NewClientRequestDTO() {
		
	}
	
	public NewClientRequestDTO(String serviceType, String projectTitle, String projectDescription, String duration,
			double minAmount, double maxAMount, MultipartFile languageUpload1, String languageSource1,
			String languageDestination1, MultipartFile languageUpload2, String languageSource2,
			String languageDestination2, MultipartFile languageUpload3, String languageSource3,
			String languageDestination3, MultipartFile languageUpload4, String languageSource4,
			String languageDestination4, MultipartFile languageUpload5, String languageSource5,
			String languageDestination5, MultipartFile languageUpload6, String languageSourc6,
			String languageDestination6) {
		this.serviceType = serviceType;
		this.projectTitle = projectTitle;
		this.projectDescription = projectDescription;
		this.duration = duration;
		this.minAmount = minAmount;
		this.maxAMount = maxAMount;
		this.languageUpload1 = languageUpload1;
		this.languageSource1 = languageSource1;
		this.languageDestination1 = languageDestination1;
		this.languageUpload2 = languageUpload2;
		this.languageSource2 = languageSource2;
		this.languageDestination2 = languageDestination2;
		this.languageUpload3 = languageUpload3;
		this.languageSource3 = languageSource3;
		this.languageDestination3 = languageDestination3;
		this.languageUpload4 = languageUpload4;
		this.languageSource4 = languageSource4;
		this.languageDestination4 = languageDestination4;
		this.languageUpload5 = languageUpload5;
		this.languageSource5 = languageSource5;
		this.languageDestination5 = languageDestination5;
		this.languageUpload6 = languageUpload6;
		this.languageSource6 = languageSource6;
		this.languageDestination6 = languageDestination6;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	public double getMaxAMount() {
		return maxAMount;
	}

	public void setMaxAMount(double maxAMount) {
		this.maxAMount = maxAMount;
	}

	public MultipartFile getLanguageUpload1() {
		return languageUpload1;
	}

	public void setLanguageUpload1(MultipartFile languageUpload1) {
		this.languageUpload1 = languageUpload1;
	}

	public String getLanguageSource1() {
		return languageSource1;
	}

	public void setLanguageSource1(String languageSource1) {
		this.languageSource1 = languageSource1;
	}

	public String getLanguageDestination1() {
		return languageDestination1;
	}

	public void setLanguageDestination1(String languageDestination1) {
		this.languageDestination1 = languageDestination1;
	}

	public MultipartFile getLanguageUpload2() {
		return languageUpload2;
	}

	public void setLanguageUpload2(MultipartFile languageUpload2) {
		this.languageUpload2 = languageUpload2;
	}

	public String getLanguageSource2() {
		return languageSource2;
	}

	public void setLanguageSource2(String languageSource2) {
		this.languageSource2 = languageSource2;
	}

	public String getLanguageDestination2() {
		return languageDestination2;
	}

	public void setLanguageDestination2(String languageDestination2) {
		this.languageDestination2 = languageDestination2;
	}

	public MultipartFile getLanguageUpload3() {
		return languageUpload3;
	}

	public void setLanguageUpload3(MultipartFile languageUpload3) {
		this.languageUpload3 = languageUpload3;
	}

	public String getLanguageSource3() {
		return languageSource3;
	}

	public void setLanguageSource3(String languageSource3) {
		this.languageSource3 = languageSource3;
	}

	public String getLanguageDestination3() {
		return languageDestination3;
	}

	public void setLanguageDestination3(String languageDestination3) {
		this.languageDestination3 = languageDestination3;
	}

	public MultipartFile getLanguageUpload4() {
		return languageUpload4;
	}

	public void setLanguageUpload4(MultipartFile languageUpload4) {
		this.languageUpload4 = languageUpload4;
	}

	public String getLanguageSource4() {
		return languageSource4;
	}

	public void setLanguageSource4(String languageSource4) {
		this.languageSource4 = languageSource4;
	}

	public String getLanguageDestination4() {
		return languageDestination4;
	}

	public void setLanguageDestination4(String languageDestination4) {
		this.languageDestination4 = languageDestination4;
	}

	public MultipartFile getLanguageUpload5() {
		return languageUpload5;
	}

	public void setLanguageUpload5(MultipartFile languageUpload5) {
		this.languageUpload5 = languageUpload5;
	}

	public String getLanguageSource5() {
		return languageSource5;
	}

	public void setLanguageSource5(String languageSource5) {
		this.languageSource5 = languageSource5;
	}

	public String getLanguageDestination5() {
		return languageDestination5;
	}

	public void setLanguageDestination5(String languageDestination5) {
		this.languageDestination5 = languageDestination5;
	}

	public MultipartFile getLanguageUpload6() {
		return languageUpload6;
	}

	public void setLanguageUpload6(MultipartFile languageUpload6) {
		this.languageUpload6 = languageUpload6;
	}

	public String getLanguageSource6() {
		return languageSource6;
	}

	public void setLanguageSource6(String languageSource6) {
		this.languageSource6 = languageSource6;
	}

	public String getLanguageDestination6() {
		return languageDestination6;
	}

	public void setLanguageDestination6(String languageDestination6) {
		this.languageDestination6 = languageDestination6;
	}

	@Override
	public String toString() {
		return "NewClientRequestDTO [serviceType=" + serviceType + ", projectTitle=" + projectTitle
				+ ", projectDescription=" + projectDescription + ", duration=" + duration + ", minAmount=" + minAmount
				+ ", maxAMount=" + maxAMount + ", languageUpload1=" + languageUpload1 + ", languageSource1="
				+ languageSource1 + ", languageDestination1=" + languageDestination1 + ", languageUpload2="
				+ languageUpload2 + ", languageSource2=" + languageSource2 + ", languageDestination2="
				+ languageDestination2 + ", languageUpload3=" + languageUpload3 + ", languageSource3=" + languageSource3
				+ ", languageDestination3=" + languageDestination3 + ", languageUpload4=" + languageUpload4
				+ ", languageSource4=" + languageSource4 + ", languageDestination4=" + languageDestination4
				+ ", languageUpload5=" + languageUpload5 + ", languageSource5=" + languageSource5
				+ ", languageDestination5=" + languageDestination5 + ", languageUpload6=" + languageUpload6
				+ ", languageSource6=" + languageSource6 + ", languageDestination6=" + languageDestination6 + "]";
	}
	
}
