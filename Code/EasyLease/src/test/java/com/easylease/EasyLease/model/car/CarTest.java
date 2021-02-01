package com.easylease.EasyLease.model.car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CarTest {


  @Test
  void testCostructor() {
    Car c = new Car();
    assertNotNull(c);
  }

  @org.junit.jupiter.api.Test
  public void testGetId() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");
    assertEquals("ca11111", car.getIdCar());
  }

  @org.junit.jupiter.api.Test
  void testGetBrand() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");
    assertEquals("Peugeot", car.getBrand());
  }

  @Test
  void test1_SetId() {
    Car car = new Car();
    car.setIdCar("ca11112");
    assertEquals("ca11112", car.getIdCar());


  }


  @Test
  void testSetBrand() {
    Car car = new Car();
    car.setBrand("Renault");
    assertEquals("Renault", car.getBrand());


  }

  @Test
  void testGetModel() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");
    assertEquals("3008", car.getModel());

  }

  @Test
  void testSetModel() {
    Car car = new Car();
    car.setModel("2008");
    assertEquals("2008", car.getModel());

  }

  @Test
  void testGetPrice() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

    float actual = 249;
    assertEquals(actual, car.getPrice());
  }

  @Test
  void testSetPrice() {
    Car car = new Car();
    car.setPrice(2409);

    assertEquals(2409, car.getPrice());
  }

  @Test
  void testGetType() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");
    assertEquals("SUV", car.getType());

  }

  @Test
  void testSetType() {
    Car car = new Car();
    car.setType("berlina");
    assertEquals("berlina", car.getType());

  }

  @Test
  void testGetPowerSupply() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");
    assertEquals("Diesel", car.getPowerSupply());
  }

  @Test
  void testSetPowerSupply() {
    Car car = new Car();
    car.setPowerSupply("Benzina");
    assertEquals("Benzina", car.getPowerSupply());
  }

  @Test
  void testGetVisibility() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

    assertTrue(car.getVisibility(), "get visibility errato");
  }

  @Test
  void testSetVisibility() {
    Car car = new Car();
    car.setVisibility(false);
    assertFalse(car.getVisibility(), "set visibility errato");
  }

  @Test
  void testGetDoors() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

    assertEquals(5, car.getDoors(), "get doors errato");
  }

  @Test
  void testSetDoors() {
    Car car = new Car();
    car.setDoors(3);

    assertEquals(3, car.getDoors(), "set doors errato");
  }

  @Test
  void testGetTrasmision() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");
    assertEquals("Automatico", car.getTransmission());
  }

  @Test
  void testSetTrasmision() {
    Car car = new Car();
    car.setTransmission("Manuale");

    assertEquals("Manuale", car.getTransmission());
  }

  @Test
  void testGetAvg_consumption() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");
    float s = car.getAvgConsumption();
    assertEquals(3.9f, s);
  }

  @Test
  void tetsSetAvg_consumption() {
    Car car = new Car();
    car.setAvgConsumption(2.9f);
    assertEquals(2.9f, car.getAvgConsumption());
  }

  @Test
  void testGetHorse_power() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

    assertEquals(130, car.getHorsePower());
  }

  @Test
  void testSetHorse_power() {
    Car car = new Car();
    car.setHorsePower(90);

    assertEquals(90, car.getHorsePower());
  }

  @Test
  void testGetEmission_class() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");
    assertEquals("Euro 6", car.getEmissionClass());

  }

  @Test
  void testSetEmission_class() {
    Car car = new Car();
    car.setEmissionClass("Euro 5");
    assertEquals("Euro 5", car.getEmissionClass());

  }

  @Test
  void tsetGetCo2_emissions() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

    assertEquals(104, car.getCo2Emissions());
  }

  @Test
  void testSetCo2_emissions() {
    Car car = new Car();
    car.setCo2Emissions(100);

    assertEquals(100, car.getCo2Emissions());
  }

  @Test
  void testGetCc() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");

    assertEquals(1499, car.getCapacity());
  }

  @Test
  void testSetCc() {
    Car car = new Car();
    car.setCapacity(1400);
    assertEquals(1400, car.getCapacity());
  }

  @Test
  void testGetImage() {
    Car car = new Car("ca11111", "Peugeot", "3008", 249, "SUV",
        true, 5, "Automatico", 3.9f,
        130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");
    assertEquals("peugeot_3008.jpg", car.getImage());

  }

  @Test
  void testSetImage() {
    Car car = new Car();
    car.setImage("renault_2008.jpg");
    assertEquals("renault_2008.jpg", car.getImage());
  }
}
