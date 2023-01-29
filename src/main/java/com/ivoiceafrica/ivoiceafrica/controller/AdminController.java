package com.ivoiceafrica.ivoiceafrica.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ivoiceafrica.ivoiceafrica.DTO.AddUserDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.AdminPageWordCountDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.AdminSearchDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ClientAmountDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ClientWorkDeliveryForAdmin;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerWorkDeliveryForAdmin;
import com.ivoiceafrica.ivoiceafrica.DTO.GetJobsInfoDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.SearchDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.SendJobDTO;
import com.ivoiceafrica.ivoiceafrica.auth.entity.Role;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.auth.entity.UserStatus;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryStatus;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerDeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.ProposalStatus;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryStatusService;
import com.ivoiceafrica.ivoiceafrica.service.DurationTypeService;
import com.ivoiceafrica.ivoiceafrica.service.FreelancerDeliveryAttachmentService;
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

@Controller
public class AdminController {

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

	@Autowired
	FreelancerDeliveryAttachmentService freelancerDeliveryAttachmentService;

	@GetMapping("/admin-dashboard")
	public String adminDashboard(Model model) {

		// get all counts
		List<WorkOrder> workOrders = workOrderService.findAll();

		List<User> clientSize = userService.findUserByRole(3); // 4 means freelancer role, 3 means client role, 2 means
																// supervisor role, 1 means admin role
		List<User> freelancerSize = userService.findUserByRole(4);

		Optional<WorkOrderStatus> workOrderStatus = workOrderStatusService.findById(5);

		List<WorkOrder> pendingWorkOrder = workOrderService
				.findWorkOrderByWorkOrderStatusOrderByPostingDate(workOrderStatus.get());

		List<WorkOrder> getWorkOrderWithLimit = workOrderService.findWorkOrderByLimit(10);

		model.addAttribute("workOrderSize", workOrders.size());
		model.addAttribute("clientSize", clientSize.size());
		model.addAttribute("freelancerSize", freelancerSize.size());
		model.addAttribute("pendingJobSize", pendingWorkOrder.size());
		model.addAttribute("workOrderList", getWorkOrderWithLimit);

		return "dashboards/admin/overview/dashboard";
	}

	@GetMapping("/admin-profile")
	public String adminProfile() {

		return "dashboards/admin/overview/adminprofile";
	}

	@GetMapping("/admin-job")
	public String adminJobs(Model model) {

		List<WorkOrder> getWorkOrder = workOrderService.findAll();

		List<ServiceType> serviceTypes = serviceType.findAll();
		List<WorkOrderStatus> workOrdersStatus = workOrderStatusService.findAll();

		model.addAttribute("serviceTypes", serviceTypes);
		model.addAttribute("workOrdersStatus", workOrdersStatus);
		model.addAttribute("workOrderList", getWorkOrder);
		model.addAttribute("getJobsInfoDTO", new GetJobsInfoDTO());
		return "dashboards/admin/overview/jobs/adminjobs";
	}

	@GetMapping("/admin-job-by-serviceType")
	@ResponseBody
	public String adminJobsByServiceType(@RequestParam String serviceTypeId, Model model) {

		System.out.println("===>>> Service Type: " + serviceTypeId);
		return "/admin-job-by-serviceType/" + serviceTypeId + "";
	}

	@GetMapping("/admin-job-by-serviceType/{serviceType}")
	public String getWorkOrderByServiceType(@PathVariable(value = "serviceType") String serviceTypeId, Model model) {

		System.out.println("===>>> Service Type: " + serviceTypeId);

		Optional<ServiceType> serviceTypeOp = serviceType.findById(serviceTypeId);
		List<WorkOrder> getWorkOrder = workOrderService
				.findWorkOrderByServiceTypeOrderByPostingDate(serviceTypeOp.get());

		List<ServiceType> serviceTypes = serviceType.findAll();
		List<WorkOrderStatus> workOrdersStatus = workOrderStatusService.findAll();

		model.addAttribute("serviceTypes", serviceTypes);
		model.addAttribute("workOrdersStatus", workOrdersStatus);
		model.addAttribute("workOrderList", getWorkOrder);
		model.addAttribute("getJobsInfoDTO", new GetJobsInfoDTO());
		return "dashboards/admin/overview/jobs/adminjobs";
	}

	@GetMapping("/admin-job-by-statusType")
	@ResponseBody
	public String adminJobsByStatusType(@RequestParam String statusId, Model model) {

		System.out.println("===>>> Status: " + statusId);

		return "/admin-job-by-statusType/" + statusId + "";
	}

	@GetMapping("/admin-job-by-statusType/{statusType}")
	public String getWorkOrderByStatusType(@PathVariable(value = "statusType") String statusId, Model model) {

		System.out.println("===>>> Status: " + statusId);

		Optional<WorkOrderStatus> workOrderStatus = workOrderStatusService.findById(Integer.parseInt(statusId));

		List<WorkOrder> getWorkOrder = workOrderService
				.findWorkOrderByWorkOrderStatusOrderByPostingDate(workOrderStatus.get());

		List<ServiceType> serviceTypes = serviceType.findAll();
		List<WorkOrderStatus> workOrdersStatus = workOrderStatusService.findAll();

		model.addAttribute("serviceTypes", serviceTypes);
		model.addAttribute("workOrdersStatus", workOrdersStatus);
		model.addAttribute("workOrderList", getWorkOrder);
		model.addAttribute("getJobsInfoDTO", new GetJobsInfoDTO());
		return "dashboards/admin/overview/jobs/adminjobs";
	};

