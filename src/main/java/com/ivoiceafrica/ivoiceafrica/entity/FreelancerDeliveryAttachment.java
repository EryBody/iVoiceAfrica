package com.ivoiceafrica.ivoiceafrica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "delivery_freelancer_attachments")
public class FreelancerDeliveryAttachment {

	@Id
	@GenericGenerator(name = "attachment_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.FreelancerDeliveryAttachmentGenerator")
	@GeneratedValue(generator = "attachment_id", strategy = GenerationType.SEQUENCE)
	@Column(name = "attachment_id")
	private String attachmentId;

	@ManyToOne // Mapping the column of this table
	@JoinColumn(name = "delivery_id")
	private WorkOrdersDelivery workOrderDelivery;

	@ManyToOne // Mapping the column of this table
	@JoinColumn(name = "delivery_attach_id")
	private DeliveryAttachment deliveryAttachment;

	@Column(name = "link_media_file")
	private String linkMediaFile;

	@Column(name = "modified_date")
	private String modifiedDate;

	@Column(name = "entry_date")
	private String entryDate;

	public FreelancerDeliveryAttachment() {

	}

	public FreelancerDeliveryAttachment(String attachmentId, WorkOrdersDelivery workOrderDelivery,
			DeliveryAttachment deliveryAttachment, String linkMediaFile, String modifiedDate, String entryDate) {
		this.attachmentId = attachmentId;
		this.workOrderDelivery = workOrderDelivery;
		this.deliveryAttachment = deliveryAttachment;
		this.linkMediaFile = linkMediaFile;
		this.modifiedDate = modifiedDate;
		this.entryDate = entryDate;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public WorkOrdersDelivery getWorkOrderDelivery() {
		return workOrderDelivery;
	}

	public void setWorkOrderDelivery(WorkOrdersDelivery workOrderDelivery) {
		this.workOrderDelivery = workOrderDelivery;
	}

	public DeliveryAttachment getDeliveryAttachment() {
		return deliveryAttachment;
	}

	public void setDeliveryAttachment(DeliveryAttachment deliveryAttachment) {
		this.deliveryAttachment = deliveryAttachment;
	}

	public String getLinkMediaFile() {
		return linkMediaFile;
	}

	public void setLinkMediaFile(String linkMediaFile) {
		this.linkMediaFile = linkMediaFile;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	@Override
	public String toString() {
		return "FreelancerDeliveryAttachment [attachmentId=" + attachmentId + ", workOrderDelivery=" + workOrderDelivery
				+ ", deliveryAttachment=" + deliveryAttachment + ", linkMediaFile=" + linkMediaFile + ", modifiedDate="
				+ modifiedDate + ", entryDate=" + entryDate + "]";
	}

}
