package com.easylease.EasyLease.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.junit.jupiter.api.Test;


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
        "Rossi", "m.rossi@gmail.com", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("Avellino", cliente.getBirth_place());
  }

  @Test
  public void testSetBirthPlace() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setBirth_place("Atripalda");
    assertEquals("Atripalda", cliente.getBirth_place());
  }

  @Test
  public void testGetBirthDate() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    Calendar calExpectedDate = new GregorianCalendar(1998, 8, 9);
    Date expectedDate = calBdate.getTime();
    assertEquals(expectedDate, cliente.getBirth_date());
  }

  @Test
  public void testSetBirthDate() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    Calendar calDateToSet = new GregorianCalendar(1994, 4, 12);
    Date dateToSet = calDateToSet.getTime();
    cliente.setBirth_date(dateToSet);
    assertEquals(dateToSet, cliente.getBirth_date());
  }

  @Test
  public void testGetKind() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("Uomo", cliente.getKind());
  }

  @Test
  public void testSetKind() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setKind("Altro");
    assertEquals("Altro", cliente.getKind());
  }

  @Test
  public void testGetCity() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("Avellino", cliente.getCity());
  }

  @Test
  public void testSetCity() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setCity("Nola");
    assertEquals("Nola", cliente.getCity());
  }

  @Test
  public void testGetPc() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("83020", cliente.getPc());
  }

  @Test
  public void testSetPc() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setPc("89090");
    assertEquals("89090", cliente.getPc());
  }

  @Test
  public void testGetStreet() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    assertEquals("Contrada Petrulli 3", cliente.getStreet());
  }

  @Test
  public void testSetStreet() {
    Calendar calBdate = new GregorianCalendar(1998, 8, 9);
    Date bdate = calBdate.getTime();
    Client cliente = new Client("CLABC12", "Mario",
        "Rossi", "m.rossi@gmail.com", "Avellino", bdate,
        "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
    cliente.setStreet("Via Provinciale 28");
    assertEquals("Via Provinciale 28", cliente.getStreet());
  }
}