	@PostMapping("/get-jobs-info")
	public String getJobsInfo(@ModelAttribute("getJobsInfoDTO") GetJobsInfoDTO getJobsInfoDTO, Model model) {

		System.out.println("===>>> GetJobsInfoDTO: " + getJobsInfoDTO);

		List<WorkOrder> workOrders = new ArrayList<>();

		if (getJobsInfoDTO.getJobId().isEmpty() && getJobsInfoDTO.getClientId().isEmpty()) {
			model.addAttribute("message", "Search fields cannot be empty");
		}

		if (!getJobsInfoDTO.getJobId().isEmpty()) {

			workOrders = workOrderService.findWorkOrderByWorkIdOrderByPostingDate(getJobsInfoDTO.getJobId());

		} else if (!getJobsInfoDTO.getClientId().isEmpty()) {

			Optional<User> opUser = userService.findFirstUserByUsername(getJobsInfoDTO.getClientId());
			workOrders = workOrderService.findWorkOrderByUserOrderByPostingDate(opUser.get());

		} else {
			workOrders = workOrderService.findAll();
		}

		List<ServiceType> serviceTypes = serviceType.findAll();
		List<WorkOrderStatus> workOrdersStatus = workOrderStatusService.findAll();

		model.addAttribute("serviceTypes", serviceTypes);
		model.addAttribute("workOrdersStatus", workOrdersStatus);
		model.addAttribute("workOrderList", workOrders);
		model.addAttribute("getJobsInfoDTO", new GetJobsInfoDTO());
		return "dashboards/admin/overview/jobs/adminjobs";
	};

	@GetMapping("/admin-client")
	public String adminClient(Model model) {

		List<User> clients = userService.findUserByRole(3);
		model.addAttribute("clients", clients);
		model.addAttribute("adminSearchDTO", new AdminSearchDTO());
		return "dashboards/admin/overview/clients/clients";
	}
	
	
	@PostMapping("/admin-client-search")
	public String searchAdminClient(@ModelAttribute("adminSearchDTO") AdminSearchDTO adminSearchDTO, Model model) {

		System.out.println("===>>> AdminSearchDTO: "+adminSearchDTO);
		
		List<User> clients = new ArrayList<>();
		
		if(adminSearchDTO.getSearchOption().equals("ClientID")) {
			clients = userService.findUserByRoleAndUsername(3, adminSearchDTO.getSearchValue());
		}
		else if(adminSearchDTO.getSearchOption().equals("FirstName")) {
			clients = userService.findUserByRoleAndFirstname(3, adminSearchDTO.getSearchValue());
		}
		else if(adminSearchDTO.getSearchOption().equals("LastName")) {
			clients = userService.findUserByRoleAndLastname(3, adminSearchDTO.getSearchValue());
		}
		else if(adminSearchDTO.getSearchOption().equals("Phone")) {
			clients = userService.findUserByRoleAndPhone(3, adminSearchDTO.getSearchValue());
		}else {
			clients = userService.findUserByRole(3);
		}
		
		model.addAttribute("clients", clients);
		model.addAttribute("adminSearchDTO", new AdminSearchDTO());
		return "dashboards/admin/overview/clients/clients";
	}

	
	@GetMapping("/client-details/{user}")
	public String clientDetails(@PathVariable(value = "user") String username, Model model) {

		clientUserDetails(username, model, 0, "","",0);//The first String is the workId, The second String is the service type

		return "dashboards/admin/overview/clients/clientdetails";
	}
	
	
	
	@GetMapping("/client-details-by-type")
	@ResponseBody
	public String clientDetailsByType(@RequestParam String statusId, @RequestParam String userId, Model model) {
		System.out.println("===>>> Status Id: " + statusId+" :User Id: "+userId);

		return "/client-details/"+userId+"/"+statusId+"";
	}

	
	@GetMapping("/client-details/{user}/{statusId}")
	public String getclientDetailsByType(@PathVariable(value = "user") String userId,@PathVariable(value = "statusId") String statusId, Model model) {
		System.out.println("===>>> Status Id: " + statusId+" :User Id: "+userId);
		
		clientUserDetails(userId, model, Integer.parseInt(statusId),"","",0);

		return "dashboards/admin/overview/clients/clientdetails";
	};
	
	
	@GetMapping("/client-details-by-servicetype")
	@ResponseBody
	public String clientDetailsByServiceType(@RequestParam String serviceType, @RequestParam String userId, Model model) {
		System.out.println("===>>>serviceType: " + serviceType+" :User Id: "+userId);

		return "/client-details-serviceType/"+userId+"/"+serviceType+"";
	}
	
	
	@GetMapping("/client-details-serviceType/{user}/{serviceType}")
	public String getclientDetailsByServiceType(@PathVariable(value = "user") String userId,@PathVariable(value = "serviceType") String serviceType, Model model) {
		System.out.println("===>>> serviceType: " + serviceType+" :User Id: "+userId);
		
		clientUserDetails(userId, model, -2,"",serviceType,0);

		return "dashboards/admin/overview/clients/clientdetails";
	};
	
	
	@PostMapping("/client-details-search")
	public String getClientDetailsBySearch(@ModelAttribute("SearchDTO") SearchDTO searchDTO, Model model) {
		
		System.out.println("===>>> Status Id: " +searchDTO);
		
		clientUserDetails(searchDTO.getUserId(), model, -1, searchDTO.getWorkOrderId(),"",0);

		return "dashboards/admin/overview/clients/clientdetails";
	};
	
