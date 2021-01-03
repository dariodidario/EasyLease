package com.easylease.EasyLease.model.advisor;

import static java.util.Calendar.JANUARY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

class DBAdvisorDAOTest {
  Advisor advisor = new Advisor("AD12DD2", "Marco", "Montemagno", "m.marco@gmail.com",
      "password", new GregorianCalendar(2021, JANUARY, 1).getTime());
  DBAdvisorDAO dbAdvisorDAO = mock(DBAdvisorDAO.class);

  @Test
  public void retrieveById_CorrectIdGiven_ExpectedTrue() {
    when(dbAdvisorDAO.retrieveById("AD12DD2")).thenReturn(advisor);
    assertEquals(advisor, dbAdvisorDAO.retrieveById("AD12DD2"));
  }

  @Test
  public void retrieveById_UnexistngIdGiven_ExceptedNull() {
    when(dbAdvisorDAO.retrieveById("AD00000")).thenReturn(null);
    assertNull(dbAdvisorDAO.retrieveById("AD00000"));
  }

  @Test
  public void retrieveById_WrongIdGiven_ExceptedNull() {
    when(dbAdvisorDAO.retrieveById("CL10AB1")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> dbAdvisorDAO.retrieveById("CL10AB1"));
  }

  @Test
  public void retrieveById_NullIdGiven_ExceptedNull() {
    when(dbAdvisorDAO.retrieveById(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> dbAdvisorDAO.retrieveById(null));
  }

  @Test
  public void retrieveById_EmptyIdGiven_ExceptedNull() {
    when(dbAdvisorDAO.retrieveById("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> dbAdvisorDAO.retrieveById(""));
  }

  @Test
  public void retrieveByEmail_CorrectEmailGiven_ExpectedTrue() {
    when(dbAdvisorDAO.retrieveByEmail("m.marco@gmail.com")).thenReturn(advisor);
    assertEquals(advisor, dbAdvisorDAO.retrieveByEmail("m.marco@gmail.com"));
  }

  @Test
  public void retrieveByEmail_UnexistingEmailGiven_ExpectedTrue() {
    when(dbAdvisorDAO.retrieveByEmail("marco@gmail.com")).thenReturn(advisor);
    assertEquals(advisor, dbAdvisorDAO.retrieveByEmail("marco@gmail.com"));
  }

  @Test
  public void retrieveByEmail_EmptyEmailGiven_ExpectedException() {
    when(dbAdvisorDAO.retrieveByEmail("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> dbAdvisorDAO.retrieveByEmail(""));
  }

  @Test
  public void retrieveByEmail_NullEmailGiven_ExpectedException() {
    when(dbAdvisorDAO.retrieveByEmail(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> dbAdvisorDAO.retrieveByEmail(null));
  }

  @Test
  public void insert_CorrectAdvisorGiven_ExpectedTrue() {
    doNothing().when(dbAdvisorDAO).insert(advisor);
    dbAdvisorDAO.insert(advisor);
    verify(dbAdvisorDAO).insert(advisor);
    verifyNoMoreInteractions(dbAdvisorDAO);
  }

  @Test
  public void insert_NullAdvisorGiven_ExpectedException() {
    doThrow(IllegalArgumentException.class).when(dbAdvisorDAO).insert(null);
    assertThrows(IllegalArgumentException.class, () -> dbAdvisorDAO.insert(null));
  }

  @Test
  public void update_CorrectAdvisorGiven_ExpectedTrue() {
    doNothing().when(dbAdvisorDAO).update(advisor);
    dbAdvisorDAO.update(advisor);
    verify(dbAdvisorDAO).update(advisor);
    verifyNoMoreInteractions(dbAdvisorDAO);
  }

  @Test
  public void update_NullAdvisorGiven_ExpectedException() {
    doThrow(IllegalArgumentException.class).when(dbAdvisorDAO).update(null);
    assertThrows(IllegalArgumentException.class, () -> dbAdvisorDAO.update(null));
  }

  @Test
  public void delete_CorrectAdvisorGiven_ExpectedTrue() {
    doNothing().when(dbAdvisorDAO).delete(advisor);
    dbAdvisorDAO.delete(advisor);
    verify(dbAdvisorDAO).delete(advisor);
    verifyNoMoreInteractions(dbAdvisorDAO);
  }

  @Test
  public void delelete_NullAdvisorGiven_ExpectedException() {
    doThrow(IllegalArgumentException.class).when(dbAdvisorDAO).delete(null);
    assertThrows(IllegalArgumentException.class, () -> dbAdvisorDAO.delete(null));
  }
}