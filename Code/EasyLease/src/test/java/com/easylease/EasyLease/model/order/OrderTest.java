package com.easylease.EasyLease.model.order;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.easylease.EasyLease.model.estimate.Estimate;
import java.util.Date;
import org.junit.jupiter.api.Test;

class OrderTest {

  @Test
  void testOrderConstructorEmpty() {
    Order order = new Order();
    assertNotNull(order);
  }

  @Test
  void testOrderConstructorFull() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    assertNotNull(order);
  }

  @Test
  void testOrderGetId() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    assertEquals("OR12345", order.getId());
  }

  @Test
  void testOrderGetEstimate() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    assertEquals(new Estimate().getId(), order.getEstimate().getId());
  }

  @Test
  void testOrderGetStartDate() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    assertEquals(new Date(), order.getStartDate());
  }

  @Test
  void testOrderGetEndDate() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    assertEquals(new Date(), order.getEndDate());
  }

  @Test
  void testOrderGetPickupDate() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    assertEquals(new Date(), order.getPickupDate());
  }

  @Test
  void testOrderGetVisibility() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    assertTrue(order.isVisibility());
  }

  @Test
  void testOrderSetId() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    order.setId("OR23456");
    assertEquals("OR23456", order.getId());
  }

  @Test
  void testOrderSetEstimate() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    Estimate estimate = new Estimate();
    order.setEstimate(estimate);
    assertEquals(estimate.getId(), order.getEstimate().getId());
  }

  @Test
  void testOrderSetStartDate() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    order.setStartDate(new Date());
    assertEquals(new Date(), order.getStartDate());
  }

  @Test
  void testOrderSetEndDate() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    order.setEndDate(new Date());
    assertEquals(new Date(), order.getEndDate());
  }

  @Test
  void testOrderSetPickupDate() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    order.setPickupDate(new Date());
    assertEquals(new Date(), order.getPickupDate());
  }

  @Test
  void testOrderSetVisibility() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    order.setVisibility(false);
    assertFalse(order.isVisibility());
  }

  @Test
  void testOrderNotEquals() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    assertNotEquals(order, new Order());
  }

  @Test
  void testOrderEqualsNull() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    assertFalse(order.equals(null));
  }

  @Test
  void testOrderToString() {
    Order order = new Order("OR12345", new Estimate(),
        new Date(), new Date(), new Date(), true);
    String toString = "Order{"
        + "id='" + order.getId() + '\''
        + ", estimate=" + order.getEstimate()
        + ", startDate=" + order.getStartDate()
        + ", endDate=" + order.getEndDate()
        + ", pickupDate=" + order.getPickupDate()
        + ", visibility=" + order.isVisibility()
        + '}';
    assertEquals(order.toString(), toString);
  }

}