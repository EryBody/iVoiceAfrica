package com.ivoiceafrica.ivoiceafrica.DTO;

import org.springframework.web.multipart.MultipartFile;

public class UpdateProfilePictureDTO {

	private String userId;
	private MultipartFile profilePicture;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public MultipartFile getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(MultipartFile profilePicture) {
		this.profilePicture = profilePicture;
	}

	@Override
	public String toString() {
		return "UpdateProfilePictureDTO [userId=" + userId + ", profilePicture=" + profilePicture + "]";
	}

}
