package com.easylease.EasyLease.model.client;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

  @Test
  public void testCostructorEmpty() {
    Client cliente = new Client();
    assertNotNull(cliente);
  }

  @Test
  public void testGetBirthPlace() {
    Date bdate = new Date(1998, 9, 9);
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("Avellino", cliente.getBirthPlace());
  }

  @Test
  public void testSetBirthPlace() {
    Date bdate = new Date(1998, 9, 9);
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setBirthPlace("Atripalda");
    assertEquals("Atripalda", cliente.getBirthPlace());
  }

  @Test
  public void testGetBirthDate() {
    Date bdate = new Date(1998, 9, 9);
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    Date expectedDate = new Date(1998, 9, 9);
    assertEquals(expectedDate, cliente.getBirthDate());
  }

  @Test
  public void testSetBirthDate() {
    Date bdate = new Date(1998, 9, 9);
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    Date dateToSet = new Date(1994, 5, 12);
    cliente.setBirthDate(dateToSet);
    assertEquals(dateToSet, cliente.getBirthDate());
  }

  @Test
  public void testGetKind() {
    Date bdate = new Date(1998, 9, 9);
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("Uomo", cliente.getKind());
  }

  @Test
  public void testSetKind() {
    Date bdate = new Date(1998, 9, 9);
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setKind("Altro");
    assertEquals("Altro", cliente.getKind());
  }

  @Test
  public void testGetCity() {
    Date bdate = new Date(1998, 9, 9);
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("Avellino", cliente.getCity());
  }

  @Test
  public void testSetCity() {
    Date bdate = new Date(1998, 9, 9);
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setCity("Nola");
    assertEquals("Nola", cliente.getCity());
  }

  @Test
  public void testGetPc() {
    Date bdate = new Date(1998, 9, 9);
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("83020", cliente.getPc());
  }

  @Test
  public void testSetPc() {
    Date bdate = new Date(1998, 9, 9);
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setPc("89090");
    assertEquals("89090", cliente.getPc());
  }

  @Test
  public void testGetStreet() {
    Date bdate = new Date(1998, 9, 9);
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("Contrada Petrulli 3", cliente.getStreet());
  }

  @Test
  public void testSetStreet() {
    Date bdate = new Date(1998, 9, 9);
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setStreet("Via Provinciale 28");
    assertEquals("Via Provinciale 28", cliente.getStreet());
  }
}