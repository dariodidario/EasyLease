package com.easylease.EasyLease.model.optional;

/**
 * This class models an object of type Optional.
 *
 * @since 0.1
 * @author Sara Pepe
 * @version 0.1
 */

public class Optional {
  private String optional_type;
  private String optional_name;
  private float price;
  private String optional_code;

  /** Constructor. */
  public Optional() {}

  /**
   * Constructor for the Optional Object.
   *
   * @param optional_code is the id who identifies the Optional.
   * @param optional_name is the name of the Optional.
   * @param price is the price of the Optional.
   * @param optional_type is the type of the Optional.
   */
  public Optional(String optional_code, String optional_name, String optional_type, float price) {
    setOptional_type(optional_type);
    setOptional_name(optional_name);
    setPrice(price);
    setOptional_code(optional_code);
  }

  /**
   * Get the type of the optional.
   *
   * @return type, the type of the optional.
   */
  public String getOptional_type() {
    return optional_type;
  }

  /**
   * Set the type of the optional.
   *
   * @param optional_type is the type of the optional.
   */
  public void setOptional_type(String optional_type) {
    this.optional_type = optional_type;
  }

  /**
   * Get the name of the optional.
   *
   * @return name, the name of the optional.
   */
  public String getOptional_name() {
    return optional_name;
  }

  /**
   * Set the name of the optional.
   *
   * @param optional_name is the name of the optional.
   */
  public void setOptional_name(String optional_name) {
    this.optional_name = optional_name;
  }

  /**
   * Get the price of the optional.
   *
   * @return price, the price of the optional.
   */
  public float getPrice() {
    return price;
  }

  /**
   * Set the price of the optional.
   *
   * @param price is the price of the optional.
   */
  public void setPrice(float price) {
    this.price = price;
  }

  /**
   * Get the id of the optional.
   *
   * @return id, the identifier of the optional.
   */
  public String getOptional_code() {
    return optional_code;
  }

  /**
   * Set the id of the optional.
   *
   * @param optional_code is the identifier of the optional.
   */
  public void setOptional_code(String optional_code) {
    this.optional_code = optional_code;
  }

}
