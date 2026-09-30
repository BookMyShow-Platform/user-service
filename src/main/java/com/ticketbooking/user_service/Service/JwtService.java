package com.ticketbooking.user_service.Service;


import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import java.time.Instant;

import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
@Service
public class JwtService {
	private final JwtEncoder jwtEncoder;
	public JwtService(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}
	public String generateAccessToken(Long userId, String email, String role) {
		Instant now = Instant.now();
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("ticket-booking-user-service")
				.subject(String.valueOf(userId))
				.issuedAt(now)
				.expiresAt(now.plusSeconds(15*60))
				.claim("email", email)
				.claim("role", role)
				.build();
		JwsHeader header = JwsHeader.with(SignatureAlgorithm.RS256).build();
		return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
	}

}
