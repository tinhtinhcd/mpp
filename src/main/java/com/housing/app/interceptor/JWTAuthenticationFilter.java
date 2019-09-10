package com.housing.app.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  JwtTokenProvider tokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String jwt = tokenProvider.resolveToken(request);
    if (!StringUtils.hasText(jwt)) {
      filterChain.doFilter(request, response);
      return;
    }
    if (tokenProvider.validateApplicationUserToken(jwt)) {
      Authentication auth = tokenProvider.getAuthentication(jwt);
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    filterChain.doFilter(request, response);

  }

}
