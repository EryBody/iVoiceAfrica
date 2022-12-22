package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.entity.ProposalStatus;

public interface ProposalStatusService {

	public List<ProposalStatus> findAll();
	
	public Optional<ProposalStatus> findById(int theId);

	public void save(ProposalStatus theProposal);

	public void deleteById(int theId);

}
