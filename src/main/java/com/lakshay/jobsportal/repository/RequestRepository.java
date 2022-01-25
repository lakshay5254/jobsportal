package com.lakshay.jobsportal.repository;

import com.lakshay.jobsportal.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}