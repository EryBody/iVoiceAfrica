package com.ivoiceafrica.ivoiceafrica.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ivoiceafrica.ivoiceafrica.DTO.ClientAmountDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ProfileDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.UpdatePasswordDTO;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;
import com.ivoiceafrica.ivoiceafrica.service.DurationTypeService;
import com.ivoiceafrica.ivoiceafrica.service.LanguageService;
import com.ivoiceafrica.ivoiceafrica.service.STypeService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderStatusService;

@Controller
public class MainController {

	@Autowired
	LanguageService languageService;

	@Autowired
	STypeService serviceType;

	@Autowired
	DurationTypeService durationTypeService;

	@Autowired
	WorkOrderStatusService workOrderStatusService;

	@Autowired
	CustomUserDetailService userService;

	@Autowired
	HttpSession session;

	@Autowired
	WorkOrderService workOrderService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/faq")
	public String faqs(Model model) {

		return "dashboards/clients/clientfaqs";
	}

	@GetMapping("/updatepassword")
	public String updatePassword(Model model) {

		model.addAttribute("UpdatePasswordDTO", new UpdatePasswordDTO());

		return "dashboards/clients/clientupdatepassword";
	}

	@PostMapping("/updatepassword/save")
	@ResponseBody
	public String savePassword(@ModelAttribute("UpdatePasswordDTO") UpdatePasswordDTO updatePasswordDTO, Model model) {

		String result = "";
		System.out.println("===>>> Password: " + updatePasswordDTO);

		String userId = (String) session.getAttribute("userId");
		Optional<User> user = userService.findFirstUserByUsername(userId);

		String passwordHash = user.get().getUpassword();
		String newPasswordHash = bCryptPasswordEncoder.encode(updatePasswordDTO.getNewPassword());

		// check old password
		if (bCryptPasswordEncoder.matches(updatePasswordDTO.getOldPassword(), passwordHash)) {

			// True

			System.out.println("===>>> Bcrypt Password True");
			if (updatePasswordDTO.getOldPassword().isEmpty() || updatePasswordDTO.getNewPassword().isEmpty()
					|| updatePasswordDTO.getConfirmPassword().isEmpty()) {
				
				result = "Complete all fields";
				System.out.println("===>>> Complete all fields");
			} else if (updatePasswordDTO.getOldPassword().equals(updatePasswordDTO.getNewPassword())) {
				result = "Old and new password cannot be the same";
				System.out.println("===>>> Old and new password cannot be the same");
			} else if (!updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getConfirmPassword())) {
				result = "New and confirm password must be the same";
				System.out.println("===>>> New and confirm password must be the same");
			} else {
				Optional<User> userDetails = userService.findFirstUserByUsernameAndUpassword(user.get().getUsername(),
						user.get().getUpassword());

				if (userDetails.isPresent()) {
					// update password
					int updatePasswordStatus = userService.updatePassword(newPasswordHash, user.get().getUserId());
					System.out.println("===>>> updatePasswordStatus: " + updatePasswordStatus);
					
					result = "Password Updated Successfully";
				} else {
					System.out.println("===>>> Invalid Username/Password");
					result = "Invalid Username/Password";
				}
			}

		} else {
			// false
			System.out.println("===>>> Bcrypt Password False");
			result = "Invalid Old Password";
		}

		model.addAttribute("UpdatePasswordDTO", new UpdatePasswordDTO());
		return result;
	}

	@GetMapping("/notifcations")
	public String clientNotification(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> user = userService.findFirstUserByUsername(userId);

		List<WorkOrder> workOrders = workOrderService.findWorkOrderByUserOrderByPostingDate(user.get());

		model.addAttribute("workOrdersList", workOrders);

		return "dashboards/clients/clientnotification";
	}
	
	@GetMapping("/profile")
	public String clientProfile(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		model.addAttribute("userDetails", userDetails.get());
		model.addAttribute("ProfileDTO", new ProfileDTO());

		return "dashboards/clients/clientprofile";
	}
	
	@GetMapping("/freelancer-profile")
	public String freelancerProfile(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		model.addAttribute("userDetails", userDetails.get());
		model.addAttribute("ProfileDTO", new ProfileDTO());

		return "dashboards/freelancers/freelancerprofile";
	}
	
	@GetMapping("/updatefreelancerpassword")
	public String updateFreelancerPassword(Model model) {

		model.addAttribute("UpdatePasswordDTO", new UpdatePasswordDTO());

		return "dashboards/freelancers/freelancerchangepassword";
	}
}
