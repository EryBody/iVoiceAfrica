package com.ivoiceafrica.ivoiceafrica.components.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerProfileLanguageDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerProposals;
import com.ivoiceafrica.ivoiceafrica.DTO.FreelancerServiceTypePricingDTO;
import com.ivoiceafrica.ivoiceafrica.DTO.WorkOrderCalculationsDTO;
import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.DeliveryAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerServicePricing;
import com.ivoiceafrica.ivoiceafrica.entity.Language;
import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.ProposalStatus;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceLanguages;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceTypePricing;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderAttachment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;
import com.ivoiceafrica.ivoiceafrica.models.AudioTimer;
import com.ivoiceafrica.ivoiceafrica.models.CountryCodesBean;
import com.ivoiceafrica.ivoiceafrica.models.FreelancerPriceModel;
import com.ivoiceafrica.ivoiceafrica.service.CustomUserDetailService;
import com.ivoiceafrica.ivoiceafrica.service.DeliveryAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.FreelancerPricingService;
import com.ivoiceafrica.ivoiceafrica.service.LanguageService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalService;
import com.ivoiceafrica.ivoiceafrica.service.ProposalStatusService;
import com.ivoiceafrica.ivoiceafrica.service.SLanguageService;
import com.ivoiceafrica.ivoiceafrica.service.SRenderedService;
import com.ivoiceafrica.ivoiceafrica.service.STypePricingService;
import com.ivoiceafrica.ivoiceafrica.service.STypeService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderAttachmentService;
import com.ivoiceafrica.ivoiceafrica.service.WorkOrderService;
import com.ivoiceafrica.ivoiceafrica.service.WorkPaymentService;
import com.ivoiceafrica.ivoiceafrica.utility.ReadFromJsonFile;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import com.coremedia.iso.IsoFile;

@Component("ClientComponentModel")
public class ClientComponentModel {

	@Autowired
	WorkOrderAttachmentService attachmentService;

	@Autowired
	WorkOrderService orderService;

	@Autowired
	LanguageService languageService;

	@Autowired
	ProposalService proposalService;

	@Autowired
	ProposalStatusService proposalStatusService;

	@Autowired
	SRenderedService serviceRenderedService;

	@Autowired
	SLanguageService serviceLanguageService;

	@Autowired
	STypePricingService sTypePricing;

	@Autowired
	FreelancerPricingService freelancerPricingService;

	@Autowired
	CustomUserDetailService userService;

	@Autowired
	STypeService sTypeService;

	@Autowired
	DeliveryAttachmentService deliveryAttachmentService;

	@Autowired
	HttpSession session;

	@Autowired
	WorkOrderAttachmentService workOrderAttachmentService;

	@Autowired
	WorkPaymentService workPaymentService;

	// Check the System Utility Class on GeekForGeek(Online)
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/profilepictures";

	public String getWorkAttachments(String workOrderId) {

		Optional<WorkOrder> order = orderService.findById(workOrderId);

		List<WorkOrderAttachment> workAttachments = attachmentService.findWorkOrderAttachmentByWorkOrder(order.get());

		int workAttachmentSize = workAttachments.size();

		return String.valueOf(workAttachmentSize);
	}

	public String translateIdToLanguage(int languageId) {

		Optional<Language> languageName = Optional.empty();
		try {

			languageName = languageService.findById(languageId);

		} catch (Exception ex) {
			System.out.println("===>>> Exception: " + ex.getMessage());
			languageName = Optional.empty();
		}

		return languageName.get().getLanguage();
	}

	public String getFreelancersForBids(String workOrderId) {

		Optional<ProposalStatus> proposalStatus = proposalStatusService.findById(11);// 9 Means Freelancer Accepted
																						// Status

		Optional<WorkOrder> order = orderService.findById(workOrderId);

		List<Proposal> clientProposals = proposalService
				.findProposalByWorkOrderAndProposalStatusOrderByCreatedDate(order.get(), proposalStatus.get());

		int clientProposalSize = clientProposals.size();

		return String.valueOf(clientProposalSize);
	}

