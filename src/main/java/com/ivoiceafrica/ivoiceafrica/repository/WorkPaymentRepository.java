package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkPayments;

public interface WorkPaymentRepository extends JpaRepository<WorkPayments, Integer> {
	
	List<WorkPayments> findWorkPaymentsByClientIdOrderByEntryDateDesc(User user);
	
	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE work_payments w set w.payment_status_id = :paymentStatusId where w.work_id = :workId", nativeQuery = true)
	public int updateWorkPaymentStatus(@Param("paymentStatusId")int paymentStatusId, @Param("workId")String workId);
}
