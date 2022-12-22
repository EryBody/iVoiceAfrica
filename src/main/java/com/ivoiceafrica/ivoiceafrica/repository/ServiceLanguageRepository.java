package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.entity.ServiceLanguages;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;


public interface ServiceLanguageRepository extends JpaRepository<ServiceLanguages, String> {

	Optional<ServiceLanguages> findFirstServiceLanguageByServiceRendered(ServiceRendered serviceRendered);
	
	List<ServiceLanguages> findServiceLanguageByServiceRendered(ServiceRendered serviceRendered);
	
	List<ServiceLanguages> findFirst3ServiceLanguageByServiceRendered(ServiceRendered serviceRendered);
	
}
