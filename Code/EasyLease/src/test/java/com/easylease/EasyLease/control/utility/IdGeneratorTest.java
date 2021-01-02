package com.easylease.EasyLease.control.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IdGeneratorTest {

  @Test
  public void idLengthTest() {
    String id = IdGenerator.randomIdGenerator();
    assertEquals(5, id.length());
  }

  @Test
  public void idCorrectCharacters() {
    String id = IdGenerator.randomIdGenerator();
    assertTrue(id.matches("^[a-zA-Z0-9]+$"));
  }
}