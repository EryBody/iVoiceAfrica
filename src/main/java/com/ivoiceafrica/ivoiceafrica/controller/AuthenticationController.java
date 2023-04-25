package com.ivoiceafrica.ivoiceafrica.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ivoiceafrica.ivoiceafrica.DTO.ClientSignupDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerSignupDTO;

@Controller
public class AuthenticationController {
	
	
	@GetMapping({"/", "/index"})
	public String root() {
		return "index";
	}

	@GetMapping("/signin")
	public String signin(Model model) {
		return "onboarding/client/sign-in";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/landing-page")
	public String landingPage() {
		return "landingpage";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}
	
	@GetMapping("/mainpage")
	public String mainpage() {
		return "onboarding/mainpage";
	}
	
	//Clients
	@GetMapping("/client-signup")
	public String clientSignup(Model model) {
		model.addAttribute("ClientSignupDTO",new ClientSignupDTO());
		return "onboarding/client/signup";
	}
	
	@GetMapping("/client-profile-setup")
	public String clientProfileSetup() {
		return "onboarding/client/profilesetup";
	}
	
	@GetMapping("/client-upload-pic")
	public String clientUploadPic() {
		return "onboarding/client/uploadprofilepic";
	}
	
	//Freelancers
	@GetMapping("/freelancer-signup")
	public String freelancerSignup(Model model) {
		model.addAttribute("FreelancerSignupDTO",new FreelancerSignupDTO());
		return "onboarding/freelancer/signup";
	}
	
	@GetMapping("/freelancer-profile-setup")
	public String freelancerProfileSetup() {
		return "onboarding/freelancer/profilesetup";
	}
	
	@GetMapping("/freelancer-profile-2")
	public String freelancerProfile2() {
		return "onboarding/freelancer/profilesetup2";
	}
	
	@GetMapping("/freelancer-profile-3")
	public String freelancerProfile3() {
		return "onboarding/freelancer/profilesetup3";
	}
	
	@GetMapping("/freelancer-profile-4")
	public String freelancerProfile4() {
		return "onboarding/freelancer/profilesetup4";
	}
	
}
