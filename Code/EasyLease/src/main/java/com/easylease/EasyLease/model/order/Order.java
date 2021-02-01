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
  private String idOrder;
  /** Estimate Object linked to the following Order. */
  private Estimate estimate;
  /** Data from which the contract begins. */
  private Date startDate;
  /** Data from which the contract ends. */
  private Date endDate;
  /** Order confirm date. */
  private Date confirmDate;
  /** Indicates whether the order is visible or not. */
  private boolean visibility;
  /** Current status of the Order. */
  private String state;
  /** Order creation date. */
  private Date creationDate;

  public Order() {

  }

  /**
   * Constructor for the Order Object.
   *
   * @param idOrder Order ID.
   * @param estimate Estimate Object linked to the following Order.
   * @param startDate Date from which the contract begins.
   * @param endDate Data from which the contract ends.
   * @param confirmDate Order confirm date.
   * @param creationDate Order creation date.
   * @param visibility Indicates whether the order is visible or not.
   * @param state Current state of the Order.
   */
  public Order(
      String idOrder, Estimate estimate, Date startDate, Date endDate, Date confirmDate,
      Date creationDate, boolean visibility, String state) {

    this.idOrder = idOrder;
    this.estimate = estimate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.confirmDate = confirmDate;
    this.visibility = visibility;
    this.state = state;
    this.creationDate = creationDate;
  }

  /**
   * Returns the ID of Order.
   *
   * @return Order ID.
   */
  public String getIdOrder() {
    return idOrder;
  }

  /**
   * Set the ID of the Order.
   *
   * @param idOrder Order ID.
   */
  public void setIdOrder(String idOrder) {
    this.idOrder = idOrder;
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
   * Returns Order confirm date.
   *
   * @return Order confirm date.
   */
  public Date getConfirmDate() {
    return confirmDate;
  }

  /**
   * Set the Order confirm date.
   *
   * @param confirmDate Order confirm date.
   */
  public void setConfirmDate(Date confirmDate) {
    this.confirmDate = confirmDate;
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
}