	public String getFreelancerCount(String workOrderId) {

		Optional<WorkOrder> order = orderService.findById(workOrderId);

		List<FreelancerProposals> proposals = new ArrayList<>();

		List<Map<String, Object>> freelancerProposals = serviceRenderedService.findFreelancerDetailsForWorks(
				order.get().getServiceType().getTypeId(), order.get().getMinAmount(), order.get().getMaxAmount());

		for (Map<String, Object> map : freelancerProposals) {

			// Create ObjectMapper instance
			ObjectMapper mapper = new ObjectMapper();
			// Converting POJO to Map
			Map<String, Object> mapping = mapper.convertValue(map, new TypeReference<Map<String, Object>>() {
			});
			FreelancerProposals proposal = mapper.convertValue(mapping, FreelancerProposals.class);

			proposals.add(proposal);

		}

		int clientProposalSize = proposals.size();

		return String.valueOf(clientProposalSize);
	}

	public ServiceLanguages getFreelancerFirstServiceLanguage(User user) {

		Optional<ServiceRendered> freelancerServiceRendered = serviceRenderedService
				.findFirstServiceRenderedByUser(user);

		Optional<ServiceLanguages> serviceLanguage = serviceLanguageService
				.findFirstServiceLanguageByServiceRendered(freelancerServiceRendered.get());

		return serviceLanguage.get();
	}

	public List<ServiceLanguages> getFirst3FreelancerServiceLanguage(User user) {

		Optional<ServiceRendered> freelancerServiceRendered = serviceRenderedService
				.findFirstServiceRenderedByUser(user);

		List<ServiceLanguages> serviceLanguages = serviceLanguageService
				.findFirst3ServiceLanguageByServiceRendered(freelancerServiceRendered.get());

		return serviceLanguages;
	}

	public List<ServiceLanguages> getFirst3FreelancerServiceLanguage(String userId) {

		Optional<User> opUser = userService.findFirstUserByUsername(userId);

		Optional<ServiceRendered> freelancerServiceRendered = serviceRenderedService
				.findFirstServiceRenderedByUser(opUser.get());

		List<ServiceLanguages> serviceLanguages = serviceLanguageService
				.findFirst3ServiceLanguageByServiceRendered(freelancerServiceRendered.get());

		return serviceLanguages;
	}

	public List<ServiceLanguages> getFirst3FreelancerServiceLang(String userId) {

		Optional<User> user = userService.findById(Integer.parseInt(userId));
		Optional<User> opUser = userService.findFirstUserByUsername(user.get().getUsername());

		Optional<ServiceRendered> freelancerServiceRendered = serviceRenderedService
				.findFirstServiceRenderedByUser(opUser.get());

		List<ServiceLanguages> serviceLanguages = serviceLanguageService
				.findFirst3ServiceLanguageByServiceRendered(freelancerServiceRendered.get());

		return serviceLanguages;
	}

	public List<FreelancerProfileLanguageDTO> get3FreelancerServiceLanguages(ServiceRendered serviceRenderedId,
			String userId) {

		Optional<User> opUser = userService.findFirstUserByUsername(userId);

		List<FreelancerProfileLanguageDTO> profileDTO = new ArrayList<>();

		List<ServiceLanguages> serviceLanguages = serviceLanguageService
				.findFirst3ServiceLanguageByServiceRenderedAndUser(serviceRenderedId, opUser.get());

		System.out.println("===>>> serviceRenderedId: " + serviceRenderedId);
		System.out.println("===>>> serviceLanguages: " + serviceLanguages);

		for (ServiceLanguages serviceLanguage : serviceLanguages) {

			FreelancerProfileLanguageDTO profileLanguage = new FreelancerProfileLanguageDTO();

			profileLanguage.setLanguageId(serviceLanguage.getLanguageId());
			profileLanguage.setLanguageDesc(serviceLanguage.getLanguageDesc());
			profileLanguage.setLanguageUpload(serviceLanguage.getLanguageUpload());
			profileLanguage.setServiceRendered(serviceLanguage.getServiceRendered());
			profileLanguage.setVoiceType(serviceLanguage.getVoiceType());

			profileDTO.add(profileLanguage);
		}

		return profileDTO;
	}

