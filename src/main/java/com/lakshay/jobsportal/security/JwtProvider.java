package com.lakshay.jobsportal.security;


import com.lakshay.jobsportal.exception.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

import static java.util.Date.from;


@Service
@RequiredArgsConstructor

public class JwtProvider {
	
	 private KeyStore keyStore;
	private static final String AUTHORITIES_KEY = "auth";
	private final UserDetailsService userDetailsService;
	@Value("${jwt.expiration.time}")
	private Long jwtExpirationInMillis;
	    
	    

	    @PostConstruct
	    public void init() {
	        try {
	            keyStore = KeyStore.getInstance("JKS");
	            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
	            keyStore.load(resourceAsStream, "secret".toCharArray());
	        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {  
	            throw new BusinessException("701","Exception occurred while loading keystore");
	        }

	    }


	public String generateToken(Authentication authentication) {
		User principle=(User)authentication.getPrincipal();
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		return Jwts.builder()
				.setSubject(principle.getUsername())
				.claim(AUTHORITIES_KEY, authorities)
				.setIssuedAt(from(Instant.now()))
				.setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
				.signWith(getPrivateKey())
				.compact();
	}

	public String generateTokenWithUserName(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(from(Instant.now()))
				.signWith(getPrivateKey())
				.setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
				.compact();
	}
	
	private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new BusinessException("702","Exception occured while retrieving public key from keystore");
        }
    }


	public boolean validateToken(String jwt) {
		Jwts.parserBuilder().setSigningKey(getPublickey()).build().parseClaimsJws(jwt);
        return true;
	}
    


	private PublicKey getPublickey() {
        try {
            return keyStore.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException e) {
            throw new BusinessException("703","Exception occured while " +
                    "retrieving public key from keystore");
        }
    }
	
	/*public String getUsernameFromJwt(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(getPublickey())
				.build()
				.parseClaimsJws(token)
				.getBody();
        return claims.getSubject();
    }*/
	public Authentication getAuthentication(String token,HttpServletRequest request) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(getPublickey())
				.build()
				.parseClaimsJws(token)
				.getBody();

		/*Collection<? extends GrantedAuthority> authorities =
				Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());*/

		UserDetails principal = userDetailsService.loadUserByUsername(claims.getSubject());
		UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(principal,token, principal.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		return authentication;
	}

	public Long getJwtExpirationInMillis() {
		return jwtExpirationInMillis;
	}
}