	@GetMapping("/client-details-by-deliverytype")
	@ResponseBody
	public String clientDetailsByDeliveryType(@RequestParam String statusId, @RequestParam String userId, Model model) {
		System.out.println("===>>> Delivery Status Id: " + statusId+" :User Id: "+userId);

		return "/client-details-deliveryType/"+userId+"/"+statusId+"";
	}
	
	@GetMapping("/client-details-deliveryType/{user}/{deliveryStatusId}")
	public String getclientDetailsByDeliveryType(@PathVariable(value = "user") String userId,@PathVariable(value = "deliveryStatusId") String deliveryStatusId, Model model) {
		System.out.println("===>>> Delivery status: " + deliveryStatusId+" :User Id: "+userId);
		
		clientUserDetails(userId, model, 0,"","", Integer.parseInt(deliveryStatusId));

		return "dashboards/admin/overview/clients/clientdetails";
	};
	
	
	@PostMapping("/client-delivery-search")
	public String getClientDeliveryBySearch(@ModelAttribute("SearchDTO") SearchDTO searchDTO, Model model) {
		
		System.out.println("===>>> Status Id: " +searchDTO);
		
		clientUserDetails(searchDTO.getUserId(), model, -3, searchDTO.getWorkOrderId(),"",0);

		return "dashboards/admin/overview/clients/clientdetails";
	};

	
	public void clientUserDetails(String username, Model model, int statusId, String workId, String serviceTypeId, int deliveryStatusId) {
		
		Optional<User> opUser = userService.findFirstUserByUsername(username);

		Optional<WorkOrderStatus> inprogressStatus = workOrderStatusService.findById(5); // means inprogress
		List<WorkOrder> workOrdersInprogress = workOrderService
				.findWorkOrderByUserAndWorkOrderStatusOrderByPostingDate(opUser.get(), inprogressStatus.get());

		Optional<WorkOrderStatus> completedStatus = workOrderStatusService.findById(2); // means completed
		List<WorkOrder> workOrdersCompleted = workOrderService
				.findWorkOrderByUserAndWorkOrderStatusOrderByPostingDate(opUser.get(), completedStatus.get());

		Optional<WorkOrderStatus> finishedStatus = workOrderStatusService.findById(9); // means finished
		List<WorkOrder> workOrdersFinished = workOrderService
				.findWorkOrderByUserAndWorkOrderStatusOrderByPostingDate(opUser.get(), finishedStatus.get());

		
		List<WorkOrder> allWorkOrderRequest = new ArrayList<>();
		
		//Get job request based on status
		if(statusId > 0) {
			Optional<WorkOrderStatus> opWorkStatus = workOrderStatusService.findById(statusId);
			allWorkOrderRequest = workOrderService.findWorkOrderByUserAndWorkOrderStatusOrderByPostingDate(opUser.get(), opWorkStatus.get());
		}
		else if(statusId == -1){
			allWorkOrderRequest = workOrderService.findWorkOrderByUserAndWorkIdOrderByPostingDate(opUser.get(), workId);
		}
		else if(statusId == -2){
			Optional<ServiceType> sevice = serviceType.findById(serviceTypeId);
			allWorkOrderRequest = workOrderService.findWorkOrderByServiceTypeAndUserOrderByPostingDate(sevice.get(), opUser.get());
		}
		else {
			allWorkOrderRequest = workOrderService.findWorkOrderByUserOrderByPostingDate(opUser.get());
		}

		
		List<WorkOrdersDelivery> workOrderDeliveries = new ArrayList<>();
		
		if(deliveryStatusId > 0) {
			Optional<DeliveryStatus> deliveryStatus = deliveryStatusService.findById(deliveryStatusId);
			workOrderDeliveries = deliveryService
					.findWorkOrdersDeliveryByClientUserIdAndDeliveryStatusOrderByCreatedDateDesc(opUser.get().getUserId(), deliveryStatus.get());
		}else if(statusId == -3){
			Optional<WorkOrder> workOrderOp = workOrderService.findById(workId);
			workOrderDeliveries = deliveryService
					.findWorkOrdersDeliveryByClientUserIdAndWorkOrderOrderByCreatedDateDesc(opUser.get().getUserId(), workOrderOp.get());
		}
		else {
			workOrderDeliveries = deliveryService
			.findWorkOrdersDeliveryByClientUserIdOrderByCreatedDateDesc(opUser.get().getUserId());
		}
		
		System.out.println("===>>>> workOrderDeliveries: "+workOrderDeliveries);
		

		Optional<DeliveryStatus> deliveryInprogressStatus = deliveryStatusService.findById(5); // means inprogress
		List<WorkOrdersDelivery> inprogressDelivery = deliveryService
				.findWorkOrdersDeliveryByClientUserIdAndDeliveryStatusOrderByCreatedDateDesc(opUser.get().getUserId(),
						deliveryInprogressStatus.get());

		Optional<DeliveryStatus> deliverycompletedStatus = deliveryStatusService.findById(2); // means completed
		List<WorkOrdersDelivery> completedDelivery = deliveryService
				.findWorkOrdersDeliveryByClientUserIdAndDeliveryStatusOrderByCreatedDateDesc(opUser.get().getUserId(),
						deliverycompletedStatus.get());

		Optional<DeliveryStatus> deliveryFinishedStatus = deliveryStatusService.findById(9); // means finished
		List<WorkOrdersDelivery> finishedDelivery = deliveryService
				.findWorkOrdersDeliveryByClientUserIdAndDeliveryStatusOrderByCreatedDateDesc(opUser.get().getUserId(),
						deliveryFinishedStatus.get());

		List<Proposal> clientProposal = proposalService.findProposalByUserOrderByCreatedDate(opUser.get());

		ClientWorkDeliveryForAdmin clientWorkDelivery = new ClientWorkDeliveryForAdmin();

		clientWorkDelivery.setActiveDelivery(inprogressDelivery.size());
		clientWorkDelivery.setCompletedDelivery(completedDelivery.size());
		clientWorkDelivery.setFinishedDelivery(finishedDelivery.size());
		clientWorkDelivery.setClientProposals(clientProposal.size());
		
		List<WorkOrderStatus> workOrderStatus = workOrderStatusService.findAll();
		List<DeliveryStatus> deliveryStatus = deliveryStatusService.findAll();
		List<ServiceType> serviceTypes = serviceType.findAll();

		// TODO: Client Transactions(Payments) details

		model.addAttribute("deliveryStatus",deliveryStatus);
		model.addAttribute("serviceTypes",serviceTypes);
		model.addAttribute("workOrderStatus",workOrderStatus);
		model.addAttribute("clientWorkDelivery", clientWorkDelivery);
		model.addAttribute("workOrderDeliveries", workOrderDeliveries);

		model.addAttribute("inprogressTotalNumber", workOrdersInprogress.size());
		model.addAttribute("completedTotalNumber", workOrdersCompleted.size());
		model.addAttribute("finishedTotalNumber", workOrdersFinished.size());
		
		
		model.addAttribute("allJobRequest", allWorkOrderRequest);

		model.addAttribute("opUser", opUser.get());
		
	}
	

