package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.DurationType;
import com.ivoiceafrica.ivoiceafrica.repository.DurationTypeRepository;

@Service
public class DurationTypeServiceImpl implements DurationTypeService {

	@Autowired
	DurationTypeRepository durationTypeRepository;

	@Override
	public List<DurationType> findAll() {
		return durationTypeRepository.findAll();
	}

	@Override
	public Optional<DurationType> findById(int theId) {

		Optional<DurationType> result = durationTypeRepository.findById(theId);

		if (result.isPresent()) {
			return result;
		} else {
			throw new RuntimeException("Did not find Record - " + theId);
		}
	}

	@Override
	public void deleteById(int theId) {
		durationTypeRepository.deleteById(theId);

	}

	@Override
	public void save(DurationType theIndustry) {
		durationTypeRepository.save(theIndustry);
	}

	@Override
	public Optional<DurationType> findDurationTypeByDurationId(int durationId) {
		Optional<DurationType> result = durationTypeRepository.findDurationTypeByDurationId(durationId);

		if (result.isPresent()) {
			return result;
		} else {
			throw new RuntimeException("Did not find Record - " + durationId);
		}
	}

}
