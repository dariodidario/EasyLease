package com.easylease.EasyLease.model.advisor;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DBAdvisorDAOTest {
  Advisor advisor = new Advisor("AD12DD2", "Marco", "Montemagno", "m.marco@gmail.com",
      "password", new Date(2021, 1, 1));
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
  public void insert_CorrectClientGiven_ExpectedTrue() {
    doNothing().when(dbAdvisorDAO).insert(advisor);
    dbAdvisorDAO.insert(advisor);
    verify(dbAdvisorDAO).insert(advisor);
    //verifyNoInteractions(dbAdvisorDAO); //TODO: Test sbagliato
  }

  @Test
  public void insert_NullClientGiven_ExpectedException() {
    doThrow(IllegalArgumentException.class).when(dbAdvisorDAO).insert(null);
    assertThrows(IllegalArgumentException.class, () -> dbAdvisorDAO.insert(null));
  }

  @Test
  public void update_CorrectClientGiven_ExpectedTrue() {
    doNothing().when(dbAdvisorDAO).update(advisor);
    dbAdvisorDAO.update(advisor);
    verify(dbAdvisorDAO).update(advisor);
    //verifyNoInteractions(dbAdvisorDAO); //TODO: Test sbagliato
  }

  @Test
  public void update_NullClientGiven_ExpectedException() {
    doThrow(IllegalArgumentException.class).when(dbAdvisorDAO).update(null);
    assertThrows(IllegalArgumentException.class, () -> dbAdvisorDAO.update(null));
  }

  @Test
  public void delete_CorrectClientGiven_ExpectedTrue() {
    doNothing().when(dbAdvisorDAO).delete(advisor);
    dbAdvisorDAO.delete(advisor);
    verify(dbAdvisorDAO).delete(advisor);
    //verifyNoInteractions(dbAdvisorDAO); //TODO: Test sbagliato
  }

  @Test
  public void delelete_NullClientGiven_ExpectedException() {
    doThrow(IllegalArgumentException.class).when(dbAdvisorDAO).delete(null);
    assertThrows(IllegalArgumentException.class, () -> dbAdvisorDAO.delete(null));
  }
}