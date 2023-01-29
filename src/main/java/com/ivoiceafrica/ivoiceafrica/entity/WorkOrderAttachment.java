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
	
	@Column(name = "source_language")
	private String source;
	
	@Column(name = "destination_language")
	private String destination;

	@Column(name = "description")
	private String description;

	@Column(name = "link_media_file")
	private String linkMediaFile;
	
	@Column(name = "word_count")
	private String wordCount;
	
	@Column(name = "page_count")
	private String pageCount;

	public WorkOrderAttachment() {
	}

	public WorkOrderAttachment(String attachId, WorkOrder workOrder, String source, String destination,
			String description, String linkMediaFile, String wordCount, String pageCount) {
		this.attachId = attachId;
		this.workOrder = workOrder;
		this.source = source;
		this.destination = destination;
		this.description = description;
		this.linkMediaFile = linkMediaFile;
		this.wordCount = wordCount;
		this.pageCount = pageCount;
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
	
	public String getWordCount() {
		return wordCount;
	}

	public void setWordCount(String wordCount) {
		this.wordCount = wordCount;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	@Override
	public String toString() {
		return "WorkOrderAttachment [attachId=" + attachId + ", workOrder=" + workOrder + ", source=" + source
				+ ", destination=" + destination + ", description=" + description + ", linkMediaFile=" + linkMediaFile
				+ ", wordCount=" + wordCount + ", pageCount=" + pageCount + "]";
	}

}
