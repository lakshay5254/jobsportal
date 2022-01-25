package com.lakshay.jobsportal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employerId;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @OneToMany(mappedBy = "employer")
    private List<Vacancy> vacancies;

    @ManyToOne
    @JsonIgnoreProperties(value = "employers", allowSetters = true)
    @JoinColumn(name = "company_id")
    private Company company;

    private String name;
    private String designation;

}
