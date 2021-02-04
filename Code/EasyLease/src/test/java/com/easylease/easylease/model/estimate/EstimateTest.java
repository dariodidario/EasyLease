package com.easylease.easylease.model.estimate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.easylease.easylease.model.advisor.Advisor;
import com.easylease.easylease.model.car.Car;
import com.easylease.easylease.model.client.Client;
import com.easylease.easylease.model.optional.Optional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

class EstimateTest {
  Optional optional = new Optional("op12345", "cerchi in lega",  "di serie", 33);
  Optional optional2 = new Optional("op54321", "vetri oscurati", "di serie", 22);
  Client client = new Client();
  Advisor advisor = new Advisor();
  Car car = new Car("ca12345", "Peugeot", "3008", 134, "city car",
      true, 6, "trasmission", 4, 300, "B",
      2, "Diesel", 33, "path");
  List<Optional> optionalList = new ArrayList<>();


  @Test
  public void emptyConstructorTest() {
    Estimate estimate = new Estimate();
    assertNotNull(estimate);
  }

  @Test
  public void constructorTest() {
    client.setIdUser("cl12346");
    advisor.setIdUser("ADfake0");
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor,
        car, 30, optionalList, true, "Attesa", null,
        new Date(System.currentTimeMillis()));
    assertNotNull(estimate);
  }

  @Test
  public void getIdTest() {
    client.setIdUser("cl12346");
    advisor.setIdUser("ADfake0");
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor,
        car, 30, optionalList, true, "Attesa", null,
        new Date(System.currentTimeMillis()));
    assertEquals("es4312", estimate.getIdEstimate());
  }

  @Test
  public void setIdTest() {
    Estimate estimate = new Estimate();
    estimate.setIdEstimate("es4312");
    assertEquals("es4312", estimate.getIdEstimate());
  }

  @Test
  public void getPriceTest() {
    client.setIdUser("cl12346");
    advisor.setIdUser("ADfake0");
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor,
        car, 30, optionalList, true, "Attesa", null,
        new Date(System.currentTimeMillis()));
    assertEquals(240, estimate.getPrice());
  }

  @Test
  public void setPriceTest() {
    Estimate estimate = new Estimate();
    estimate.setPrice(240);
    assertEquals(240, estimate.getPrice());
  }

  @Test
  public void getClientTest() {
    client.setIdUser("cl12346");
    advisor.setIdUser("ADfake0");
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor,
        car, 30, optionalList, true, "Attesa", null,
        new Date(System.currentTimeMillis()));
    assertEquals(client, estimate.getClient());
  }

  @Test
  public void setClientTest() {
    Estimate estimate = new Estimate();
    estimate.setClient(client);
    assertEquals(client, estimate.getClient());
  }

  @Test
  public void getAdvisorTest() {
    client.setIdUser("cl12346");
    advisor.setIdUser("ADfake0");
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor,
        car, 30, optionalList, true, "Attesa", null,
        new Date(System.currentTimeMillis()));
    assertEquals(advisor, estimate.getAdvisor());
  }

  @Test
  public void setAdvisorTest() {
    Estimate estimate = new Estimate();
    estimate.setAdvisor(advisor);
    assertEquals(advisor, estimate.getAdvisor());
  }

  @Test
  public void getCarTest() {
    client.setIdUser("cl12346");
    advisor.setIdUser("ADfake0");
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor,
        car, 30, optionalList, true, "Attesa", null,
        new Date(System.currentTimeMillis()));
    assertEquals(car, estimate.getCar());
  }

  @Test
  public void setCarTest() {
    Estimate estimate = new Estimate();
    estimate.setCar(car);
    assertEquals(car, estimate.getCar());
  }

  @Test
  public void getPeriodTest() {
    client.setIdUser("cl12346");
    advisor.setIdUser("ADfake0");
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor,
        car, 30, optionalList, true, "Attesa", null,
        new Date(System.currentTimeMillis()));
    assertEquals(30, estimate.getPeriod());
  }

  @Test
  public void setPeriodTest() {
    Estimate estimate = new Estimate();
    estimate.setPeriod(30);
    assertEquals(30, estimate.getPeriod());
  }

  @Test
  public void getOptionalListTest() {
    client.setIdUser("cl12346");
    advisor.setIdUser("ADfake0");
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor,
        car, 30, optionalList, true, "Attesa", null,
        new Date(System.currentTimeMillis()));
    assertEquals(optionalList, estimate.getOptionalList());
  }

  @Test
  public void setOptionalListTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate();
    estimate.setOptionalList(optionalList);
    assertEquals(optionalList, estimate.getOptionalList());
  }

  @Test
  public void getVisibilityTest() {
    client.setIdUser("cl12346");
    advisor.setIdUser("ADfake0");
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor,
        car, 30, optionalList, true, "Attesa", null,
        new Date(System.currentTimeMillis()));
    assertEquals(true, estimate.isVisibility());
  }

  @Test
  public void setVisibilityTest() {
    Estimate estimate = new Estimate();
    estimate.setVisibility(true);
    assertEquals(true, estimate.isVisibility());
  }

  @Test
  public void getStateTest() {
    client.setIdUser("cl12346");
    advisor.setIdUser("ADfake0");
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor,
        car, 30, optionalList, true, "Attesa", null,
        new Date(System.currentTimeMillis()));
    assertEquals("Attesa", estimate.getState());
  }

  @Test
  public void setStateTest() {
    Estimate estimate = new Estimate();
    estimate.setState("Attesa");
    assertEquals("Attesa", estimate.getState());
  }

  @Test
  public void getRequestDate() {
    client.setIdUser("cl12346");
    advisor.setIdUser("ADfake0");
    optionalList.add(optional);
    optionalList.add(optional2);
    Date date = new Date(System.currentTimeMillis());
    Estimate estimate = new Estimate("es4312", 240, client, advisor,
        car, 30, optionalList, true, "Attesa",
        date, null);
    assertEquals(date, estimate.getRequestDate());
  }

  @Test
  public void setRequestDate() {
    Estimate estimate = new Estimate();
    Date date = new Date(System.currentTimeMillis());
    estimate.setRequestDate(date);
    assertEquals(date, estimate.getRequestDate());
  }

  @Test
  public void getResponseDate() {
    client.setIdUser("cl12346");
    advisor.setIdUser("ADfake0");
    optionalList.add(optional);
    optionalList.add(optional2);
    Date date = new Date(System.currentTimeMillis());
    Estimate estimate = new Estimate("es4312", 240, client, advisor,
        car, 30, optionalList, true, "Attesa",
        null, date);
    assertEquals(date, estimate.getResponseDate());
  }

  @Test
  public void setResponseDate() {
    Estimate estimate = new Estimate();
    Date date = new Date(System.currentTimeMillis());
    estimate.setResponseDate(date);
    assertEquals(date, estimate.getResponseDate());
  }
}