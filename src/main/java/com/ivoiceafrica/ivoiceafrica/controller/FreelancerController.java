package com.ivoiceafrica.ivoiceafrica.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ivoiceafrica.ivoiceafrica.DTO.ClientAmountDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.ClientPersonalDetailDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerAcceptanceDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerOfferDeclineDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerPersonalDetailDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerProfilePictureDetailDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerSignupDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerSkillDetailDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.SaveClientJobsDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.UpdateProfilePictureDTO;
import com.ivoiceafrica.ivoiceafrica.auth.entity.Role;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.auth.entity.UserStatus;
import com.ivoiceafrica.ivoiceafrica.components.models.ClientComponentModel;
import com.ivoiceafrica.ivoiceafrica.entity.BankDetail;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryStatus;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerDeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerServicePricing;
import com.ivoiceafrica.ivoiceafrica.entity.Industry;
import com.ivoiceafrica.ivoiceafrica.entity.Language;
import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.ProposalStatus;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceIndustries;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceLanguages;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceTypePricing;
import com.ivoiceafrica.ivoiceafrica.entity.WorkFreelancerPayment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderReview;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;
import com.ivoiceafrica.ivoiceafrica.entity.WorkTransactions;
import com.ivoiceafrica.ivoiceafrica.models.FreelancerDocumentNameModel;
import com.ivoiceafrica.ivoiceafrica.models.FreelancerPricingModel;
import com.ivoiceafrica.ivoiceafrica.models.SkillUploadModel;
import com.ivoiceafrica.ivoiceafrica.service.BankDetailService;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryStatusService;
import com.ivoiceafrica.ivoiceafrica.service.FreelancerDeliveryAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.FreelancerPricingService;
import com.ivoiceafrica.ivoiceafrica.service.IndustryService;
import com.ivoiceafrica.ivoiceafrica.service.LanguageService;
import com.ivoiceafrica.ivoiceafrica.service.PricingTypeService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalStatusService;
import com.ivoiceafrica.ivoiceafrica.service.RoleService;
import com.ivoiceafrica.ivoiceafrica.service.SIndustriesService;
import com.ivoiceafrica.ivoiceafrica.service.SLanguageService;
import com.ivoiceafrica.ivoiceafrica.service.SRenderedService;
import com.ivoiceafrica.ivoiceafrica.service.STypePricingService;
import com.ivoiceafrica.ivoiceafrica.service.STypeService;
import com.ivoiceafrica.ivoiceafrica.service.UserStatusService;
import com.ivoiceafrica.ivoiceafrica.service.WorkFreelancerPaymentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderReviewService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderStatusService;
import com.ivoiceafrica.ivoiceafrica.service.WorkPaymentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkPaymentStatusService;
import com.ivoiceafrica.ivoiceafrica.service.WorkTransactionService;
import com.ivoiceafrica.ivoiceafrica.utility.GetEndDate;

@Controller
public class FreelancerController {

	@Value("${upload.path}")
	String uploadDir;

	@Autowired
	CustomUserDetailService userService;

	@Autowired
	UserStatusService userStatusService;

	@Autowired
	LanguageService languageService;

	@Autowired
	IndustryService industryService;

	@Autowired
	RoleService roleService;

	@Autowired
	SLanguageService serviceLanguage;

	@Autowired
	SIndustriesService serviceIndustry;

	@Autowired
	FreelancerPricingService freelancerPricing;

	@Autowired
	SRenderedService serviceRendered;

	@Autowired
	STypePricingService serviceTypePricing;

	@Autowired
	PricingTypeService pricingTypeService;

	@Autowired
	STypeService serviceType;

	@Autowired
	HttpSession session;

	@Autowired
	DeliveryService deliveryService;

	@Autowired
	DeliveryStatusService deliveryStatusService;

	@Autowired
	DeliveryAttachmentService deliveryAttachmentService;

	@Autowired
	ProposalService proposalService;

	@Autowired
	ProposalStatusService proposalStatusService;

	@Autowired
	WorkOrderService workOrderService;

	@Autowired
	WorkOrderStatusService workOrderStatusService;

	@Autowired
	WorkOrderAttachmentService workOrderAttachmentService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	SRenderedService serviceRenderedService;

	@Autowired
	FreelancerDeliveryAttachmentService attachmentService;

	@Autowired
	BankDetailService bankDetailService;

	@Autowired
	WorkPaymentStatusService workPaymentStatusService;

	@Autowired
	WorkPaymentService workPaymentService;

	@Autowired
	WorkFreelancerPaymentService workFreelancerPaymentService;

	@Autowired
	ClientComponentModel clientComponentModel;

	@Autowired
	WorkTransactionService workTransactionService;
	
	@Autowired
	WorkOrderReviewService orderReviewService;

	void dashboardFinancials(Model model, String userId) {

		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		List<WorkFreelancerPayment> workfreelancerPayments = workFreelancerPaymentService
				.findWorkFreelancerPaymentByFreelancerIdOrderByEntryDateDesc(userDetails.get());

		double inAccount = 0.0;
		double inEscrow = 0.0;
		double Withdrawn = 0.0;
		double totalEarnings = 0.0;
		double moneyInAccount = 0.0;

		for (WorkFreelancerPayment payment : workfreelancerPayments) {

			if (payment.getPaymentStatus().getPaymentStatusId() == 4) { // 4 means excrow status
				inEscrow += payment.getAmount();
			}

//			if (payment.getPaymentStatus().getPaymentStatusId() == 5) { // 5 means withdrwal status
//				Withdrawn += payment.getAmount();
//			}

			if (payment.getPaymentStatus().getPaymentStatusId() == 6) {// 6 means in account
				inAccount += payment.getAmount();
			}

			totalEarnings += payment.getAmount();

		}
		
		List<WorkTransactions> debitedTransactions = workTransactionService
				.findWorkTransactionsByUserAndIsInFlowOrderByEntryDateDesc(userDetails.get(), false);

		List<WorkTransactions> creditedTransactions = workTransactionService
				.findWorkTransactionsByUserAndIsInFlowOrderByEntryDateDesc(userDetails.get(), true);

		
		
		double withdrawnTrans = 0.0;
		for (WorkTransactions transactions : debitedTransactions) {
			withdrawnTrans += transactions.getAmount();
		}

		double creditedTrans = 0.0;
		for (WorkTransactions transactions : creditedTransactions) {
			creditedTrans += transactions.getAmount();
		}

		if(withdrawnTrans == 0.0) {
			moneyInAccount = inAccount;
		}else {
			moneyInAccount = creditedTrans-withdrawnTrans;
		}

		System.out.println("===>>>User ID Freelancer: " + userId);

		model.addAttribute("workfreelancerPayments", workfreelancerPayments);
		model.addAttribute("workfreelancerPaymentsSize", workfreelancerPayments.size());
		model.addAttribute("totalEscrow", inEscrow);
		model.addAttribute("totalWithdrawn", withdrawnTrans);
		model.addAttribute("inAccount", moneyInAccount);
		model.addAttribute("totalEarning", totalEarnings);

	}

	@GetMapping("/freelancer-dashboard")
	public String freelancerDashboard(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		String currentProposalDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		dashboardFinancials(model, userId);

		// Get jobs based on status (all, after deadline, deadline, processing) and the
		// languages related to the jobs;
		List<WorkOrdersDelivery> allDelivery = deliveryService
				.findWorkOrdersDeliveryByUserOrderByCreatedDateDesc(userDetails.get());

		List<WorkOrdersDelivery> overdueDelivery = deliveryService.findOverdueWorkOrdersDelivery(currentDate,
				userDetails.get().getUserId());

		List<WorkOrdersDelivery> inprogressDelivery = deliveryService
				.findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(
						deliveryStatusService.findById(5).get(), userDetails.get()); // 5 means in progress

		// Get job alerts for user
		int limitOfRecord = 5;
		List<Proposal> proposalByLimit = proposalService.findProposalByLimitAndStatusAndCurrentDate(
				userDetails.get().getUserId(), proposalStatusService.findById(11).get().getProposalStatusId(),
				currentProposalDate, limitOfRecord); // 11 means assigned to freelancer by client

		// get freelancerStatus from db and attach to session
		session.setAttribute("userStatus", userDetails.get().getUserStatus());

		model.addAttribute("userDetails", userDetails.get());
		model.addAttribute("allDeliveries", allDelivery);
		model.addAttribute("overdueDeliveries", overdueDelivery);
		model.addAttribute("inprogressDeliveries", inprogressDelivery);
		model.addAttribute("proposalsByLimit", proposalByLimit);

		return "dashboards/freelancers/dashboard";
	}

	@GetMapping("/freelancer-jobs-alerts")
	public String freelancerJobAlert(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		List<Proposal> userProposal = proposalService.findProposalByUserAndStatusOrderByCreatedDesc(
				userDetails.get().getUserId(), proposalStatusService.findById(9).get().getProposalStatusId()); // Freelancer
																												// Request
		model.addAttribute("userProposal", userProposal);
		return "dashboards/freelancers/jobalerts";
	}

	@GetMapping("/freelancer-jobs-details/{workOrderId}/{proposalId}")
	public String freelancerJobDetails(@PathVariable(value = "workOrderId") String workOrderId,
			@PathVariable(value = "proposalId") String proposalId, Model model) {

		Optional<WorkOrder> opWorkOrder = workOrderService.findById(workOrderId);

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		List<WorkOrderAttachment> workOrderAttachments = workOrderAttachmentService
				.findWorkOrderAttachmentByWorkOrder(opWorkOrder.get());

		Optional<Proposal> proposal = proposalService.findById(proposalId);

		String message = clientComponentModel.workOrderCalculations(opWorkOrder, model);
		if (message.equals("done")) {
			model.addAttribute("workOrderAttachmentList", workOrderAttachments);
			model.addAttribute("userDetails", userDetails.get());
			model.addAttribute("opWorkOrder", opWorkOrder.get());
			model.addAttribute("proposalId", proposalId);
			model.addAttribute("proposal", proposal.get());
			model.addAttribute("freelancerAcceptanceDTO", new FreelancerAcceptanceDTO());
			model.addAttribute("freelancerOfferDeclineDTO", new FreelancerOfferDeclineDTO());
		} else {
			model.addAttribute("message", message);
			return "dashboards/freelancers/jobdetail";
		}

		return "dashboards/freelancers/jobdetail";
	}

