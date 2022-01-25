package com.lakshay.jobsportal.repository;

import com.lakshay.jobsportal.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}