	public List<DeliveryAttachment> getListOfDeliveryAttachment(WorkOrdersDelivery workOrderDelivery) {

		List<DeliveryAttachment> deliveryAttachment = deliveryAttachmentService
				.findDeliveryAttachmentByWorkOrderDelivery(workOrderDelivery);

		return deliveryAttachment;
	}

	public List<WorkOrderAttachment> getAttachmentForDelivery(String workOrderId) {

		Optional<WorkOrder> opWorkOrder = orderService.findById(workOrderId);

		List<WorkOrderAttachment> deliveryAttachment = attachmentService
				.findWorkOrderAttachmentByWorkOrder(opWorkOrder.get());

		return deliveryAttachment;
	}

	public String getAttachmentSizeForDelivery(String workOrderId) {

		Optional<WorkOrder> opWorkOrder = orderService.findById(workOrderId);

		List<WorkOrderAttachment> deliveryAttachment = attachmentService
				.findWorkOrderAttachmentByWorkOrder(opWorkOrder.get());
		return String.valueOf(deliveryAttachment.size());
	}

	public String getSizeOfDeliveryAttachment(WorkOrdersDelivery workOrderDelivery) {

		List<DeliveryAttachment> deliveryAttachment = deliveryAttachmentService
				.findDeliveryAttachmentByWorkOrderDelivery(workOrderDelivery);
		return String.valueOf(deliveryAttachment.size());
	}

	public String getNoOfServiceRendered(User user) {
		List<ServiceRendered> serviceRendered = serviceRenderedService.findServiceRenderedListByUser(user);

		int serviceRenderedSize = serviceRendered.size();

		return String.valueOf(serviceRenderedSize);
	}

	public String checkProfile() {

		String userRole = (String) session.getAttribute("userRole");
		String userRoleName = "";

		if (userRole.equals("ROLE_ADMIN")) {
			userRoleName = "Administrator";
		} else if (userRole.equals("ROLE_SUPERVISOR")) {
			userRoleName = "Supervisor";
		} else if (userRole.equals("ROLE_CLIENT")) {
			userRoleName = "Client";
		} else if (userRole.equals("ROLE_FREELANCER")) {
			userRoleName = "Freelancer";
		}

		return String.valueOf(userRoleName);
	}

	public String getNoOfServicesRendered(String userId) {

		Optional<User> user = userService.findById(Integer.parseInt(userId));
		Optional<User> opUser = userService.findFirstUserByUsername(user.get().getUsername());

		List<ServiceRendered> serviceRendered = serviceRenderedService.findServiceRenderedListByUser(opUser.get());

		int serviceRenderedSize = serviceRendered.size();

		return String.valueOf(serviceRenderedSize);
	}

	public String checkCurrentJobStatus(String workOrderId) {

		Optional<WorkOrder> workOrder = orderService.findById(workOrderId);

		return workOrder.get().getWorkOrderStatus().getStatus();

	}

	public String checkExtention(String fileName) {

		String[] name = fileName.split(".", 2);
		String extension = name[1];

		System.out.println("===>>> fileName: " + fileName);
		String fileType = "";

		String[] extensions = { "png", "jpg", "jpeg", "pdf", "tiff", "tif", "doc", "docx", "html", "htm", "xls", "xlsx",
				"txt", "ppt", "pptx" };

		// check if the specified element
		// is present in the array or not
		// using contains() method
		boolean test = Arrays.asList(extensions).contains(extension);

		if (test == true) {
			fileType = "image";
		} else {
			fileType = "other-file";
		}
		return fileType;
	}