	@PostMapping("/freelancer-job-acceptance")
	public String freelancerJobAcceptance(
			@ModelAttribute("FreelancerAcceptanceDTO") FreelancerAcceptanceDTO freelancerAcceptanceDTO, Model model) {

		System.out.println("===>>> freelancerAcceptanceDTO: " + freelancerAcceptanceDTO.toString());

		try {
			Optional<WorkOrder> workOrder = workOrderService.findById(freelancerAcceptanceDTO.getWorkOrderId());
			Optional<User> userDetails = userService.findFirstUserByUsername(freelancerAcceptanceDTO.getUserId());
			Optional<Proposal> proposal = proposalService.findById(freelancerAcceptanceDTO.getProposalId());

			String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			String sd = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			Date startDate;

			int updateProposalAmount = proposalService.updateProposalAmount(
					freelancerAcceptanceDTO.getFreelancerAmount(), freelancerAcceptanceDTO.getProposalId());
			System.out.println("===>>> updateProposalAmount: " + updateProposalAmount);

			if (updateProposalAmount == 1) {

				// update freelancer to accept request
				int updateProposalStatus = proposalService.updateProposalByProposalId(11,
						freelancerAcceptanceDTO.getProposalId());// 11 means freelancer accepted

				System.out.println("===>>> updateProposalStatus: " + updateProposalStatus);

				if (updateProposalStatus == 1) {

					WorkOrdersDelivery workDelivery = new WorkOrdersDelivery();

					startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sd);
					Date endDate = GetEndDate.calculateNextSettleDate(startDate,
							workOrder.get().getDuration().getDurationId());

					String ed = new SimpleDateFormat("yyyy-MM-dd").format(endDate);

					System.out.println("EndDate: " + endDate);
					System.out.println("==>> ED: " + ed);

					Optional<DeliveryStatus> deliveryStatus = deliveryStatusService.findById(5);// 5 means inprogress
																								// status
					// add workOrder Delivery
//					workDelivery.setDeliveryId("");
					workDelivery.setWorkOrder(workOrder.get());
					workDelivery.setClientUserId(workOrder.get().getUser().getUserId());// client User Id
					workDelivery.setUser(userDetails.get());// freelancer User
					workDelivery.setAmount(String.valueOf(freelancerAcceptanceDTO.getFreelancerAmount()));
					workDelivery.setDeliveryStatus(deliveryStatus.get());
					workDelivery.setStartDate(sd);
					workDelivery.setEndDate(ed);
					workDelivery.setCreatedDate(createdDate);

					deliveryService.save(workDelivery);

					Optional<WorkOrdersDelivery> opWorkOrderDelivery = deliveryService
							.findFirstWorkOrdersDeliveryByUserOrderByCreatedDateDesc(userDetails.get());

					List<WorkOrderAttachment> workOrderAttachments = workOrderAttachmentService
							.findWorkOrderAttachmentByWorkOrder(workOrder.get());

					for (WorkOrderAttachment attachment : workOrderAttachments) {

						DeliveryAttachment deliveryAttachment = new DeliveryAttachment();
//						deliveryAttachment.setDeliveryAttachId("");
						deliveryAttachment.setWorkOrderDelivery(opWorkOrderDelivery.get());
						deliveryAttachment.setLinkMediaFile(attachment.getLinkMediaFile());
						deliveryAttachment.setDescription(attachment.getDescription());
						deliveryAttachment.setSource(attachment.getSource());
						deliveryAttachment.setDestination(attachment.getDestination());

						deliveryAttachmentService.save(deliveryAttachment);
					}

					int updateWorkOrderStatus = workOrderService.updateWorkOrderStatus(5, workOrder.get().getWorkId());
					System.out.println("===>>> updateWorkOrderStatus Inprogess: " + updateWorkOrderStatus);

					model.addAttribute("freelancerAcceptanceDTO", new FreelancerAcceptanceDTO());
					model.addAttribute("freelancerOfferDeclineDTO", new FreelancerOfferDeclineDTO());
					model.addAttribute("message", "Great, Job has been accepted successfully.");

				} else {
					model.addAttribute("message", "Updating Proposal Details Failed, Logout and Try again");
				}
			} else {
				model.addAttribute("message", "Updating Proposal Amount Failed, Logout and Try again");
			}

			Optional<WorkOrdersDelivery> delivery = deliveryService
					.findWorkOrdersDeliveryByUserAndWorkOrder(userDetails.get(), workOrder.get());

			model.addAttribute("freelancerStatus", 5);
			model.addAttribute("userDetails", userDetails.get());
			model.addAttribute("opWorkOrder", workOrder.get());
			model.addAttribute("proposal", proposal.get());
			model.addAttribute("freelancerAcceptanceDTO", new FreelancerAcceptanceDTO());
			model.addAttribute("freelancerOfferDeclineDTO", new FreelancerOfferDeclineDTO());
			model.addAttribute("delivery", delivery.get());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in date: " + e.getMessage());
		}

