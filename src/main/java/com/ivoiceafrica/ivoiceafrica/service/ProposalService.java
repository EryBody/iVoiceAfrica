package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.ProposalStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;

public interface ProposalService {

	public List<Proposal> findAll();
	
	public Optional<Proposal> findById(String theId);

	public void save(Proposal theProposal);

	public void deleteById(String theId);
	
	List<Proposal> findProposalByUserAndProposalStatusOrderByCreatedDate(User user, ProposalStatus proposalStatus);
	
	List<Proposal> findProposalByWorkOrderAndProposalStatusOrderByCreatedDate(WorkOrder workOrder, ProposalStatus proposalStatus);
	
	public List<Proposal> findProposalByLimitAndStatusAndCurrentDate(int userId, int proposalStatusId, String currentDate, int limitNumber);

	public List<Proposal> findProposalByUserAndStatusOrderByCreatedDesc(int userId, int propsalStatusId);

}
