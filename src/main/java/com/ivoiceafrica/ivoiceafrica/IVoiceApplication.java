package com.ivoiceafrica.ivoiceafrica;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ivoiceafrica.ivoiceafrica.components.models.ClientComponentModel;
import com.ivoiceafrica.ivoiceafrica.components.models.FreelancerComponentModel;
import com.ivoiceafrica.ivoiceafrica.controller.ClientController;
import com.ivoiceafrica.ivoiceafrica.controller.FreelancerController;
import com.ivoiceafrica.ivoiceafrica.repository.UserRepository;
import com.ivoiceafrica.ivoiceafrica.service.BankDetailService;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryStatusService;
import com.ivoiceafrica.ivoiceafrica.service.FlutterwaveService;
import com.ivoiceafrica.ivoiceafrica.service.FreelancerPricingService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalStatusService;
import com.ivoiceafrica.ivoiceafrica.service.SRenderedService;
import com.ivoiceafrica.ivoiceafrica.service.STypePricingService;
import com.ivoiceafrica.ivoiceafrica.service.STypeService;
import com.ivoiceafrica.ivoiceafrica.service.UserStatusService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderService;

@SpringBootApplication
@ComponentScan({ "com.ivoiceafrica.ivoiceafrica"})
public class IVoiceApplication implements CommandLineRunner {

	@Autowired
	UserStatusService userStatusService;

	@Autowired
	ClientComponentModel clientComponentModel;

	@Autowired
	STypeService service;

	@Autowired
	SRenderedService serviceRenderedService;

	@Autowired
	FreelancerPricingService freelancerPricingService;

	@Autowired
	STypePricingService pricingService;

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
	CustomUserDetailService userService;

	@Autowired
	WorkOrderService workOrderService;

	@Autowired
	WorkOrderAttachmentService workOrderAttachmentService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	FlutterwaveService flwService;

	@Autowired
	BankDetailService bankDetailService;

	@Autowired
	FreelancerComponentModel fComponentModel;

	@Autowired
	FreelancerController freelancerController;
	
	@Autowired
	ClientController clientController;
	
	@Autowired
	FlutterwaveService flutterwaveService;

	private static final Logger log = LogManager.getLogger(IVoiceApplication.class);

	@Value("${upload.path}")
	String uploadDir;

	public static void main(String[] args) {
		SpringApplication.run(IVoiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		log.info("===>>> This is an info log");
		
	}
}
