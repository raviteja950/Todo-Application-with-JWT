package com.todo.todoappmanagement.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	private final String SECRET_KEY = "5e7e4eaffab322c1318fe23172c859880e9c9fef22b3bff92c16c96ffb710e5f";

	public String extractUserEmail(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	public <T> T extractClaims(String token, Function<Claims, T> claimsResover) {
		final Claims claims = extractAllClaims(token);
		return claimsResover.apply(claims);

	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignatureKey()).build().parseClaimsJws(token).getBody();
	}

	private Key getSignatureKey() {
		byte[] keyBites = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBites);
	}

	public String generateToken(Map<String, Object> extracCliams, UserDetails userDetails) {
		return Jwts.builder().setClaims(extracCliams).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignatureKey(), SignatureAlgorithm.HS256).compact();
	}

	public String generateTokenByUserName(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {

		final String userName = extractUserEmail(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
}
