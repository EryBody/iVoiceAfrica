package com.ivoiceafrica.ivoiceafrica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ivoiceafrica.ivoiceafrica.entity.WorkEscrowTransaction;

public interface WorkEscrowTransactionRepository extends JpaRepository<WorkEscrowTransaction, Integer> {
	
	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE work_escrow_transactions w set w.is_released = :isReleased AND w.released_date = :releasedDate where w.work_id = :workId", nativeQuery = true)
	public int updateWorkEscrowIsReleasedAndDate(@Param("isReleased")boolean isReleased, @Param("releasedDate")String releasedDate, @Param("workId")String workId);
	
}
