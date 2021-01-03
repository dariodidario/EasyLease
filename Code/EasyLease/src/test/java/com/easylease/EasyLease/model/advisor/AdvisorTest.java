package com.easylease.EasyLease.model.advisor;

import com.easylease.EasyLease.model.user.User;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AdvisorTest {

  @Test
  public void testConstructorEmpty(){
    Advisor advisor = new Advisor();
    assertNotNull(advisor);
  }

  @Test
  public void testGetHireDate(){
    Date hireDate = new Date(2021, 1, 1);
    Advisor advisor = new Advisor("AD12DD2", "Marco", "Montemagno", "m.marco@gmail.com",
        "password", hireDate);
    Date expectedDate = new Date(2021, 1, 1);
    assertEquals(expectedDate, advisor.getHireDate());
  }

  @Test
  public void testSetHireDate(){
    Date hireDate = new Date(2021, 1, 1);
    Advisor advisor = new Advisor("AD12DD2", "Marco", "Montemagno", "m.marco@gmail.com",
        "password", hireDate);
    Date hireDateToSet = new Date(2021, 1, 1);
    advisor.setHireDate(hireDateToSet);
    assertEquals(hireDateToSet, advisor.getHireDate());
  }

}