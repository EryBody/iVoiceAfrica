package com.ivoiceafrica.ivoiceafrica.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ivoiceafrica.ivoiceafrica.DTO.ClientPersonalDetailDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ClientProfilePictureDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ClientSignupDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerProfilePictureDetailDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerSkillDetailDTO;

@Controller
public class ClientController {
	

	@GetMapping("/client-dashboard")
	public String clientDashboard() {
		return "dashboards/clients/newrequest";
	}
	
	@GetMapping("/freelancer-dashboard")
	public String freelancerDashboard() {
		return "dashboards/freelancers/dashboard";
	}
	
	@PostMapping("/client/signup/save")
	public String saveClientSignupInfo(@ModelAttribute("ClientSignupDTO") ClientSignupDTO clientSignupDTO, HttpSession session, Model model) {
		
		System.out.println("===>>> Client Signup details: "+clientSignupDTO);
		
		session.setAttribute("clientSignupDTO", clientSignupDTO);
		
		model.addAttribute("ClientPersonalDetailDTO", new ClientPersonalDetailDTO());
		return "onboarding/client/profilesetup";
	}
	
	@PostMapping("/client/personalDetail/save")
	public String saveClientDetail(@ModelAttribute("ClientPersonalDetailDTO") ClientPersonalDetailDTO clientPersonalDetailDTO, HttpSession session, Model model) {
		
		System.out.println("===>>> Client Personal details: "+clientPersonalDetailDTO);
		
		session.setAttribute("clientPersonalDetailDTO",  clientPersonalDetailDTO);
		
		model.addAttribute("ClientProfilePictureDTO", new ClientProfilePictureDTO());
		return "onboarding/client/uploadprofilepic";
	}
	
	@PostMapping("/client/profilePicture/save")
	public String saveClientProfilePicture(@ModelAttribute("ClientProfilePictureDTO") ClientProfilePictureDTO clientProfilePictureDTO, HttpSession session, Model model) {
		
		System.out.println("===>>> Client Profile picture info: "+clientProfilePictureDTO);
		
		session.setAttribute("clientProfilePictureDTO",  clientProfilePictureDTO);
		
		//Save all the information in the database, and create login access for freelancer
		
		
		return "onboarding/client/sign-in";
	}

}
