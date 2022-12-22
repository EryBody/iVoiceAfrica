package com.ivoiceafrica.ivoiceafrica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin-dashboard")
	public String adminDashboard() {

		return "dashboards/admin/overview/dashboard";
	}

	@GetMapping("/admin-profile")
	public String adminProfile() {

		return "dashboards/admin/overview/adminprofile";
	}

	@GetMapping("/admin-job")
	public String adminJobs() {

		return "dashboards/admin/overview/jobs/adminjobs";
	}

	@GetMapping("/admin-client")
	public String adminClient() {

		return "dashboards/admin/overview/clients/clients";
	}

	@GetMapping("/admin-freelancer")
	public String adminFreelancer() {

		return "dashboards/admin/overview/freelancers/freelancers";
	}

	@GetMapping("/admin-finance")
	public String adminFinance() {

		return "dashboards/admin/overview/finances";
	}

	@GetMapping("/admin-user")
	public String adminUser() {

		return "dashboards/admin/overview/Users/users";
	}

	@GetMapping("/add-user")
	public String addUSer() {

		return "dashboards/admin/overview/Users/newuser";
	}

	@GetMapping("/job-details")
	public String jobDetails() {

		return "dashboards/admin/overview/jobs/adminjobsdetails";
	}

	@GetMapping("/job-detail-inprogress")
	public String jobDetailInProgress() {

		return "dashboards/admin/overview/jobs/jobsinprogress";
	}

	@GetMapping("/job-detail-comnpleted")
	public String jobDetailCompleted() {

		return "dashboards/admin/overview/jobs/jobscompleted";
	}

}
