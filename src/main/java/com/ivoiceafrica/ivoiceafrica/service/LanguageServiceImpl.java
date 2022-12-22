package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoiceafrica.ivoiceafrica.entity.Language;
import com.ivoiceafrica.ivoiceafrica.repository.LanguageRepository;

@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	LanguageRepository languageRepository;

	@Override
	public List<Language> findAll() {
		return languageRepository.findAll();
	}

	@Override
	public Optional<Language> findById(int theId) {
		
		Optional<Language> result = languageRepository.findById(theId);
		
		if(result.isPresent()) {
			return result;
		}else {
			throw new RuntimeException("Did not find Record - "+theId);
		}
	}

	@Override
	public void deleteById(int theId) {
		languageRepository.deleteById(theId);
		
	}

	@Override
	public void save(Language theLanguage) {
		languageRepository.save(theLanguage);
	}

	@Override
	public Language findLanguageByLanguageId(int theLanguageId) {
		Language language = languageRepository.findLanguageByLanguageId(theLanguageId);
		return language;
	}
	
	
}
