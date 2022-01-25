package com.lakshay.jobsportal.repository;

import com.lakshay.jobsportal.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}