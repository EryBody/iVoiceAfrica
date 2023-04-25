package com.ivoiceafrica.ivoiceafrica.DTO;

public class FreelancerProposals {

	
	private String renderId;
	private int userId;
	private String typeId;
	private int experienceInYears;
	private String servicePortfolioLink;
	private String pricingId;
	private String pricingType;
	private String freelancerPricingId;
	private double freelancerMinPrice;
	private double freelancerMaxPrice;
	
	public FreelancerProposals() {
	}
	
	public FreelancerProposals(String renderId, int userId, String typeId, int experienceInYears,
			String servicePortfolioLink, String pricingId, String pricingType, String freelancerPricingId,
			double freelancerMinPrice, double freelancerMaxPrice) {
		this.renderId = renderId;
		this.userId = userId;
		this.typeId = typeId;
		this.experienceInYears = experienceInYears;
		this.servicePortfolioLink = servicePortfolioLink;
		this.pricingId = pricingId;
		this.pricingType = pricingType;
		this.freelancerPricingId = freelancerPricingId;
		this.freelancerMinPrice = freelancerMinPrice;
		this.freelancerMaxPrice = freelancerMaxPrice;
	}
	public String getRenderId() {
		return renderId;
	}
	public void setRenderId(String renderId) {
		this.renderId = renderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public int getExperienceInYears() {
		return experienceInYears;
	}
	public void setExperienceInYears(int experienceInYears) {
		this.experienceInYears = experienceInYears;
	}
	public String getServicePortfolioLink() {
		return servicePortfolioLink;
	}
	public void setServicePortfolioLink(String servicePortfolioLink) {
		this.servicePortfolioLink = servicePortfolioLink;
	}
	public String getPricingId() {
		return pricingId;
	}
	public void setPricingId(String pricingId) {
		this.pricingId = pricingId;
	}
	public String getPricingType() {
		return pricingType;
	}
	public void setPricingTypeId(String pricingType) {
		this.pricingType = pricingType;
	}
	public String getFreelancerPricingId() {
		return freelancerPricingId;
	}
	public void setFreelancerPricingId(String freelancerPricingId) {
		this.freelancerPricingId = freelancerPricingId;
	}
	public double getFreelancerMinPrice() {
		return freelancerMinPrice;
	}
	public void setFreelancerMinPrice(double freelancerMinPrice) {
		this.freelancerMinPrice = freelancerMinPrice;
	}
	public double getFreelancerMaxPrice() {
		return freelancerMaxPrice;
	}
	public void setFreelancerMaxPrice(double freelancerMaxPrice) {
		this.freelancerMaxPrice = freelancerMaxPrice;
	}
	@Override
	public String toString() {
		return "FreelancerProposals [renderId=" + renderId + ", userId=" + userId + ", typeId=" + typeId
				+ ", experienceInYears=" + experienceInYears + ", servicePortfolioLink=" + servicePortfolioLink
				+ ", pricingId=" + pricingId + ", pricingType=" + pricingType + ", freelancerPricingId="
				+ freelancerPricingId + ", freelancerMinPrice=" + freelancerMinPrice + ", freelancerMaxPrice="
				+ freelancerMaxPrice + "]";
	}
	
	
	
	
	
}
