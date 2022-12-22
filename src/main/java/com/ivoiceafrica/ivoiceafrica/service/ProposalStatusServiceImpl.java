package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.Proposal;
import com.ivoiceafrica.ivoiceafrica.entity.ProposalStatus;
import com.ivoiceafrica.ivoiceafrica.repository.ProposalRepository;
import com.ivoiceafrica.ivoiceafrica.repository.ProposalStatusRepository;

@Service
public class ProposalStatusServiceImpl implements ProposalStatusService {

	@Autowired
	ProposalStatusRepository proposalStatusRepository;
	
	@Override
	public List<ProposalStatus> findAll() {
		return proposalStatusRepository.findAll();
	}

	@Override
	public void save(ProposalStatus theStatus) {
		proposalStatusRepository.save(theStatus);
	}

	@Override
	public void deleteById(int theId) {
		proposalStatusRepository.deleteById(theId);
	}
	
	@Override
	public Optional<ProposalStatus> findById(int theId) {
		
		Optional<ProposalStatus> result = proposalStatusRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			//we didn't find the Role
			throw new RuntimeException("Did not find record id - "+theId);
		}
	}
	
}
