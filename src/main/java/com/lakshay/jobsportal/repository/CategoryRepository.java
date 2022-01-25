package com.lakshay.jobsportal.repository;

import com.lakshay.jobsportal.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}