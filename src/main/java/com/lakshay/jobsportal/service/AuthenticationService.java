package com.lakshay.jobsportal.service;



import com.lakshay.jobsportal.dto.AuthenticationResponse;
import com.lakshay.jobsportal.dto.LoginRequest;
import com.lakshay.jobsportal.dto.RefreshTokenRequest;
import com.lakshay.jobsportal.dto.SignupDto;
import com.lakshay.jobsportal.exception.BusinessException;
import com.lakshay.jobsportal.model.Role;
import com.lakshay.jobsportal.model.Users;
import com.lakshay.jobsportal.repository.RoleRepository;
import com.lakshay.jobsportal.repository.UsersRepository;
import com.lakshay.jobsportal.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AuthenticationService {
    private final UsersRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepository;
    private final RefreshTokenService refreshTokenService;



    public void signupService( SignupDto signupDto) {
        if(userRepository.findByUsername(signupDto.getUsername()).isPresent()){
            throw new BusinessException("601","Username already present");
        }else  if (userRepository.findByMobile(signupDto.getMobile()).isPresent()) {
            throw new BusinessException("601","Mobile already present");
        }else if (userRepository.findByEmail(signupDto.getEmail()).isPresent()){
            throw new BusinessException("601","email already present");
        }else {
            try {

                Users newUser = Users.builder()
                        .username(signupDto.getUsername())
                        .email(signupDto.getEmail())
                        .mobile(signupDto.getMobile().trim())
                        .password(passwordEncoder.encode(signupDto.getPassword()))
                        .role(roleRepository.findById(1).get())
                        .build();
                userRepository.save(newUser);
                log.info("signup success");
            } catch (Exception e) {
                throw new BusinessException("602", "Unexpected error while trying to save user");
            }
        }
    }

    public AuthenticationResponse loginService(LoginRequest loginRequest) {
        Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        log.info("login success");
        String token= jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }


    public Users getCurrentUser(){
        String name= SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(name).orElseThrow(()->new UsernameNotFoundException("username not found"));
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }


}