	@GetMapping("/admin-freelancer")
	public String adminFreelancer(Model model) {
		List<User> freelancers = userService.findUserByRole(4);

		model.addAttribute("adminSearchDTO", new AdminSearchDTO());
		model.addAttribute("freelancers", freelancers);
		return "dashboards/admin/overview/freelancers/freelancers";
	}
	
	@PostMapping("/admin-freelancer-search")
	public String searchFreelancerClient(@ModelAttribute("adminSearchDTO") AdminSearchDTO adminSearchDTO, Model model) {

		System.out.println("===>>> AdminSearchDTO: "+adminSearchDTO);
		
		List<User> freelancers = new ArrayList<>();
		
		if(adminSearchDTO.getSearchOption().equals("ClientID")) {
			freelancers = userService.findUserByRoleAndUsername(4, adminSearchDTO.getSearchValue());
		}
		else if(adminSearchDTO.getSearchOption().equals("FirstName")) {
			freelancers = userService.findUserByRoleAndFirstname(4, adminSearchDTO.getSearchValue());
		}
		else if(adminSearchDTO.getSearchOption().equals("LastName")) {
			freelancers = userService.findUserByRoleAndLastname(4, adminSearchDTO.getSearchValue());
		}
		else if(adminSearchDTO.getSearchOption().equals("Phone")) {
			freelancers = userService.findUserByRoleAndPhone(4, adminSearchDTO.getSearchValue());
		}else {
			freelancers = userService.findUserByRole(4);
		}
		
		model.addAttribute("adminSearchDTO", new AdminSearchDTO());
		model.addAttribute("freelancers", freelancers);
		return "dashboards/admin/overview/freelancers/freelancers";
	}

	@GetMapping("/get-freelancer/{user}")
	public String getFreelancer(@PathVariable(value = "user") String username, Model model) {
		
		freelancerDetails(username, model,0,"",0);

		return "dashboards/admin/overview/freelancers/freelancer";
	}
	
	@GetMapping("/freelancer-details-by-statusId")
	@ResponseBody
	public String getFreelancerProposalByStatus(@RequestParam String statusId, @RequestParam String userId, Model model) {
		System.out.println("===>>> Freelancer Status: " + statusId+" :User Id: "+userId);

		return "/freelancer-details-proposalStatus/"+userId+"/"+statusId+"";
	}
	
