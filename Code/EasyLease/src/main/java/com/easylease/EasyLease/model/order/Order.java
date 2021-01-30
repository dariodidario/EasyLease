package com.easylease.EasyLease.model.order;

import com.easylease.EasyLease.model.estimate.Estimate;
import java.util.Date;

/**
 * This class models an object of type Order within the system.
 *
 * @since 0.1
 * @author Antonio Sarro
 * @version 0.2
 */
public class Order {

  /** Order ID. */
  private String id_order;
  /** Estimate Object linked to the following Order. */
  private Estimate estimate;
  /** Data from which the contract begins. */
  private Date start_date;
  /** Data from which the contract ends. */
  private Date end_date;
  /** Order confirm date. */
  private Date confirm_date;
  /** Indicates whether the order is visible or not. */
  private boolean visibility;
  /** Current status of the Order. */
  private String state;
  /** Order creation date. */
  private Date creation_date;

  public Order() {

  }

  /**
   * Constructor for the Order Object.
   *
   * @param id_order Order ID.
   * @param estimate Estimate Object linked to the following Order.
   * @param start_date Date from which the contract begins.
   * @param end_date Data from which the contract ends.
   * @param confirm_date Order confirm date.
   * @param creation_date Order creation date.
   * @param visibility Indicates whether the order is visible or not.
   * @param state Current state of the Order.
   */
  public Order(
      String id_order, Estimate estimate, Date start_date, Date end_date, Date confirm_date,
      Date creation_date, boolean visibility, String state) {

    this.id_order = id_order;
    this.estimate = estimate;
    this.start_date = start_date;
    this.end_date = end_date;
    this.confirm_date = confirm_date;
    this.visibility = visibility;
    this.state = state;
    this.creation_date = creation_date;
  }

  /**
   * Returns the ID of Order.
   *
   * @return Order ID.
   */
  public String getId_order() {
    return id_order;
  }

  /**
   * Set the ID of the Order.
   *
   * @param id_order Order ID.
   */
  public void setId_order(String id_order) {
    this.id_order = id_order;
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
  public Date getStart_date() {
    return start_date;
  }

  /**
   * Set the Date from which the contract begins.
   *
   * @param start_date Date from which the contract begins.
   */
  public void setStart_date(Date start_date) {
    this.start_date = start_date;
  }

  /**
   * Returns the Data from which the contract ends.
   *
   * @return the Data from which the contract ends.
   */
  public Date getEnd_date() {
    return end_date;
  }

  /**
   * Set Data from which the contract ends.
   *
   * @param end_date the Data from which the contract ends.
   */
  public void setEnd_date(Date end_date) {
    this.end_date = end_date;
  }

  /**
   * Returns Order confirm date.
   *
   * @return Order confirm date.
   */
  public Date getConfirm_date() {
    return confirm_date;
  }

  /**
   * Set the Order confirm date.
   *
   * @param confirm_date Order confirm date.
   */
  public void setConfirm_date(Date confirm_date) {
    this.confirm_date = confirm_date;
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
  public Date getCreation_date() {
    return creation_date;
  }

  /**
   * Set the Order creation Date.
   *
   * @param creation_date The order creation Date.
   */
  public void setCreation_date(Date creation_date) {
    this.creation_date = creation_date;
  }
}
