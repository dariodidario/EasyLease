package com.easylease.EasyLease.model.client;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

  @Test
  public void testCostructorEmpty() {
    Client cliente = new Client();
    assertNotNull(cliente);
  }

  @Test
  public void testGetBirthPlace() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("Avellino", cliente.getBirthPlace());
  }

  @Test
  public void testSetBirthPlace() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setBirthPlace("Atripalda");
    assertEquals("Atripalda", cliente.getBirthPlace());
  }

  @Test
  public void testGetBirthDate() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    Calendar calExpectedDate = new GregorianCalendar(1998, 8, 9);
    Date expectedDate = calBdate.getTime();
    assertEquals(expectedDate, cliente.getBirthDate());
  }

  @Test
  public void testSetBirthDate() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    Calendar calDateToSet = new GregorianCalendar(1994, 4, 12);
    Date dateToSet = calDateToSet.getTime();
    cliente.setBirthDate(dateToSet);
    assertEquals(dateToSet, cliente.getBirthDate());
  }

  @Test
  public void testGetKind() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("Uomo", cliente.getKind());
  }

  @Test
  public void testSetKind() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setKind("Altro");
    assertEquals("Altro", cliente.getKind());
  }

  @Test
  public void testGetCity() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("Avellino", cliente.getCity());
  }

  @Test
  public void testSetCity() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setCity("Nola");
    assertEquals("Nola", cliente.getCity());
  }

  @Test
  public void testGetPc() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("83020", cliente.getPc());
  }

  @Test
  public void testSetPc() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setPc("89090");
    assertEquals("89090", cliente.getPc());
  }

  @Test
  public void testGetStreet() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("Contrada Petrulli 3", cliente.getStreet());
  }

  @Test
  public void testSetStreet() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com",
        "mrossi", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setStreet("Via Provinciale 28");
    assertEquals("Via Provinciale 28", cliente.getStreet());
  }
}