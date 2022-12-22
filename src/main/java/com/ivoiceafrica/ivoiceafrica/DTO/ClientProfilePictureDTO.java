package com.ivoiceafrica.ivoiceafrica.DTO;

import org.springframework.web.multipart.MultipartFile;

public class ClientProfilePictureDTO {

	private MultipartFile profilePicture;
	
	public ClientProfilePictureDTO() {
	}

	public MultipartFile getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(MultipartFile profilePicture) {
		this.profilePicture = profilePicture;
	}

	@Override
	public String toString() {
		return "ClientProfilePictureDTO [profilePicture=" + profilePicture + "]";
	}
	
	
	
}
