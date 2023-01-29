package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;


public interface SRenderedService {

	public List<ServiceRendered> findAll();
	
	public Optional<ServiceRendered> findById(String theId);

	public void save(ServiceRendered theRole);

	public void deleteById(String theId);	
	
	Optional<ServiceRendered> findServiceRenderedByUser(User user);
	
	Optional<ServiceRendered> findFirstServiceRenderedByUser(User user);
	
	List<ServiceRendered> findServiceRenderedListByUser(User user);
	
	List<ServiceRendered> findServiceRenderedListByServiceType(ServiceType serviceType);
}
