package com.ivoiceafrica.ivoiceafrica.service;

import java.util.List;
import java.util.Optional;

import com.ivoiceafrica.ivoiceafrica.entity.Language;


public interface LanguageService {

	public List<Language> findAll();

	public Optional<Language> findById(int theId);

	public void save(Language theLanguage);

	public void deleteById(int theId);
	
	public Language findLanguageByLanguageId(int theLanguageId);
	
}
