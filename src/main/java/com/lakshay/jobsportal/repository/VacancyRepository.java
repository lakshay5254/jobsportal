package com.lakshay.jobsportal.repository;

import com.lakshay.jobsportal.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}