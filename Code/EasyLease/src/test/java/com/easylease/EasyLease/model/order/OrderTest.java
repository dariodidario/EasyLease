package com.easylease.EasyLease.model.order;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.easylease.EasyLease.model.estimate.Estimate;
import java.util.Date;
import org.junit.jupiter.api.Test;

/**
 * Test of the Order model.
 *
 * @author Antonio Sarro
 * @version 0.1
 * @since 0.1
 */
class OrderTest {

  @Test
  void testOrderConstructorEmpty() {
    Order order = new Order();
    assertNotNull(order);
  }

  @Test
  void testOrderConstructorFull() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    assertNotNull(order);
  }

  @Test
  void testOrderGetId() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    assertEquals("OR12RT4", order.getIdOrder());
  }

  @Test
  void testOrderGetEstimate() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    assertEquals(new Estimate().getIdEstimate(), order.getEstimate().getIdEstimate());
  }

  @Test
  void testOrderGetStartDate() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    assertEquals(new Date(), order.getStartDate());
  }

  @Test
  void testOrderGetEndDate() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    assertEquals(new Date(), order.getEndDate());
  }

  @Test
  void testOrderGetConfirmDate() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    assertEquals(new Date(), order.getConfirmDate());
  }

  @Test
  void testOrderGetVisibility() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    assertTrue(order.isVisibility());
  }

  @Test
  void testOrderSetId() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    order.setIdOrder("OR23456");
    assertEquals("OR23456", order.getIdOrder());
  }

  @Test
  void testOrderSetEstimate() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    Estimate estimate = new Estimate();
    order.setEstimate(estimate);
    assertEquals(estimate.getIdEstimate(), order.getEstimate().getIdEstimate());
  }

  @Test
  void testOrderSetStartDate() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    order.setStartDate(new Date());
    assertEquals(new Date(), order.getStartDate());
  }

  @Test
  void testOrderSetEndDate() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    order.setEndDate(new Date());
    assertEquals(new Date(), order.getEndDate());
  }

  @Test
  void testOrderSetConfirmDate() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    order.setConfirmDate(new Date());
    assertEquals(new Date(), order.getConfirmDate());
  }

  @Test
  void testOrderSetVisibility() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    order.setVisibility(false);
    assertFalse(order.isVisibility());
  }
}