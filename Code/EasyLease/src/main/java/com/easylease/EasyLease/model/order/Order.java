package com.easylease.EasyLease.model.order;

import com.easylease.EasyLease.model.estimate.Estimate;
import java.util.Date;
import java.util.Objects;

/**
 * This class models an object of type Order within the system.
 *
 * @since 0.1
 * @author Antonio Sarro
 * @version 0.1
 */
public class Order {

  /** Order ID. */
  private String id;
  /** Estimate Object linked to the following Order. */
  private Estimate estimate;
  /** Data from which the contract begins. */
  private Date startDate;
  /** Data from which the contract ends. */
  private Date endDate;
  /** Order pickup date. */
  private Date pickupDate;
  /** Indicates whether the order is visible or not. */
  private boolean visibility;

  public Order() {

  }

  /**
   * Constructor for the Order Object.
   *
   * @param id Order ID.
   * @param estimate Estimate Object linked to the following Order.
   * @param startDate Date from which the contract begins.
   * @param endDate Data from which the contract ends.
   * @param pickupDate Order pickup date.
   * @param visibility Indicates whether the order is visible or not
   */
  public Order(
      String id, Estimate estimate, Date startDate, Date endDate, Date pickupDate,
      boolean visibility) {

    this.id = id;
    this.estimate = estimate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.pickupDate = pickupDate;
    this.visibility = visibility;
  }

  /**
   * Returns the ID of Order.
   *
   * @return Order ID.
   */
  public String getId() {
    return id;
  }

  /**
   * Set the ID of the Order.
   *
   * @param id Order ID.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Returns the Estimate of Order.
   *
   * @return Estimate Object linked to the following Order.
   */
  public Estimate getEstimate() {
    return estimate;
  }

  /**
   * Set the Estimate Object linked to the following Order.
   *
   * @param estimate Estimate Object linked to the following Order.
   */
  public void setEstimate(Estimate estimate) {
    this.estimate = estimate;
  }

  /**
   * Returns Date from which the contract begins.
   *
   * @return Date from which the contract begins.
   */
  public Date getStartDate() {
    return startDate;
  }

  /**
   * Set the Date from which the contract begins.
   *
   * @param startDate Date from which the contract begins.
   */
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  /**
   * Returns the Data from which the contract ends.
   *
   * @return the Data from which the contract ends.
   */
  public Date getEndDate() {
    return endDate;
  }

  /**
   * Set Data from which the contract ends.
   *
   * @param endDate the Data from which the contract ends.
   */
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  /**
   * Returns Order pickup date.
   *
   * @return Order pickup date.
   */
  public Date getPickupDate() {
    return pickupDate;
  }

  /**
   * Set the Order pickup date.
   *
   * @param pickupDate Order pickup date.
   */
  public void setPickupDate(Date pickupDate) {
    this.pickupDate = pickupDate;
  }

  /**
   * Returns the visibility of the Order.
   *
   * @return the Order visibility.
   */
  public boolean isVisibility() {
    return visibility;
  }

  /**
   * Set the Order visibility.
   *
   * @param visibility The order visibility.
   */
  public void setVisibility(boolean visibility) {
    this.visibility = visibility;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Order order = (Order) obj;
    return this.id.equals(order.id);
  }

  @Override
  public String toString() {
    return "Order{"
        + "id='" + id + '\''
        + ", estimate=" + estimate
        + ", startDate=" + startDate
        + ", endDate=" + endDate
        + ", pickupDate=" + pickupDate
        + ", visibility=" + visibility
        + '}';
  }
}
