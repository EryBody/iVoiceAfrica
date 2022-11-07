package com.ivoiceafrica.ivoiceafrica.DTO;

public class ClientProfilePictureDTO {

	private String profilePicture;
	
	public ClientProfilePictureDTO() {
	}
	
	public ClientProfilePictureDTO(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	@Override
	public String toString() {
		return "ClientProfilePictureDTO [profilePicture=" + profilePicture + "]";
	}
	
}
