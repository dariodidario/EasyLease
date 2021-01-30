package com.easylease.EasyLease.model.admin;



import com.easylease.EasyLease.model.user.User;

/**
 * This class models an object of type Admin within the system.
 *
 * @since 0.1
 * @author Antonio Sarro
 * @version 0.1
 */
public class Admin extends User {


  private String recovery_email;

  public Admin() {

  }

  /**
   * Constructor for the Admin Object.
   *

   * @param recovery_email newProperty.
   */
  public Admin(String id, String name, String surname, String email, String recovery_email) {
    super(id, name, surname, email);
    this.recovery_email = recovery_email;
  }

  /**
   * Get the admins recoveryEmail.
   *
   * @return recoveryEmail of the admins.
   */
  public String getRecovery_email() {
    return recovery_email;
  }

  /**
   * Set the admins recoveryEmail.
   *
   * @param recovery_email the admins recoveryEmail.
   */
  public void setRecovery_email(String recovery_email) {
    this.recovery_email = recovery_email;
  }
}