	@GetMapping("/freelancer-details-proposalStatus/{user}/{proposalStatusId}")
	public String getFreelancerProposalStatusDetails(@PathVariable(value = "user") String userId,@PathVariable(value = "proposalStatusId") String proposalStatusId, Model model) {
		System.out.println("===>>> Delivery status: " + proposalStatusId+" :User Id: "+userId);
		
		freelancerDetails(userId, model, Integer.parseInt(proposalStatusId),"",0);

		return "dashboards/admin/overview/freelancers/freelancer";
	};
	
	@PostMapping("/freelancer-proposal-search")
	public String getFreelancerProposalDetail(@ModelAttribute("SearchDTO") SearchDTO searchDTO, Model model) {
		
		System.out.println("===>>> Status Id: " +searchDTO);
		
		freelancerDetails(searchDTO.getUserId(), model, -1, searchDTO.getWorkOrderId(),0);

		return "dashboards/admin/overview/freelancers/freelancer";
	};
	
	//WORKING ON THIS START
	@GetMapping("/freelancer-details-by-deliveryStatus")
	@ResponseBody
	public String getFreelancerDeliveryByStatus(@RequestParam String statusId, @RequestParam String userId, Model model) {
		System.out.println("===>>> Freelancer Status: " + statusId+" :User Id: "+userId);

		return "/freelancer-details-delivery/"+userId+"/"+statusId+"";
	};
	
	@GetMapping("/freelancer-details-delivery/{user}/{deliveryStatusId}")
	public String getFreelancerDeliveryStatusDetails(@PathVariable(value = "user") String userId,@PathVariable(value = "deliveryStatusId") String deliveryStatusId, Model model) {
		System.out.println("===>>> Delivery status: " + deliveryStatusId+" :User Id: "+userId);
		
		freelancerDetails(userId, model, 0,"",Integer.parseInt(deliveryStatusId));

		return "dashboards/admin/overview/freelancers/freelancer";
	};
	
	@PostMapping("/freelancer-delivery-search")
	public String getFreelancerDeliveryDetailSearch(@ModelAttribute("SearchDTO") SearchDTO searchDTO, Model model) {
		
		System.out.println("===>>> Status Id: " +searchDTO);
		
		freelancerDetails(searchDTO.getUserId(), model, 0, searchDTO.getWorkOrderId(), -3);
		
		return "dashboards/admin/overview/freelancers/freelancer";
	};
	//END
	
	public void freelancerDetails(String username, Model model, int proposalStatusId, String workOrderId, int deliveryId) {
		
		Optional<User> opUser = userService.findFirstUserByUsername(username);

		List<ServiceRendered> servicesRendered = serviceRenderedService.findServiceRenderedListByUser(opUser.get());

		Optional<DeliveryStatus> deliveryInprogressStatus = deliveryStatusService.findById(5);// means inprogress
		List<WorkOrdersDelivery> inprogressDelivery = deliveryService
				.findWorkOrdersDeliveryByUserAndDeliveryStatusOrderByCreatedDateDesc(opUser.get(),
						deliveryInprogressStatus.get());

		Optional<DeliveryStatus> deliverycompletedStatus = deliveryStatusService.findById(2); // means completed
		List<WorkOrdersDelivery> completedDelivery = deliveryService
				.findWorkOrdersDeliveryByUserAndDeliveryStatusOrderByCreatedDateDesc(opUser.get(),
						deliverycompletedStatus.get());

		Optional<DeliveryStatus> deliveryFinishedStatus = deliveryStatusService.findById(9); // means finished
		List<WorkOrdersDelivery> finishedDelivery = deliveryService
				.findWorkOrdersDeliveryByUserAndDeliveryStatusOrderByCreatedDateDesc(opUser.get(),
						deliveryFinishedStatus.get());

		List<ProposalStatus> proposalsStatus = proposalStatusService.findAll();
		
		List<Proposal> proposals = new ArrayList<>();
		
		if(proposalStatusId > 0) {
			Optional<ProposalStatus> proposalStatus = proposalStatusService.findById(proposalStatusId);
			proposals = proposalService.findProposalByUserAndProposalStatusOrderByCreatedDate(opUser.get(), proposalStatus.get());
		}else if(proposalStatusId == -1) {
			Optional<WorkOrder> workOrderOp = workOrderService.findById(workOrderId);
			proposals = proposalService.findProposalByUserAndWorkOrderOrderByCreatedDateDesc(opUser.get(), workOrderOp.get());
		}
		else {
			proposals = proposalService.findProposalByUserOrderByCreatedDate(opUser.get());
		}
		
		List<DeliveryStatus> deliveryStatus = deliveryStatusService.findAll();
		List<WorkOrdersDelivery> allFreelancerDelivery = new ArrayList<>();
		
		if(deliveryId > 0) {
			Optional<DeliveryStatus> deliveryStatusop = deliveryStatusService.findById(deliveryId);
			allFreelancerDelivery = deliveryService
					.findWorkOrdersDeliveryByUserAndDeliveryStatusOrderByCreatedDateDesc(opUser.get(), deliveryStatusop.get());
		}else if(deliveryId == -3) {
			try {
				Optional<WorkOrder> workOrderOp = workOrderService.findById(workOrderId);
				
				allFreelancerDelivery = deliveryService
						.findWorkOrdersDeliveryByUserAndWorkOrderOrderByCreatedDateDesc(opUser.get(), workOrderOp.get());
			}catch(Exception ex) {
				model.addAttribute("message", ""+workOrderId+" does not exit");
			}
			
		}
		else {
			allFreelancerDelivery = deliveryService
					.findWorkOrdersDeliveryByUserOrderByCreatedDateDesc(opUser.get());
		}

		FreelancerWorkDeliveryForAdmin deliverySize = new FreelancerWorkDeliveryForAdmin();
		deliverySize.setActiveDelivery(inprogressDelivery.size());
		deliverySize.setCompletedDelivery(completedDelivery.size());
		deliverySize.setFinishedDelivery(finishedDelivery.size());
		deliverySize.setAllRequest(allFreelancerDelivery.size());

		// TODO: Freelancer Transactions(Payments) details

		model.addAttribute("deliveryStatus", deliveryStatus);
		model.addAttribute("proposalsStatus", proposalsStatus);
		model.addAttribute("opUser", opUser.get());
		model.addAttribute("servicesRendered", servicesRendered);
		model.addAttribute("deliverySize", deliverySize);
		model.addAttribute("allFreelancerDelivery", allFreelancerDelivery);
		model.addAttribute("proposals", proposals);
		
	}

