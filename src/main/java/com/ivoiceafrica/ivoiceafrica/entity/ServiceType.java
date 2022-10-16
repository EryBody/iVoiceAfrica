package com.ivoiceafrica.ivoiceafrica.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "service_types")
public class ServiceType {

	@Id
    @GenericGenerator(name = "type_id", strategy = "com.ivoiceafrica.ivoiceafrica.IdGenerator.ServiceTypeGenerator")
    @GeneratedValue(generator = "type_id", strategy = GenerationType.SEQUENCE)  
    @Column(name="type_id")
	private String typeId;
	
	@Column(name="type_name")
	private String typeName;
	 
	@Column(name="created_date")
	private String createdDate;
	
	@OneToMany(mappedBy = "serviceType",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<ServiceRendered> serviceRendereds;
	
	@OneToMany(mappedBy = "serviceType",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<ServiceTypePricing> serviceTypePricings;
	
	@OneToMany(mappedBy = "serviceType",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.REFRESH, CascadeType.DETACH})
	@JsonIgnore
    private Set<WorkOrder> workOrders;

	

	public ServiceType() {
	}



	public ServiceType(String typeId, String typeName, String createdDate, Set<ServiceRendered> serviceRendereds,
			Set<ServiceTypePricing> serviceTypePricings, Set<WorkOrder> workOrders) {
		this.typeId = typeId;
		this.typeName = typeName;
		this.createdDate = createdDate;
		this.serviceRendereds = serviceRendereds;
		this.serviceTypePricings = serviceTypePricings;
		this.workOrders = workOrders;
	}



	public String getTypeId() {
		return typeId;
	}



	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}



	public String getTypeName() {
		return typeName;
	}



	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}



	public String getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}



	public Set<ServiceRendered> getServiceRendereds() {
		return serviceRendereds;
	}



	public void setServiceRendereds(Set<ServiceRendered> serviceRendereds) {
		this.serviceRendereds = serviceRendereds;
	}



	public Set<ServiceTypePricing> getServiceTypePricings() {
		return serviceTypePricings;
	}



	public void setServiceTypePricings(Set<ServiceTypePricing> serviceTypePricings) {
		this.serviceTypePricings = serviceTypePricings;
	}



	public Set<WorkOrder> getWorkOrders() {
		return workOrders;
	}



	public void setWorkOrders(Set<WorkOrder> workOrders) {
		this.workOrders = workOrders;
	}
	

}
