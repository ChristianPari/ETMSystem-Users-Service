package com.christianpari.usersservice.controller;

import com.christianpari.usersservice.entity.User;
import com.christianpari.usersservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

  @Autowired
  private UserService service;

  @PostConstruct
  public void initUsers() {
    service.initUsers();
  }

  @PostMapping({"/registerNewUser"})
  public User registerNewUser(@RequestBody User user) {
    return service.registerNewUser(user);
  }

  @GetMapping({"/forAdmin"})
  @PreAuthorize("hasRole('admin')")
  public String forAdmin() { return "For admin"; }

  @GetMapping({"/forManager"})
  @PreAuthorize("hasRole('manager')")
  public String forManager() { return "For manager"; }

  @GetMapping({"/forEmployee"})
  @PreAuthorize("hasRole('employee')")
  public String forEmployee() { return "For employee"; }

}
