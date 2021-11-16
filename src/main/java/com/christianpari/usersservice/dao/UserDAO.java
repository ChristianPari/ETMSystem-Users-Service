package com.christianpari.usersservice.dao;

import com.christianpari.usersservice.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer> {
}
