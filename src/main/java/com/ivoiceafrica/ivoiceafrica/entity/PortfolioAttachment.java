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
@Table(name = "portfolio_attachments")
public class PortfolioAttachment {

	@Id
	@GenericGenerator(name = "attach_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.PortfolioAttachmentGenerator")
	@GeneratedValue(generator = "attach_id", strategy = GenerationType.SEQUENCE)
	@Column(name = "attach_id")
	private String attachId;

	@ManyToOne // Mapping the column of this table
	@JoinColumn(name = "portfolio_id")
	private Portfolio portfolio;

	@Column(name = "description")
	private String description;

	@Column(name = "link_media_file")
	private String linkMediaFile;

	@Column(name = "createdDate")
	private String createdDate;
	
	public PortfolioAttachment() {
		
	}

	public PortfolioAttachment(String attachId, Portfolio portfolio, String description, String linkMediaFile,
			String createdDate) {
		this.attachId = attachId;
		this.portfolio = portfolio;
		this.description = description;
		this.linkMediaFile = linkMediaFile;
		this.createdDate = createdDate;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
