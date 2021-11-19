package com.christianpari.usersservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

  @Id
  private Integer uin;
  private String username;
  private String password;
  private String firstName;
  private String middleInitial;
  private String lastName;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "USER_ROLE",
    joinColumns = {@JoinColumn(name = "USER_USERNAME")},
    inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
  private Set<Role> role;

}
