package com.ivoiceafrica.ivoiceafrica.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer>  {

	public Language findLanguageByLanguageId(int languageId);
}
