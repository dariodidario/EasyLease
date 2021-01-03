package com.easylease.EasyLease.model.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DBAdminDAOTest {

  DBAdminDAO dbAdminDAO = mock(DBAdminDAO.class);

  private static Admin admin;
  private static List<Admin> admins;

  @BeforeAll
  static void setUp() {
    admin = new Admin("AD12345", "Antonio", "Sarro", "test@gmail.com",
        "testing", "testing@gmail.com");
    admins = new ArrayList<>();
    admins.add(admin);
  }

  @Test
  void retrieveByCorrectId() {
    when(dbAdminDAO.retrieveById("AD12345")).thenReturn(admin);
    assertEquals(admin, dbAdminDAO.retrieveById("AD12345"));
  }

  @Test
  void retrieveByNonexistentId() {
    when(dbAdminDAO.retrieveById("AD1E345")).thenReturn(null);
    assertNull(dbAdminDAO.retrieveById("AD1E345"));
  }

  @Test
  void retrieveByWrongId() {
    when(dbAdminDAO.retrieveById("AP12345")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> dbAdminDAO.retrieveById("AP12345"));
  }

  @Test
  void retrieveByNullId() {
    when(dbAdminDAO.retrieveById(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> dbAdminDAO.retrieveById(null));
  }

  @Test
  void retrieveByEmptyId() {
    when(dbAdminDAO.retrieveById("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> dbAdminDAO.retrieveById(""));
  }

  @Test
  void retrieveByCorrectMail() {
    when(dbAdminDAO.retrieveByEmail("test@gmail.com")).thenReturn(admin);
    assertEquals(admin, dbAdminDAO.retrieveByEmail("test@gmail.com"));
  }

  @Test
  void retrieveByNonexistentEmail() {
    when(dbAdminDAO.retrieveByEmail("test2@gmail.com")).thenReturn(null);
    assertNull(dbAdminDAO.retrieveByEmail("test2@gmail.com"));
  }

  @Test
  void retrieveByWrongEmail() {
    when(dbAdminDAO.retrieveByEmail("testgmailcom")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> dbAdminDAO.retrieveByEmail("testgmailcom"));
  }

  @Test
  void retrieveByNullEmail() {
    when(dbAdminDAO.retrieveByEmail(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> dbAdminDAO.retrieveByEmail(null));
  }

  @Test
  void retrieveByEmptyEmail() {
    when(dbAdminDAO.retrieveByEmail("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> dbAdminDAO.retrieveByEmail(""));
  }

  @Test
  void retrieveByAll() {
    when(dbAdminDAO.retrieveAll()).thenReturn(admins);
    assertNotNull(admins);
  }
}