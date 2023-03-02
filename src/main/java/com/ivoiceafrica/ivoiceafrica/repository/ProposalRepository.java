package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.ProposalStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;

public interface ProposalRepository extends JpaRepository<Proposal, String> {

	List<Proposal> findProposalByUserAndProposalStatusOrderByCreatedDate(User user, ProposalStatus proposalStatus);
	
	List<Proposal> findProposalByUserAndWorkOrderOrderByCreatedDateDesc(User user, WorkOrder workOrder);
	
	List<Proposal> findProposalByWorkOrderAndProposalStatusOrderByCreatedDate(WorkOrder workOrder, ProposalStatus proposalStatus);
	
	List<Proposal> findProposalByUserOrderByCreatedDate(User user);
	
	@Query(value = "select * from proposals p where p.user_id = :userId AND p.proposal_status_id = :propsalStatusId AND CAST(p.created_date AS Date) = :createdDate LIMIT :limitNumber", nativeQuery = true)
	public List<Proposal> findProposalByLimitAndStatusAndCurrentDate(@Param("userId")int userId, @Param("propsalStatusId")int propsalStatusId, @Param("createdDate")String createdDate, @Param("limitNumber")int limitNumber);

	@Query(value = "select * from proposals p where p.user_id = :userId AND p.proposal_status_id = :propsalStatusId order by created_date desc", nativeQuery = true)
	public List<Proposal> findProposalByUserAndStatusOrderByCreatedDesc(@Param("userId")int userId, @Param("propsalStatusId")int propsalStatusId);

	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE proposals p set p.amount = :amount where p.proposal_id = :proposalId", nativeQuery = true)
	public int updateProposalAmount(@Param("amount")double proposedAmount, @Param("proposalId")String proposalId);
	
	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE proposals p set p.proposal_status_id = :proposalStatusId where p.proposal_id = :proposalId", nativeQuery = true)
	public int updateProposalByProposalId(@Param("proposalStatusId")int proposalStatusId, @Param("proposalId")String proposalId);
	
	@Transactional
	@Modifying(clearAutomatically = true,flushAutomatically = true)
	@Query(value = "UPDATE proposals p set p.proposal_status_id = :proposalStatusId where p.work_id = :workId", nativeQuery = true)
	public int updateProposalByWorkOrderId(@Param("proposalStatusId")int proposalStatusId, @Param("workId")String workId);
	
}
