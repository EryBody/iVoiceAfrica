package com.ivoiceafrica.ivoiceafrica.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerPersonalDetailDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerProfilePictureDetailDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerSignupDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerSkillDetailDTO;

@Controller
public class FreelancerController {

	
	
	@PostMapping("/freelancer/signup/save")
	public String saveFreelancerSign(@ModelAttribute("FreelancerSignupDTO")  FreelancerSignupDTO freelancerSignupDTO, HttpSession session, Model model) {
		
		session.setAttribute("freelancerSignupDto", freelancerSignupDTO);
		
		model.addAttribute("FreelancerPersonalDetailDTO", new FreelancerPersonalDetailDTO());
		return "onboarding/freelancer/profilesetup";
	}
	
	
	@PostMapping("/freelancer/detail/save")
	public String saveFreelancerDetails(@ModelAttribute("FreelancerPersonalDetailDTO")  FreelancerPersonalDetailDTO freelancerPersonalDetailDTO, HttpSession session, Model model) {
		
		System.out.println("===>>> Freelancer personal details: "+freelancerPersonalDetailDTO.getCountry());
		
		session.setAttribute("freelancerPersonalDetailDTO", freelancerPersonalDetailDTO);
		
		model.addAttribute("FreelancerSkillDetailDTO", new FreelancerSkillDetailDTO());
		return "onboarding/freelancer/profilesetup2";
	}
	
	
	@PostMapping("/freelancer/skill/save")
	public String saveFreelancerSkill(@ModelAttribute("FreelancerSkillDetailDTO") FreelancerSkillDetailDTO freelancerSkillDetailDTO, HttpSession session, Model model) {
		
		System.out.println("===>>> Freelancer Skill details: "+freelancerSkillDetailDTO);
		
		session.setAttribute("freelancerSkillDetailDTO",  freelancerSkillDetailDTO);
		
		model.addAttribute("FreelancerProfilePictureDetailDTO", new FreelancerProfilePictureDetailDTO());
		return "onboarding/freelancer/profilesetup4";
	}
	
	
	@PostMapping("/freelancer/profilepicture/save")
	public String saveFreelancerProfile(@ModelAttribute("FreelancerProfilePictureDetailDTO")  FreelancerProfilePictureDetailDTO freelancerProfilePictureDetailDTO, HttpSession session, Model model) {
		
		System.out.println("===>>> Freelancer Profile details: "+freelancerProfilePictureDetailDTO.getProfilePicture());
		
		session.setAttribute("freelancerProfilePictureDetailDTO", freelancerProfilePictureDetailDTO);
		
		//Save all the information in the database, and create login access for freelancer
		
		
		return "onboarding/client/sign-in";
	}
}
