package com.lakshay.jobsportal.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	

	private final JwtProvider jwtProvider;
	public static final String AUTHORIZATION_HEADER = "Authorization";


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt=getJwtFromRequest(request);

		 if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
			 Authentication authentication = jwtProvider.getAuthentication(jwt,request);

			 SecurityContextHolder.getContext().setAuthentication(authentication);
		 }
	        filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {

		String bearerToken=request.getHeader("Authorization");
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		} 
		return bearerToken;
	}

}