		return "dashboards/freelancers/jobdetail";
	}

	@PostMapping("/freelancer-offer-decline")
	public String freelancerDeclineOffer(
			@ModelAttribute("FreelancerOfferDeclineDTO") FreelancerOfferDeclineDTO freelancerOfferDeclineDTO,
			Model model) {

		System.out.println("===>>> freelancerOfferDeclineDTO: " + freelancerOfferDeclineDTO.toString());

		Optional<WorkOrder> opWorkOrder = workOrderService.findById(freelancerOfferDeclineDTO.getWorkOrderId());
		
		System.out.println("===>>> opWorkOrder: " + opWorkOrder);

		Optional<User> userDetails = userService.findFirstUserByUsername(freelancerOfferDeclineDTO.getUserId()); //freelancer user
		
		System.out.println("===>>> userDetails: " + userDetails);
		
		Optional<Proposal> proposal = proposalService.findById(freelancerOfferDeclineDTO.getProposalId());
		
		System.out.println("===>>> proposal: " + proposal);
		
//		Optional<WorkOrdersDelivery> delivery = deliveryService
//				.findWorkOrdersDeliveryByUserAndWorkOrder(userDetails.get(), opWorkOrder.get());
		
//		System.out.println("===>>> delivery: " + delivery);

		// update freelancer to decline request
		int updateProposalStatus = proposalService.updateProposalByProposalId(14,
				freelancerOfferDeclineDTO.getProposalId());// 14 means freelancer declined workorder status
		
		System.out.println("===>>> DeclineFreelancerRequest: " + updateProposalStatus);

		model.addAttribute("proposal", proposal.get());
//		model.addAttribute("delivery", delivery.get());
		model.addAttribute("freelancerStatus", 14);
		model.addAttribute("userDetails", userDetails.get());
		model.addAttribute("opWorkOrder", opWorkOrder.get());
		model.addAttribute("freelancerAcceptanceDTO", new FreelancerAcceptanceDTO());
		model.addAttribute("freelancerOfferDeclineDTO", new FreelancerOfferDeclineDTO());
		model.addAttribute("message", "Great, Job has been declined.");

		return "dashboards/freelancers/jobdetail";
	}

	@GetMapping("/freelancer-active-jobs")
	public String freelancerActiveJobs(Model model) {

		// get user from session;
		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		System.out.println("====>>> User: " + userDetails.get().getUserId());

		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		Optional<ProposalStatus> proposalStatus = proposalStatusService.findById(5); // 5 represents in Progress

		List<WorkOrdersDelivery> allDelivery = deliveryService
				.findWorkOrdersDeliveryByUserOrderByCreatedDateDesc(userDetails.get());

		List<WorkOrdersDelivery> overdueDelivery = deliveryService.findOverdueWorkOrdersDelivery(currentDate,
				userDetails.get().getUserId());

		List<WorkOrdersDelivery> inprogressDelivery = deliveryService
				.findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(
						deliveryStatusService.findById(5).get(), userDetails.get()); // 5 means in progress

		model.addAttribute("userDetails", userDetails);
		model.addAttribute("allDeliveries", allDelivery);
		model.addAttribute("overdueDeliveries", overdueDelivery);
		model.addAttribute("inprogressDeliveries", inprogressDelivery);
		return "dashboards/freelancers/activejobs";
	}

	@GetMapping("/freelancer-active-details/{workOrderId}")
	public String freelancerActiveDetails(@PathVariable(value = "workOrderId") String workOrderId, Model model) {

		Optional<WorkOrder> opWorkOrder = workOrderService.findById(workOrderId);

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		Optional<WorkOrdersDelivery> opWorkOrderDelivery = deliveryService
				.findFirstWorkOrdersDeliveryByWorkOrderOrderByCreatedDateDesc(opWorkOrder.get());

		List<DeliveryAttachment> deliveryAttachments = deliveryAttachmentService
				.findDeliveryAttachmentByWorkOrderDelivery(opWorkOrderDelivery.get());

		List<WorkOrderAttachment> workOrderAttachments = workOrderAttachmentService
				.findWorkOrderAttachmentByWorkOrder(opWorkOrder.get());
		
		//Get Reviews
		List<WorkOrderReview> reviews = orderReviewService.findWorkOrderReviewByworkOrderAndUserOrderByEntryDateDesc(opWorkOrder.get(), userDetails.get());

		model.addAttribute("reviews", reviews);
		model.addAttribute("workOrderAttachmentList", workOrderAttachments);
		model.addAttribute("deliveryAttachments", deliveryAttachments);
		model.addAttribute("opWorkOrderDelivery", opWorkOrderDelivery.get());
		model.addAttribute("userDetails", userDetails.get());
		model.addAttribute("opWorkOrder", opWorkOrder.get());
		model.addAttribute("saveClientJobsDTO", new SaveClientJobsDTO());
		model.addAttribute("workOrderStatus", workOrderService.findById(workOrderId).get());

		return "dashboards/freelancers/activejobdetail";
	}

	@GetMapping("/freelancer-activedetails-inprogress")
	public String freelancerActiveDetailsDueSoon(Model model) {

		// get user from session;
		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		// TODO: Change to the original Status
		Optional<ProposalStatus> proposalStatus = proposalStatusService.findById(5); // 5 represents in Progress

		List<WorkOrdersDelivery> allDelivery = deliveryService
				.findWorkOrdersDeliveryByUserOrderByCreatedDateDesc(userDetails.get());

		List<WorkOrdersDelivery> overdueDelivery = deliveryService.findOverdueWorkOrdersDelivery(currentDate,
				userDetails.get().getUserId());

		List<WorkOrdersDelivery> inprogressDelivery = deliveryService
				.findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(
						deliveryStatusService.findById(5).get(), userDetails.get()); // 5 means in progress

		model.addAttribute("userDetails", userDetails);
		model.addAttribute("allDeliveries", allDelivery);
		model.addAttribute("overdueDeliveries", overdueDelivery);
		model.addAttribute("inprogressDeliveries", inprogressDelivery);

		return "dashboards/freelancers/inprogress2";
	}

	@GetMapping("/freelancer-activedetails-overdue")
	public String freelancerActiveDetailsOverDue(Model model) {

		// get user from session;
		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		// TODO: Change to the original Status
		Optional<ProposalStatus> proposalStatus = proposalStatusService.findById(5); // 5 represents in Progress

		List<WorkOrdersDelivery> allDelivery = deliveryService
				.findWorkOrdersDeliveryByUserOrderByCreatedDateDesc(userDetails.get());

		List<WorkOrdersDelivery> overdueDelivery = deliveryService.findOverdueWorkOrdersDelivery(currentDate,
				userDetails.get().getUserId());

		List<WorkOrdersDelivery> inprogressDelivery = deliveryService
				.findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(
						deliveryStatusService.findById(5).get(), userDetails.get()); // 5 means in progress

		model.addAttribute("userDetails", userDetails);
		model.addAttribute("allDeliveries", allDelivery);
		model.addAttribute("overdueDeliveries", overdueDelivery);
		model.addAttribute("inprogressDeliveries", inprogressDelivery);

		return "dashboards/freelancers/overdue";
	}

	@GetMapping("/freelancer-activedetails-overdue2")
	public String freelancerActiveDetailsOverDue2(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		String currentProposalDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		dashboardFinancials(model, userId);

		// Get jobs based on status (all, after deadline, deadline, processing) and the
		// languages related to the jobs;
		List<WorkOrdersDelivery> allDelivery = deliveryService
				.findWorkOrdersDeliveryByUserOrderByCreatedDateDesc(userDetails.get());

		List<WorkOrdersDelivery> overdueDelivery = deliveryService.findOverdueWorkOrdersDelivery(currentDate,
				userDetails.get().getUserId());

		List<WorkOrdersDelivery> inprogressDelivery = deliveryService
				.findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(
						deliveryStatusService.findById(5).get(), userDetails.get()); // 5 means in progress

		// Get job alerts for user
		int limitOfRecord = 5;
		List<Proposal> proposalByLimit = proposalService.findProposalByLimitAndStatusAndCurrentDate(
				userDetails.get().getUserId(), proposalStatusService.findById(11).get().getProposalStatusId(),
				currentProposalDate, limitOfRecord); // 11 means assigned to freelancer by client

		model.addAttribute("userDetails", userDetails.get());
		model.addAttribute("allDeliveries", allDelivery);
		model.addAttribute("overdueDeliveries", overdueDelivery);
		model.addAttribute("inprogressDeliveries", inprogressDelivery);
		model.addAttribute("proposalsByLimit", proposalByLimit);

		return "dashboards/freelancers/overdue2";
	}

	@GetMapping("/freelancer-inprogress")
	public String freelancerUpcomingdeadline(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		String currentProposalDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		dashboardFinancials(model, userId);

		// Get jobs based on status (all, after deadline, deadline, processing) and the
		// languages related to the jobs;
		List<WorkOrdersDelivery> allDelivery = deliveryService
				.findWorkOrdersDeliveryByUserOrderByCreatedDateDesc(userDetails.get());

		List<WorkOrdersDelivery> overdueDelivery = deliveryService.findOverdueWorkOrdersDelivery(currentDate,
				userDetails.get().getUserId());

		List<WorkOrdersDelivery> inprogressDelivery = deliveryService
				.findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(
						deliveryStatusService.findById(5).get(), userDetails.get()); // 5 means in progress

		// Get job alerts for user
		int limitOfRecord = 5;
		List<Proposal> proposalByLimit = proposalService.findProposalByLimitAndStatusAndCurrentDate(
				userDetails.get().getUserId(), proposalStatusService.findById(11).get().getProposalStatusId(),
				currentProposalDate, limitOfRecord); // 11 means assigned to freelancer by client

		model.addAttribute("userDetails", userDetails.get());
		model.addAttribute("allDeliveries", allDelivery);
		model.addAttribute("overdueDeliveries", overdueDelivery);
		model.addAttribute("inprogressDeliveries", inprogressDelivery);
		model.addAttribute("proposalsByLimit", proposalByLimit);

		return "dashboards/freelancers/inprogress";
	}

	@GetMapping("/freelancer-finished-jobs")
	public String freelancerFinishedJobs(Model model) {

		Optional<DeliveryStatus> deliveryStatus = deliveryStatusService.findById(9); // 9 means finished
		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		List<WorkOrdersDelivery> finishedDelivery = deliveryService
				.findWorkOrdersDeliveryByDeliveryStatusAndUserOrderByCreatedDateDesc(deliveryStatus.get(),
						userDetails.get());

		model.addAttribute("finishedDeliveries", finishedDelivery);
		return "dashboards/freelancers/finishedjobs";
	}

	@GetMapping("/freelancer-finished-jobs2/{deliveryId}")
	public String freelancerFinishedJobs2(@PathVariable(value = "deliveryId") String deliveryId, Model model) {

		Optional<WorkOrdersDelivery> opDelivery = deliveryService.findById(deliveryId);

		// TODO: This will change to freelancerAttachment from db
		List<WorkOrderAttachment> workOrderAttachments = workOrderAttachmentService
				.findWorkOrderAttachmentByWorkOrder(opDelivery.get().getWorkOrder());

		model.addAttribute("workOrderAttachments", workOrderAttachments);
		model.addAttribute("opDelivery", opDelivery.get());

		return "dashboards/freelancers/finishedjobs2";
	}

	@GetMapping("/freelancer-notification")
	public String freelancerNotifications(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		List<Proposal> userProposal = proposalService.findProposalByUserAndStatusOrderByCreatedDesc(
				userDetails.get().getUserId(), proposalStatusService.findById(9).get().getProposalStatusId()); // Freelancer
																												// Request
																												// Sent

		model.addAttribute("notificationWorkDeliveries", userProposal);
		return "dashboards/freelancers/notification";
	}

	@GetMapping("/freelancer-status/{statusId}")
	public String freelancerStatusChange(@PathVariable(value = "statusId") String statusId, Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		if (statusId.equals("active")) {
			int user = userService.updateUserStatus(2, userDetails.get().getUserId());// 2 means active from db
			System.out.println("===>>> Active User: " + user);
		} else if (statusId.equals("busy")) {
			int user = userService.updateUserStatus(6, userDetails.get().getUserId());// 6 means busy from db
			System.out.println("===>>> Busy User: " + user);
		} else if (statusId.equals("away")) {
			int user = userService.updateUserStatus(7, userDetails.get().getUserId());// 7 means away from db
			System.out.println("===>>> Away User: " + user);
		}

		return "redirect:/freelancer-dashboard";
	}

	@GetMapping("/freelancer-job-status/{statusId}/{workOrderId}")
	public String freelancerJobStatus(@PathVariable(value = "statusId") String statusId,
			@PathVariable(value = "workOrderId") String workOrderId, Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		if (statusId.equals("inprogress")) {
			int updateWorkOrderStatus = workOrderService.updateWorkOrderStatus(5, workOrderId);
			System.out.println("===>>> updateWorkOrderStatus Inprogess: " + updateWorkOrderStatus);
		} else if (statusId.equals("decline")) {
			int updateWorkOrderStatus = workOrderService.updateWorkOrderStatus(3, workOrderId);
			System.out.println("===>>> updateWorkOrderStatus Decline: " + updateWorkOrderStatus);
		} else if (statusId.equals("pending")) {
			int updateWorkOrderStatus = workOrderService.updateWorkOrderStatus(1, workOrderId);
			System.out.println("===>>> updateWorkOrderStatus Pending: " + updateWorkOrderStatus);
		}

		return "redirect:/freelancer-active-details/" + workOrderId + "";
	}

	@GetMapping("/freelancer-weekly-financials")
	@ResponseBody
	public String freelancerWeekFinance(@RequestParam String inputParameter, Model model) {

		System.out.println("===>>> inputParameter: " + inputParameter);

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);

		String financialType = "";
		if (inputParameter.equals("this-week")) {
			System.out.println("===>>> This Week: ");

			financialType = "This week financials loaded successfully";
		} else if (inputParameter.equals("last-week")) {
			System.out.println("===>>> Last Week: ");
			financialType = "Last week financials loaded successfully";
		}
		return financialType;
	}

	@PostMapping("/save-freelancer-jobs")
	public String submitClientJob(@ModelAttribute("saveClientJobsDTO") SaveClientJobsDTO saveClientJobsDTO, Model model,
			RedirectAttributes attributes) {

		System.out.println("===>>> SaveClientJobsDTO: " + saveClientJobsDTO);

		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String entryDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		int attachmentSize = saveClientJobsDTO.getDeliveryId().length;

		for (int i = 0; i < attachmentSize; i++) {

			MultipartFile[] attachment = saveClientJobsDTO.getMultipartList();

			FreelancerDeliveryAttachment delivery = new FreelancerDeliveryAttachment();

			Optional<WorkOrdersDelivery> workOrderDelivery = deliveryService
					.findById(saveClientJobsDTO.getDeliveryId()[0]);

			String fileSize = FreelancerController.getDynamicSpace(attachment[i].getSize());

			System.out.println("File Size: " + fileSize);

			String imageUUID;
			if (!attachment[i].isEmpty()) {
				imageUUID = attachment[i].getOriginalFilename();
				Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
				try {
					Files.write(fileNameAndPath, attachment[i].getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("===>>>Exception: " + e.getMessage());
				}
			} else {
				imageUUID = "";
			}

			String[] deliveryAttachmentId = saveClientJobsDTO.getDeliveryAttachment();
			Optional<DeliveryAttachment> opDeliveryAttachment = deliveryAttachmentService
					.findById(deliveryAttachmentId[i]);

//			delivery.setAttachmentId("");
			delivery.setWorkOrderDelivery(workOrderDelivery.get());
			delivery.setDeliveryAttachment(opDeliveryAttachment.get());
			delivery.setLinkMediaFile(imageUUID);
			delivery.setModifiedDate(modifiedDate);
			delivery.setEntryDate(entryDate);

			attachmentService.save(delivery);
			System.out.println("===>>> Job submitted successfully");
		}

		// update workDelivery and WorkOrder
		// 2 means completed
		int updateDeliveryStatus = deliveryService.updateWorkDeliveryStatus(2, saveClientJobsDTO.getDeliveryId()[0]);
		System.out.println("===>>> updateDeliveryStatus: " + updateDeliveryStatus);

		int updateWorkOrderStatus = workOrderService.updateWorkOrderStatus(2, saveClientJobsDTO.getWorkOrderId()[0]);
		System.out.println("===>>> updateWorkOrderStatus: " + updateWorkOrderStatus);
		
		int updateCompletedDate = deliveryService.updateCompletedDate(modifiedDate, saveClientJobsDTO.getDeliveryId()[0]);
		System.out.println("===>>> updateCompletedDate: " + updateCompletedDate);

		attributes.addFlashAttribute("message", "Great, your job was succesfully submitted.");
		return "redirect:/freelancer-active-details/" + saveClientJobsDTO.getWorkOrderId()[0] + "";
	}

	@GetMapping("/freelancer-faqs")
	public String faqs() {
		return "dashboards/freelancers/faqs";
	}

	@GetMapping("/freelancer-finances")
	public String freelancerFinances(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);
		System.out.println("===>>> User Details Freelancer: " + userDetails.get());

		// get Payments
		List<WorkFreelancerPayment> workfreelancerPayments = workFreelancerPaymentService
				.findWorkFreelancerPaymentByFreelancerIdOrderByEntryDateDesc(userDetails.get());

		double inAccount = 0.0;
		double inEscrow = 0.0;
		double Withdrawn = 0.0;
		double totalEarnings = 0.0;
		
		double moneyInAccount = 0.0;

		for (WorkFreelancerPayment payment : workfreelancerPayments) {

			if (payment.getPaymentStatus().getPaymentStatusId() == 4) { // 4 means excrow status
				inEscrow += payment.getAmount();
			}

//			if (payment.getPaymentStatus().getPaymentStatusId() == 5) { // 5 means withdrwal status
//				Withdrawn += payment.getAmount();
//			}

			if (payment.getPaymentStatus().getPaymentStatusId() == 6) {// 6 means in account
				inAccount += payment.getAmount();
			}

			totalEarnings += payment.getAmount();

		}

		List<WorkTransactions> debitedTransactions = workTransactionService
				.findWorkTransactionsByUserAndIsInFlowOrderByEntryDateDesc(userDetails.get(), false);

		List<WorkTransactions> creditedTransactions = workTransactionService
				.findWorkTransactionsByUserAndIsInFlowOrderByEntryDateDesc(userDetails.get(), true);

		
		double withdrawnTrans = 0.0;
		for (WorkTransactions transactions : debitedTransactions) {
			withdrawnTrans += transactions.getAmount();
		}

		double creditedTrans = 0.0;
		for (WorkTransactions transactions : creditedTransactions) {
			creditedTrans += transactions.getAmount();
		}

		if(withdrawnTrans == 0.0) {
			moneyInAccount = inAccount;
		}else {
			moneyInAccount = creditedTrans-withdrawnTrans;
		}
		
		session.setAttribute("inAccountBalance", inAccount);
		session.setAttribute("totalEarnings", totalEarnings);

		boolean isBankDetailsExist = false;

		System.out.println("===>>>User ID Freelancer: " + userId);

		BankDetail detail = bankDetailService.findBankDetailsWithUserId(userDetails.get().getUserId());

		if (detail != null) {
			if (!detail.getBankId().isEmpty()) {
				isBankDetailsExist = true;
			} else {
				isBankDetailsExist = false;
			}

			if (!isBankDetailsExist) {
				model.addAttribute("message", "Please add your Bank Detail.");
			} else {
				model.addAttribute("message", "Bank Details Exist.");
			}

		} else {
			model.addAttribute("message", "Please add your Bank Detail.");
		}

		model.addAttribute("workfreelancerPayments", workfreelancerPayments);
		model.addAttribute("totalEscrow", inEscrow);
		model.addAttribute("totalWithdrawn", withdrawnTrans);
		model.addAttribute("inAccount", moneyInAccount);
		model.addAttribute("totalEarning", totalEarnings);

		return "dashboards/freelancers/Finances";
	}
	
	@GetMapping("/freelancer-transactions")
	public String freelancerTransactions(Model model) {

		String userId = (String) session.getAttribute("userId");
		Optional<User> userDetails = userService.findFirstUserByUsername(userId);
		System.out.println("===>>> User Details Freelancer: " + userDetails.get());

		List<WorkTransactions> transactions = workTransactionService
				.findWorkTransactionsByUserOrderByEntryDateDesc(userDetails.get());

		model.addAttribute("transactions", transactions);

		return "dashboards/freelancers/transactions";
	}
	


	@GetMapping("/freelancer-profile/{user}")
	public String freelnacerProfile(@PathVariable(value = "user") String user, Model model) {

		
		try {

			Optional<User> opUser = userService.findFirstUserByUsername(user);

			List<ServiceRendered> servicesRendered = serviceRenderedService.findServiceRenderedListByUser(opUser.get());

			model.addAttribute("userDetails", opUser.get());
			model.addAttribute("servicesRendered", servicesRendered);

		} catch (Exception ex) {
			model.addAttribute("workOrderFirstAttachment", new WorkOrderAttachment());
		}

		return "dashboards/freelancers/profile";
	}
	
	@PostMapping("/replace-freelancerprofile-picture")
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

		return "redirect:/freelancer-profile";
	}

	public static String getDynamicSpace(long diskSpaceUsed) {
		if (diskSpaceUsed <= 0) {
			return "0";
		}

		final String[] units = new String[] { "B", "KiB", "MiB", "GiB", "TiB" };
		int digitGroups = (int) (Math.log10(diskSpaceUsed) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(diskSpaceUsed / Math.pow(1024, digitGroups)) + " "
				+ units[digitGroups];
	}

	public static String getUploadFileName(MultipartFile file) {
		String filename = "";

		FreelancerController f = new FreelancerController();
		try {

			System.out.println("===>>> Multipart File: " + file.getSize());

			if (!file.isEmpty()) {
				filename = file.getOriginalFilename();
				System.out.println("===>>> Multipart File: " + file.getOriginalFilename());
				Path fileNameAndPath = Paths.get(f.uploadDir, filename);
				Files.write(fileNameAndPath, file.getBytes());
			} else {
				filename = "";
			}

		} catch (Exception ex) {
			System.out.println("===>>> Upload File Exception: " + ex.getMessage());
		}

		System.out.println("====>>> Final File name to save: " + filename);

		return filename;
	}

	@PostMapping("/freelancer/signup/save")
	public String saveFreelancerSign(@ModelAttribute("FreelancerSignupDTO") FreelancerSignupDTO freelancerSignupDTO,
			HttpSession session, Model model) {

		User user = userService.findUserDetailsByUsername(freelancerSignupDTO.getEmail());

		System.out.println("===>>> User: " + user);

		if (user != null) {

			model.addAttribute("message", "This user already exist");
			model.addAttribute("FreelancerPersonalDetailDTO", new FreelancerPersonalDetailDTO());
			return "onboarding/freelancer/signup";

		} else {
			session.setAttribute("freelancerSignupDto", freelancerSignupDTO);

			model.addAttribute("FreelancerPersonalDetailDTO", new FreelancerPersonalDetailDTO());

			return "onboarding/freelancer/profilesetup";
		}
	}

	@PostMapping("/freelancer/detail/save")
	public String saveFreelancerDetails(
			@ModelAttribute("FreelancerPersonalDetailDTO") FreelancerPersonalDetailDTO freelancerPersonalDetailDTO,
			HttpSession session, Model model) {

		List<Language> languages = languageService.findAll();
		List<Industry> industries = industryService.findAll();

		System.out.println("===>>> Freelancer personal details: " + freelancerPersonalDetailDTO);
		session.setAttribute("freelancerPersonalDetailDTO", freelancerPersonalDetailDTO);

		model.addAttribute("languages", languages);
		model.addAttribute("industries", industries);
		model.addAttribute("FreelancerSkillDetailDTO", new FreelancerSkillDetailDTO());
		return "onboarding/freelancer/profilesetup2";
	}

	@PostMapping("/freelancer/skill/save")
	public String saveFreelancerSkill(
			@ModelAttribute("FreelancerSkillDetailDTO") FreelancerSkillDetailDTO freelancerSkillDetailDTO,
			BindingResult bindingResult, @RequestParam("translationUpload1") MultipartFile tu1,
			@RequestParam("translationUpload2") MultipartFile tu2,
			@RequestParam("translationUpload3") MultipartFile tu3,
			@RequestParam("translationUpload4") MultipartFile tu4,
			@RequestParam("translationUpload5") MultipartFile tu5, @RequestParam("interUpload1") MultipartFile iu1,
			@RequestParam("interUpload2") MultipartFile iu2, @RequestParam("interUpload3") MultipartFile iu3,
			@RequestParam("interUpload4") MultipartFile iu4, @RequestParam("interUpload5") MultipartFile iu5,
			@RequestParam("vcUpload1") MultipartFile vcu1, @RequestParam("vcUpload2") MultipartFile vcu2,
			@RequestParam("vcUpload3") MultipartFile vcu3, @RequestParam("vcUpload4") MultipartFile vcu4,
			@RequestParam("vcUpload5") MultipartFile vcu5, @RequestParam("transcribeUpload1") MultipartFile tsu1,
			@RequestParam("transcribeUpload2") MultipartFile tsu2,
			@RequestParam("transcribeUpload3") MultipartFile tsu3,
			@RequestParam("transcribeUpload4") MultipartFile tsu4,
			@RequestParam("transcribeUpload5") MultipartFile tsu5, HttpSession session, Model model) {

		System.out.println("===>>>Binding Result: " + bindingResult);

		try {
			FreelancerDocumentNameModel documentName = new FreelancerDocumentNameModel();

			List<MultipartFile> uploads = new ArrayList<>();

			freelancerSkillDetailDTO.setTranslationUpload1(tu1);
			freelancerSkillDetailDTO.setTranslationUpload2(tu2);
			freelancerSkillDetailDTO.setTranslationUpload3(tu3);
			freelancerSkillDetailDTO.setTranslationUpload4(tu4);
			freelancerSkillDetailDTO.setTranslationUpload5(tu5);

			documentName.setTranslationFilename1(getUploadFileName(tu1) == null ? "" : getUploadFileName(tu1));
			documentName.setTranslationFilename2(getUploadFileName(tu2) == null ? "" : getUploadFileName(tu2));
			documentName.setTranslationFilename3(getUploadFileName(tu3) == null ? "" : getUploadFileName(tu3));
			documentName.setTranslationFilename4(getUploadFileName(tu4) == null ? "" : getUploadFileName(tu4));
			documentName.setTranslationFilename5(getUploadFileName(tu5) == null ? "" : getUploadFileName(tu5));

			freelancerSkillDetailDTO.setInterUpload1(iu1);
			freelancerSkillDetailDTO.setInterUpload2(iu2);
			freelancerSkillDetailDTO.setInterUpload3(iu3);
			freelancerSkillDetailDTO.setInterUpload4(iu4);
			freelancerSkillDetailDTO.setInterUpload5(iu5);

			documentName.setInterFilename1(getUploadFileName(iu1) == null ? "" : getUploadFileName(iu1));
			documentName.setInterFilename2(getUploadFileName(iu2) == null ? "" : getUploadFileName(iu2));
			documentName.setInterFilename3(getUploadFileName(iu3) == null ? "" : getUploadFileName(iu3));
			documentName.setInterFilename4(getUploadFileName(iu4) == null ? "" : getUploadFileName(iu4));
			documentName.setInterFilename5(getUploadFileName(iu5) == null ? "" : getUploadFileName(iu5));

			freelancerSkillDetailDTO.setVcUpload1(vcu1);
			freelancerSkillDetailDTO.setVcUpload2(vcu2);
			freelancerSkillDetailDTO.setVcUpload3(vcu3);
			freelancerSkillDetailDTO.setVcUpload4(vcu4);
			freelancerSkillDetailDTO.setVcUpload5(vcu5);

			documentName.setVcFilename1(getUploadFileName(vcu1) == null ? "" : getUploadFileName(vcu1));
			documentName.setVcFilename2(getUploadFileName(vcu2) == null ? "" : getUploadFileName(vcu2));
			documentName.setVcFilename3(getUploadFileName(vcu3) == null ? "" : getUploadFileName(vcu3));
			documentName.setVcFilename4(getUploadFileName(vcu4) == null ? "" : getUploadFileName(vcu4));
			documentName.setVcFilename5(getUploadFileName(vcu5) == null ? "" : getUploadFileName(vcu5));

			freelancerSkillDetailDTO.setTranscribeUpload1(tsu1);
			freelancerSkillDetailDTO.setTranscribeUpload2(tsu2);
			freelancerSkillDetailDTO.setTranscribeUpload3(tsu3);
			freelancerSkillDetailDTO.setTranscribeUpload4(tsu4);
			freelancerSkillDetailDTO.setTranscribeUpload5(tsu5);

			documentName.setTranscribeFilename1(getUploadFileName(tsu1) == null ? "" : getUploadFileName(tsu1));
			documentName.setTranscribeFilename2(getUploadFileName(tsu2) == null ? "" : getUploadFileName(tsu2));
			documentName.setTranscribeFilename3(getUploadFileName(tsu3) == null ? "" : getUploadFileName(tsu3));
			documentName.setTranscribeFilename4(getUploadFileName(tsu4) == null ? "" : getUploadFileName(tsu4));
			documentName.setTranscribeFilename5(getUploadFileName(tsu5) == null ? "" : getUploadFileName(tsu5));

			System.out.println("===>>> Freelancer Skill details: " + freelancerSkillDetailDTO);
			System.out.println("===>>> FreelancerDocumentNameModel: " + documentName);

			session.setAttribute("freelancerSkillDetailDTO", freelancerSkillDetailDTO);
			session.setAttribute("freelancerDocumentNameModel", documentName);

			model.addAttribute("FreelancerProfilePictureDetailDTO", new FreelancerProfilePictureDetailDTO());
			model.addAttribute("message", "Uploads saved successfully, please continue with final step.");

		} catch (Exception e) {
			System.out.println("===>>> Exception: " + e);
		}

		return "onboarding/freelancer/profilesetup4";
	}

	@PostMapping("/freelancer/profilepicture/save")
	public String saveFreelancerProfile(
			@ModelAttribute("FreelancerProfilePictureDetailDTO") FreelancerProfilePictureDetailDTO freelancerProfilePictureDetailDTO,
			BindingResult bindingResult, @RequestParam("profilePicture") MultipartFile file, HttpSession session,
			RedirectAttributes attributes) {

		System.out.println("===>>>Binding Result: " + bindingResult);

		try {

			String fileSize = FreelancerController.getDynamicSpace(file.getSize());

			if (!fileSize.equals("2 MiB")) {
				System.out.println("fileSize: " + fileSize);

				String imageUUID;
				if (!file.isEmpty()) {
					imageUUID = file.getOriginalFilename();
					Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
					Files.write(fileNameAndPath, file.getBytes());
				} else {
					imageUUID = "";
				}

				System.out.println(
						"===>>> Freelancer Profile details: " + freelancerProfilePictureDetailDTO.getProfilePicture());

				session.setAttribute("freelancerProfilePictureDetailDTO", freelancerProfilePictureDetailDTO);

				// Save all the information in the database, and create login access for
				// freelancer
				String modifiedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

				// Get the freeLancer signupData from session
				FreelancerSignupDTO signupData = (FreelancerSignupDTO) session.getAttribute("freelancerSignupDto");

				// Get the freeLancer OtherInfoData from session
				FreelancerPersonalDetailDTO personalInfoData = (FreelancerPersonalDetailDTO) session
						.getAttribute("freelancerPersonalDetailDTO");
//
				// Get the freeLancer skillData from session
				FreelancerSkillDetailDTO freelancerSkillData = (FreelancerSkillDetailDTO) session
						.getAttribute("freelancerSkillDetailDTO");

				// Get the freeLancer profilePictureData from session
				FreelancerProfilePictureDetailDTO profilePictureData = (FreelancerProfilePictureDetailDTO) session
						.getAttribute("freelancerProfilePictureDetailDTO");

				// Get the freeLancer fileName from session
				FreelancerDocumentNameModel documentName = (FreelancerDocumentNameModel) session
						.getAttribute("freelancerDocumentNameModel");

				// Pass the session data to the user service and save
				User user = new User();

				// Set user Role
				List<Role> roles = new ArrayList<>();
				roles.add(roleService.findById(4).get());// 4 means freelancer role, 3 means client role, 2 means
															// supervisor role, 1 means admin role
				user.setRoles(roles);

				// getUserStatus
				Optional<UserStatus> userStatus = userStatusService.findById(2);// 2 means active from user status table

				System.out.println("===>>> userStatus: " + userStatus.get());
				System.out.println("===>>> Signup: " + signupData);
				System.out.println("===>>> Personal Details: " + personalInfoData);
				System.out.println("===>>> Personal Skill: " + freelancerSkillData);
				System.out.println("===>>> Personal profile: " + profilePictureData);

				String password = signupData.getPassword();

				user.setUserId(0);
				user.setUsername(signupData.getEmail());
				user.setUpassword(bCryptPasswordEncoder.encode(password));
				user.setFirstName(signupData.getFirstName());
				user.setLastName(signupData.getLastName());
				user.setCountry(personalInfoData.getCountry());
				user.setPhone(personalInfoData.getMobileNumber());
				user.setCountryCode(personalInfoData.getCountryCode());
				user.setEmailAddress(signupData.getEmail());
				user.setUserStatus(userStatus.get());
				user.setGender(personalInfoData.getGender());
				user.setNationality(personalInfoData.getNationality());
				user.setAddress(personalInfoData.getStreetAddress());
				user.setSummary(profilePictureData.getProfileHeadline());
				user.setEducation("");
				user.setMiddleName("");
				user.setPostalCode(personalInfoData.getPostalCode());
				user.setProfilePicture(imageUUID);
				user.setModifiedDate(modifiedDate);
				user.setCreatedDate(createdDate);

				System.out.println("===>>>User Data: " + user.toString());

				userService.save(user);

				// Save Portfolio/Skill data
				System.out.println("freelancerSkillData: " + freelancerSkillData);
				if (freelancerSkillData.getTranslationServiceType().equals("ST101")) {

					System.out.println("====>>>> Translation Data Entry");
					// get into service industries;
					String industryData = freelancerSkillData.getTranslationIndustries();

					if (industryData != null) {
						saveTranslationInfo(signupData, freelancerSkillData, profilePictureData, industryData,
								documentName);
					}
				}

				if (freelancerSkillData.getInterServiceType().equals("ST102")) {

					System.out.println("====>>>> Interpretation Data Entry");
					// insert into service industries;
					String industryData = freelancerSkillData.getInterpretationIndustries();

					if (industryData != null) {
						saveInterpretationInfo(signupData, freelancerSkillData, profilePictureData, industryData,
								documentName);
					}
				}

				if (freelancerSkillData.getScribingServiceType().equals("ST104")) {

					System.out.println("====>>>> Scribing Data Entry");
					// insert into service industries;
					String industryData = freelancerSkillData.getScribingIndustries();

					if (industryData != null) {
						saveTranscribinginfo(signupData, freelancerSkillData, profilePictureData, industryData,
								documentName);
					}
				}

				if (freelancerSkillData.getVcServiceType().equals("ST103")) {

					System.out.println("====>>>> VC Data Entry");
					// insert into service industries;
					String industryData = freelancerSkillData.getVcIndustries();

					if (industryData != null) {
						saveVcInfo(signupData, freelancerSkillData, profilePictureData, industryData, documentName);
					}
				}

				attributes.addFlashAttribute("message", "Great, your profile has been created!");
				return "redirect:/signin";

			} else {
				return "onboarding/freelancer/profilesetup4";
			}
		} catch (Exception e) {
			System.out.println("===>>> Exception: " + e);
		}
		return "onboarding/freelancer/profilesetup4";
	}

	public void saveTranslationInfo(FreelancerSignupDTO signupData, FreelancerSkillDetailDTO freelancerSkillData,
			FreelancerProfilePictureDetailDTO profilePictureData, String industryData,
			FreelancerDocumentNameModel documentName) {
		System.out.println("====>>> Translation IN");

		Optional<User> userDetails = userService.findFirstUserByUsername(signupData.getEmail());
		Optional<ServiceType> serviceTypeDetail = serviceType.findById("ST101");

		System.out.println("====>>> userDetails: " + userDetails.get());
		System.out.println("====>>> serviceTypeDetail: " + serviceTypeDetail);

		System.out.println("===>>>> freelancerSkillData Translation: " + freelancerSkillData);

		ServiceRendered rendered = new ServiceRendered();
//		rendered.setRenderId("SR01");
		rendered.setUser(userDetails.get());
		rendered.setServiceType(serviceTypeDetail.get());
		rendered.setExperienceInYears(freelancerSkillData.getTranslationExperience());
		rendered.setOtherInfo(freelancerSkillData.getTranslationPortfolioLink());

		serviceRendered.save(rendered);

		System.out.println("====>>> Translation Service Saved");
		System.out.println("===>>> Translation industryData List: " + industryData);

		Optional<ServiceRendered> serviceRenderedId = serviceRendered
				.findServiceRenderedByUserAndServiceType(userDetails.get(), serviceTypeDetail.get());

		System.out.println("===>>> Translation serviceRenderedId: " + serviceRenderedId);

		if (industryData.split(",").length > 1) {

			String[] industries = industryData.split(",");

			System.out.println("===>>> Translation List of Industries: " + industries);

			for (int i = 0; i < industries.length; i++) {
				ServiceIndustries industry = new ServiceIndustries();

				String freelancerIndustry = industries[i];
				Optional<Industry> industryId = industryService.findById(Integer.parseInt(freelancerIndustry));

				Optional<Industry> industryName = industryService
						.findIndustryByIndustryId(Integer.parseInt(freelancerIndustry));

//				industry.setServiceIndustryId("");
				industry.setIndustryId(industryId.get());
				industry.setServiceRendered(serviceRenderedId.get());
				industry.setIndustry(industryName.get().getIndustry());
				serviceIndustry.save(industry);

				System.out.println("====>>> Translation Service Industry Saved(Multiple)");
			}
		} else {
			ServiceIndustries industry = new ServiceIndustries();
			Optional<Industry> industryId = industryService.findById(Integer.parseInt(industryData));

			Optional<Industry> industryName = industryService.findIndustryByIndustryId(Integer.parseInt(industryData));

//			industry.setServiceIndustryId("");
			industry.setIndustryId(industryId.get());
			industry.setServiceRendered(serviceRenderedId.get());
			industry.setIndustry(industryName.get().getIndustry());
			serviceIndustry.save(industry);

			System.out.println("====>>> Translation Service Industry Saved(one)");
		}

		List<SkillUploadModel> skillModels = new ArrayList<>();

		if (!freelancerSkillData.getTranslationLanguage1().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getTranslationLanguage1()), "",
					documentName.getTranslationFilename1()));
		}
		if (!freelancerSkillData.getTranslationLanguage2().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getTranslationLanguage2()), "",
					documentName.getTranslationFilename2()));
		}
		if (!freelancerSkillData.getTranslationLanguage3().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getTranslationLanguage3()), "",
					documentName.getTranslationFilename3()));
		}
		if (!freelancerSkillData.getTranslationLanguage4().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getTranslationLanguage4()), "",
					documentName.getTranslationFilename4()));
		}
		if (!freelancerSkillData.getTranslationLanguage5().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getTranslationLanguage5()), "",
					documentName.getTranslationFilename5()));
		}

		System.out.println("===>>> Translation List of Languages: " + skillModels);

		for (SkillUploadModel skillModel : skillModels) {
			System.out.println("\n===>>> freelancer SkillModel Translation: " + skillModel);

			ServiceLanguages serviceLanguageObj = new ServiceLanguages();

			Optional<Language> opLanguage = languageService.findById(skillModel.getLanguage());

			Language languageName = languageService.findLanguageByLanguageId(skillModel.getLanguage());
			if (languageName == null) {
				// TODO: Do Something
			}

//			language.setServiceLanguageId(industryData);
			serviceLanguageObj.setLanguageId(opLanguage.get());
			serviceLanguageObj.setServiceRendered(serviceRenderedId.get());
			serviceLanguageObj.setUser(userDetails.get());
			serviceLanguageObj.setLanguageDesc(languageName.getLanguage());
			serviceLanguageObj.setLanguageUpload(skillModel.getDocumentName());

			System.out.println("===>>> serviceLanguageObj Translation: " + serviceLanguageObj);

			serviceLanguage.save(serviceLanguageObj);

			System.out.println("====>>> Translation Service Language Saved");

		}

		// insert into freeLancer pricing
		FreelancerServicePricing freelancerPricingData = new FreelancerServicePricing();

		List<FreelancerPricingModel> fPricingModels = new ArrayList<>();
		fPricingModels.add(
				new FreelancerPricingModel("stw101", Double.parseDouble(freelancerSkillData.getTranslationPerWordMin()),
						Double.parseDouble(freelancerSkillData.getTranslationPerWordMax())));

		for (FreelancerPricingModel freelancerPricingModel : fPricingModels) {
			Optional<ServiceTypePricing> serviceTypePricingData = serviceTypePricing
					.findById(freelancerPricingModel.getPricingType());

			System.out.println("===>>> serviceTypePricingData: " + serviceTypePricingData);
			System.out.println("===>>> freelancerUser: " + userDetails);

//			freelancerPricing.setFpricingId("");
			freelancerPricingData.setServiceTypePricing(serviceTypePricingData.get());
			freelancerPricingData.setUser(userDetails.get());
			freelancerPricingData.setMinPrice(freelancerPricingModel.getMinPrice());
			freelancerPricingData.setMaxPrice(freelancerPricingModel.getMaxPrice());

			System.out.println("===>>> freelancerPricingData Translation: " + freelancerPricingData);

			freelancerPricing.save(freelancerPricingData);

			System.out.println("====>>> Translation Freelancer Price Saved");

		}
	}

	public void saveInterpretationInfo(FreelancerSignupDTO signupData, FreelancerSkillDetailDTO freelancerSkillData,
			FreelancerProfilePictureDetailDTO profilePictureData, String industryData,
			FreelancerDocumentNameModel documentName) {
		System.out.println("====>>> Interpretation IN");

		Optional<User> userDetails = userService.findFirstUserByUsername(signupData.getEmail());
		Optional<ServiceType> serviceTypeDetail = serviceType.findById("ST102");

		System.out.println("====>>> Inter userDetails: " + userDetails.get());
		System.out.println("====>>> Inter serviceTypeDetail: " + serviceTypeDetail);
		System.out.println("===>>>> freelancerSkillData Interpretation: " + freelancerSkillData);

		ServiceRendered rendered = new ServiceRendered();
//		rendered.setRenderId("SR01");
		rendered.setUser(userDetails.get());
		rendered.setServiceType(serviceTypeDetail.get());
		rendered.setExperienceInYears(freelancerSkillData.getIexperience());
		rendered.setOtherInfo(freelancerSkillData.getInterPortfolioLink());

		serviceRendered.save(rendered);

		System.out.println("====>>> Inter Service Rendered Saved");

		System.out.println("===>>> Inter industryData List: " + industryData);

		Optional<ServiceRendered> serviceRenderedId = serviceRendered
				.findServiceRenderedByUserAndServiceType(userDetails.get(), serviceTypeDetail.get());

		System.out.println("===>>> Inter serviceRenderedId: " + serviceRenderedId);

		if (industryData.split(",").length > 1) {
			String[] industries = industryData.split(",");

			System.out.println("===>>> Inter List of Industries: " + industries);

			for (int i = 0; i < industries.length; i++) {
				ServiceIndustries industry = new ServiceIndustries();

				String freelancerIndustry = industries[i];
				Optional<Industry> industryId = industryService.findById(Integer.parseInt(freelancerIndustry));

				Optional<Industry> industryName = industryService
						.findIndustryByIndustryId(Integer.parseInt(freelancerIndustry));

//				industry.setServiceIndustryId("");
				industry.setIndustryId(industryId.get());
				industry.setServiceRendered(serviceRenderedId.get());
				industry.setIndustry(industryName.get().getIndustry());
				serviceIndustry.save(industry);

				System.out.println("====>>> Inter Service Industry Saved(Multiple)");
			}

		} else {
			ServiceIndustries industry = new ServiceIndustries();
			Optional<Industry> industryId = industryService.findById(Integer.parseInt(industryData));

			Optional<Industry> industryName = industryService.findIndustryByIndustryId(Integer.parseInt(industryData));

//			industry.setServiceIndustryId("");
			industry.setIndustryId(industryId.get());
			industry.setServiceRendered(serviceRenderedId.get());
			industry.setIndustry(industryName.get().getIndustry());
			serviceIndustry.save(industry);

			System.out.println("====>>> Inter Service Industry Saved(one)");
		}

		// insert into service languages;
		// TODO: Upload document file goes as null to db, Check why!!!
		List<SkillUploadModel> skillModels = new ArrayList<>();

		if (!freelancerSkillData.getInterLanguage1().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getInterLanguage1()), "",
					documentName.getInterFilename1()));
		}
		if (!freelancerSkillData.getInterLanguage2().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getInterLanguage2()), "",
					documentName.getInterFilename2()));
		}
		if (!freelancerSkillData.getInterLanguage3().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getInterLanguage3()), "",
					documentName.getInterFilename3()));
		}
		if (!freelancerSkillData.getInterLanguage4().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getInterLanguage4()), "",
					documentName.getInterFilename4()));
		}
		if (!freelancerSkillData.getInterLanguage5().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getInterLanguage5()), "",
					documentName.getInterFilename5()));
		}

		System.out.println("===>>> Inter List of Languages: " + skillModels);
		for (SkillUploadModel skillModel : skillModels) {
			System.out.println("\n===>>> freelancer SkillModel Interpretation: " + skillModel);

			ServiceLanguages serviceLanguageObj = new ServiceLanguages();

			Optional<Language> opLanguage = languageService.findById(skillModel.getLanguage());

			Language languageName = languageService.findLanguageByLanguageId(skillModel.getLanguage());
			if (languageName == null) {
				// TODO: Do Something
			}

//			language.setServiceLanguageId(industryData);
			serviceLanguageObj.setLanguageId(opLanguage.get());
			serviceLanguageObj.setServiceRendered(serviceRenderedId.get());
			serviceLanguageObj.setUser(userDetails.get());
			serviceLanguageObj.setLanguageDesc(languageName.getLanguage());
			serviceLanguageObj.setLanguageUpload(skillModel.getDocumentName());

			System.out.println("===>>>> serviceLanguageObj Inter: " + serviceLanguageObj);

			serviceLanguage.save(serviceLanguageObj);

			System.out.println("====>>> Interpretation Service Language Saved");

		}

		// insert into freeLancer pricing
		FreelancerServicePricing freelancerPricingData = new FreelancerServicePricing();

		List<FreelancerPricingModel> fPricingModels = new ArrayList<>();

		fPricingModels
				.add(new FreelancerPricingModel("sth102", Double.parseDouble(freelancerSkillData.getInterPerWordMin()),
						Double.parseDouble(freelancerSkillData.getInterPerWordMax())));

		for (FreelancerPricingModel freelancerPricingModel : fPricingModels) {
			Optional<ServiceTypePricing> serviceTypePricingData = serviceTypePricing
					.findById(freelancerPricingModel.getPricingType());

			System.out.println("===>>> Inter serviceTypePricingData: " + serviceTypePricingData);
			System.out.println("===>>> Inter freelancerUser: " + userDetails);

//			freelancerPricing.setFpricingId("");
			freelancerPricingData.setServiceTypePricing(serviceTypePricingData.get());
			freelancerPricingData.setUser(userDetails.get());
			freelancerPricingData.setMinPrice(freelancerPricingModel.getMinPrice());
			freelancerPricingData.setMaxPrice(freelancerPricingModel.getMaxPrice());

			System.out.println("===>>>> freelancerPricingData Inter: " + freelancerPricingData);

			freelancerPricing.save(freelancerPricingData);

			System.out.println("====>>> Interpretation Freelancer Saved");

		}
	}

	public void saveVcInfo(FreelancerSignupDTO signupData, FreelancerSkillDetailDTO freelancerSkillData,
			FreelancerProfilePictureDetailDTO profilePictureData, String industryData,
			FreelancerDocumentNameModel documentName) {
		System.out.println("====>>> VC IN");

		Optional<User> userDetails = userService.findFirstUserByUsername(signupData.getEmail());
		Optional<ServiceType> serviceTypeDetail = serviceType.findById("ST103");

		System.out.println("====>>> VC userDetails: " + userDetails);
		System.out.println("====>>> VC serviceTypeDetail: " + serviceTypeDetail);
		System.out.println("===>>>> freelancerSkillData VC: " + freelancerSkillData);

		ServiceRendered rendered = new ServiceRendered();
//		rendered.setRenderId("SR01");
		rendered.setUser(userDetails.get());
		rendered.setServiceType(serviceTypeDetail.get());
		rendered.setExperienceInYears(freelancerSkillData.getVcExperience());
		rendered.setOtherInfo(freelancerSkillData.getVcPortfolioLink());

		serviceRendered.save(rendered);

		System.out.println("====>>> VC Service rendered Saved");

		System.out.println("===>>> VC industryData List: " + industryData);
		System.out.println("===>>> VC opUser: " + userDetails);

		Optional<ServiceRendered> serviceRenderedId = serviceRendered
				.findServiceRenderedByUserAndServiceType(userDetails.get(), serviceTypeDetail.get());

		System.out.println("===>>> VC serviceRenderedId: " + serviceRenderedId);

		if (industryData.split(",").length > 1) {
			String[] industries = industryData.split(",");

			System.out.println("===>>> VC List of Industries: " + industries);

			for (int i = 0; i < industries.length; i++) {
				ServiceIndustries industry = new ServiceIndustries();

				String freelancerIndustry = industries[i];
				Optional<Industry> industryId = industryService.findById(Integer.parseInt(freelancerIndustry));

				Optional<Industry> industryName = industryService
						.findIndustryByIndustryId(Integer.parseInt(freelancerIndustry));

//				industry.setServiceIndustryId("");
				industry.setIndustryId(industryId.get());
				industry.setServiceRendered(serviceRenderedId.get());
				industry.setIndustry(industryName.get().getIndustry());
				serviceIndustry.save(industry);

				System.out.println("====>>> VC Service Industry Saved(Multiple)");
			}

		} else {

			ServiceIndustries industry = new ServiceIndustries();
			Optional<Industry> industryId = industryService.findById(Integer.parseInt(industryData));

			Optional<Industry> industryName = industryService.findIndustryByIndustryId(Integer.parseInt(industryData));

//			industry.setServiceIndustryId("");
			industry.setIndustryId(industryId.get());
			industry.setServiceRendered(serviceRenderedId.get());
			industry.setIndustry(industryName.get().getIndustry());
			serviceIndustry.save(industry);

			System.out.println("====>>> VC Service Industry Saved(one)");
		}

		List<SkillUploadModel> skillModels = new ArrayList<>();

		if (!freelancerSkillData.getVcLanguage1().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getVcLanguage1()),
					freelancerSkillData.getVoiceDesc1(), documentName.getVcFilename1()));
		}
		if (!freelancerSkillData.getVcLanguage2().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getVcLanguage2()),
					freelancerSkillData.getVoiceDesc2(), documentName.getVcFilename2()));
		}
		if (!freelancerSkillData.getVcLanguage3().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getVcLanguage3()),
					freelancerSkillData.getVoiceDesc3(), documentName.getVcFilename3()));
		}
		if (!freelancerSkillData.getVcLanguage4().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getVcLanguage4()),
					freelancerSkillData.getVoiceDesc4(), documentName.getVcFilename4()));
		}
		if (!freelancerSkillData.getVcLanguage5().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getVcLanguage5()),
					freelancerSkillData.getVoiceDesc5(), documentName.getVcFilename5()));
		}

		System.out.println("===>>> VC List of Languages: " + skillModels);

		for (SkillUploadModel skillModel : skillModels) {
			System.out.println("\n===>>> freelancer SkillModel VC: " + skillModel);

			ServiceLanguages serviceLanguageObj = new ServiceLanguages();

			Optional<Language> opLanguage = languageService.findById(skillModel.getLanguage());

			Language languageName = languageService.findLanguageByLanguageId(skillModel.getLanguage());
			if (languageName == null) {
				// TODO: Do Something
			}

//			language.setServiceLanguageId(industryData);
			serviceLanguageObj.setLanguageId(opLanguage.get());
			serviceLanguageObj.setUser(userDetails.get());
			serviceLanguageObj.setServiceRendered(serviceRenderedId.get());
			serviceLanguageObj.setLanguageDesc(languageName.getLanguage());
			serviceLanguageObj.setVoiceType(skillModel.getDescriptionOfVoice());
			serviceLanguageObj.setLanguageUpload(skillModel.getDocumentName());

			System.out.println("===>>>> serviceLanguageObj VC : " + serviceLanguageObj);

			serviceLanguage.save(serviceLanguageObj);

			System.out.println("====>>> VC Service Language Saved");

		}

		List<FreelancerPricingModel> fPricingModels = new ArrayList<>();

		if (!freelancerSkillData.getVcPerHourMin().isEmpty()) {
			fPricingModels
					.add(new FreelancerPricingModel("stm105", Double.parseDouble(freelancerSkillData.getVcPerHourMin()),
							Double.parseDouble(freelancerSkillData.getVcPerHourMax())));
		}
		if (!freelancerSkillData.getVcPerMinuteMin().isEmpty()) {
			fPricingModels.add(
					new FreelancerPricingModel("stm106", Double.parseDouble(freelancerSkillData.getVcPerMinuteMin()),
							Double.parseDouble(freelancerSkillData.getVcPerMinuteMax())));
		}

		if (!freelancerSkillData.getVcPerWordMin().isEmpty()) {
			fPricingModels
					.add(new FreelancerPricingModel("stm104", Double.parseDouble(freelancerSkillData.getVcPerWordMin()),
							Double.parseDouble(freelancerSkillData.getVcPerWordMax())));
		}

		System.out.println("===>>> fPricingModels: " + fPricingModels);

		for (FreelancerPricingModel freelancerPricingModel : fPricingModels) {

			FreelancerServicePricing freelancerPricingData = new FreelancerServicePricing();

			Optional<ServiceTypePricing> serviceTypePricingData = serviceTypePricing
					.findById(freelancerPricingModel.getPricingType());

//			freelancerPricing.setFpricingId("");
			freelancerPricingData.setServiceTypePricing(serviceTypePricingData.get());
			freelancerPricingData.setUser(userDetails.get());
			freelancerPricingData.setMinPrice(freelancerPricingModel.getMinPrice());
			freelancerPricingData.setMaxPrice(freelancerPricingModel.getMaxPrice());

			System.out.println("===>>>> freelancerPricingData VC: " + freelancerPricingData);

			freelancerPricing.save(freelancerPricingData);

			System.out.println("====>>> VC Freelancer Saved");

		}
	}

	public void saveTranscribinginfo(FreelancerSignupDTO signupData, FreelancerSkillDetailDTO freelancerSkillData,
			FreelancerProfilePictureDetailDTO profilePictureData, String industryData,
			FreelancerDocumentNameModel documentName) {

		System.out.println("====>>> Scribing IN");

		Optional<User> userDetails = userService.findFirstUserByUsername(signupData.getEmail());
		Optional<ServiceType> serviceTypeDetail = serviceType.findById("ST104");

		System.out.println("====>>> SC userDetails: " + userDetails);
		System.out.println("====>>> SC serviceTypeDetail: " + serviceTypeDetail);
		System.out.println("===>>>> freelancerSkillData Transcribing: " + freelancerSkillData);

		ServiceRendered rendered = new ServiceRendered();
//		rendered.setRenderId("SR01");
		rendered.setUser(userDetails.get());
		rendered.setServiceType(serviceTypeDetail.get());
		rendered.setExperienceInYears(freelancerSkillData.getScribingExperience());
		rendered.setOtherInfo(freelancerSkillData.getScribingPortfolioLink());

		serviceRendered.save(rendered);
		System.out.println("====>>> SC Service Rendered Saved");

		System.out.println("===>>> SC industryData List: " + industryData);
		System.out.println("===>>> SC opUser: " + userDetails);

		Optional<ServiceRendered> serviceRenderedId = serviceRendered
				.findServiceRenderedByUserAndServiceType(userDetails.get(), serviceTypeDetail.get());

		System.out.println("===>>> SC serviceRenderedId: " + serviceRenderedId);

		if (industryData.split(",").length > 1) {

			String[] industries = industryData.split(",");

			System.out.println("===>>> SC List of Industries: " + industries);

			for (int i = 0; i < industries.length; i++) {
				ServiceIndustries industry = new ServiceIndustries();

				String freelancerIndustry = industries[i];
				Optional<Industry> industryId = industryService.findById(Integer.parseInt(freelancerIndustry));

				Optional<Industry> industryName = industryService
						.findIndustryByIndustryId(Integer.parseInt(freelancerIndustry));

//				industry.setServiceIndustryId("");
				industry.setIndustryId(industryId.get());
				industry.setServiceRendered(serviceRenderedId.get());
				industry.setIndustry(industryName.get().getIndustry());
				serviceIndustry.save(industry);

				System.out.println("====>>> SC Service Industry Saved(Multiple)");
			}

		} else {
			ServiceIndustries industry = new ServiceIndustries();
			Optional<Industry> industryId = industryService.findById(Integer.parseInt(industryData));

			Optional<Industry> industryName = industryService.findIndustryByIndustryId(Integer.parseInt(industryData));

//			industry.setServiceIndustryId("");
			industry.setIndustryId(industryId.get());
			industry.setServiceRendered(serviceRenderedId.get());
			industry.setIndustry(industryName.get().getIndustry());
			serviceIndustry.save(industry);

			System.out.println("====>>> SC Service Industry Saved(one)");
		}

		List<SkillUploadModel> skillModels = new ArrayList<>();

		if (!freelancerSkillData.getTranscribeLanguage1().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getTranscribeLanguage1()), "",
					documentName.getTranscribeFilename1()));
		}
		if (!freelancerSkillData.getTranscribeLanguage2().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getTranscribeLanguage2()), "",
					documentName.getTranscribeFilename2()));
		}
		if (!freelancerSkillData.getTranscribeLanguage3().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getTranscribeLanguage3()), "",
					documentName.getTranscribeFilename3()));
		}
		if (!freelancerSkillData.getTranscribeLanguage4().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getTranscribeLanguage4()), "",
					documentName.getTranscribeFilename4()));
		}
		if (!freelancerSkillData.getTranscribeLanguage5().isEmpty()) {
			skillModels.add(new SkillUploadModel(Integer.parseInt(freelancerSkillData.getTranscribeLanguage5()), "",
					documentName.getTranscribeFilename5()));
		}

		System.out.println("===>>> SC List of Languages: " + skillModels);
		for (SkillUploadModel skillModel : skillModels) {
			System.out.println("\n===>>> freelancer SkillModel VC: " + skillModel);

			ServiceLanguages serviceLanguageObj = new ServiceLanguages();

			Optional<Language> opLanguage = languageService.findById(skillModel.getLanguage());

			Language languageName = languageService.findLanguageByLanguageId(skillModel.getLanguage());
			if (languageName == null) {
				// TODO: Do Something
			}

//			language.setServiceLanguageId(industryData);
			serviceLanguageObj.setLanguageId(opLanguage.get());
			serviceLanguageObj.setUser(userDetails.get());
			serviceLanguageObj.setServiceRendered(serviceRenderedId.get());
			serviceLanguageObj.setLanguageDesc(languageName.getLanguage());
			serviceLanguageObj.setVoiceType(skillModel.getDescriptionOfVoice());
			serviceLanguageObj.setLanguageUpload(skillModel.getDocumentName());

			System.out.println("===>>>> serviceLanguageObj VC : " + serviceLanguageObj);

			serviceLanguage.save(serviceLanguageObj);

			System.out.println("====>>> VC Service Language Saved");

		}

		// insert into freeLancer pricing
		FreelancerServicePricing freelancerPricingData = new FreelancerServicePricing();

		List<FreelancerPricingModel> fPricingModels = new ArrayList<>();
		fPricingModels.add(
				new FreelancerPricingModel("sth103", Double.parseDouble(freelancerSkillData.getScribingPerWordMin()),
						Double.parseDouble(freelancerSkillData.getScribingPerWordMax())));

		for (FreelancerPricingModel freelancerPricingModel : fPricingModels) {

			Optional<ServiceTypePricing> serviceTypePricingData = serviceTypePricing
					.findById(freelancerPricingModel.getPricingType());

			System.out.println("===>>> SC serviceTypePricingData: " + serviceTypePricingData);
			System.out.println("===>>> SC freelancerUser: " + userDetails);

//			freelancerPricing.setFpricingId("");
			freelancerPricingData.setServiceTypePricing(serviceTypePricingData.get());
			freelancerPricingData.setUser(userDetails.get());
			freelancerPricingData.setMinPrice(freelancerPricingModel.getMinPrice());
			freelancerPricingData.setMaxPrice(freelancerPricingModel.getMaxPrice());

			System.out.println("===>>>> freelancerPricingData Transcribing: " + freelancerPricingData);

			freelancerPricing.save(freelancerPricingData);
			System.out.println("====>>> SC Freelancer Saved");

		}
	}

}
