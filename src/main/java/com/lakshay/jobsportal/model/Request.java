package com.lakshay.jobsportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long requestId;

    @ManyToOne
    @JsonIgnoreProperties(value = "requests", allowSetters = true)
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    @ManyToOne
    @JsonIgnoreProperties(value = "requests", allowSetters = true)
    @JoinColumn(name = "jobseeker_id")
    private  Jobseeker jobseeker;

    private Boolean confirm;

}