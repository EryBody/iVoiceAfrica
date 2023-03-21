package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;


public interface ServiceRenderedRepository extends JpaRepository<ServiceRendered, String> {

	Optional<ServiceRendered> findServiceRenderedByUser(User user);
	
	Optional<ServiceRendered> findFirstServiceRenderedByUser(User user);
	
	List<ServiceRendered> findServiceRenderedListByUser(User user);
	
	List<ServiceRendered> findServiceRenderedListByServiceType(ServiceType serviceType);
	
	List<ServiceRendered> findServiceRenderedListByUserAndServiceType(User user, ServiceType type);
}
