package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkTransactions;

public interface WorkTransactionRepository extends JpaRepository<WorkTransactions, Integer> {

	List<WorkTransactions> findWorkTransactionsByUserOrderByEntryDateDesc(User user);

	List<WorkTransactions> findWorkTransactionsByWorkOrderOrderByEntryDateDesc(WorkOrder workOrder);
}
