package com.christianpari.usersservice.service;

import com.christianpari.usersservice.dao.RoleDAO;
import com.christianpari.usersservice.dao.UserDAO;
import com.christianpari.usersservice.entity.Role;
import com.christianpari.usersservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class UserService {

  @Autowired
  private UserDAO userDAO;

  @Autowired
  private RoleDAO roleDAO;

  @Autowired
  private PasswordEncoder passwordEncoder;

  private final Random random = new Random();

  // PostConstruct on this method for mapping in Controller to prefill DB with data
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
    admin.setPassword(getEncodedPassword("admin"));
    admin.setUin(100000000);
    Set<Role> adminRoles = new HashSet<>();
    adminRoles.add(adminRole);
    adminRoles.add(managerRole);
    admin.setRole(adminRoles);
    userDAO.save(admin);

    User manager = new User();
    manager.setFirstName("manager");
    manager.setMiddleInitial("m");
    manager.setLastName("manager");
    manager.setUsername("mmm0000");
    manager.setPassword(getEncodedPassword("manager"));
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
    employee.setPassword(getEncodedPassword("employee"));
    employee.setUin(300000000);
    Set<Role> employeeRoles = new HashSet<>();
    employeeRoles.add(employeeRole);
    employee.setRole(employeeRoles);
    userDAO.save(employee);

  }

  // New User creation, defaults to role : employee
  public User registerNewUser(User user) {
    Role role = roleDAO.findById("employee").get();
    Set<Role> roles = new HashSet<>();
    roles.add(role);
    user.setRole(roles);
    user.setUsername(generateUsername(user));
    user.setPassword(getEncodedPassword(user.getPassword()));
    user.setUin(generateUin());
    return userDAO.save(user);
  }

  public String getEncodedPassword(String password) {
    return passwordEncoder.encode(password);
  }

  private String generateUsername(User user) {
    // creates a string like: "john a doe -> jad / john doe -> jxd"
    String starter = generateInitials(
      user.getFirstName(),
      user.getMiddleInitial(),
      user.getLastName()
    ).toLowerCase();

    String idNumber = generateIdNumber(); // generates 4 digit num 'xxxx'

    while (!isValidUsername(starter + idNumber)) { // querying db to see if username exists
      idNumber = generateIdNumber();
    }

    return starter + idNumber;
  }

  private String generateInitials(String first, String middle, String last) {
    return first.charAt(0) + (middle.equals("") ? "x" : middle) + last.charAt(0);
  }

  private String generateIdNumber() {
    // generates 4 digit string from 0 - 9999
    return String.format("%04d", random.nextInt(10000));
  }

  private boolean isValidUsername(String username) {
    // SQL Query is searching and then returning how many users have the username
    // if 1 then already taken, 0 means available
    return userDAO.usernameExists(username) == 0;
  }

  private int generateUin() {
    int uin = random.nextInt(1000000000); // generate 0 - 999999999
    while (!isValidUin(uin)) {
      uin = random.nextInt(1000000000);
    }
    return uin;
  }

  private boolean isValidUin(int uin) {
    // SQL Query is searching and then returning how many users have the uin
    // if 1 then already taken, 0 means available
    return userDAO.uinExists(uin) == 0;
  }

}
