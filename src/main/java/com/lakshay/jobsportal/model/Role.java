package com.lakshay.jobsportal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
public class Role {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Long id;


	private String name;






}
