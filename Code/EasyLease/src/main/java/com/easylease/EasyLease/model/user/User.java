package com.easylease.EasyLease.model.user;

import java.io.Serializable;
/**
 * This class models an object of type User within the system.
 *
 * @author Torino Francesco Maria
 * @since 0.1
 * @version 0.1
 */

public abstract class User implements Serializable {

  private static final long serialVersionUID = 1L;

  /** Constructor */

  public User() {

  }


  /**
   * Constructor for the User object.
   *
   * @param id_user The ID who identify the User.
   * @param first_name The name of the User.
   * @param surname The surname of the User.
   * @param email The email of the User.
   */
  public User(String id_user, String first_name, String surname, String email) {
    this.id_user = id_user;
    this.first_name = first_name;
    this.surname = surname;
    this.email = email;
  }


  /**
   * Get the user's id.
   *
   * @return ID the user's identificator.
   */
  public String getId_user() {
    return id_user;
  }


  /**
   * Set the user's id.
   *
   * @param id_user the user's identificator.
   */
  public void setId_user(String id_user) {
    this.id_user = id_user;
  }


  /**
   * Get the user's name.
   *
   * @return ID the user's name.
   */
  public String getFirst_name() {
    return first_name;
  }


  /**
   * Set the user's name.
   *
   * @param first_name the user's identificator.
   */
  public void setFirst_name(String first_name) {
    this.first_name = first_name;
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

  /** Rapresents the user's identificator. */
  protected String id_user;

  /** Rapresents the user's name. */
  protected String first_name;

  /** Rapresents the user's surname. */
  protected String surname;

  /** Rapresents the user's email. */
  protected String email;

}
