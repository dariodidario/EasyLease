package com.easylease.EasyLease.model.order;

import com.easylease.EasyLease.model.estimate.Estimate;
import java.util.Date;

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
  /** Current status of the Order */
  private String state;
  /** Order creation date */
  private Date creationDate;

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
   * @param creationDate Order creation date.
   * @param visibility Indicates whether the order is visible or not.
   * @param state Current state of the Order.
   */
  public Order(
      String id, Estimate estimate, Date startDate, Date endDate, Date pickupDate,
      Date creationDate, boolean visibility, String state) {

    this.id = id;
    this.estimate = estimate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.pickupDate = pickupDate;
    this.visibility = visibility;
    this.state = state;
    this.creationDate = creationDate;
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

  /**
   * Returns the state of the Order.
   *
   * @return the Order state.
   */
  public String getState() {
    return state;
  }

  /**
   * Set the Order state.
   *
   * @param state The order state.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Returns the creation Date of the Order.
   *
   * @return the Order creation Date.
   */
  public Date getCreationDate() {
    return creationDate;
  }

  /**
   * Set the Order creation Date.
   *
   * @param creationDate The order creation Date.
   */
  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
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
    return "Order{" +
        "id='" + id + '\'' +
        ", estimate=" + estimate +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", pickupDate=" + pickupDate +
        ", visibility=" + visibility +
        ", state='" + state + '\'' +
        ", creationDate=" + creationDate +
        '}';
  }
}
