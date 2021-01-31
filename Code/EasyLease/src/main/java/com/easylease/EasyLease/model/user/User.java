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

  /** Constructor. */

  public User() {

  }


  /**
   * Constructor for the User object.
   *
   * @param idUser The ID who identify the User.
   * @param firstName The name of the User.
   * @param surname The surname of the User.
   * @param email The email of the User.
   */
  public User(String idUser, String firstName, String surname, String email) {
    this.idUser = idUser;
    this.firstName = firstName;
    this.surname = surname;
    this.email = email;
  }


  /**
   * Get the user's id.
   *
   * @return ID the user's identificator.
   */
  public String getIdUser() {
    return idUser;
  }


  /**
   * Set the user's id.
   *
   * @param idUser the user's identificator.
   */
  public void setIdUser(String idUser) {
    this.idUser = idUser;
  }


  /**
   * Get the user's name.
   *
   * @return ID the user's name.
   */
  public String getFirstName() {
    return firstName;
  }


  /**
   * Set the user's name.
   *
   * @param firstName the user's identificator.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
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
  protected String idUser;

  /** Rapresents the user's name. */
  protected String firstName;

  /** Rapresents the user's surname. */
  protected String surname;

  /** Rapresents the user's email. */
  protected String email;

}
