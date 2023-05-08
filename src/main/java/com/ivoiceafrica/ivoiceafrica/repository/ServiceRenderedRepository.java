package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceRendered;
import com.ivoiceafrica.ivoiceafrica.entity.ServiceType;



public interface ServiceRenderedRepository extends JpaRepository<ServiceRendered, String> {

	Optional<ServiceRendered> findServiceRenderedByUser(User user);
	
	Optional<ServiceRendered> findFirstServiceRenderedByUser(User user);
	
	Optional<ServiceRendered> findServiceRenderedByUserAndServiceType(User user, ServiceType type);
	
	List<ServiceRendered> findServiceRenderedListByUser(User user);
	
	List<ServiceRendered> findServiceRenderedListByServiceType(ServiceType serviceType);
	
	List<ServiceRendered> findServiceRenderedListByUserAndServiceType(User user, ServiceType type);
	
	@Query("SELECT DISTINCT sr.renderId AS renderId, sr.user.userId AS userId , sr.serviceType.typeId AS typeId, sr.experienceInYears AS experienceInYears, sr.otherInfo AS servicePortfolioLink, "
			+ "sp.pricingId AS pricingId, sp.pricingType.pricingType AS pricingType, fp.fpricingId AS freelancerPricingId, fp.minPrice AS freelancerMinPrice, fp.maxPrice AS freelancerMaxPrice "
			+ "FROM ServiceRendered sr INNER JOIN sr.serviceType st "
			+ "ON sr.serviceType.typeId = st.typeId INNER JOIN st.serviceTypePricings sp "
			+ "ON sp.serviceType.typeId = st.typeId INNER JOIN sp.freelancerServicePricings fp "
			+ "ON fp.serviceTypePricing.pricingId = sp.pricingId "
			+ "WHERE sp.serviceType.typeId = :serviceTypeId AND fp.minPrice >= :freelancerMinPrice AND fp.maxPrice <= :freelancerMaxPrice")
	List<Map<String,Object>> findFreelancerDetailsForWorks(@Param("serviceTypeId")String serviceTypeId,@Param("freelancerMinPrice")double freelancerMinPrice,@Param("freelancerMaxPrice")double freelancerMaxPrice);

}
