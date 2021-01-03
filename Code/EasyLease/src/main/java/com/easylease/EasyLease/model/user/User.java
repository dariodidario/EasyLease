
package com.easylease.EasyLease.model.user;

import java.io.Serializable;
import java.util.Date;

/**
 * This class models an object of type User within the system.
 *
 * @author Torino Francesco Maria
 * @since 0.1
 * @version 0.1
 */

public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  /** Constructor */
  public User() {

  }


  /**
   * Constructor for the User object.
   *
   * @param ID The ID who identify the User.
   * @param name The name of the User.
   * @param surname The surname of the User.
   * @param email The email of the User.
   * @param password The password of the User.
   */
  public User(String ID, String name, String surname, String email, String password) {
    this.id = ID;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
  }


  /**
   * Get the user's id.
   *
   * @return ID the user's identificator.
   */
  public String getId() {
    return id;
  }


  /**
   * Set the user's id.
   *
   * @param ID the user's identificator.
   */
  public void setId(String ID) {
    this.id = ID;
  }


  /**
   * Get the user's name.
   *
   * @return ID the user's name.
   */
  public String getName() {
    return name;
  }


  /**
   * Set the user's name.
   *
   * @param name the user's identificator.
   */
  public void setName(String name) {
    this.name = name;
  }


  /**
   * Get the user's surname.
   *
   * @return ID the user's surname.
   */
  public String getSurname() {
    return surname;
  }


  /**
   * Set the user's surname.
   *
   * @param surname the user's identificator.
   */
  public void setSurname(String surname) {
    this.surname = surname;
  }


  /**
   * Get the user's email.
   *
   * @return ID the user's email.
   */
  public String getEmail() {
    return email;
  }


  /**
   * Set the user's email.
   *
   * @param email the user's email.
   */
  public void setEmail(String email) {
    this.email = email;
  }


  /**
   * Get the user's password.
   *
   * @return ID the user's password.
   */
  public String getPassword() {
    return password;
  }


  /**
   * Set the user's password.
   *
   * @param password the user's password.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /** Rapresents the user's identificator. */
  protected String id;

  /** Rapresents the user's name. */
  protected String name;

  /** Rapresents the user's surname. */
  protected String surname;

  /** Rapresents the user's email. */
  protected String email;

  /** Rapresents the user's password. */
  protected String password;
}
