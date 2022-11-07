package com.ivoiceafrica.ivoiceafrica.DTO;

public class FreelancerProfilePictureDetailDTO {

	private String profilePicture;
	private String profileHeadline;
	
	
	public FreelancerProfilePictureDetailDTO() {
		
	}
	
	public FreelancerProfilePictureDetailDTO(String profilePicture, String profileHeadline) {
		this.profilePicture = profilePicture;
		this.profileHeadline = profileHeadline;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getProfileHeadline() {
		return profileHeadline;
	}

	public void setProfileHeadline(String profileHeadline) {
		this.profileHeadline = profileHeadline;
	}

	@Override
	public String toString() {
		return "FreelancerProfilePictureDetailDTO [profilePicture=" + profilePicture + ", profileHeadline="
				+ profileHeadline + "]";
	}
	
	
	
}
