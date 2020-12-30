package com.easylease.EasyLease.model.user;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  public User() {

  }

  public User(String ID, String name, String surname, String email, String password) {
    this.id = ID;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public void setId(String ID) {
    this.id = ID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  protected String id;
  protected String name;
  protected String surname;
  protected String email;
  protected String password;


}
