package com.lakshay.jobsportal.service;


import com.lakshay.jobsportal.model.Role;
import com.lakshay.jobsportal.model.Users;
import com.lakshay.jobsportal.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;


@AllArgsConstructor
@Service
public class MyUserDetailService implements UserDetailsService {
    private final UsersRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String a = ""
;        Optional<Users> userOptional = userRepository.findByUsername(username);
        Users user = userOptional
                .orElseThrow(()->new UsernameNotFoundException("Username is incorrect"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user.getRole().getName()));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return singletonList(new SimpleGrantedAuthority(role));
    }
}
