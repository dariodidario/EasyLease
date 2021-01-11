package com.easylease.EasyLease.model.estimate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.optional.Optional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;

class EstimateTest {
/*
  Optional optional = new Optional("op12345", "cerchi in lega",  "di serie", 33);
  Optional optional2 = new Optional("op54321", "vetri oscurati", "di serie", 22);
  Client client = new Client("cl123", "Marco", "Polo", "email", "password",
      "Roma", new Date(), "Uomo", "Roma", "84018", "Via");
  Advisor advisor = new Advisor("ad12345", "Mario", "Rossi", "email",
      "password", new Date());
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
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);
    assertNotNull(estimate);
  }

  @Test
  public void getIdTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);
    assertEquals("es4312", estimate.getId());
  }

  @Test
  public void setIdTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate();
    estimate.setId("es4312");
    assertEquals("es4312", estimate.getId());
  }

  @Test
  public void getPriceTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);
    assertEquals(240, estimate.getPrice());
  }

  @Test
  public void setPriceTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate();
    estimate.setPrice(240);
    assertEquals(240, estimate.getPrice());
  }

  @Test
  public void getClientTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);
    assertEquals(client, estimate.getClient());
  }

  @Test
  public void setClientTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate();
    estimate.setClient(client);
    assertEquals(client, estimate.getClient());
  }

  @Test
  public void getAdvisorTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);
    assertEquals(advisor, estimate.getAdvisor());
  }

  @Test
  public void setAdvisorTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate();
    estimate.setAdvisor(advisor);
    assertEquals(advisor, estimate.getAdvisor());
  }

  @Test
  public void getCarTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);
    assertEquals(car, estimate.getCar());
  }

  @Test
  public void setCarTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate();
    estimate.setCar(car);
    assertEquals(car, estimate.getCar());
  }

  @Test
  public void getPeriodTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);
    assertEquals(30, estimate.getPeriod());
  }

  @Test
  public void setPeriodTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate();
    estimate.setPeriod(30);
    assertEquals(30, estimate.getPeriod());
  }

  @Test
  public void getOptionalListTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);
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
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);
    assertEquals(true, estimate.isVisibility());
  }

  @Test
  public void setVisibilityTest() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate estimate = new Estimate();
    estimate.setVisibility(true);
    assertEquals(true, estimate.isVisibility());
  }
*/
}