package com.lakshay.jobsportal.dto;

import com.lakshay.jobsportal.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}