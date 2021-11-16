package com.christianpari.usersservice.service;

import com.christianpari.usersservice.dao.RoleDAO;
import com.christianpari.usersservice.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

  @Autowired
  private RoleDAO dao;

  public Role createNewRole(Role role) {
    return dao.save(role);
  }
}
