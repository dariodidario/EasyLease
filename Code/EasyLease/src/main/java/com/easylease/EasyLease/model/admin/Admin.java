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

  private String recoveryEmail;

  public Admin() {

  }

  /**
   * Constructor for the Admin Object.
   *
   * @param recoveryEmail newProperty.
   */
  public Admin(String id, String name, String surname, String email,
               String password, String recoveryEmail) {
    super(id, name, surname, email, password);
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

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Admin admin = (Admin) obj;
    return this.id.equals(admin.id);
  }

  @Override
  public String toString() {
    return "Admin{"
        + "recoveryEmail='" + recoveryEmail + '\''
        + ", id='" + id + '\''
        + ", name='" + name + '\''
        + ", surname='" + surname + '\''
        + ", email='" + email + '\''
        + ", password='" + password + '\''
        + '}';
  }
}