	@GetMapping("/admin-user")
	public String adminUser(Model model) {

		List<User> users = userService.findAll();

		model.addAttribute("adminSearchDTO", new AdminSearchDTO());
		model.addAttribute("users", users);

		return "dashboards/admin/overview/Users/users";
	}
	
	@PostMapping("/admin-user-search")
	public String adminUserSearch(@ModelAttribute("adminSearchDTO") AdminSearchDTO adminSearchDTO, Model model) {

		List<User> users = userService.findAll();
		
		if(adminSearchDTO.getSearchOption().equals("ClientID")) {
			users = userService.findUsersByUsername(adminSearchDTO.getSearchValue());
		}
		else if(adminSearchDTO.getSearchOption().equals("FirstName")) {
			users = userService.findUsersByFirstName(adminSearchDTO.getSearchValue());
		}
		else if(adminSearchDTO.getSearchOption().equals("LastName")) {
			users = userService.findUsersByLastName(adminSearchDTO.getSearchValue());
		}
		else if(adminSearchDTO.getSearchOption().equals("Phone")) {
			users = userService.findUsersByPhone(adminSearchDTO.getSearchValue());
		}else {
			users = userService.findAll();
		}

		model.addAttribute("adminSearchDTO", new AdminSearchDTO());
		model.addAttribute("users", users);
		return "dashboards/admin/overview/Users/users";
	}

	@GetMapping("/get-user/{user}")
	public String getUser(@PathVariable(value = "user") String username, Model model) {

		Optional<User> opUser = userService.findFirstUserByUsername(username);

		model.addAttribute("opUser", opUser.get());
		return "dashboards/admin/overview/Users/user";
	}

	@GetMapping("/add-user")
	public String addUser() {

		return "dashboards/admin/overview/Users/newuser";
	}

	@GetMapping("/block-client-user/{user}")
	public String blockClientUser(@PathVariable(value = "user") String username, RedirectAttributes attributes) {

		int updateUserStatus = updateUserStatus(username, 4); // 4 means disabled
		System.out.println("===>>>updateUserStatus: " + updateUserStatus);
		attributes.addFlashAttribute("message", "User Block successfully.");
		return "redirect:/client-details/" + username;
	}

	@GetMapping("/activate-client-user/{user}")
	public String activateClientUser(@PathVariable(value = "user") String username, RedirectAttributes attributes) {

		int updateUserStatus = updateUserStatus(username, 2); // = 2 means activated
		System.out.println("===>>>updateUserStatus: " + updateUserStatus);
		attributes.addFlashAttribute("message", "User Activated successfully.");
		return "redirect:/client-details/" + username;
	}

	@GetMapping("/block-freelancer-user/{user}")
	public String blockFreelancerUser(@PathVariable(value = "user") String username, RedirectAttributes attributes) {

		int updateUserStatus = updateUserStatus(username, 4); // 4 means disabled
		System.out.println("===>>>updateUserStatus: " + updateUserStatus);
		attributes.addFlashAttribute("message", "User Block successfully.");
		return "redirect:/get-freelancer/" + username;
	}

	@GetMapping("/activate-freelancer-user/{user}")
	public String activateFreelancerUser(@PathVariable(value = "user") String username, RedirectAttributes attributes) {

		int updateUserStatus = updateUserStatus(username, 2); // = 2 means activated
		System.out.println("===>>>updateUserStatus: " + updateUserStatus);
		attributes.addFlashAttribute("message", "User Activated successfully.");
		return "redirect:/get-freelancer/" + username;
	}

	@GetMapping("/block-user/{user}")
	public String blockUser(@PathVariable(value = "user") String username, RedirectAttributes attributes) {

		int updateUserStatus = updateUserStatus(username, 4); // 4 means disabled
		System.out.println("===>>>updateUserStatus: " + updateUserStatus);
		attributes.addFlashAttribute("message", "User Block successfully.");
		return "redirect:/get-user/" + username;
	}

