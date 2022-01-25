package com.lakshay.jobsportal.repository;

import com.lakshay.jobsportal.model.Jobseeker;
import com.lakshay.jobsportal.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobseekerRepository extends JpaRepository<Jobseeker, Users> {
}