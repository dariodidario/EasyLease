package com.easylease.EasyLease.control.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test of the IdGenerator utility class.
 *
 * @author Antonio Sarro
 * @version 0.1
 * @since 0.1
 */
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