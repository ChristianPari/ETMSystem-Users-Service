package com.christianpari.usersservice.dao;

import com.christianpari.usersservice.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDAO extends CrudRepository<User, Integer> {

  @Query(value = "FROM #{#entityName} WHERE username = :username")
  User findByUsername(@Param("username") String username);

  @Query(value = "SELECT COUNT(*) FROM #{#entityName} WHERE username = :username")
  int usernameExists(@Param("username") String username);

  @Query(value = "SELECT COUNT(*) FROM #{#entityName} WHERE uin = :uin")
  int uinExists(@Param("uin") Integer uin);

}
