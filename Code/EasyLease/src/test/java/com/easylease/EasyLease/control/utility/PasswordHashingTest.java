package com.easylease.EasyLease.control.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test of the PasswordHashing utility class.
 *
 * @author Antonio Sarro
 * @version 0.1
 * @since 0.1
 */
class PasswordHashingTest {

  @Test
  void generatePasswordTest() {
    String password = PasswordHashing.generatePassword("testing", "SHA-1");
    assert password != null;
    assertEquals(40, password.length());
  }

  @Test
  void passwordAuthenticatorTest() {
    String password = PasswordHashing.generatePassword("testing", "SHA-1");
    assert password != null;
    assertTrue(PasswordHashing.passwordAuthenticator("testing", password, "SHA-1"));
  }

  @Test
  void wrongAlgorithmTest() {
    String password = PasswordHashing.generatePassword("testing", "Wrong");
    assertNull(password);
  }
}