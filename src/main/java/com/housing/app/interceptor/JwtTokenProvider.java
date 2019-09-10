package com.housing.app.interceptor;

import com.housing.app.common.AppConstant;
import com.housing.app.exception.AccessTokenExpiredException;
import com.housing.app.service.UserService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

  private static final Logger logger = LoggerFactory.getLogger("application-info");

  private String apiApplicationUserSecret= "abc12345";

  @Autowired
  UserService userService;

  public String generateToken(String userName) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + AppConstant.EXPIRATION_TIME);

    return AppConstant.TOKEN_PREFIX + Jwts.builder()
        .setSubject(userName)
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, b64Encode(apiApplicationUserSecret))
        .compact();
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userService.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(b64Encode(apiApplicationUserSecret))
        .parseClaimsJws(token)
        .getBody();

    return claims.getSubject();
  }

  public boolean validateApplicationUserToken(String authToken) {
    return validateTokenWithSecret(authToken, apiApplicationUserSecret);
  }

  private String b64Encode(String secret) {
    return Base64.getEncoder().encodeToString(secret.getBytes());
  }

  public String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7, bearerToken.length());
    }
    return null;
  }
  private boolean validateTokenWithSecret(String authToken, String secret) {
    try {
      String base64Secret = b64Encode(secret);
      Jwts.parser().setSigningKey(base64Secret).parse(authToken);
      return true;
    } catch (SignatureException ex) {
      logger.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      logger.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      logger.error("Expired JWT token");
      throw new AccessTokenExpiredException();
    } catch (UnsupportedJwtException ex) {
      logger.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      logger.error("JWT claims string is empty.");
    }
    return false;
  }
}
