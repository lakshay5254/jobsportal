package com.lakshay.jobsportal.model;

import com.lakshay.jobsportal.model.enumerated.BranchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationDetail {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long educationId;
    private LocalDate tenthPassDate;
    private Integer tenthPercentage;
    private LocalDate twelvethPassDate;
    private Integer twelvethPercentage;
    private String ugDegree;
    private LocalDate ugDegreePassDate;
    private Integer ugDegreePercentage;
    private String pgDegree;
    private LocalDate pgDegreePassDate;
    private Integer pgDegreePercentage;
}