package com.lakshay.jobsportal.model;

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
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long companyId;

    @OneToMany(mappedBy = "company")
    private List<Employer> employers;

    @OneToMany(mappedBy = "company")
    private List<Vacancy> vacancies;

    private String name;
    private String address;
    private String country;
    private String state;
    private String pinCode;
    private String website;
    private Integer numberOfEmployee;




}