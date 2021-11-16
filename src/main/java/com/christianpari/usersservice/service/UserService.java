package com.christianpari.usersservice.service;

import com.christianpari.usersservice.dao.RoleDAO;
import com.christianpari.usersservice.dao.UserDAO;
import com.christianpari.usersservice.entity.Role;
import com.christianpari.usersservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

  @Autowired
  private UserDAO userDAO;

  @Autowired
  private RoleDAO roleDAO;

  public User registerNewUser(User user) {
    return userDAO.save(user);
  }

  public void initUsers() {
    Role adminRole = new Role();
    adminRole.setRoleName("admin");
    adminRole.setRoleDescription("Admin Role");
    roleDAO.save(adminRole);

    Role managerRole = new Role();
    managerRole.setRoleName("manager");
    managerRole.setRoleDescription("Manager Role");
    roleDAO.save(managerRole);

    Role employeeRole = new Role();
    employeeRole.setRoleName("employee");
    employeeRole.setRoleDescription("Employee Role");
    roleDAO.save(employeeRole);

    User admin = new User();
    admin.setFirstName("admin");
    admin.setMiddleInitial("a");
    admin.setLastName("admin");
    admin.setUsername("aaa0000");
    admin.setPassword("admin");
    admin.setUin(100000000);
    Set<Role> adminRoles = new HashSet<>();
    adminRoles.add(adminRole);
    admin.setRole(adminRoles);
    userDAO.save(admin);

    User manager = new User();
    manager.setFirstName("manager");
    manager.setMiddleInitial("m");
    manager.setLastName("manager");
    manager.setUsername("mmm0000");
    manager.setPassword("manager");
    manager.setUin(200000000);
    Set<Role> managerRoles = new HashSet<>();
    managerRoles.add(managerRole);
    managerRoles.add(employeeRole);
    manager.setRole(managerRoles);
    userDAO.save(manager);

    User employee = new User();
    employee.setFirstName("employee");
    employee.setMiddleInitial("e");
    employee.setLastName("employee");
    employee.setUsername("eee0000");
    employee.setPassword("employee");
    employee.setUin(300000000);
    Set<Role> employeeRoles = new HashSet<>();
    employeeRoles.add(employeeRole);
    employee.setRole(employeeRoles);
    userDAO.save(employee);

  }

}
