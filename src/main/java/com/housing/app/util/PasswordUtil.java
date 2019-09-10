package com.housing.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PasswordUtil {


  private static BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public PasswordUtil(BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;

  }
  public static String encrypt(String password) {
    if (!StringUtils.isEmpty(password))
      return bCryptPasswordEncoder.encode(password);
    return null;

  }
}
