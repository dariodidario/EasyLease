package com.easylease.EasyLease.model.advisor;

import static java.util.Calendar.JANUARY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

class AdvisorTest {
  Calendar calendar = new GregorianCalendar(2021, JANUARY, 1);

  @Test
  public void testConstructorEmpty() {
    Advisor advisor = new Advisor();
    assertNotNull(advisor);
  }

  @Test
  public void testGetHireDate() {
    Date hireDate = calendar.getTime();
    Advisor advisor = new Advisor("AD12DD2", "Marco", "Montemagno", "m.marco@gmail.com", hireDate);
    Date expectedDate = calendar.getTime();
    assertEquals(expectedDate, advisor.getHireDate());
  }

  @Test
  public void testSetHireDate() {
    Date hireDate = calendar.getTime();
    Advisor advisor = new Advisor("AD12DD2", "Marco", "Montemagno", "m.marco@gmail.com", hireDate);
    Date hireDateToSet = calendar.getTime();
    advisor.setHireDate(hireDateToSet);
    assertEquals(hireDateToSet, advisor.getHireDate());
  }

}