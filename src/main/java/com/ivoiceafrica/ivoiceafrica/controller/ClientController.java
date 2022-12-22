package com.ivoiceafrica.ivoiceafrica.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ivoiceafrica.ivoiceafrica.DTO.ClientAmountDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ClientPersonalDetailDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ClientProfilePictureDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ClientSignupDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.NewClientRequestDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ProfileDTO;
import com.ivoiceafrica.ivoiceafrica.auth.entity.Role;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.auth.entity.UserStatus;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryStatus;
import com.ivoiceafrica.ivoiceafrica.entity.DurationType;
import com.ivoiceafrica.ivoiceafrica.entity.Language;
import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.ProposalStatus;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;
import com.ivoiceafrica.ivoiceafrica.models.ClientUploadModel;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryStatusService;
import com.ivoiceafrica.ivoiceafrica.service.DurationTypeService;
import com.ivoiceafrica.ivoiceafrica.service.LanguageService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalStatusService;
import com.ivoiceafrica.ivoiceafrica.service.RoleService;
import com.ivoiceafrica.ivoiceafrica.service.SRenderedService;
import com.ivoiceafrica.ivoiceafrica.service.STypeService;
import com.ivoiceafrica.ivoiceafrica.service.UserStatusService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderStatusService;
import com.ivoiceafrica.ivoiceafrica.utility.GetEndDate;

@Controller
public class ClientController {

	// Check the System Utility Class on GeekForGeek(Online)
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/profilepictures";

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
				.findWorkOrdersDeliveryByUserAndDeliveryStatusOrderByCreatedDateDesc(user.get(), opDeliveryStatus.get());

		try {
			
			Optional<WorkOrderStatus> workOrderStatus = workOrderStatusService.findById(5);// 5 means in Progress
			
			List<WorkOrder> workOrders = workOrderService
					.findWorkOrderByUserAndWorkOrderStatusOrderByPostingDate(user.get(), workOrderStatus.get());
			
			if(workOrders.size() > 1) {
				
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

				attributes.addFlashAttribute("message", "Great, your job has been sent successfully");
				return "redirect:/client-dashboard";
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

		return "dashboards/clients/inprogressjobdetails";
	}

	@GetMapping("/client/job/finished")
	public String clientFinishedJobs(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> user = userService.findFirstUserByUsername(userId);

		Optional<WorkOrderStatus> workOrderStatus = workOrderStatusService.findById(5);// 5 means in Progress

		List<WorkOrder> workOrders = workOrderService
				.findWorkOrderByUserAndWorkOrderStatusOrderByPostingDate(user.get(), workOrderStatus.get());

		// Delivery Status, Work Delivery and Delivery Attachment
		Optional<DeliveryStatus> opDeliveryStatus = deliveryStatusService.findById(9);// 5 means in Progress

		List<WorkOrdersDelivery> workOrderDelivery = deliveryService
				.findWorkOrdersDeliveryByUserAndDeliveryStatusOrderByCreatedDateDesc(user.get(), opDeliveryStatus.get());

		try {
			
			Optional<WorkOrderAttachment> opWorkOrderAttachment = workOrderAttachmentService
					.findFirstWorkOrderAttachmentByWorkOrder(workOrders.get(0));
			model.addAttribute("orderSource", opWorkOrderAttachment.get().getSource());
			model.addAttribute("orderDestination", opWorkOrderAttachment.get().getDestination());
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

		return "dashboards/clients/clientfinishedDetails";
	}

	@GetMapping("/client/finance/all")
	public String clientAllFinance(Model model) {

		return "dashboards/clients/clientfinance";
	}

	@GetMapping("/profile")
	public String clientProfile(Model model) {
		
		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);
		
		model.addAttribute("userDetails", userDetails.get());
		model.addAttribute("ProfileDTO", new ProfileDTO());
		
		return "dashboards/clients/clientprofile";
	}
	
	@PostMapping("/profile/save")
	public String saveProfile(@ModelAttribute("ProfileDTO") ProfileDTO profileDTO, Model model) {
		
		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);
		
		System.out.println("===>>> ProfileDTO: "+profileDTO);
		
		profileDTO.setUserId(userDetails.get().getUserId());
		int updateUserInfoStatus = userService.updateUserInfo(profileDTO);
		
		System.out.println("Update Status: "+updateUserInfoStatus);
		
		model.addAttribute("userDetails", userDetails.get());

		return "redirect:/profile";
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
			Optional<WorkOrderAttachment> workOrderAttachment = workOrderAttachmentService
					.findFirstWorkOrderAttachmentByWorkOrder(workOrder.get());

			List<WorkOrderAttachment> workOrderAttachments = workOrderAttachmentService
					.findWorkOrderAttachmentByWorkOrder(workOrder.get());

			model.addAttribute("workOrderAttachmentList", workOrderAttachments);
			model.addAttribute("workOrderFirstAttachment", workOrderAttachment.get());
			model.addAttribute("workOrderDetails", workOrder.get());

		} catch (Exception ex) {
			model.addAttribute("workOrderFirstAttachment", new WorkOrderAttachment());
		}

