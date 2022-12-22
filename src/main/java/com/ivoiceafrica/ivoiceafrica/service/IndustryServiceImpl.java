package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.Industry;
import com.ivoiceafrica.ivoiceafrica.repository.IndustryRepository;

@Service
public class IndustryServiceImpl implements IndustryService {

	@Autowired
	IndustryRepository industryRepository;

	@Override
	public List<Industry> findAll() {
		return industryRepository.findAll();
	}

	@Override
	public Optional<Industry> findById(int theId) {
		
		Optional<Industry> result = industryRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			throw new RuntimeException("Did not find Record - "+theId);
		}
	}

	@Override
	public void deleteById(int theId) {
		industryRepository.deleteById(theId);
		
	}

	@Override
	public void save(Industry theIndustry) {
		industryRepository.save(theIndustry);
	}

	@Override
	public Optional<Industry> findIndustryByIndustryId(int industry) {
		
		Optional<Industry> result = industryRepository.findIndustryByIndustryId(industry);
		
		if(result.isPresent()) {
			return result;
		}else {
			throw new RuntimeException("Did not find Record - "+industry);
		}
	}


	
}
