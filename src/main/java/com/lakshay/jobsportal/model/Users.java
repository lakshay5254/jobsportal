package com.lakshay.jobsportal.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long userId;
    private String username;
    @Column(nullable = false,unique = true)
    private String mobile;
    private String email;
    private String password;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

}