		return "dashboards/clients/clientrequestdetails";
	}

	@GetMapping("/client/bidsdetails/{id}")
	public String bidDetails(@PathVariable(value = "id") String id, Model model) {

		try {

			Optional<WorkOrder> workOrder = workOrderService.findById(id);

			Optional<WorkOrderAttachment> workOrderAttachment = workOrderAttachmentService
					.findFirstWorkOrderAttachmentByWorkOrder(workOrder.get());

			List<WorkOrderAttachment> workOrderAttachments = workOrderAttachmentService
					.findWorkOrderAttachmentByWorkOrder(workOrder.get());

			Optional<ProposalStatus> proposalStatus = proposalStatusService.findById(9);// 9 Means Freelancer Request
																						// Sent Status

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

	@GetMapping("/clientProfileOverview/{id}/{user}")
	public String profileOverview(@PathVariable(value = "id") String id, @PathVariable(value = "user") String user,
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
			model.addAttribute("clientAmountDTO", new ClientAmountDTO());

		} catch (Exception ex) {
			model.addAttribute("workOrderFirstAttachment", new WorkOrderAttachment());
		}

		return "dashboards/clients/profile2";
	}

	@PostMapping("/client/signup/save")
	public String saveClientSignupInfo(@ModelAttribute("ClientSignupDTO") ClientSignupDTO clientSignupDTO,
			HttpSession session, Model model) {

		System.out.println("===>>> Client Signup details: " + clientSignupDTO);

		session.setAttribute("clientSignupDTO", clientSignupDTO);

		model.addAttribute("ClientPersonalDetailDTO", new ClientPersonalDetailDTO());
		return "onboarding/client/profilesetup";
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
					imageUUID = "tom@gmail.com";
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

		WorkOrder wo = new WorkOrder();
//		wo.setWorkId("");
		wo.setWorkTitle(newClientRequestDTO.getProjectTitle());
		wo.setUser(userDetails.get());
		wo.setServiceType(serviceTypeDetail.get());
		wo.setDuration(durationDetail.get());
		wo.setWorkOrderStatus(workOrderStatus.get());
		wo.setDescription(newClientRequestDTO.getProjectDescription());
		wo.setMinAmount(newClientRequestDTO.getMinAmount());
		wo.setMaxAmount(newClientRequestDTO.getMaxAMount());
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

		attributes.addFlashAttribute("message", "Great, Job Created!");
		return "redirect:/client-dashboard";
	}

	public static String getUploadFileName(MultipartFile file) {
		String filename = "";

		try {

			System.out.println("===>>> Multipart File: " + file.getSize());

			if (!file.isEmpty()) {
				filename = file.getOriginalFilename();
				System.out.println("===>>> Multipart File: " + file.getOriginalFilename());
				Path fileNameAndPath = Paths.get(uploadDir, filename);
				Files.write(fileNameAndPath, file.getBytes());
			} else {
				filename = "";
			}

		} catch (Exception ex) {
			System.out.println("===>>> Upload File Exception: " + ex.getMessage());
		}

		return filename;
	}

}
