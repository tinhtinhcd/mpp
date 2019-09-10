package com.housing.app.service;

import com.housing.app.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

  UserDetails loadUserByUsername(String userName);

  User findUserByEmail(String email);

  void checkUserExisted (String email);

  User saveUser(User user);

}
