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
public class Admin {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long adminId;

    private  String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;


}