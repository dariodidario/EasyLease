package com.easylease.EasyLease.model.client;

import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class DBClientDAOTest {
  Date bdate = new Date(1998, 9, 9);
  Client cliente = new Client("CLABC12", "Mario", "Rossi", "m.rossi@gmail.com",
      "mrossi", "Avellino", bdate, "Uomo", "Avellino", "83020", "Contrada Petrulli 3");

  DBClientDAO dbClientDao = Mockito.mock(DBClientDAO.class);

  @Test
  public void retrieveById_CorrectIdGiven_ExpectedTrue() {
    Mockito.when(dbClientDao.retrieveById("CLABC12")).thenReturn(cliente);
    assertEquals(cliente, dbClientDao.retrieveById("CLABC12"));
  }

  @Test
  public void retrieveById_UnexistingIdGiven_ExpectedNull() {
    Mockito.when(dbClientDao.retrieveById("CLABC22")).thenReturn(null);
    assertNull(dbClientDao.retrieveById("CLABC22"));
  }

  @Test
  public void retrieveById_WrongIdGiven_ExpectedException() {
    Mockito.when(dbClientDao.retrieveById("ADABC22")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbClientDao.retrieveById("ADABC22");
    });
  }

  @Test
  public void retrieveById_NullIdGiven_ExpectedException() {
    Mockito.when(dbClientDao.retrieveById(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbClientDao.retrieveById(null);
    });
  }

  @Test
  public void retrieveById_EmptyIdGiven_ExpectedException() {
    Mockito.when(dbClientDao.retrieveById("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbClientDao.retrieveById("");
    });
  }

  @Test
  public void retrieveByEmail_CorrectEmailGiven_ExpectedTrue() {
    Mockito.when(dbClientDao.retrieveByEmail("m.rossi@gmail.com")).thenReturn(cliente);
    assertEquals(cliente, dbClientDao.retrieveByEmail("m.rossi@gmail.com"));
  }

  @Test
  public void retrieveByEmail_UnexistingEmailGiven_ExpectedNull() {
    Mockito.when(dbClientDao.retrieveByEmail("r.bianchi@gmail.com")).thenReturn(null);
    assertNull(dbClientDao.retrieveByEmail("r.bianchi@gmail.com"));
  }

  @Test
  public void retrieveByEmail_EmptyEmailGiven_ExpectedException() {
    Mockito.when(dbClientDao.retrieveByEmail("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbClientDao.retrieveByEmail("");
    });
  }

  @Test
  public void retrieveByEmail_NullEmailGiven_ExpectedException() {
    Mockito.when(dbClientDao.retrieveByEmail(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbClientDao.retrieveByEmail(null);
    });
  }

  @Test
  public void insert_CorrectClientGiven_ExpectedTrue() {
    Mockito.doNothing().when(dbClientDao).insert(cliente);
    dbClientDao.insert(cliente);
    Mockito.verify(dbClientDao).insert(cliente);
    Mockito.verifyNoMoreInteractions(dbClientDao);
  }

  @Test
  public void insert_NullClientGiven_ExpectedException() {
    Client nullClient = null;
    Mockito.doThrow(IllegalArgumentException.class).when(dbClientDao).insert(null);
    assertThrows(IllegalArgumentException.class, () -> {
      dbClientDao.insert(nullClient);
    });
  }

  @Test
  public void update_CorrectClientGiven_ExpectedTrue() {
    Mockito.doNothing().when(dbClientDao).update(cliente);
    dbClientDao.update(cliente);
    Mockito.verify(dbClientDao).update(cliente);
    Mockito.verifyNoMoreInteractions(dbClientDao);
  }

  @Test
  public void update_NullClientGiven_ExpectedException() {
    Client nullClient = null;
    Mockito.doThrow(IllegalArgumentException.class).when(dbClientDao).update(null);
    assertThrows(IllegalArgumentException.class, () -> {
      dbClientDao.update(nullClient);
    });
  }

  @Test
  public void delete_CorrectClientGiven_ExpectedException() {
    Mockito.doNothing().when(dbClientDao).delete(cliente);
    dbClientDao.delete(cliente);
    Mockito.verify(dbClientDao).delete(cliente);
    Mockito.verifyNoMoreInteractions(dbClientDao);
  }

  @Test
  public void delete_NullClientGiven_ExpectedException() {
    Client nullClient = null;
    Mockito.doThrow(IllegalArgumentException.class).when(dbClientDao).delete(null);
    assertThrows(IllegalArgumentException.class, () -> {
      dbClientDao.delete(nullClient);
    });
  }
}