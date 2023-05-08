package com.ivoiceafrica.ivoiceafrica.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivoiceafrica.ivoiceafrica.DTO.ClientAmountDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ClientPersonalDetailDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ClientProfilePictureDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ClientSignupDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FilterBidsRequestDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerProposalsDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.JobStatusDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.NewClientRequestDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ProfileDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ReviewWorkOrderDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.UpdateProfilePictureDTO;
import com.ivoiceafrica.ivoiceafrica.auth.entity.Role;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.auth.entity.UserStatus;
import com.ivoiceafrica.ivoiceafrica.components.models.ClientComponentModel;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryStatus;
import com.ivoiceafrica.ivoiceafrica.entity.DurationType;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerDeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerServicePricing;
import com.ivoiceafrica.ivoiceafrica.entity.Language;
import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.ProposalStatus;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderReview;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPayments;
import com.ivoiceafrica.ivoiceafrica.models.ClientUploadModel;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryStatusService;
import com.ivoiceafrica.ivoiceafrica.service.DurationTypeService;
import com.ivoiceafrica.ivoiceafrica.service.FreelancerDeliveryAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.FreelancerPricingService;
import com.ivoiceafrica.ivoiceafrica.service.LanguageService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalStatusService;
import com.ivoiceafrica.ivoiceafrica.service.RoleService;
import com.ivoiceafrica.ivoiceafrica.service.SRenderedService;
import com.ivoiceafrica.ivoiceafrica.service.STypeService;
import com.ivoiceafrica.ivoiceafrica.service.UserStatusService;
import com.ivoiceafrica.ivoiceafrica.service.WorkFreelancerPaymentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderReviewService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderStatusService;
import com.ivoiceafrica.ivoiceafrica.service.WorkPaymentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkPaymentStatusService;
import com.ivoiceafrica.ivoiceafrica.utility.GetEndDate;

@Controller
public class ClientController {

	@Value("${upload.path}")
	String uploadDir;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	CustomUserDetailService userService;

	@Autowired
	UserStatusService userStatusService;

	@Autowired
	RoleService roleService;

	@Autowired
	LanguageService languageService;

	@Autowired
	WorkOrderService workOrderService;

	@Autowired
	STypeService serviceType;

	@Autowired
	DurationTypeService durationTypeService;

	@Autowired
	WorkOrderStatusService workOrderStatusService;

	@Autowired
	WorkOrderAttachmentService workOrderAttachmentService;

	@Autowired
	HttpSession session;

	@Autowired
	ProposalService proposalService;

	@Autowired
	ProposalStatusService proposalStatusService;

	@Autowired
	SRenderedService serviceRenderedService;

	@Autowired
	DeliveryService deliveryService;

	@Autowired
	DeliveryStatusService deliveryStatusService;

	@Autowired
	DeliveryAttachmentService deliveryAttachmentService;

	@Autowired
	WorkPaymentStatusService workPaymentStatusService;

	@Autowired
	WorkPaymentService workPaymentService;

	@Autowired
	WorkFreelancerPaymentService workFreelancerPaymentService;

	@Autowired
	FreelancerDeliveryAttachmentService freelancerDeliveryAttachmentService;

	@Autowired
	ClientComponentModel clientComponentModel;

	@Autowired
	FreelancerPricingService freelancerPricingService;

	@Autowired
	WorkOrderReviewService workOrderReviewService;

	@GetMapping("/client-dashboard")
	public String clientDashboard(Model model) {

		List<Language> languages = languageService.findAll();
		model.addAttribute("languages", languages);
		model.addAttribute("durations", durationTypeService.findAll());
		model.addAttribute("serviceTypes", serviceType.findAll());
		model.addAttribute("NewClientRequestDTO", new NewClientRequestDTO());

		return "dashboards/clients/newrequest";
	}

	@GetMapping("/client/requests/all")
	public String allRequests(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		List<WorkOrder> workOrders = workOrderService.findWorkOrderByUserOrderByPostingDate(userDetails.get());
		model.addAttribute("allClientRequests", workOrders);

		return "dashboards/clients/clientrequests";
	}

	@GetMapping("/client/job/bids")
	public String clientBids(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> user = userService.findFirstUserByUsername(userId);

		List<WorkOrder> workOrders = workOrderService.findWorkOrderByUserOrderByPostingDate(user.get());

		model.addAttribute("workOrdersList", workOrders);

		return "dashboards/clients/clientbids";
	}

	@GetMapping("/client/job/inprogress")
	public String clientInProgress(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> user = userService.findFirstUserByUsername(userId);

		// Delivery Status, Work Delivery and Delivery Attachment
		Optional<DeliveryStatus> opDeliveryStatus = deliveryStatusService.findById(5);// 5 means in Progress

		List<WorkOrdersDelivery> workOrderDelivery = deliveryService
				.findWorkOrdersDeliveryByClientUserIdAndDeliveryStatusOrderByCreatedDateDesc(user.get().getUserId(),
						opDeliveryStatus.get());

		try {

			Optional<WorkOrderStatus> workOrderStatus = workOrderStatusService.findById(5);// 5 means in Progress

			List<WorkOrder> workOrders = workOrderService
					.findWorkOrderByUserAndWorkOrderStatusOrderByPostingDate(user.get(), workOrderStatus.get());

			if (workOrders.size() > 1) {

				Optional<WorkOrderAttachment> opWorkOrderAttachment = workOrderAttachmentService
						.findFirstWorkOrderAttachmentByWorkOrder(workOrders.get(0));

				model.addAttribute("workOrdersList", workOrders);
				model.addAttribute("workOrderStatus", workOrderStatus.get());
				model.addAttribute("orderSource", opWorkOrderAttachment.get().getSource());
				model.addAttribute("orderDestination", opWorkOrderAttachment.get().getDestination());
			}

		} catch (Exception ex) {
			System.out.println("====>>>> Error: " + ex.getMessage());
		}

		model.addAttribute("workOrderDeliveryList", workOrderDelivery);
		model.addAttribute("opDeliveryStatus", opDeliveryStatus.get());

		return "dashboards/clients/inprogress";
	}

