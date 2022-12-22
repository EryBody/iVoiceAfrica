package com.ivoiceafrica.ivoiceafrica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoiceafrica.ivoiceafrica.entity.Industry;

public interface IndustryRepository extends JpaRepository<Industry, Integer> {

	Optional<Industry> findIndustryByIndustryId(int industry);

}
