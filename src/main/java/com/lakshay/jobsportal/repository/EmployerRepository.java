package com.lakshay.jobsportal.repository;

import com.lakshay.jobsportal.model.Employer;
import com.lakshay.jobsportal.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Users> {
}