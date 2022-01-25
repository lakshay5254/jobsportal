package com.lakshay.jobsportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long vacancyId;


    @ManyToOne
    @JsonIgnoreProperties(value = "vacancies", allowSetters = true)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JsonIgnoreProperties(value = "vacancies", allowSetters = true)
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @OneToMany(mappedBy = "vacancy")
    private List<Request> requests;

    private String jobTitle;
    private String jobDescription;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Category> skillRequired;

    @OneToOne
    @JoinColumn(name = "education_id")
    private EducationDetail educationDetailRequired;

    private String designation;
    private Long salary;
    private Float experienceRequired;



}