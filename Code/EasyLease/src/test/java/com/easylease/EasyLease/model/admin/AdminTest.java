package com.easylease.EasyLease.model.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Test of the Admin model.
 *
 * @author Antonio Sarro
 * @version 0.1
 * @since 0.1
 */
class AdminTest {

  @Test
  void testAdminConstructorEmpty() {
    Admin admin = new Admin();
    assertNotNull(admin);
  }

  @Test
  void testOrderConstructorFull() {
    Admin admin = new Admin("1234567", "Antonio", "Sarro",
        "test@gmail.com", "recovery@gmail.com");
    assertNotNull(admin);
  }

  @Test
  void testAdminGetRecoveryEmail() {
    Admin admin = new Admin("1234567", "Antonio", "Sarro",
        "test@gmail.com", "recovery@gmail.com");
    assertEquals("recovery@gmail.com", admin.getRecoveryEmail());
  }

  @Test
  void testAdminSetRecoveryEmail() {
    Admin admin = new Admin("1234567", "Antonio", "Sarro",
        "test@gmail.com", "recovery@gmail.com");
    admin.setRecoveryEmail("recovery2@gmail.com");
    assertEquals("recovery2@gmail.com", admin.getRecoveryEmail());
  }

  @Test
  void testAdminEquals() {
    Admin admin = new Admin("1234567", "Antonio", "Sarro",
        "test@gmail.com", "recovery@gmail.com");
    assertEquals(admin, admin);
  }

  @Test
  void testAdminNotEquals() {
    Admin admin = new Admin("1234567", "Antonio", "Sarro",
        "test@gmail.com", "recovery@gmail.com");
    assertNotEquals(admin, new Admin());
  }

  @Test
  void testAdminEqualsNull() {
    Admin admin = new Admin("1234567", "Antonio", "Sarro",
        "test@gmail.com", "recovery@gmail.com");
    assertFalse(admin.equals(null));
  }

  @Test
  void testAdminToString() {
    Admin admin = new Admin("1234567", "Antonio", "Sarro",
        "test@gmail.com", "recovery@gmail.com");
    String toString = "Admin{"
        + "recoveryEmail='" + admin.getRecoveryEmail() + '\''
        + ", id='" + admin.getId() + '\''
        + ", name='" + admin.getName() + '\''
        + ", surname='" + admin.getSurname() + '\''
        + ", email='" + admin.getEmail() + '\''
        + '}';
    assertEquals(admin.toString(), toString);
  }
}