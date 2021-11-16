package com.christianpari.usersservice.dao;

import com.christianpari.usersservice.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends CrudRepository<Role, String> {

}
