package com.lakshay.jobsportal.controller;



import com.lakshay.jobsportal.dto.AuthenticationResponse;
import com.lakshay.jobsportal.dto.LoginRequest;
import com.lakshay.jobsportal.dto.RefreshTokenRequest;
import com.lakshay.jobsportal.dto.SignupDto;
import com.lakshay.jobsportal.exception.Message;
import com.lakshay.jobsportal.service.AuthenticationService;
import com.lakshay.jobsportal.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<Message> signup(@Valid @RequestBody SignupDto signupDto){
        authenticationService.signupService(signupDto);
        return ResponseEntity.ok().body(new Message(HttpStatus.OK.value(),LocalDateTime.now(),"User registration success!!"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.loginService(loginRequest));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<AuthenticationResponse> refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<Message> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body(new Message(HttpStatus.OK.value(),LocalDateTime.now(),"Refresh Token Deleted Successfully!!"));
    }

}
