package com.gnp.auth;

import javax.persistence.*;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @Column(nullable = false, unique = true)
  private String username;
  
  private String password;
  
  private String company;
  
  private String email;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getCompany() {
	  return company;
  }
  public void setCompany (String company) {
	  this.company = company;
  }
  public String getEmail() {
	  return email;
  }
  public void setEmail (String email) {
	  this.email = email;
  }
}