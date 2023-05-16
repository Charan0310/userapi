package com.example.Util;

import java.util.Date;

import com.example.model.RoleEntity;
import com.example.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {
	 private static String secret = "This_is_secret";
	 private static long expiryDuration = 60 * 60;
public static String generateJWt(UserEntity user) {
	  long milliTime = System.currentTimeMillis();
      long expiryTime = milliTime + expiryDuration * 1000;
      
      Date issuedAt = new Date(milliTime);
      Date expiryAt = new Date(expiryTime);
      Integer id=Integer.valueOf(user.getId().toString())-1;
    
      Claims claims = Jwts.claims()
              .setIssuedAt(issuedAt)
              .setExpiration(expiryAt);
      claims.put("username",user.getuserName());
      claims.put("role", user.getRoles().getName());
      

      return Jwts.builder()
              .setClaims(claims)
              .signWith(SignatureAlgorithm.HS512, secret)
              .compact();
}
public Claims verify(String authorization) throws Exception {

    try {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
        return claims;
    } catch(Exception e) {
        throw new Exception();
    }
}}
