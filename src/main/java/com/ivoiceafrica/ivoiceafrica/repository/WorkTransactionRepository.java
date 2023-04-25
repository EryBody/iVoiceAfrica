package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkTransactions;

public interface WorkTransactionRepository extends JpaRepository<WorkTransactions, Integer> {

	List<WorkTransactions> findWorkTransactionsByUserOrderByEntryDateDesc(User user);

	List<WorkTransactions> findWorkTransactionsByWorkOrderOrderByEntryDateDesc(WorkOrder workOrder);
	
	List<WorkTransactions> findWorkTransactionsByWorkOrderAndIsInFlowOrderByEntryDateDesc(WorkOrder workOrder, Boolean isInFlow);
	
	List<WorkTransactions> findWorkTransactionsByUserAndIsInFlowOrderByEntryDateDesc(User user, Boolean isInFlow);
	
	@Query(value = "select * from work_transactions w where w.user_id = :userId order by entry_date desc LIMIT 1", nativeQuery = true)
    Optional<WorkTransactions> findLastWorkTransactions(@Param("userId") int userId);
}
