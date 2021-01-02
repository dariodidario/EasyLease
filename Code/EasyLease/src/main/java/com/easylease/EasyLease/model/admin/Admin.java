package com.easylease.EasyLease.model.admin;

import com.easylease.EasyLease.model.order.Order;

import java.util.Objects;

import com.easylease.EasyLease.model.user.User;

/**
 * This class models an object of type Admin within the system.
 *
 * @since 0.1
 * @author Antonio Sarro
 * @version 0.1
 */
public class Admin extends User {

  private String newProperty; //TODO: Trovare proprieta da aggiungere

  public Admin() {

  }

  /**
   * Constructor for the Admin Object.
   *
   * @param newProperty newProperty.
   */
  public Admin(String newProperty) {
    super();
    this.newProperty = newProperty;
  }

  /**
   * Returns the newProperty.
   *
   * @return newProperty.
   */
  public String getNewProperty() {
    return newProperty;
  }

  /**
   * Set the newProperty.
   *
   * @param newProperty newProperty.
   */
  public void setNewProperty(String newProperty) {
    this.newProperty = newProperty;
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
    //return this.id.equals(order.id);
    return true; //TODO: Cambiare quando avrò tutti i model
  }

  @Override
  public int hashCode() {
    return Objects.hash(newProperty); //TODO: Cambiare con id quando ho tutto
  }

  @Override
  public String toString() { //TODO: Aggiustare toString quando ho tutto
    return "Admin{"
        + "newProperty='" + newProperty + '\''
        + '}';
  }
}
