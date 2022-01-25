package com.lakshay.jobsportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lakshay.jobsportal.model.enumerated.BranchType;
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
public class EducationBranch {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long branchId;

    private String branchName;


    @ManyToOne
    @JsonIgnoreProperties(value = "children", allowSetters = true)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private Set<Category> children = new HashSet<>();


    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private BranchType type;




}