	@GetMapping("/activate-user/{user}")
	public String activateUser(@PathVariable(value = "user") String username, RedirectAttributes attributes) {

		int updateUserStatus = updateUserStatus(username, 2); // = 2 means activated
		System.out.println("===>>>updateUserStatus: " + updateUserStatus);
		attributes.addFlashAttribute("message", "User Activated successfully.");
		return "redirect:/get-user/" + username;
	}

	@GetMapping("/job-details/{workId}/{workOrderStatus}")
	public String getJobStatusWithStatus(@PathVariable(value = "workId") String workId,
			@PathVariable(value = "workOrderStatus") String workOrderStatusId, Model model) {

		try {

			Optional<WorkOrder> workOrderInfo = workOrderService.findFirstWorkOrderByWorkId(workId);

			List<WorkOrderAttachment> workAttachments = workOrderAttachmentService
					.findWorkOrderAttachmentByWorkOrder(workOrderInfo.get());

			Optional<WorkOrderAttachment> workAttachmentDetails = workOrderAttachmentService
					.findFirstWorkOrderAttachmentByWorkOrder(workOrderInfo.get());

			if (deliveryService.findFirstWorkOrdersDeliveryByWorkOrderOrderByCreatedDateDesc(
					workOrderService.findById(workId).get()).isPresent()) {

				Optional<WorkOrdersDelivery> workDeliveryDetail = deliveryService
						.findFirstWorkOrdersDeliveryByWorkOrderOrderByCreatedDateDesc(
								workOrderService.findById(workId).get());

				List<DeliveryAttachment> deliveryAttachments = deliveryAttachmentService
						.findDeliveryAttachmentByWorkOrderDelivery(workDeliveryDetail.get());

				List<FreelancerDeliveryAttachment> fDeliveryAttachments = freelancerDeliveryAttachmentService
						.findFreelancerDeliveryAttachmentByWorkOrderDeliveryOrderByEntryDateDesc(
								workDeliveryDetail.get());

				model.addAttribute("fDeliveryAttachments", fDeliveryAttachments);
				model.addAttribute("workDeliveryDetail", workDeliveryDetail.get());
				model.addAttribute("deliveryAttachments", deliveryAttachments);
			}

			// get Freelancer based on service type
			Optional<ServiceType> opServiceType = serviceType
					.findById(workOrderInfo.get().getServiceType().getTypeId());
			List<ServiceRendered> serviceRendered = serviceRenderedService
					.findServiceRenderedListByServiceType(opServiceType.get());

			model.addAttribute("workOrderInfoDetails", workOrderInfo.get());
			model.addAttribute("workAttachments", workAttachments);
			model.addAttribute("workAttachmentDetails", workAttachmentDetails.get());
			model.addAttribute("adminPageWordCountDTO", new AdminPageWordCountDTO());
			model.addAttribute("serviceRenderedList", serviceRendered);

		} catch (Exception ex) {
			System.out.println("===>>> Exception: " + ex);
		}

		return "dashboards/admin/overview/jobs/adminjobsdetails";
	}

	@PostMapping("/saveJobCount")
	public String saveJobCount(@ModelAttribute("adminPageWordCountDTO") AdminPageWordCountDTO adminPageWordCountDTO,
			BindingResult bindingResultModel, Model model, RedirectAttributes attributes) {

		int adminPageCount = adminPageWordCountDTO.getWorkAttachmentId().size();

		System.out.println("===>>> adminPageWordCountDTO: " + adminPageWordCountDTO);

		for (int i = 0; i < adminPageCount; i++) {

			int pageCount = Integer.parseInt(adminPageWordCountDTO.getPageCount().get(i));
			int wordCount = Integer.parseInt(adminPageWordCountDTO.getWorkCount().get(i));

			int updateJobCounts = workOrderAttachmentService.updateJobCounts(wordCount, pageCount,
					adminPageWordCountDTO.getWorkAttachmentId().get(i));

			System.out.println("===>>> updateJobCounts: " + updateJobCounts);

		}

		model.addAttribute("jobStatusDTO", new SendJobDTO());

		attributes.addFlashAttribute("message", "Job counts saved successfully");
		return "redirect:/job-details/" + adminPageWordCountDTO.getWorkId() + "/"
				+ adminPageWordCountDTO.getWorkOrderStatus() + "";
	}

	@PostMapping("/sendFinishedJob")
	public String saveJobCount(@ModelAttribute("jobStatusDTO") SendJobDTO sendJobDTO, BindingResult bindingResultModel,
			Model model, RedirectAttributes attributes) {

		System.out.println("===>>> adminPageWordCountDTO: " + sendJobDTO);

		// update work and delivery status
		int updateWorkOrderStatus = workOrderService.updateWorkOrderStatus(9, sendJobDTO.getWorkId());// 9 means //
																										// sending to
																										// finished job
		int updateWorkDeliveryStatus = deliveryService.updateWorkDeliveryStatus(9, sendJobDTO.getDeliveryId());

		System.out.println("===>>> updateWorkOrderStatus: " + updateWorkOrderStatus);
		System.out.println("===>>> updateWorkDeliveryStatus: " + updateWorkDeliveryStatus);

		attributes.addFlashAttribute("message", "Finished Job Forwarded to Client");

		return "redirect:/job-details/" + sendJobDTO.getWorkId() + "/" + sendJobDTO.getJobStatus() + "";
	}