	public List<FreelancerServiceTypePricingDTO> getFreelancerPricing(ServiceType serviceType, String userId) {

		List<FreelancerServiceTypePricingDTO> pricingsDTO = new ArrayList<>();

		try {

			Optional<User> opUser = userService.findFirstUserByUsername(userId);

			Optional<ServiceTypePricing> pricing = sTypePricing.findServiceTypePricingByServiceType(serviceType);

			List<FreelancerServicePricing> fpricings = freelancerPricingService
					.findFreelancerServicePricingListByServiceTypePricingAndUser(pricing.get(), opUser.get());

			for (FreelancerServicePricing p : fpricings) {

				FreelancerServiceTypePricingDTO freelancerPricingDTO = new FreelancerServiceTypePricingDTO();

				freelancerPricingDTO.setUser(p.getUser());
				freelancerPricingDTO.setServiceTypePricing(p.getServiceTypePricing());
				freelancerPricingDTO.setMinPrice(p.getMinPrice());
				freelancerPricingDTO.setMaxPrice(p.getMaxPrice());

				pricingsDTO.add(freelancerPricingDTO);

			}
		} catch (Exception ex) {
			System.out.println("===>>> Exception in component model: " + ex.getMessage());
		}
		return pricingsDTO;
	}

	public List<ServiceTypePricing> getServicePricings(ServiceType serviceType) {

		List<ServiceTypePricing> servicePricings = new ArrayList<>();

		try {

			servicePricings = sTypePricing.findServiceTypePricingListByServiceType(serviceType);

		} catch (Exception ex) {
			System.out.println("===>>> Exception in getServicePricings model: " + ex.getMessage());
		}

		return servicePricings;
	}

	public FreelancerPriceModel getFreelancerPrices(ServiceTypePricing serviceTypePricing, String userId) {

		System.out.println("\n===>>> serviceTypePricing: " + serviceTypePricing.getPricingId() + "\n");

		FreelancerPriceModel model = new FreelancerPriceModel();

		try {

			Optional<User> opUser = userService.findFirstUserByUsername(userId);

			Optional<FreelancerServicePricing> freelancerPricing = freelancerPricingService
					.findFreelancerServicePricingByServiceTypePricingAndUser(serviceTypePricing, opUser.get());

			System.out.println("===>>> FreelancerPricing: " + freelancerPricing);

			if (freelancerPricing.isPresent()) {

				model.setMinPrice(freelancerPricing.get().getMinPrice());
				model.setMaxPrice(freelancerPricing.get().getMaxPrice());

			} else {

				model.setMinPrice(0.0);
				model.setMaxPrice(0.0);
			}

		} catch (Exception ex) {
			System.out.println("===>>> Exception in getFreelancerPrices model: " + ex.getMessage());
		}

		return model;
	}

	public String getProfilePictureName(String userId) {

		String profilePictureName = "";

		Optional<User> opUser = userService.findFirstUserByUsername(userId);
		profilePictureName = opUser.get().getProfilePicture();

		return String.valueOf(profilePictureName);
	}

	public User getUserDetails(String userId) {

		Optional<User> user = userService.findById(Integer.parseInt(userId));
		Optional<User> opUser = userService.findFirstUserByUsername(user.get().getUsername());

		if (opUser.isPresent()) {
			return opUser.get();
		} else {
			return new User();
		}

	}

	public Proposal getProposalDetails(String userId, String workId) {

		Proposal proposalUser = proposalService.checkLastStatusOfProposal(Integer.parseInt(userId), workId);
		return proposalUser;

	}

	public boolean getProposalStatusFromWorkOrder(String workId) {

		List<Proposal> proposals = proposalService.findProposalByWorkOrder(workId);

		for (Proposal proposal : proposals) {

			// 11 means freelancer accepted
			if (proposal.getProposalStatus().getProposalStatusId() == 11) {
				return true;
			}
		}
		return false;
	}

	public String getProposalUserFromWorkOrder(String workId) {

		List<Proposal> proposals = proposalService.findProposalByWorkOrder(workId);

		for (Proposal proposal : proposals) {

			// 11 means freelancer accepted
			if (proposal.getProposalStatus().getProposalStatusId() == 11) {
				return proposal.getUser().getUsername();
			}
		}
		return "";
	}

