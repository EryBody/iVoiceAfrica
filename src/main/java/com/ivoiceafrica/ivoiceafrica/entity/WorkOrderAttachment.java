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
@Table(name = "wo_attachments")
public class WorkOrderAttachment {

	@Id
	@GenericGenerator(name = "wo_attach_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.WorkOrderAttachmentGenerator")
	@GeneratedValue(generator = "wo_attach_id", strategy = GenerationType.SEQUENCE)
	@Column(name = "wo_attach_id")
	private String attachId;

	@ManyToOne // Mapping the column of this table
	@JoinColumn(name = "work_id")
	private WorkOrder workOrder;

	@Column(name = "description")
	private String description;

	@Column(name = "link_media_file")
	private String linkMediaFile;

	public WorkOrderAttachment() {
		
	}
	
	
	public WorkOrderAttachment(String attachId, WorkOrder workOrder, String description, String linkMediaFile) {
		this.attachId = attachId;
		this.workOrder = workOrder;
		this.description = description;
		this.linkMediaFile = linkMediaFile;
	}


	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
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
	
	
	
}
