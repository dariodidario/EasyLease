package com.easylease.easylease.model.optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class OptionalTest {
  @Test
  public void constructorTest() {
    Optional optional = new Optional("op12345", "Sensori di parcheggio", "type", 23.5F);
    assertNotNull(optional);
  }

  @Test
  public void emptyConstructorTest() {
    Optional optional = new Optional();
    assertNotNull(optional);
  }

  @Test
  public void getIdTest() {
    Optional optional = new Optional("op12345", "Sensori di parcheggio", "type", 23.5F);
    assertEquals("op12345", optional.getOptionalCode());
  }

  @Test
  public void setIdTest() {
    Optional optional = new Optional();
    optional.setOptionalCode("op12345");
    assertEquals("op12345", optional.getOptionalCode());
  }

  @Test
  public void getNameTest() {
    Optional optional = new Optional("op12345", "Sensori di parcheggio", "type", 23.5F);
    assertEquals("Sensori di parcheggio", optional.getOptionalName());
  }

  @Test
  public void setNameTest() {
    Optional optional = new Optional();
    optional.setOptionalName("Sensori di parcheggio");
    assertEquals("Sensori di parcheggio", optional.getOptionalName());
  }

  @Test
  public void getTypeTest() {
    Optional optional = new Optional("op12345", "Sensori di parcheggio", "type", 23.5F);
    assertEquals("type", optional.getOptionalType());
  }

  @Test
  public void setTypeTest() {
    Optional optional = new Optional();
    optional.setOptionalType("type");
    assertEquals("type", optional.getOptionalType());
  }

  @Test
  public void getPriceTest() {
    Optional optional = new Optional("op12345", "Sensori di parcheggio", "type", 23.5F);
    assertEquals(23.5F, optional.getPrice());
  }

  @Test
  public void setPriceTest() {
    Optional optional = new Optional();
    optional.setPrice(23.5F);
    assertEquals(23.5F, optional.getPrice());
  }
}