	public int getProposalUserIdFromWorkOrder(String workId) {

		List<Proposal> proposals = proposalService.findProposalByWorkOrder(workId);

		for (Proposal proposal : proposals) {

			// 11 means freelancer accepted
			if (proposal.getProposalStatus().getProposalStatusId() == 11) {
				return proposal.getUser().getUserId();
			}
		}
		return 0;
	}

	public int getTxtWordcount(String filePath) {

		int wc = 0; // Intialize word count to zero
		try {
			File f1 = new File(filePath); // Creation of File Descriptor for input file
			String[] words = null; // Intialize the word Array

			FileReader fr = new FileReader(f1); // Creation of File Reader object
			BufferedReader br = new BufferedReader(fr); // Creation of BufferedReader object
			String s;

			while ((s = br.readLine()) != null) // Reading Content from the file
			{
				words = s.split(" "); // Split the word using space
				wc = wc + words.length; // increase the word count for each word
			}

			fr.close();
			System.out.println("Number of words in the file:" + wc);

		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		return wc;
	}

	public long countTxtLine(String filePath) {

		Path path = Paths.get(filePath);
		long lines = 0;
		try {
			lines = Files.lines(path).count();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		return lines;
	}

	public int countPageDoc(String filePath, String extention) {

		int pageCount = 0;
		HWPFDocument document;
		try {

			if (extention.equals("docx")) {

				XWPFDocument docx = new XWPFDocument(POIXMLDocument.openPackage(filePath));
				pageCount = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();

				System.out.println("===>>> Count Word Docx Page: " + pageCount);
			} else if (extention.equals("doc")) {

				document = new HWPFDocument(new FileInputStream(filePath));
				pageCount = document.getSummaryInformation().getPageCount();

				System.out.println("===>>> Count Word Doc Page: " + pageCount);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return pageCount;
		}

		return pageCount;
	}

	public long countWordDoc(String filePath) {

		long wordCount = 0;

		try {

			File file = new File(filePath);

			POITextExtractor textExtractor;
			if (file.getName().endsWith(".docx")) {
				XWPFDocument doc = new XWPFDocument(new FileInputStream(file));
				textExtractor = new XWPFWordExtractor(doc);
			} else if (file.getName().endsWith(".doc")) {
				textExtractor = new WordExtractor(new FileInputStream(file));
			} else {
				throw new IllegalArgumentException("Not a MS Word file.");
			}

			wordCount = Arrays.stream(textExtractor.getText().split("\\s+"))
					.filter(s -> s.matches("^.*[\\p{L}\\p{N}].*$")).count();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return wordCount;
		}

		return wordCount;
	}

	public int countPdfPage(String filePath) {

		int pdfPageCount = 0;

		try {

			PDDocument doc = PDDocument.load(new File(filePath));
			pdfPageCount = doc.getNumberOfPages();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return pdfPageCount;
		}

		return pdfPageCount;
	}

	public int countPdfWord(String filePath) {

		int pdfPageCount = 0;

		try {
			PDDocument document = PDDocument.load(new File(filePath));
			if (!document.isEncrypted()) {

				PDFTextStripper stripper = new PDFTextStripper();
				String text = stripper.getText(document);

				if (text == null || text.isEmpty()) {
					return pdfPageCount;
				}

				StringTokenizer tokens = new StringTokenizer(text);

				pdfPageCount = tokens.countTokens();

			}
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return pdfPageCount;
		}
		return pdfPageCount;
	}

	public AudioTimer getAudioLengthInSecs(String filePath, String format) {

		AudioTimer timer = new AudioTimer();
		long durationInSecs = 0;
		try {

			durationInSecs = getDuration(filePath, format);

			long hours = durationInSecs / 3600;
			long minutes = (durationInSecs % 3600) / 60;

			timer.setAudioInSecs(durationInSecs);
			timer.setAudioInMins(minutes);
			timer.setAudioInHours(hours);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===>>> Exception: " + e.getMessage());
			return timer;
		}

		return timer;
	}

	public static long getDuration(String filePath) throws IOException {
		String format = getVideoFormat(filePath);
		long result = 0;
		if ("wav".equals(format)) {
			result = getDurationForAudio(filePath).intValue();
		} else if ("mp3".equals(format)) {
			result = getMp3Duration(filePath).intValue();
		} else if ("m4a".equals(format)) {
			result = getMp4DurationForVideo(filePath);
		} else if ("mov".equals(format)) {
			result = getMp4DurationForVideo(filePath);
		} else if ("mp4".equals(format)) {
			result = getMp4DurationForVideo(filePath);
		}

		return result;
	}

	public static long getDuration(String filePath, String format) throws IOException {
		long result = 0;
		if ("wav".equals(format)) {
			result = getDurationForAudio(filePath).intValue();
		} else if ("mp3".equals(format)) {
			result = getMp3Duration(filePath).intValue();
		} else if ("m4a".equals(format)) {
			result = getMp4DurationForVideo(filePath);
		} else if ("mov".equals(format)) {
			result = getMp4DurationForVideo(filePath);
		} else if ("mp4".equals(format)) {
			result = getMp4DurationForVideo(filePath);
		}

		return result;
	}

	public static long getMp4DurationForVideo(String videoPath) throws IOException {
		IsoFile isoFile = new IsoFile(videoPath);
		long lengthInSeconds = isoFile.getMovieBox().getMovieHeaderBox().getDuration()
				/ isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
		return lengthInSeconds;
	}

	public static String getVideoFormat(String path) {
		return path.toLowerCase().substring(path.toLowerCase().lastIndexOf(".") + 1);
	}

	public static Float getDurationForAudio(String filePath) {
		try {

			File destFile = new File(filePath);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(destFile);
			AudioFormat format = audioInputStream.getFormat();
			long audioFileLength = destFile.length();
			int frameSize = format.getFrameSize();
			float frameRate = format.getFrameRate();
			float durationInSeconds = (audioFileLength / (frameSize * frameRate));
			return durationInSeconds;

		} catch (Exception e) {
			e.printStackTrace();
			return 0f;
		}
	}

	public static Float getMp3Duration(String filePath) {
		try {
			File mp3File = new File(filePath);
			MP3File f = (MP3File) AudioFileIO.read(mp3File);
			MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
			return Float.parseFloat(audioHeader.getTrackLength() + "");
		} catch (Exception e) {
			e.printStackTrace();
			return 0f;
		}
	}

	public static Float getMp3Duration(File mp3File) {

		try {
			// File mp3File = new File(filePath);
			MP3File f = (MP3File) AudioFileIO.read(mp3File);
			MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
			return Float.parseFloat(audioHeader.getTrackLength() + "");
		} catch (Exception e) {
			e.printStackTrace();
			return 0f;
		}
	}

	public static long getPCMDurationMilliSecond(String filePath) {
		File file = new File(filePath);
		long second = file.length() / 32000;
		long milliSecond = Math.round((file.length() % 32000) / 32000.0 * 1000);
		return second * 1000 + milliSecond;
	}

	public CountryCodesBean[] loadCountriesFromJsonFile() {
		ReadFromJsonFile file = new ReadFromJsonFile();
		CountryCodesBean[] countries = file.readFromJson(uploadDir + "/CountryCodesMod.json");
		return countries;
	}

	public String workOrderCalculations(Optional<WorkOrder> workOrder, Model model) {

		String message = "";
		double totalMinAmount = 0.0;
		double totalMaxAmount = 0.0;
		List<WorkOrderCalculationsDTO> workOrderCalculations = new ArrayList<>();

		// calculate the price based on the job and attachments
		List<WorkOrderAttachment> workOrderAttachments = workOrderAttachmentService
				.findWorkOrderAttachmentByWorkOrder(workOrder.get());

		final String[] fileExtention = new String[] { "mp3", "pdf", "doc", "docx", "txt", "m4a", "wav" };
		for (WorkOrderAttachment workAttach : workOrderAttachments) {

			WorkOrderCalculationsDTO workOrderCalculation = new WorkOrderCalculationsDTO();

			String filePath = uploadDir + "/" + workAttach.getLinkMediaFile();

			// pass it through the calculation based on the file type
			int index = filePath.lastIndexOf('.');

			if (index > 0) {
				String extension = filePath.substring(index + 1);
				System.out.println("===>>> File extension is " + extension);

				List<String> fileExt = Arrays.asList(fileExtention);

				if (fileExt.contains(extension)) {

					// calculate and set amount
					workOrderCalculation.setServiceType(workOrder.get().getServiceType().getTypeName());
					workOrderCalculation.setExtention(extension);

					if (extension.equals("mp3") || extension.equals("m4a") || extension.equals("wav")) {

						AudioTimer audioLength = getAudioLengthInSecs(filePath, extension);
						System.out.println("===>>> audioLength: " + audioLength.toString());

						long timerCountInSecs = audioLength.getAudioInSecs();
						long timerCountInMins = audioLength.getAudioInMins();
						long timerCountInHours = audioLength.getAudioInHours();

						workOrderCalculation.setTimerCount(String.valueOf(timerCountInSecs));
						workOrderCalculation.setTimerCountInMins(String.valueOf(timerCountInMins));
						workOrderCalculation.setTimerCountInHours(String.valueOf(timerCountInHours));

						// In the sample of this code, the calculation works with the minimum amount
						// setting job amount

						double minAmount = 0.0;
						double maxAmount = 0.0;
						String workSelection = "";

						if (workOrder.get().getWorkRate().equals("perseconds")) {
							workSelection = "Rate(Per Seconds)";
							minAmount = timerCountInSecs * workOrder.get().getMinAmount();
							maxAmount = timerCountInSecs * workOrder.get().getMaxAmount();
						} else if (workOrder.get().getWorkRate().equals("perminutes")) {
							workSelection = "Rate(Per Minutes)";
							minAmount = timerCountInMins * workOrder.get().getMinAmount();
							maxAmount = timerCountInMins * workOrder.get().getMaxAmount();
						} else if (workOrder.get().getWorkRate().equals("perhour")) {
							workSelection = "Rate(Per Hour)";
							minAmount = timerCountInHours * workOrder.get().getMinAmount();
							maxAmount = timerCountInHours * workOrder.get().getMaxAmount();
						}

						workOrderCalculation.setWorkRateSelection(workSelection);
						workOrderCalculation.setJobAmount(minAmount);
						workOrderCalculation.setJobMaxAmount(maxAmount);

					} else if (extension.equals("doc") || extension.equals("docx")) {

						int wordPageCount = countPageDoc(filePath, "docx");
						System.out.println("===>>> wordPageCount: " + wordPageCount);

						long wordDocCount = countWordDoc(filePath);
						System.out.println("===>>> wordDocCount: " + wordDocCount);

						// calculate for word and page count
						workOrderCalculation.setWordCount(String.valueOf(wordDocCount));

						double minAmount = 0.0;
						double maxAmount = 0.0;
						String workSelection = "";

						if (workOrder.get().getWorkRate().equals("perword")) {
							workSelection = "Rate(Per Word)";
							minAmount = wordDocCount * workOrder.get().getMinAmount();
							maxAmount = wordDocCount * workOrder.get().getMaxAmount();
						} else if (workOrder.get().getWorkRate().equals("perpage")) {
							workSelection = "Rate(Per Page)";
							minAmount = wordPageCount * workOrder.get().getMinAmount();
							maxAmount = wordPageCount * workOrder.get().getMaxAmount();
						}

						workOrderCalculation.setWorkRateSelection(workSelection);
						workOrderCalculation.setJobAmount(minAmount);
						workOrderCalculation.setJobMaxAmount(maxAmount);

					} else if (extension.equals("pdf")) {

						int pdfWordCount = countPdfWord(filePath);
						System.out.println("===>>> pdfWordCounts: " + pdfWordCount);

						int pdfPageCount = countPdfPage(filePath);
						System.out.println("===>>> wordPageCount: " + pdfPageCount);

						// calculate for word and page count
						workOrderCalculation.setWordCount(String.valueOf(pdfWordCount));

						double minAmount = 0.0;
						double maxAmount = 0.0;
						String workSelection = "";

						if (workOrder.get().getWorkRate().equals("perword")) {
							workSelection = "Rate(Per Word)";
							minAmount = pdfWordCount * workOrder.get().getMinAmount();
							maxAmount = pdfWordCount * workOrder.get().getMaxAmount();
						} else if (workOrder.get().getWorkRate().equals("perpage")) {
							workSelection = "Rate(Per Page)";
							minAmount = pdfPageCount * workOrder.get().getMinAmount();
							maxAmount = pdfPageCount * workOrder.get().getMaxAmount();
						}

						workOrderCalculation.setWorkRateSelection(workSelection);
						workOrderCalculation.setJobAmount(minAmount);
						workOrderCalculation.setJobMaxAmount(maxAmount);

					} else if (extension.equals("txt")) {

						int txtCount = getTxtWordcount(filePath);
						System.out.println("===>>> TxtCount: :" + txtCount);

						long txtLineCount = countTxtLine(filePath);
						System.out.println("===>>> TxtLineCount: :" + txtLineCount);

						// calculate for word and page count
						workOrderCalculation.setWordCount(String.valueOf(txtCount));

						double minAmount = 0.0;
						double maxAmount = 0.0;
						String workSelection = "";

						if (workOrder.get().getWorkRate().equals("perword")) {
							workSelection = "Rate(Per Word)";
							minAmount = txtCount * workOrder.get().getMinAmount();
							maxAmount = txtCount * workOrder.get().getMaxAmount();
						}

						workOrderCalculation.setWorkRateSelection(workSelection);
						workOrderCalculation.setJobAmount(minAmount);
						workOrderCalculation.setJobMaxAmount(maxAmount);

					} else {
						message = "Cannot process file type with this extention " + extension;
					}

				} else {
					message = "Cannot process file type, We only accept mp3, m4a, wav, aac, pdf, doc, docx, txt. Thank you";
				}
			}

			totalMinAmount += workOrderCalculation.getJobAmount();
			totalMaxAmount += workOrderCalculation.getJobMaxAmount();

			workOrderCalculations.add(workOrderCalculation);

			message = "done";
		}

		model.addAttribute("workOrderCalculations", workOrderCalculations);
		model.addAttribute("totalMinAmount", totalMinAmount);
		model.addAttribute("totalMaxAmount", totalMaxAmount);

		return message;
	}

	public String workRateSelection(String workRate) {

		String translatedWorkRate = "";

		if (workRate.equals("perword")) {

			translatedWorkRate = "Per Word";

		} else if (workRate.equals("perseconds")) {
			translatedWorkRate = "Per Second";
		} else if (workRate.equals("perminutes")) {

			translatedWorkRate = "Per Minute";
		} else if (workRate.equals("perhour")) {
			translatedWorkRate = "Per Hour";
		} else if (workRate.equals("perpage")) {
			translatedWorkRate = "Per Page";
		}

		return translatedWorkRate;
	}

	public String checkClientWordOrderPayment(int clientUserId, String workOrderId) {

		if (workPaymentService.findWorkPaymentByWorkOrderIdAndClientId(clientUserId, workOrderId) == null) {
			return "not-paid";
		} else {
			return "paid";
		}
	}

	public int getLastProposalUser(String workOrderId) {

		Proposal prop = proposalService.findProposalByWorkOrderId(workOrderId);

		if (prop == null) {
			return 0;
		} else {
			return prop.getUser().getUserId();
		}
	}

}
