package com.housing.app.repo;

import com.housing.app.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

  @Query("SELECT DISTINCT u FROM User u WHERE u.email= ?1 AND u.active = true")
  User findByEmail(String email);
}