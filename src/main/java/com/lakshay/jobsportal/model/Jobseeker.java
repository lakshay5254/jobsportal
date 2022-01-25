package com.lakshay.jobsportal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jobseeker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobseekerId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private  Users users;

    @OneToOne
    @JoinColumn(name = "education_id")
    private EducationDetail educationDetail;


    @OneToMany(mappedBy = "jobseeker")
    private List<Request> requests;

    @OneToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;

    private String name;
    private Integer age;
    private String address;
    private String state;
    private String country;
    private String pinCode;
    private LocalDate dateOfBith;
    private Float experience;
    private String skills;

}