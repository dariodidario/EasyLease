package com.easylease.EasyLease.model.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


public class UserTest {

  @Test
  void testOrderConstructorEmpty() {
    User user = new User();
    assertNotNull(user);
  }

  @Test
  void testOrderConstructorFull() {
    User user = new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password");
    assertNotNull(user);
  }

  @Test
  void testGetId() {
    User user = new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password");
    assertEquals("CL0001", user.getId());
  }

  @Test
  void testGetName() {
    User user = new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password");
    assertEquals("Francesco", user.getName());
  }

  @Test
  void testGetSurname() {
    User user = new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password");
    assertEquals("Torino", user.getSurname());
  }

  @Test
  void testGetEmail() {
    User user = new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password");
    assertEquals("francesco.torino1999@gmail.com", user.getEmail());
  }

  @Test
  void testGetPassword() {
    User user = new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password");
    assertEquals("password", user.getPassword());
  }

  @Test
  void testSetId() {
    User user = new User();
    user.setId("CL0001");
    assertEquals("CL0001", user.getId());
  }

  @Test
  void testSetName() {
    User user = new User();
    user.setName("Francesco");
    assertEquals("Francesco", user.getName());
  }

  @Test
  void testSetSurname() {
    User user = new User();
    user.setSurname("Torino");
    assertEquals("Torino", user.getSurname());
  }

  @Test
  void testSetEmail() {
    User user = new User();
    user.setEmail("francesco.torino1999@gmail.com");
    assertEquals("francesco.torino1999@gmail.com", user.getEmail());
  }

  @Test
  void testSetPassword() {
    User user = new User();
    user.setPassword("password");
    assertEquals("password", user.getPassword());
  }
}
