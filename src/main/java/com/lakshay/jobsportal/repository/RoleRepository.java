package com.lakshay.jobsportal.repository;


import com.lakshay.jobsportal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
