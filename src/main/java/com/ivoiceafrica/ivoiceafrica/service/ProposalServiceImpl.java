package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.auth.entity.User;
import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.ProposalStatus;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrder;
import com.ivoiceafrica.ivoiceafrica.entity.WorkOrdersDelivery;
import com.ivoiceafrica.ivoiceafrica.repository.ProposalRepository;

@Service
public class ProposalServiceImpl implements ProposalService {

	@Autowired
	ProposalRepository proposalRepository;
	
	@Override
	public List<Proposal> findAll() {
		return proposalRepository.findAll();
	}

	@Override
	public void save(Proposal theProposal) {
		proposalRepository.save(theProposal);
	}

	@Override
	public void deleteById(String theId) {
		proposalRepository.deleteById(theId);
	}
	
	@Override
	public Optional<Proposal> findById(String theId) {
		
		Optional<Proposal> result = proposalRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't find the Role
			throw new RuntimeException("Did not find record id - "+theId);
		}
	}

	@Override
	public List<Proposal> findProposalByUserAndProposalStatusOrderByCreatedDate(User user, ProposalStatus proposalStatus) {
		return proposalRepository.findProposalByUserAndProposalStatusOrderByCreatedDate(user, proposalStatus);
	}

	@Override
	public List<Proposal> findProposalByWorkOrderAndProposalStatusOrderByCreatedDate(WorkOrder workOrder,
			ProposalStatus proposalStatus) {
		return proposalRepository.findProposalByWorkOrderAndProposalStatusOrderByCreatedDate(workOrder, proposalStatus);
	}

	@Override
	public List<Proposal> findProposalByLimitAndStatusAndCurrentDate(int userId, int proposalStatusId, String currentDate, int limitNumber) {
		return proposalRepository.findProposalByLimitAndStatusAndCurrentDate(userId, proposalStatusId, currentDate, limitNumber);
	}

	@Override
	public List<Proposal> findProposalByUserAndStatusOrderByCreatedDesc(int userId, int propsalStatusId) {
		return proposalRepository.findProposalByUserAndStatusOrderByCreatedDesc(userId, propsalStatusId);
	}
	
}
