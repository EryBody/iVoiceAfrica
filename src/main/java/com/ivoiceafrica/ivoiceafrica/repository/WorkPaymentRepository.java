package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPayments;

public interface WorkPaymentRepository extends JpaRepository<WorkPayments, Integer> {
	
	List<WorkPayments> findWorkPaymentsByClientIdOrderByEntryDateDesc(User user);
	
	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE work_payments w set w.payment_status_id = :paymentStatusId where w.work_id = :workId", nativeQuery = true)
	public int updateWorkPaymentStatus(@Param("paymentStatusId")int paymentStatusId, @Param("workId")String workId);
	
	@Query(value = "select * from work_payments w where w.client_id = :clientId AND w.work_id = :workId order by entry_date desc LIMIT 1", nativeQuery = true)
	public WorkPayments findWorkPaymentByWorkOrderIdAndClientId(@Param("clientId") int clientId, @Param("workId") String workId);
}