	@GetMapping("/sendJobProposal")
	@ResponseBody
	public String sendProposal(@RequestParam String freelancerUserId, @RequestParam String workId,
			@RequestParam String status, @RequestParam String message, @RequestParam String user,
			@RequestParam String deliveryId, Model model) {

		System.out.println("===>>> adminPageWordCountDTO: " + freelancerUserId);

		Optional<WorkOrder> workOrder = workOrderService.findById(workId);
		Optional<User> opUser = userService.findFirstUserByUsername(freelancerUserId);
		Optional<ProposalStatus> proposalStatus = proposalStatusService.findById(9);// Freelancer Request Sent

		Proposal proposal = new Proposal();
//		proposal.setProposalId("");
		proposal.setWorkOrder(workOrder.get());
		proposal.setUser(opUser.get());
		proposal.setProposalStatus(proposalStatus.get());
		proposal.setAmount(0.0);
		proposal.setModifiedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		proposal.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		proposalService.save(proposal);

		return "Job Forwarded to Freelancer";
	}

	@GetMapping("/freelancer-details/{id}/{user}")
	public String freelancerDetails(@PathVariable(value = "id") String id, @PathVariable(value = "user") String user,
			Model model) {

		try {

			Optional<WorkOrder> workOrder = workOrderService.findById(id);

			System.out.println("===>>> workOrder: " + workOrder);

			List<WorkOrderAttachment> workOrderAttachments = workOrderAttachmentService
					.findWorkOrderAttachmentByWorkOrder(workOrder.get());

			System.out.println("===>>> workOrderAttachments: " + workOrderAttachments);

			Optional<User> opUser = userService.findFirstUserByUsername(user);

			System.out.println("===>>> opUser: " + opUser);

			List<ServiceRendered> servicesRendered = serviceRenderedService.findServiceRenderedListByUser(opUser.get());

			System.out.println("===>>> servicesRendered: " + servicesRendered);

			model.addAttribute("workOrderDetails", workOrder.get());
			model.addAttribute("userDetails", opUser.get());
			model.addAttribute("servicesRendered", servicesRendered);
			model.addAttribute("clientAmountDTO", new ClientAmountDTO());

		} catch (Exception ex) {
			model.addAttribute("workOrderFirstAttachment", new WorkOrderAttachment());
		}

		return "dashboards/admin/overview/users/freelancerdetails";
	}
	
	@PostMapping("/add-new-user")
	public String addNewUser(@ModelAttribute("AddUserDTO") AddUserDTO addUserDTO,
			 Model model, RedirectAttributes attributes) {
		
		System.out.println("===>>> addUserDTO: "+addUserDTO);
		
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		User user = new User();

		
		// Set user Role
		List<Role> roles = new ArrayList<>();
		roles.add(roleService.findById(1).get());// 4 means freelancer role, 3 means client role, 2 means
													// supervisor role, 1 means admin role
		user.setRoles(roles);

		// getUserStatus
		Optional<UserStatus> userStatus = userStatusService.findById(2);// 2 means active from user status table

		String password = addUserDTO.getPassword();

		user.setUserId(0);
		user.setUsername(addUserDTO.getUsername());
		user.setUpassword(bCryptPasswordEncoder.encode(password));
		user.setFirstName(addUserDTO.getFirstName());
		user.setLastName(addUserDTO.getLastName());
		user.setCountry(addUserDTO.getCountry());
		user.setPhone(addUserDTO.getPhone());
		user.setEmailAddress(addUserDTO.getEmail());
		user.setUserStatus(userStatus.get());
		user.setGender(addUserDTO.getGender());
		user.setNationality(addUserDTO.getNationality());
		user.setAddress(addUserDTO.getAddress());
		user.setSummary(addUserDTO.getSummary());
		user.setEducation("");
		user.setMiddleName(addUserDTO.getMiddleName());
		user.setPostalCode(addUserDTO.getPostalCode());
		user.setProfilePicture("");
		user.setModifiedDate(modifiedDate);
		user.setCreatedDate(createdDate);

		System.out.println("===>>>User Data: " + user.toString());

		userService.save(user);

		attributes.addFlashAttribute("message", "Great, Profile has been created!");
		return "redirect:/admin-user";
	}

	@GetMapping("/admin-finance")
	public String adminFinance() {

		return "dashboards/admin/overview/finances";
	}

	public int updateUserStatus(String username, int status) {

		Optional<User> opUser = userService.findFirstUserByUsername(username);

		Optional<UserStatus> userStatus = userStatusService.findById(status);

		int updateUserStatus = userService.updateUserStatus(userStatus.get().getUserStatusId(),
				opUser.get().getUserId());

		System.out.println("===>>>updateUserStatus: " + updateUserStatus);

		return updateUserStatus;

	}

}
