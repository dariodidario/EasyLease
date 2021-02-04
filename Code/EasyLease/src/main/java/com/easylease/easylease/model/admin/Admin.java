package com.easylease.easylease.model.admin;



import com.easylease.easylease.model.user.User;

/**
 * This class models an object of type Admin within the system.
 *
 * @since 0.1
 * @author Antonio Sarro
 * @version 0.1
 */
public class Admin extends User {


  private String recoveryEmail;

  public Admin() {

  }

  /**
   * Constructor for the Admin Object.
   *

   * @param recoveryEmail newProperty.
   */
  public Admin(String id, String name, String surname, String email, String recoveryEmail) {
    super(id, name, surname, email);
    this.recoveryEmail = recoveryEmail;
  }

  /**
   * Get the admins recoveryEmail.
   *
   * @return recoveryEmail of the admins.
   */
  public String getRecoveryEmail() {
    return recoveryEmail;
  }

  /**
   * Set the admins recoveryEmail.
   *
   * @param recoveryEmail the admins recoveryEmail.
   */
  public void setRecoveryEmail(String recoveryEmail) {
    this.recoveryEmail = recoveryEmail;
  }
}
