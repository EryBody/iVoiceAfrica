package com.ivoiceafrica.ivoiceafrica.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "delivery_attachments")
public class DeliveryAttachment {

	@Id
	@GenericGenerator(name = "delivery_attach_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.DeliveryAttachmentGenerator")
	@GeneratedValue(generator = "delivery_attach_id", strategy = GenerationType.SEQUENCE)
	@Column(name = "delivery_attach_id")
	private String deliveryAttachId;

	@ManyToOne // Mapping the column of this table
	@JoinColumn(name = "delivery_id")
	private WorkOrdersDelivery workOrderDelivery;

	@Column(name = "source_language")
	private String source;

	@Column(name = "destination_language")
	private String destination;

	@Column(name = "description")
	private String description;

	@Column(name = "link_media_file")
	private String linkMediaFile;
	
	@OneToMany(mappedBy = "deliveryAttachment", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JsonIgnore
	private Set<FreelancerDeliveryAttachment> freelancerDeliveryAttachment;
	
	public DeliveryAttachment() {

	}

	public DeliveryAttachment(String deliveryAttachId, WorkOrdersDelivery workOrderDelivery, String source,
			String destination, String description, String linkMediaFile,
			Set<FreelancerDeliveryAttachment> freelancerDeliveryAttachment) {
		this.deliveryAttachId = deliveryAttachId;
		this.workOrderDelivery = workOrderDelivery;
		this.source = source;
		this.destination = destination;
		this.description = description;
		this.linkMediaFile = linkMediaFile;
		this.freelancerDeliveryAttachment = freelancerDeliveryAttachment;
	}

	public String getDeliveryAttachId() {
		return deliveryAttachId;
	}

	public void setDeliveryAttachId(String deliveryAttachId) {
		this.deliveryAttachId = deliveryAttachId;
	}

	public WorkOrdersDelivery getWorkOrderDelivery() {
		return workOrderDelivery;
	}

	public void setWorkOrderDelivery(WorkOrdersDelivery workOrderDelivery) {
		this.workOrderDelivery = workOrderDelivery;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLinkMediaFile() {
		return linkMediaFile;
	}

	public void setLinkMediaFile(String linkMediaFile) {
		this.linkMediaFile = linkMediaFile;
	}

	public Set<FreelancerDeliveryAttachment> getFreelancerDeliveryAttachment() {
		return freelancerDeliveryAttachment;
	}

	public void setFreelancerDeliveryAttachment(Set<FreelancerDeliveryAttachment> freelancerDeliveryAttachment) {
		this.freelancerDeliveryAttachment = freelancerDeliveryAttachment;
	}

	@Override
	public String toString() {
		return "DeliveryAttachment [deliveryAttachId=" + deliveryAttachId + ", source=" + source + ", destination="
				+ destination + ", description=" + description + ", linkMediaFile=" + linkMediaFile + "]";
	}

}
