package com.easylease.EasyLease.model.optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.easylease.EasyLease.model.optional.Optional;
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
    assertEquals("op12345", optional.getId());
  }

  @Test
  public void setIdTest() {
    Optional optional = new Optional();
    optional.setId("op12345");
    assertEquals("op12345", optional.getId());
  }

  @Test
  public void getNameTest() {
    Optional optional = new Optional("op12345", "Sensori di parcheggio", "type", 23.5F);
    assertEquals("Sensori di parcheggio", optional.getName());
  }

  @Test
  public void setNameTest() {
    Optional optional = new Optional();
    optional.setName("Sensori di parcheggio");
    assertEquals("Sensori di parcheggio", optional.getName());
  }

  @Test
  public void getTypeTest() {
    Optional optional = new Optional("op12345", "Sensori di parcheggio", "type", 23.5F);
    assertEquals("type", optional.getType());
  }

  @Test
  public void setTypeTest() {
    Optional optional = new Optional();
    optional.setType("type");
    assertEquals("type", optional.getType());
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