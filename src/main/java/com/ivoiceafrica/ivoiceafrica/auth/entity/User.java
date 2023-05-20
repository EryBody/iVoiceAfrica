package com.ivoiceafrica.ivoiceafrica.auth.entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivoiceafrica.ivoiceafrica.entity.Administrator;
import com.ivoiceafrica.ivoiceafrica.entity.BankDetail;
import com.ivoiceafrica.ivoiceafrica.entity.Client;
import com.ivoiceafrica.ivoiceafrica.entity.Freelancer;
import com.ivoiceafrica.ivoiceafrica.entity.FreelancerServicePricing;
import com.ivoiceafrica.ivoiceafrica.entity.Portfolio;
import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceLanguages;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;
import com.ivoiceafrica.ivoiceafrica.entity.VoiceCapability;
import com.ivoiceafrica.ivoiceafrica.entity.WorkEscrowTransaction;
import com.ivoiceafrica.ivoiceafrica.entity.WorkFreelancerPayment;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrderReview;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPayments;
import com.ivoiceafrica.ivoiceafrica.entity.WorkTransactions;


@Entity
@Table(name = "users")
public class User  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Integer userId;

	@Column(name = "username",nullable = false, unique = true)
	@NotEmpty
	@Email(message = "{errors.invalid_email}")
	private String username;

	@Column(name = "upassword")
	private String upassword;

	@NotEmpty
	@Column(name = "first_name",nullable = false)
	private String firstName;
	
	@NotEmpty
	@Column(name = "last_name",nullable = false)
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;
	
	@NotEmpty
	@Column(name = "gender", nullable = false)
	private String gender;

	@NotEmpty
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "country_code")
	private String countryCode;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email_address",nullable = false, unique = true)
	private String emailAddress;
	
	@Column(name = "summary")
	private String summary;
	
	@Column(name = "education")
	private String education;
	
	@ManyToOne // Mapping the column of this table 
    @JoinColumn(name = "user_status_id")
    private UserStatus userStatus;
	
	@Column(name = "profile_picture")
	private String profilePicture;
	
	@Column(name = "modified_date")
	private String modifiedDate;
	
	@Column(name = "created_date")
	private String createdDate;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_roles",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
			inverseJoinColumns = {@JoinColumn (name = "role_id", referencedColumnName = "role_id")}
	)
	private Collection<Role> roles;
	
	//To Check if the user has a role or not
	public boolean hasRole(String roleName) {
		Iterator<Role> iterator = this.roles.iterator();
        while (iterator.hasNext()) {
            Role role = iterator.next();
            if (role.getName().equals(roleName)) {
            	System.out.println("===>>>Role Name(DB): "+role.getName()+": Role Name(User Input): "+roleName);
                return true;
            }
        }
         
        return false;
    }
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<Administrator> admin;
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<Client> clients;
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<Freelancer> freelancer;
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<FreelancerServicePricing> freelancerServicePricings;
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<Portfolio> portfolios;
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<Proposal> proposals;
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<ServiceRendered> serviceRendereds;
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<WorkOrder> workOrders;
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<WorkOrdersDelivery> workOrdersDeliveries;
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<VoiceCapability> voiceCapabilities;
	
	@OneToMany(mappedBy = "clientId",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<WorkPayments> workPayments;
	
	@OneToMany(mappedBy = "clientId",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
	private Set<WorkEscrowTransaction> workEscrowClientTransaction;
	
	@OneToMany(mappedBy = "freelancerId",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
	private Set<WorkEscrowTransaction> workEscrowFreelancerTransaction;
	
	@OneToMany(mappedBy = "freelancerId",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
	private Set<WorkFreelancerPayment> freelancerPayments;
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
	private Set<WorkTransactions> workTransaction;
	
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
	private Set<BankDetail> bankDetail;
	
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
	private Set<ServiceLanguages> serviceLanguages;
	
	
	@OneToMany(mappedBy = "user",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
	private Set<WorkOrderReview> workOrderReviews;
	
	public User() {
		
	}

	public User(Integer userId, @NotEmpty @Email(message = "{errors.invalid_email}") String username, String upassword,
			@NotEmpty String firstName, @NotEmpty String lastName, String middleName, @NotEmpty String gender,
			@NotEmpty String country, String nationality, String address, String phone, String emailAddress,
			String summary, String education, UserStatus userStatus, String profilePicture, String modifiedDate,
			String createdDate, String postalCode, Collection<Role> roles, Set<Administrator> admin,
			Set<Client> clients, Set<Freelancer> freelancer, Set<FreelancerServicePricing> freelancerServicePricings,
			Set<Portfolio> portfolios, Set<Proposal> proposals, Set<ServiceRendered> serviceRendereds,
			Set<WorkOrder> workOrders, Set<WorkOrdersDelivery> workOrdersDeliveries,
			Set<VoiceCapability> voiceCapabilities, Set<WorkPayments> workPayments,
			Set<WorkEscrowTransaction> workEscrowClientTransaction,
			Set<WorkEscrowTransaction> workEscrowFreelancerTransaction, Set<WorkFreelancerPayment> freelancerPayments,
			Set<WorkTransactions> workTransaction) {
		this.userId = userId;
		this.username = username;
		this.upassword = upassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.gender = gender;
		this.country = country;
		this.nationality = nationality;
		this.address = address;
		this.phone = phone;
		this.emailAddress = emailAddress;
		this.summary = summary;
		this.education = education;
		this.userStatus = userStatus;
		this.profilePicture = profilePicture;
		this.modifiedDate = modifiedDate;
		this.createdDate = createdDate;
		this.postalCode = postalCode;
		this.roles = roles;
		this.admin = admin;
		this.clients = clients;
		this.freelancer = freelancer;
		this.freelancerServicePricings = freelancerServicePricings;
		this.portfolios = portfolios;
		this.proposals = proposals;
		this.serviceRendereds = serviceRendereds;
		this.workOrders = workOrders;
		this.workOrdersDeliveries = workOrdersDeliveries;
		this.voiceCapabilities = voiceCapabilities;
		this.workPayments = workPayments;
		this.workEscrowClientTransaction = workEscrowClientTransaction;
		this.workEscrowFreelancerTransaction = workEscrowFreelancerTransaction;
		this.freelancerPayments = freelancerPayments;
		this.workTransaction = workTransaction;
	}


	public User(User user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.upassword = user.getUpassword();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.middleName = user.getMiddleName();
		this.gender = user.getGender();
		this.country = user.getCountry();
		this.countryCode = user.getCountryCode();
		this.nationality = user.getNationality();
		this.address = user.getAddress();
		this.phone = user.getPhone();
		this.emailAddress = user.getEmailAddress();
		this.summary = user.getSummary();
		this.education = user.getEducation();
		this.userStatus = user.getUserStatus();
		this.profilePicture = user.getProfilePicture();
		this.modifiedDate = user.getModifiedDate();
		this.createdDate = user.getCreatedDate();
		this.postalCode = user.getPostalCode();
		this.roles = user.getRoles();
	}

	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Set<Administrator> getAdmin() {
		return admin;
	}

	public void setAdmin(Set<Administrator> admin) {
		this.admin = admin;
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	public Set<Freelancer> getFreelancer() {
		return freelancer;
	}

	public void setFreelancer(Set<Freelancer> freelancer) {
		this.freelancer = freelancer;
	}

	public Set<FreelancerServicePricing> getFreelancerServicePricings() {
		return freelancerServicePricings;
	}

	public void setFreelancerServicePricings(Set<FreelancerServicePricing> freelancerServicePricings) {
		this.freelancerServicePricings = freelancerServicePricings;
	}

	public Set<Portfolio> getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(Set<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	public Set<Proposal> getProposals() {
		return proposals;
	}

	public void setProposals(Set<Proposal> proposals) {
		this.proposals = proposals;
	}

	public Set<ServiceRendered> getServiceRendereds() {
		return serviceRendereds;
	}

	public void setServiceRendereds(Set<ServiceRendered> serviceRendereds) {
		this.serviceRendereds = serviceRendereds;
	}

	public Set<WorkOrder> getWorkOrders() {
		return workOrders;
	}

	public void setWorkOrders(Set<WorkOrder> workOrders) {
		this.workOrders = workOrders;
	}

	public Set<WorkOrdersDelivery> getWorkOrdersDeliveries() {
		return workOrdersDeliveries;
	}

	public void setWorkOrdersDeliveries(Set<WorkOrdersDelivery> workOrdersDeliveries) {
		this.workOrdersDeliveries = workOrdersDeliveries;
	}

	public Set<VoiceCapability> getVoiceCapabilities() {
		return voiceCapabilities;
	}

	public void setVoiceCapabilities(Set<VoiceCapability> voiceCapabilities) {
		this.voiceCapabilities = voiceCapabilities;
	}
	
	public Set<WorkPayments> getWorkPayments() {
		return workPayments;
	}

	public void setWorkPayments(Set<WorkPayments> workPayments) {
		this.workPayments = workPayments;
	}

	public Set<WorkEscrowTransaction> getWorkEscrowClientTransaction() {
		return workEscrowClientTransaction;
	}

	public void setWorkEscrowClientTransaction(Set<WorkEscrowTransaction> workEscrowClientTransaction) {
		this.workEscrowClientTransaction = workEscrowClientTransaction;
	}

	public Set<WorkEscrowTransaction> getWorkEscrowFreelancerTransaction() {
		return workEscrowFreelancerTransaction;
	}

	public void setWorkEscrowFreelancerTransaction(Set<WorkEscrowTransaction> workEscrowFreelancerTransaction) {
		this.workEscrowFreelancerTransaction = workEscrowFreelancerTransaction;
	}

	public Set<WorkFreelancerPayment> getFreelancerPayments() {
		return freelancerPayments;
	}

	public void setFreelancerPayments(Set<WorkFreelancerPayment> freelancerPayments) {
		this.freelancerPayments = freelancerPayments;
	}

	public Set<WorkTransactions> getWorkTransaction() {
		return workTransaction;
	}

	public void setWorkTransaction(Set<WorkTransactions> workTransaction) {
		this.workTransaction = workTransaction;
	}

	public Set<BankDetail> getBankDetail() {
		return bankDetail;
	}

	public void setBankDetail(Set<BankDetail> bankDetail) {
		this.bankDetail = bankDetail;
	}
	
	public Set<ServiceLanguages> getServiceLanguages() {
		return serviceLanguages;
	}

	public void setServiceLanguages(Set<ServiceLanguages> serviceLanguages) {
		this.serviceLanguages = serviceLanguages;
	}
	
	public Set<WorkOrderReview> getWorkOrderReviews() {
		return workOrderReviews;
	}

	public void setWorkOrderReviews(Set<WorkOrderReview> workOrderReviews) {
		this.workOrderReviews = workOrderReviews;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", upassword=" + upassword + ", firstName="
				+ firstName + ", lastName=" + lastName + ", middleName=" + middleName + ", gender=" + gender
				+ ", country=" + country + ", countryCode=" + countryCode + ", nationality=" + nationality + ", address=" + address + ", phone=" + phone
				+ ", emailAddress=" + emailAddress + ", summary=" + summary + ", education=" + education
				+ ", userStatus=" + userStatus + ", profilePicture=" + profilePicture + ", modifiedDate=" + modifiedDate
				+ ", createdDate=" + createdDate + ", postalCode=" + postalCode + "]";
	}

	
	
}
