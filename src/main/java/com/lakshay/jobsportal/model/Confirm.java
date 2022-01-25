package com.lakshay.jobsportal.model;

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
public class Confirm {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long confirmId;




}