package com.ivoiceafrica.ivoiceafrica.DTO;

import org.springframework.web.multipart.MultipartFile;

public class FreelancerProfilePictureDetailDTO {

	private MultipartFile profilePicture;
	private String profileHeadline;
	
	
	public FreelancerProfilePictureDetailDTO() {
		
	}
	
	
	public FreelancerProfilePictureDetailDTO(MultipartFile profilePicture, String profileHeadline) {
		this.profilePicture = profilePicture;
		this.profileHeadline = profileHeadline;
	}


	public MultipartFile getProfilePicture() {
		return profilePicture;
	}


	public void setProfilePicture(MultipartFile profilePicture) {
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