	@PostMapping("/client/amount/set-job-amount")
	public String client(@ModelAttribute("clientAmountDTO") ClientAmountDTO clientAmountDTO,
			BindingResult bindingResultModel, Model model, RedirectAttributes attributes) {

		try {

			String userId = (String) session.getAttribute("userId");
			Optional<User> clientUserId = userService.findFirstUserByUsername(userId);

			Optional<User> user = userService.findFirstUserByUsername(clientAmountDTO.getUserID());
			Optional<WorkOrder> workOrder = workOrderService.findById(clientAmountDTO.getWorkOrderID());

			Optional<DeliveryStatus> deliveryStatus = deliveryStatusService.findById(5);// 5 means inprogress status

			WorkOrdersDelivery workDelivery = new WorkOrdersDelivery();
			String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String sd = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			Date startDate;
			try {
				startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sd);
				Date endDate = GetEndDate.calculateNextSettleDate(startDate,
						workOrder.get().getDuration().getDurationId());

				String ed = new SimpleDateFormat("yyyy-MM-dd").format(endDate);

				System.out.println("EndDate: " + endDate);
				System.out.println("==>> ED: " + ed);

//				workDelivery.setDeliveryId("");
				workDelivery.setWorkOrder(workOrder.get());
				workDelivery.setClientUserId(clientUserId.get().getUserId());
				workDelivery.setUser(user.get());
				workDelivery.setAmount(String.valueOf(clientAmountDTO.getClientAmount()));
				workDelivery.setDeliveryStatus(deliveryStatus.get());
				workDelivery.setStartDate(sd);
				workDelivery.setEndDate(ed);
				workDelivery.setCreatedDate(createdDate);

				deliveryService.save(workDelivery);

				Optional<WorkOrdersDelivery> opWorkOrderDelivery = deliveryService
						.findFirstWorkOrdersDeliveryByUserOrderByCreatedDateDesc(user.get());

				List<WorkOrderAttachment> workOrderAttachments = workOrderAttachmentService
						.findWorkOrderAttachmentByWorkOrder(workOrder.get());

				for (WorkOrderAttachment attachment : workOrderAttachments) {

					DeliveryAttachment deliveryAttachment = new DeliveryAttachment();
//					deliveryAttachment.setDeliveryAttachId("");
					deliveryAttachment.setWorkOrderDelivery(opWorkOrderDelivery.get());
					deliveryAttachment.setLinkMediaFile(attachment.getLinkMediaFile());
					deliveryAttachment.setDescription(attachment.getDescription());
					deliveryAttachment.setSource(attachment.getSource());
					deliveryAttachment.setDestination(attachment.getDestination());

					deliveryAttachmentService.save(deliveryAttachment);
				}

				int updateWorkOrderStatus = workOrderService.updateWorkOrderStatus(5, clientAmountDTO.getWorkOrderID());
				System.out.println("===>>> updateWorkOrderStatus Inprogess: " + updateWorkOrderStatus);

//				attributes.addFlashAttribute("message", "Great, your job has been sent successfully");
				return "redirect:/select-payment-option/" + user.get().getUsername() + "/"
						+ clientAmountDTO.getProposalId();

			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("Exception in date: " + e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception: " + e.getMessage());
		}

		attributes.addFlashAttribute("message", "An error occured while creating job");
		return "redirect:/client-dashboard";
	}

	@GetMapping("/client-inprogress-details/{workOrderId}/{deliveryId}")
	public String inProgressDetails(@PathVariable(value = "workOrderId") String workOrderId,
			@PathVariable(value = "deliveryId") String deliveryId, Model model) {

		Optional<WorkOrderStatus> workOrderStatus = workOrderStatusService.findById(5);// 5 means in Progress

		Optional<WorkOrder> opWorkOrder = workOrderService.findById(workOrderId);

		// Delivery Status, Work Delivery and Delivery Attachment

		Optional<DeliveryStatus> opDeliveryStatus = deliveryStatusService.findById(5);// 5 means in Progress

		Optional<WorkOrdersDelivery> opDeliveryDetails = deliveryService.findById(deliveryId);

		List<WorkOrdersDelivery> workOrderDelivery = deliveryService
				.findWorkOrdersDeliveryByDeliveryStatusOrderByCreatedDateDesc(opDeliveryStatus.get());

		Optional<WorkOrderAttachment> opWorkOrderAttachment = workOrderAttachmentService
				.findFirstWorkOrderAttachmentByWorkOrder(opWorkOrder.get());

		List<DeliveryAttachment> deliveryAttachment = deliveryAttachmentService
				.findDeliveryAttachmentByWorkOrderDelivery(opDeliveryDetails.get());

		model.addAttribute("deliveryAttachmentList", deliveryAttachment);
		model.addAttribute("opDeliveryDetails", opDeliveryDetails.get());
		model.addAttribute("workOrderStatus", workOrderStatus.get());
		model.addAttribute("workOrderDeliveryList", workOrderDelivery);
		model.addAttribute("opDeliveryStatus", opDeliveryStatus.get());
		model.addAttribute("orderSource", opWorkOrderAttachment.get().getSource());
		model.addAttribute("orderDestination", opWorkOrderAttachment.get().getDestination());

		List<JobStatusDTO> jobStatuses = getJobStatuses();

		System.out.println("===>>> jobStatuses: " + jobStatuses);

		model.addAttribute("jobStatuses", jobStatuses);

		return "dashboards/clients/inprogressjobdetails";
	}

	@GetMapping("/client/job/finished")
	public String clientFinishedJobs(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> user = userService.findFirstUserByUsername(userId);

		Optional<WorkOrderStatus> workOrderStatus = workOrderStatusService.findById(9);// 9 means finished

		List<WorkOrder> workOrders = workOrderService
				.findWorkOrderByUserAndWorkOrderStatusOrderByPostingDate(user.get(), workOrderStatus.get());

		System.out.println("===>>> workOrders: " + workOrders);

		// Delivery Status, Work Delivery and Delivery Attachment
		Optional<DeliveryStatus> opDeliveryStatus = deliveryStatusService.findById(9);// 9 means finished

		List<WorkOrdersDelivery> workOrderDelivery = deliveryService
				.findWorkOrdersDeliveryByClientUserIdAndDeliveryStatusOrderByCreatedDateDesc(user.get().getUserId(),
						opDeliveryStatus.get());

		System.out.println("===>>> workOrderDelivery: " + workOrderDelivery.size());

		try {

			if (!workOrders.isEmpty()) {
				Optional<WorkOrderAttachment> opWorkOrderAttachment = workOrderAttachmentService
						.findFirstWorkOrderAttachmentByWorkOrder(workOrders.get(0));

				model.addAttribute("orderSource", opWorkOrderAttachment.get().getSource());
				model.addAttribute("orderDestination", opWorkOrderAttachment.get().getDestination());
			}

		} catch (Exception ex) {
			System.out.println("====>>>> Error: " + ex.getMessage());
		}

		model.addAttribute("workOrdersList", workOrders);
		model.addAttribute("workOrderStatus", workOrderStatus.get());
		model.addAttribute("workOrderDeliveryList", workOrderDelivery);
		model.addAttribute("opDeliveryStatus", opDeliveryStatus.get());

		return "dashboards/clients/clientfinished";
	}

	@GetMapping("/client-finished-details/{workOrderId}/{deliveryId}")
	public String clientFinishedDetails(@PathVariable(value = "workOrderId") String workOrderId,
			@PathVariable(value = "deliveryId") String deliveryId, Model model) {

		Optional<WorkOrderStatus> workOrderStatus = workOrderStatusService.findById(9);// 9 means in Progress

		Optional<WorkOrder> opWorkOrder = workOrderService.findById(workOrderId);

		// Delivery Status, Work Delivery and Delivery Attachment

		Optional<DeliveryStatus> opDeliveryStatus = deliveryStatusService.findById(9);// 9 means in Progress

		Optional<WorkOrdersDelivery> opDeliveryDetails = deliveryService.findById(deliveryId);

		System.out.println("===>>> opDeliveryDetails: " + opDeliveryDetails);

		List<WorkOrdersDelivery> workOrderDelivery = deliveryService
				.findWorkOrdersDeliveryByDeliveryStatusOrderByCreatedDateDesc(opDeliveryStatus.get());

		Optional<WorkOrderAttachment> opWorkOrderAttachment = workOrderAttachmentService
				.findFirstWorkOrderAttachmentByWorkOrder(opWorkOrder.get());

		List<DeliveryAttachment> deliveryAttachment = deliveryAttachmentService
				.findDeliveryAttachmentByWorkOrderDelivery(opDeliveryDetails.get());

		List<FreelancerDeliveryAttachment> fDeliveryAttachments = freelancerDeliveryAttachmentService
				.findFreelancerDeliveryAttachmentByWorkOrderDeliveryOrderByEntryDateDesc(opDeliveryDetails.get());

		System.out.println("===>>>> deliveryAttachment: " + deliveryAttachment);
		
		//Get Reviews
		List<WorkOrderReview> reviews = workOrderReviewService.findWorkOrderReviewByworkOrderOrderByEntryDateDesc(opWorkOrder.get());

		model.addAttribute("reviews", reviews);
		model.addAttribute("deliveryAttachmentList", fDeliveryAttachments);
		model.addAttribute("opDeliveryDetails", opDeliveryDetails.get());
		model.addAttribute("workOrderStatus", workOrderStatus.get());
		model.addAttribute("workOrderDeliveryList", workOrderDelivery);
		model.addAttribute("opDeliveryStatus", opDeliveryStatus.get());
		model.addAttribute("orderSource", opWorkOrderAttachment.get().getSource());
		model.addAttribute("orderDestination", opWorkOrderAttachment.get().getDestination());

		List<JobStatusDTO> jobStatuses = getJobStatuses();

		System.out.println("===>>> jobStatuses: " + jobStatuses);

		model.addAttribute("jobStatuses", jobStatuses);

		return "dashboards/clients/clientfinishedDetails";
	}
	

	@GetMapping("/client/finance/all")
	public String clientAllFinance(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> user = userService.findFirstUserByUsername(userId);

		List<WorkPayments> workPayments = workPaymentService.findWorkPaymentsByClientIdOrderByEntryDateDesc(user.get());

		double totalAmount = 0.0;
		for (WorkPayments payment : workPayments) {
			totalAmount += payment.getAmount();
		}

		model.addAttribute("totalAmount", totalAmount);
		model.addAttribute("workPayments", workPayments);

		return "dashboards/clients/clientfinance";
	}

	@PostMapping("/profile/save")
	public String saveProfile(@ModelAttribute("ProfileDTO") ProfileDTO profileDTO, Model model) {

		String redirectUrl = "";

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		System.out.println("===>>> ProfileDTO: " + profileDTO);

		profileDTO.setUserId(userDetails.get().getUserId());
		int updateUserInfoStatus = userService.updateUserInfo(profileDTO);

		System.out.println("Update Status: " + updateUserInfoStatus);

		model.addAttribute("userDetails", userDetails.get());

		String userRole = (String) session.getAttribute("userRole");
		if (userRole.equals("ROLE_CLIENT")) {
			redirectUrl = "redirect:/profile";
		} else if (userRole.equals("ROLE_FREELANCER")) {
			redirectUrl = "redirect:/freelancer-profile";
		}

		return redirectUrl;
	}

	@GetMapping("/clientFreelancerProfile/{id}/{user}")
	public String freelancerPortfolio(@PathVariable(value = "id") String id, @PathVariable(value = "user") String user,
			Model model) {

		try {

			Optional<WorkOrder> workOrder = workOrderService.findById(id);

			List<WorkOrderAttachment> workOrderAttachments = workOrderAttachmentService
					.findWorkOrderAttachmentByWorkOrder(workOrder.get());

			Optional<User> opUser = userService.findFirstUserByUsername(user);

			List<ServiceRendered> servicesRendered = serviceRenderedService.findServiceRenderedListByUser(opUser.get());

			model.addAttribute("workOrderDetails", workOrder.get());
			model.addAttribute("userDetails", opUser.get());
			model.addAttribute("servicesRendered", servicesRendered);

		} catch (Exception ex) {
			model.addAttribute("workOrderFirstAttachment", new WorkOrderAttachment());
		}

		return "dashboards/clients/freelancerprofile";
	}

	@GetMapping("/client/details/{id}")
	public String requestDetail(@PathVariable(value = "id") String id, Model model) {

		try {
			Optional<WorkOrder> workOrder = workOrderService.findById(id);
			System.out.println("===>>> workOrderDetails: " + workOrder.get());

			Optional<WorkOrderAttachment> workOrderAttachment = workOrderAttachmentService
					.findFirstWorkOrderAttachmentByWorkOrder(workOrder.get());
			System.out.println("===>>> workOrderFirstAttachment: " + workOrderAttachment.get());

			List<WorkOrderAttachment> workOrderAttachments = workOrderAttachmentService
					.findWorkOrderAttachmentByWorkOrder(workOrder.get());
			System.out.println("===>>> workOrderAttachmentList: " + workOrderAttachments);

			model.addAttribute("workOrderAttachmentList", workOrderAttachments);
			model.addAttribute("workOrderFirstAttachment", workOrderAttachment.get());
			model.addAttribute("workOrderDetails", workOrder.get());

			List<JobStatusDTO> jobStatuses = getJobStatuses();

			System.out.println("===>>> jobStatuses: " + jobStatuses);
			model.addAttribute("jobStatuses", jobStatuses);

		} catch (Exception ex) {
			System.out.println("===>>> workOrderFirstAttachment: " + new WorkOrderAttachment());
			model.addAttribute("workOrderFirstAttachment", new WorkOrderAttachment());
		}

		return "dashboards/clients/clientrequestdetails";
	}

	@GetMapping("/client/bidsdetails/{id}")
	public String bidDetails(@PathVariable(value = "id") String id, Model model) {

		try {

			Optional<WorkOrder> workOrder = workOrderService.findById(id);

			System.out.println("===>>> workOrder: " + workOrder.get());

			Optional<WorkOrderAttachment> workOrderAttachment = workOrderAttachmentService
					.findFirstWorkOrderAttachmentByWorkOrder(workOrder.get());

			Optional<ProposalStatus> proposalStatus = proposalStatusService.findById(9);// 11 Means Freelancer accepted
																							// request Status
			List<Proposal> clientProposals = proposalService
					.findProposalByWorkOrderAndProposalStatusOrderByCreatedDate(workOrder.get(), proposalStatus.get());

			model.addAttribute("clientProposalList", clientProposals);
			model.addAttribute("workOrderFirstAttachment", workOrderAttachment.get());
			model.addAttribute("workOrderDetails", workOrder.get());

		} catch (Exception ex) {
			model.addAttribute("workOrderFirstAttachment", new WorkOrderAttachment());
		}

		return "dashboards/clients/clientbidsdetails";
	}

	@GetMapping("/clientProfileOverview/{id}/{proposalId}")
	public String profileOverview(@PathVariable(value = "id") String id,
			@PathVariable(value = "proposalId") String proposalId, Model model) {

		try {

			System.out.println("===>>> proposalId: " + proposalId);

			Optional<Proposal> proposalOp = proposalService.findById(proposalId);
			System.out.println("===>>> proposalOp: " + proposalOp);

			Optional<WorkOrder> workOrder = workOrderService.findById(id);

			List<WorkOrderAttachment> workOrderAttachments = workOrderAttachmentService
					.findWorkOrderAttachmentByWorkOrder(workOrder.get());

			Optional<User> opUser = userService.findFirstUserByUsername(proposalOp.get().getUser().getUsername());

			List<ServiceRendered> servicesRendered = serviceRenderedService.findServiceRenderedListByUser(opUser.get());

			model.addAttribute("workOrderDetails", workOrder.get());
			model.addAttribute("userDetails", opUser.get());
			model.addAttribute("servicesRendered", servicesRendered);
			model.addAttribute("proposalDetails", proposalOp.get());
			model.addAttribute("clientAmountDTO", new ClientAmountDTO());

		} catch (Exception ex) {
			model.addAttribute("workOrderFirstAttachment", new WorkOrderAttachment());
		}

		return "dashboards/clients/profile2";
	}

	@GetMapping("/clientFreelancerOverview/{id}/{user}")
	public String clientFreelancerOverview(@PathVariable(value = "id") String id,
			@PathVariable(value = "user") String userId, Model model) {

		try {

			Optional<WorkOrder> workOrder = workOrderService.findById(id);

			List<WorkOrderAttachment> workOrderAttachments = workOrderAttachmentService
					.findWorkOrderAttachmentByWorkOrder(workOrder.get());

			Optional<User> opUser = userService.findFirstUserByUsername(userId);

			List<ServiceRendered> servicesRendered = serviceRenderedService.findServiceRenderedListByUser(opUser.get());

			model.addAttribute("workOrderDetails", workOrder.get());
			model.addAttribute("userDetails", opUser.get());
			model.addAttribute("servicesRendered", servicesRendered);
			model.addAttribute("clientAmountDTO", new ClientAmountDTO());

		} catch (Exception ex) {
			model.addAttribute("workOrderFirstAttachment", new WorkOrderAttachment());
		}

		return "dashboards/clients/profile2";
	}

	@PostMapping("/client/signup/save")
	public String saveClientSignupInfo(@ModelAttribute("ClientSignupDTO") ClientSignupDTO clientSignupDTO,
			HttpSession session, Model model) {

		User user = userService.findUserDetailsByUsername(clientSignupDTO.getEmail());

		System.out.println("===>>> User: " + user);

		if (user != null) {

			model.addAttribute("message", "This user already exist");
			model.addAttribute("ClientPersonalDetailDTO", new ClientPersonalDetailDTO());
			return "onboarding/client/signup";

		} else {
			System.out.println("===>>> Client Signup details: " + clientSignupDTO);
			session.setAttribute("clientSignupDTO", clientSignupDTO);

			model.addAttribute("ClientPersonalDetailDTO", new ClientPersonalDetailDTO());

			return "onboarding/client/profilesetup";
		}

	}

	@PostMapping("/client/personalDetail/save")
	public String saveClientDetail(
			@ModelAttribute("ClientPersonalDetailDTO") ClientPersonalDetailDTO clientPersonalDetailDTO,
			HttpSession session, Model model) {

		System.out.println("===>>> Client Personal details: " + clientPersonalDetailDTO);

		session.setAttribute("clientPersonalDetailDTO", clientPersonalDetailDTO);

		model.addAttribute("ClientProfilePictureDTO", new ClientProfilePictureDTO());
		return "onboarding/client/uploadprofilepic";
	}

	@PostMapping("/client/profilePicture/save")
	public String saveClientProfilePicture(
			@ModelAttribute("ClientProfilePictureDTO") ClientProfilePictureDTO clientProfilePictureDTO,
			BindingResult bindingResult, @RequestParam("profilePicture") MultipartFile file, HttpSession session,
			Model model, RedirectAttributes attributes) {

		String imageUUID = "";

		System.out.println("===>>> uploads : " + uploadDir);

		try {

			System.out.println("===>>> Client Profile picture info: " + clientProfilePictureDTO);

			session.setAttribute("clientProfilePictureDTO", clientProfilePictureDTO);

			// Save all the information in the database, and create login access for client
			ClientSignupDTO clientSignupdto = (ClientSignupDTO) session.getAttribute("clientSignupDTO");

			ClientPersonalDetailDTO clientPersonalDetail = (ClientPersonalDetailDTO) session
					.getAttribute("clientPersonalDetailDTO");

			String fileSize = FreelancerController.getDynamicSpace(file.getSize());

			if (!fileSize.equals("2 MiB")) {
				System.out.println("fileSize: " + fileSize);
				if (!file.isEmpty()) {
					imageUUID = file.getOriginalFilename();
					Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
					Files.write(fileNameAndPath, file.getBytes());
				} else {
					imageUUID = clientSignupdto.getEmail();
				}
			}

			if (clientSignupdto != null || clientPersonalDetail != null) {
				String modifiedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

				User user = new User();

				// Set user Role
				List<Role> roles = new ArrayList<>();
				roles.add(roleService.findById(3).get());// 4 means freelancer role, 3 means client role, 2 means
															// supervisor role, 1 means admin role
				user.setRoles(roles);

				// getUserStatus
				Optional<UserStatus> userStatus = userStatusService.findById(2);// 2 means active from user status table

				String password = clientSignupdto.getPassword();

				user.setUserId(0);
				user.setUsername(clientSignupdto.getEmail());
				user.setUpassword(bCryptPasswordEncoder.encode(password));
				user.setFirstName(clientPersonalDetail.getFirstName());
				user.setLastName(clientPersonalDetail.getLastName());
				user.setCountry(clientPersonalDetail.getCountry());
				user.setCountryCode(clientPersonalDetail.getCountryCode());
				user.setPhone(clientPersonalDetail.getMobileNumber());
				user.setEmailAddress(clientSignupdto.getEmail());
				user.setUserStatus(userStatus.get());
				user.setGender(clientPersonalDetail.getGender());
				user.setNationality(clientPersonalDetail.getNationality());
				user.setAddress(clientPersonalDetail.getStreetAddress());
				user.setSummary("");
				user.setEducation("");
				user.setMiddleName("");
				user.setPostalCode(clientPersonalDetail.getPostalCode());
				user.setProfilePicture(imageUUID);
				user.setModifiedDate(modifiedDate);
				user.setCreatedDate(createdDate);

				System.out.println("===>>>User Data: " + user.toString());

				userService.save(user);

				attributes.addFlashAttribute("message", "Great, your profile has been created!");
				return "redirect:/signin";
			} else {

				return "onboarding/client/uploadprofilepic";
			}

		} catch (Exception ex) {
			System.out.println("===>>> Exception: " + ex.getMessage());
		}

		return "onboarding/client/uploadprofilepic";
	}

	@PostMapping("/client/new/request/save")
	public String saveNewClientRequest(@ModelAttribute("NewClientRequestDTO") NewClientRequestDTO newClientRequestDTO,
			BindingResult bindingResult, Model model, RedirectAttributes attributes, HttpSession session) {

		String userId = (String) session.getAttribute("userId");

		System.out.println("===>>> userId: " + userId);

		String serviceTypeId = newClientRequestDTO.getServiceType();
		System.out.println("===>>> serviceTypeId: " + serviceTypeId);

		Optional<User> userDetails = userService.findFirstUserByUsername(userId);
		System.out.println("===>>> userDetails: " + userDetails);

		Optional<ServiceType> serviceTypeDetail = serviceType.findById(serviceTypeId);
		System.out.println("===>>> serviceTypeDetail: " + serviceTypeDetail);

		Optional<DurationType> durationDetail = durationTypeService
				.findById(Integer.parseInt(newClientRequestDTO.getDuration()));
		System.out.println("===>>> durationDetail: " + durationDetail);

		Optional<WorkOrderStatus> workOrderStatus = workOrderStatusService.findById(1); // 1 Means Pending
		System.out.println("===>>> workOrderStatus: " + workOrderStatus);

		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String postingDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		double minAmount = 0.0;
		double maxAmount = 0.0;

		if (newClientRequestDTO.getRateSelection().equals("perword")) {
			minAmount = newClientRequestDTO.getMinAmount();
			maxAmount = newClientRequestDTO.getMaxAmount();
		} else if (newClientRequestDTO.getRateSelection().equals("perseconds")) {
			minAmount = newClientRequestDTO.getMinAmountPerSeconds();
			maxAmount = newClientRequestDTO.getMaxAmountPerSeconds();
		} else if (newClientRequestDTO.getRateSelection().equals("perminutes")) {
			minAmount = newClientRequestDTO.getMinAmountPerMinute();
			maxAmount = newClientRequestDTO.getMaxAmountPerMinute();
		} else if (newClientRequestDTO.getRateSelection().equals("perhour")) {
			minAmount = newClientRequestDTO.getMinAmountPerHour();
			maxAmount = newClientRequestDTO.getMaxAmountPerHour();
		} else if (newClientRequestDTO.getRateSelection().equals("perpage")) {
			minAmount = newClientRequestDTO.getMinAmountPerPage();
			maxAmount = newClientRequestDTO.getMaxAmountPerPage();
		}

		WorkOrder wo = new WorkOrder();
//		wo.setWorkId("");
		wo.setWorkTitle(newClientRequestDTO.getProjectTitle());
		wo.setUser(userDetails.get());
		wo.setServiceType(serviceTypeDetail.get());
		wo.setDuration(durationDetail.get());
		wo.setWorkOrderStatus(workOrderStatus.get());
		wo.setDescription(newClientRequestDTO.getProjectDescription());
		wo.setMinAmount(minAmount);
		wo.setMaxAmount(maxAmount);
		wo.setWorkRate(newClientRequestDTO.getRateSelection());
		wo.setModifiedDate(modifiedDate);
		wo.setPostingDate(postingDate);

		workOrderService.save(wo);

		System.out.println("===>>> Work Order Saved:");

		List<ClientUploadModel> uploads = new ArrayList<>();

		if (!newClientRequestDTO.getLanguageUpload1().isEmpty()) {
			String languageUpload1 = getUploadFileName(newClientRequestDTO.getLanguageUpload1());
			uploads.add(new ClientUploadModel(languageUpload1, newClientRequestDTO.getLanguageSource1(),
					newClientRequestDTO.getLanguageDestination1()));
		}

		if (!newClientRequestDTO.getLanguageUpload2().isEmpty()) {
			String languageUpload2 = getUploadFileName(newClientRequestDTO.getLanguageUpload2());
			uploads.add(new ClientUploadModel(languageUpload2, newClientRequestDTO.getLanguageSource2(),
					newClientRequestDTO.getLanguageDestination2()));
		}

		if (!newClientRequestDTO.getLanguageUpload3().isEmpty()) {
			String languageUpload3 = getUploadFileName(newClientRequestDTO.getLanguageUpload3());
			uploads.add(new ClientUploadModel(languageUpload3, newClientRequestDTO.getLanguageSource3(),
					newClientRequestDTO.getLanguageDestination3()));
		}

		if (!newClientRequestDTO.getLanguageUpload4().isEmpty()) {
			String languageUpload4 = getUploadFileName(newClientRequestDTO.getLanguageUpload4());
			uploads.add(new ClientUploadModel(languageUpload4, newClientRequestDTO.getLanguageSource4(),
					newClientRequestDTO.getLanguageDestination4()));
		}

		if (!newClientRequestDTO.getLanguageUpload5().isEmpty()) {
			String languageUpload5 = getUploadFileName(newClientRequestDTO.getLanguageUpload5());
			uploads.add(new ClientUploadModel(languageUpload5, newClientRequestDTO.getLanguageSource5(),
					newClientRequestDTO.getLanguageDestination5()));
		}

		if (!newClientRequestDTO.getLanguageUpload6().isEmpty()) {
			String languageUpload6 = getUploadFileName(newClientRequestDTO.getLanguageUpload6());
			uploads.add(new ClientUploadModel(languageUpload6, newClientRequestDTO.getLanguageSource6(),
					newClientRequestDTO.getLanguageDestination6()));
		}

		Optional<WorkOrder> workOrder = workOrderService
				.findFirstWorkOrderByUserOrderByPostingDateDesc(userDetails.get());
		System.out.println("===>>> workOrder: " + workOrder.get());

		for (ClientUploadModel upload : uploads) {
			System.out.println("===>>> ClientUploadModel: " + upload);

			WorkOrderAttachment attach = new WorkOrderAttachment();
//			attach.setAttachId("");
			attach.setWorkOrder(workOrder.get());
			attach.setSource(upload.getLanguageSource());
			attach.setDestination(upload.getLanguageDestination());
			attach.setDescription("Description");
			attach.setLinkMediaFile(upload.getLanguageUpload());

			workOrderAttachmentService.save(attach);
		}

		System.out.println("===>>> Work Order Attachment Saved");

		attributes.addFlashAttribute("message",
				"Great, work order created, kindly go to bids to select a freelancer for work order.");

		WorkOrder workObj = workOrderService.findLastWorkOrder(1);

		System.out.println("===>>> Work Order Id: " + workObj.getWorkId());

		return "redirect:/client/details/" + workObj.getWorkId();
	}

	public String getUploadFileName(MultipartFile file) {
		String filename = "";

		try {
			System.out.println("===>>> Multipart File: " + file.getSize());

			if (!file.isEmpty()) {
				filename = file.getOriginalFilename();
				System.out.println("===>>> Multipart File: " + filename);

				File fileNameAndPath = new File(uploadDir + "/" + filename);

				FileOutputStream output = new FileOutputStream(fileNameAndPath);
				output.write(file.getBytes());
				output.close();

			} else {
				filename = "";
			}

		} catch (Exception ex) {
			System.out.println("===>>> Upload File Exception: " + ex.getMessage());
		}

		return filename;
	}

	public Resource load(String filename) {

		final Path root = Paths.get(uploadDir);

		System.out.println("===>>>> Path Root: " + root);

		try {
			Path file = root.resolve(filename);

			System.out.println("===>>>> Path File: " + file);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (Exception e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	// This makes the app slow
	@GetMapping("/images/view/slow")
	public ResponseEntity<Resource> getImage(@Param(value = "filename") String filename) {
		Resource file = load(filename);

		if (file.exists() || file.isReadable()) {
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
					.body(file);
		} else {
			String fileOutput = "no filename";
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileOutput + "\"").body(file);
		}

	}

	@GetMapping("/images/view")
	public @ResponseBody void affichimage(@Param(value = "filename") String filename, HttpServletResponse response,
			HttpServletRequest request) {

		try {

			String file = uploadDir + "/" + filename;

			System.out.println("===>>> file: " + file + " :filename: " + filename);

			File imageFile = new File(file);

			String mimeType = URLConnection.guessContentTypeFromName(imageFile.getName());
			if (mimeType == null) {
				// unknown mimetype so set the mimetype to application/octet-stream
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			InputStream in = new FileInputStream(imageFile);

			FileCopyUtils.copy(in, response.getOutputStream());

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("===>>> Exception: " + ex.getMessage());
		}
	}

	@GetMapping("/files/download")
	public void downloadFile(@Param(value = "id") String id, Model model, HttpServletResponse response)
			throws IOException {

		String fileName = id;
		String fileUrl = uploadDir.concat("/").concat(fileName);

		File file = new File(fileUrl);
		if (file.exists()) {

			// get the mimetype
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				// unknown mimetype so set the mimetype to application/octet-stream
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			/**
			 * In a regular HTTP response, the Content-Disposition response header is a
			 * header indicating if the content is expected to be displayed inline in the
			 * browser, that is, as a Web page or as part of a Web page, or as an
			 * attachment, that is downloaded and saved locally.
			 * 
			 */

			/**
			 * Here we have mentioned it to show inline
			 */
//			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

			// Here we have mentioned it to show as attachment
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

			System.out.println("=====>>>>> downloaded File: " + fileUrl);

		} else {
			model.addAttribute("message", "File does not exist, if issues persist contact admin");
		}
	}

	@GetMapping("/files/load")
	@ResponseBody
	public String showImage(@Param("id") String id, Model model, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("===>>> ID: " + id);
		String filePath = uploadDir;

		String fileName = id;
		String fileUrl = filePath.concat("/").concat(fileName);

		System.out.println("===>>> fileUrl: " + fileUrl);

		return fileUrl;

	}

	public List<JobStatusDTO> getJobStatuses() {
		List<JobStatusDTO> jobStatusList = new ArrayList<>();

		List<WorkOrderStatus> workOrderStatus = workOrderStatusService.findAll();

		for (WorkOrderStatus wo : workOrderStatus) {
			JobStatusDTO jobStatusDto = new JobStatusDTO();

			if (wo.getWoStatusId() == 1) {// Pending
				jobStatusDto.setJobStatusId(wo.getWoStatusId());
				jobStatusDto.setJobStatusName(wo.getStatus());
				jobStatusList.add(jobStatusDto);
			}

			if (wo.getWoStatusId() == 7) {// Freelancer Searching
				jobStatusDto.setJobStatusId(wo.getWoStatusId());
				jobStatusDto.setJobStatusName(wo.getStatus());
				jobStatusList.add(jobStatusDto);
			}

			if (wo.getWoStatusId() == 5) {// In progress
				jobStatusDto.setJobStatusId(wo.getWoStatusId());
				jobStatusDto.setJobStatusName(wo.getStatus());
				jobStatusList.add(jobStatusDto);
			}

			if (wo.getWoStatusId() == 4) {// Reviewing
				jobStatusDto.setJobStatusId(wo.getWoStatusId());
				jobStatusDto.setJobStatusName(wo.getStatus());
				jobStatusList.add(jobStatusDto);
			}

			if (wo.getWoStatusId() == 2) {// Completed
				jobStatusDto.setJobStatusId(wo.getWoStatusId());
				jobStatusDto.setJobStatusName(wo.getStatus());
				jobStatusList.add(jobStatusDto);
			}

			if (wo.getWoStatusId() == 9) {// Finished
				jobStatusDto.setJobStatusId(wo.getWoStatusId());
				jobStatusDto.setJobStatusName(wo.getStatus());
				jobStatusList.add(jobStatusDto);
			}

		}

		return jobStatusList;
	}

	@PostMapping("/replace-profile-picture")
	public String replaceProfilePicture(
			@ModelAttribute("UpdateProfilePictureDTO") UpdateProfilePictureDTO updateProfilePictureDTO,
			@RequestParam("profilePicture") MultipartFile profilePicture, RedirectAttributes attributes,
			HttpSession session, Model model) {

		System.out.println("===>>> update profile picture Detail: " + updateProfilePictureDTO);
		String imageUUID = "";

		try {

			String fileSize = FreelancerController.getDynamicSpace(profilePicture.getSize());

			if (!fileSize.equals("2 MiB")) {
				System.out.println("fileSize: " + fileSize);
				if (!profilePicture.isEmpty()) {
					imageUUID = profilePicture.getOriginalFilename();
					Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
					Files.write(fileNameAndPath, profilePicture.getBytes());
				} else {
					imageUUID = updateProfilePictureDTO.getUserId();
				}
			}

			// update db;
			Optional<User> userDetails = userService.findFirstUserByUsername(updateProfilePictureDTO.getUserId());
			int updateProfilePicture = userService.updateProfilePicture(imageUUID, userDetails.get().getUserId());
			System.out.println("===>>> updateProfilePicture: " + updateProfilePicture);

			attributes.addFlashAttribute("message", "Profile picture change successfully.");

		} catch (Exception ex) {
			attributes.addFlashAttribute("message", "File does not exist, if issues persist contact admin.");
		}

		return "redirect:/profile";
	}

	//Old implementation of selecting freelancers
	@GetMapping("/client/bids-client-details/{id}")
	public String bidDetailsForClient(@PathVariable(value = "id") String id, Model model) {

		try {

			Optional<WorkOrder> workOrder = workOrderService.findById(id);

			Optional<WorkOrderAttachment> workOrderAttachment = workOrderAttachmentService
					.findFirstWorkOrderAttachmentByWorkOrder(workOrder.get());

			List<FreelancerProposalsDTO> proposals = new ArrayList<>();

			List<Map<String, Object>> freelancerProposals = serviceRenderedService.findFreelancerDetailsForWorks(
					workOrder.get().getServiceType().getTypeId(), workOrder.get().getMinAmount(),
					workOrder.get().getMaxAmount());
			System.out.println("===>>> freelancerProposals: " + freelancerProposals);

			for (Map<String, Object> map : freelancerProposals) {

				// Create ObjectMapper instance
				ObjectMapper mapper = new ObjectMapper();
				// Converting POJO to Map
				Map<String, Object> mapping = mapper.convertValue(map, new TypeReference<Map<String, Object>>() {
				});
				FreelancerProposalsDTO proposal = mapper.convertValue(mapping, FreelancerProposalsDTO.class);

				proposals.add(proposal);

			}

			model.addAttribute("clientProposalList", proposals);
			model.addAttribute("workOrderFirstAttachment", workOrderAttachment.get());
			model.addAttribute("workOrderDetails", workOrder.get());

		} catch (Exception ex) {
			model.addAttribute("workOrderFirstAttachment", new WorkOrderAttachment());
		}

		return "dashboards/clients/clientbidsdetailsforfreelancers";
	}

	@GetMapping("/bids-client-details/{id}/{filter}")
	public String bidDetailsForClientUsingFilters(@PathVariable(value = "id") String id,
			@PathVariable(value = "filter") String filterType, Model model) {

		try {

			Optional<WorkOrder> workOrder = workOrderService.findById(id);

			Optional<WorkOrderAttachment> workOrderAttachment = workOrderAttachmentService
					.findFirstWorkOrderAttachmentByWorkOrder(workOrder.get());

			List<FreelancerProposalsDTO> proposals = new ArrayList<>();

			if (filterType.equals("serviceType")) {
				List<ServiceRendered> serviceRendereds = serviceRenderedService
						.findServiceRenderedListByServiceType(workOrder.get().getServiceType());

				for (ServiceRendered serviceRendered : serviceRendereds) {

					FreelancerProposalsDTO freelancerProposal = new FreelancerProposalsDTO();

					freelancerProposal.setUserId(serviceRendered.getUser().getUserId());
					freelancerProposal.setExperienceInYears(Integer.parseInt(serviceRendered.getExperienceInYears()));

					proposals.add(freelancerProposal);
				}
			} else if (filterType.equals("minAmount")) {

				List<ServiceRendered> serviceRendereds = serviceRenderedService.findAll();

				for (ServiceRendered serviceRendered : serviceRendereds) {

					FreelancerProposalsDTO freelancerProposal = new FreelancerProposalsDTO();

					Optional<FreelancerServicePricing> freelancerServicePricing = freelancerPricingService
							.findFirstFreelancerServicePricingByUser(serviceRendered.getUser());

					if (freelancerServicePricing.isPresent()) {
						if (freelancerServicePricing.get().getMinPrice() >= workOrder.get().getMinAmount()
								&& freelancerServicePricing.get().getMinPrice() <= workOrder.get().getMaxAmount()) {

							freelancerProposal.setUserId(freelancerServicePricing.get().getUser().getUserId());

							Optional<ServiceRendered> opService = serviceRenderedService
									.findFirstServiceRenderedByUser(freelancerServicePricing.get().getUser());
							freelancerProposal
									.setExperienceInYears(Integer.parseInt(opService.get().getExperienceInYears()));

							proposals.add(freelancerProposal);
						}
					}

				}
			} else if (filterType.equals("maxAmount")) {

				List<ServiceRendered> serviceRendereds = serviceRenderedService.findAll();

				for (ServiceRendered serviceRendered : serviceRendereds) {

					FreelancerProposalsDTO freelancerProposal = new FreelancerProposalsDTO();

					Optional<FreelancerServicePricing> freelancerServicePricing = freelancerPricingService
							.findFirstFreelancerServicePricingByUser(serviceRendered.getUser());

					System.out.println("====>>> freelancerServicePricing: " + freelancerServicePricing);

					if (freelancerServicePricing.isPresent()) {

						if (freelancerServicePricing.get().getMaxPrice() >= workOrder.get().getMinAmount()
								&& freelancerServicePricing.get().getMaxPrice() <= workOrder.get().getMaxAmount()) {

							freelancerProposal.setUserId(freelancerServicePricing.get().getUser().getUserId());

							Optional<ServiceRendered> opService = serviceRenderedService
									.findFirstServiceRenderedByUser(freelancerServicePricing.get().getUser());
							freelancerProposal
									.setExperienceInYears(Integer.parseInt(opService.get().getExperienceInYears()));

							proposals.add(freelancerProposal);

						}
					}
				}
			} else if (filterType.equals("allusers")) {
				List<ServiceRendered> serviceRendereds = serviceRenderedService.findAll();
				for (ServiceRendered serviceRendered : serviceRendereds) {

					FreelancerProposalsDTO freelancerProposal = new FreelancerProposalsDTO();

					freelancerProposal.setUserId(serviceRendered.getUser().getUserId());
					freelancerProposal.setExperienceInYears(Integer.parseInt(serviceRendered.getExperienceInYears()));

					proposals.add(freelancerProposal);
				}
			}

			model.addAttribute("FilterBidsRequestDTO", new FilterBidsRequestDTO());
			model.addAttribute("clientProposalList", proposals);
			model.addAttribute("workOrderFirstAttachment", workOrderAttachment.get());
			model.addAttribute("workOrderDetails", workOrder.get());

		} catch (Exception ex) {
			model.addAttribute("workOrderFirstAttachment", new WorkOrderAttachment());
		}

		return "dashboards/clients/clientbidsdetailsforfreelancers";
	}

	@PostMapping("/client/bid-request-filter")
	public String bidDetailsForClientUsingFilter(
			@ModelAttribute("FilterBidsRequestDTO") FilterBidsRequestDTO filterBidsRequestDTO) {

		return "redirect:/bids-client-details/" + filterBidsRequestDTO.getWorkOrderId() + "/"
				+ filterBidsRequestDTO.getFilterType();
	}

	@GetMapping("/client-profile-overview/{id}/{userId}")
	public String clientProfileOverview(@PathVariable(value = "id") String workId,
			@PathVariable(value = "userId") String userId, Model model, RedirectAttributes attribute) {

		try {

			Optional<WorkOrder> workOrder = workOrderService.findById(workId);

			System.out.println("===>>> workOrder: " + workOrder);

			Optional<User> user = userService.findById(Integer.parseInt(userId));
			Optional<User> opUser = userService.findFirstUserByUsername(user.get().getUsername());

			List<ServiceRendered> servicesRendered = serviceRenderedService.findServiceRenderedListByUser(opUser.get());

			String message = clientComponentModel.workOrderCalculations(workOrder, model);
			if (message.equals("done")) {
				model.addAttribute("workOrderDetails", workOrder.get());
				model.addAttribute("userDetails", opUser.get());
				model.addAttribute("servicesRendered", servicesRendered);
				model.addAttribute("clientAmountDTO", new ClientAmountDTO());
			} else {
				attribute.addFlashAttribute("message", message);
				return "redirect:/bids-client-details/" + workOrder.get().getWorkId() + "/serviceType";
			}

		} catch (Exception ex) {
			model.addAttribute("workOrderFirstAttachment", new WorkOrderAttachment());
		}

		return "dashboards/clients/profile2";
	}

	@PostMapping("/client/amount/set-job-amount-for-freelancers")
	public String setClientAmount(@ModelAttribute("clientAmountDTO") ClientAmountDTO clientAmountDTO,
			BindingResult bindingResultModel, Model model, RedirectAttributes attributes) {

		try {

			if (clientAmountDTO.getClientAmount() >= clientAmountDTO.getTotalMinAmount()
					&& clientAmountDTO.getClientAmount() <= clientAmountDTO.getTotalMaxAmount()) {

				String userId = (String) session.getAttribute("userId");
				
				Optional<User> clientUserId = userService.findFirstUserByUsername(userId);
				System.out.println("===>>> clientUserId: "+clientUserId);

				Optional<User> user = userService.findFirstUserByUsername(clientAmountDTO.getUserID());
				
				System.out.println("===>>> freelancer user: "+user);
				
				Optional<WorkOrder> workOrder = workOrderService.findById(clientAmountDTO.getWorkOrderID());
				
				System.out.println("===>>> workOrder: "+workOrder);

				// send proposal to freelancer to accept, get the last id and redirect to
				// payment page

				Optional<ProposalStatus> proposalStatus = proposalStatusService.findById(9);// Freelancer Request Sent

				if(proposalService.checkLastStatusOfProposal(user.get().getUserId(),workOrder.get().getWorkId()) == null) {
					Proposal proposal = new Proposal();
//					proposal.setProposalId("");
					proposal.setWorkOrder(workOrder.get());
					proposal.setUser(user.get());
					proposal.setProposalStatus(proposalStatus.get());
					proposal.setAmount(clientAmountDTO.getClientAmount());
					proposal.setModifiedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					proposal.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	
					proposalService.save(proposal);
	
					System.out.println("===>>> proposal Saved: "+proposal);
				}

				Proposal prop = proposalService.findProposalByUserAndStatusOrderByCreatedDescWithLimit(
						user.get().getUserId(), proposalStatus.get().getProposalStatusId());
				
				// update proposal amount
				int updateproposalAmount = proposalService.updateProposalAmount(clientAmountDTO.getClientAmount(),
						prop.getProposalId());

				System.out.println("===>>> Proposal Amount Update Status: " + updateproposalAmount);
				System.out.println("===>>> prop: " + prop);

				// This will check if the client has paid
				if (workPaymentService.findWorkPaymentByWorkOrderIdAndClientId(clientUserId.get().getUserId(),
						workOrder.get().getWorkId()) == null) {
					return "redirect:/select-payment-option/" + user.get().getUsername() + "/" + prop.getProposalId();
				} else {
					attributes.addFlashAttribute("message", "Work order to sent freelancer");
					return "redirect:/client-dashboard";
				}

			} else {
				attributes.addFlashAttribute("message", "Specified work order fee not in range.");
				return "redirect:/client-dashboard";
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception: " + e.getMessage());
		}

		attributes.addFlashAttribute("message", "An error occured while creating job");
		return "redirect:/client-dashboard";
	}
	
	@PostMapping("/review-work-order-client")
	public String reviewWorkOrder(@ModelAttribute("ReviewWorkOrderDTO") ReviewWorkOrderDTO reviewWorkOrderDTO,
			BindingResult bindingResultModel, Model model, RedirectAttributes attributes) {

		try {
			//Review
			String entryDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			int uopdateStatus = 4;

			int updateWorkOrdertoReviewing = workOrderService.updateWorkOrderStatus(uopdateStatus, reviewWorkOrderDTO.getWorkOrderId());// 4 means reviewing
			System.out.println("===>>>>> updateWorkOrdertoReviewing: " + updateWorkOrdertoReviewing);

			int updateDeliveryToReviewing = deliveryService.updateWorkDeliveryStatus(uopdateStatus, reviewWorkOrderDTO.getDeliveryId()); // 4 means reviewing
			System.out.println("===>>>>> updateDeliveryToReviewing: " + updateDeliveryToReviewing);
			
			Optional<User> user = userService.findById(reviewWorkOrderDTO.getUserId());
			Optional<WorkOrder> workOrder = workOrderService.findFirstWorkOrderByWorkId(reviewWorkOrderDTO.getWorkOrderId());
			
			WorkOrderReview order = new WorkOrderReview();
//			order.setReviewId(0);
			order.setUser(user.get());
			order.setWorkOrder(workOrder.get());
			order.setReview(reviewWorkOrderDTO.getReview());
			order.setEntryDate(entryDate);
			
			workOrderReviewService.save(order);

			attributes.addFlashAttribute("message", "Reviewing application.");

			return "redirect:/client-finished-details/"+reviewWorkOrderDTO.getWorkOrderId()+"/"+reviewWorkOrderDTO.getDeliveryId();

		} catch (Exception ex) {
			System.out.println("===>>> Exception: " + ex);
		}

		return "dashboards/admin/overview/jobs/adminjobsdetails";

	}

}
