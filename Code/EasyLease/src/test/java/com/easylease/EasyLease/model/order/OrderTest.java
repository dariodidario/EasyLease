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
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    assertNotNull(order);
  }

  @Test
  void testOrderGetId() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    assertEquals("OR12RT4", order.getId());
  }

  @Test
  void testOrderGetEstimate() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    assertEquals(new Estimate().getId(), order.getEstimate().getId());
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
  void testOrderGetPickupDate() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    assertEquals(new Date(), order.getPickupDate());
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
    order.setId("OR23456");
    assertEquals("OR23456", order.getId());
  }

  @Test
  void testOrderSetEstimate() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    Estimate estimate = new Estimate();
    order.setEstimate(estimate);
    assertEquals(estimate.getId(), order.getEstimate().getId());
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
  void testOrderSetPickupDate() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    order.setPickupDate(new Date());
    assertEquals(new Date(), order.getPickupDate());
  }

  @Test
  void testOrderSetVisibility() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    order.setVisibility(false);
    assertFalse(order.isVisibility());
  }

  @Test
  void testOrderNotEquals() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    assertNotEquals(order, new Order());
  }

  @Test
  void testOrderEqualsNull() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    assertFalse(order.equals(null));
  }

  @Test
  void testOrderToString() {
    Order order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), new Date(), true, "Attesa");
    String toString = "Order{" +
        "id='" + order.getId() + '\'' +
        ", estimate=" + order.getEstimate() +
        ", startDate=" + order.getStartDate() +
        ", endDate=" + order.getEndDate() +
        ", pickupDate=" + order.getPickupDate() +
        ", visibility=" + order.isVisibility() +
        ", state='" + order.getState() + '\'' +
        ", creationDate=" + order.getCreationDate() +
        '}';
    assertEquals(order.toString(), toString);
  }

}