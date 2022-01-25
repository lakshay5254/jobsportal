package com.lakshay.jobsportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lakshay.jobsportal.model.enumerated.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long resumeId;

    private String name;



}