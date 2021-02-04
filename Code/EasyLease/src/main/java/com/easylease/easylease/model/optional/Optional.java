package com.easylease.easylease.model.optional;

/**
 * This class models an object of type Optional.
 *
 * @since 0.1
 * @author Sara Pepe
 * @version 0.1
 */

public class Optional {
  private String optionalType;
  private String optionalName;
  private float price;
  private String optionalCode;

  /** Constructor. */
  public Optional() {}

  /**
   * Constructor for the Optional Object.
   *
   * @param optionalCode is the id who identifies the Optional.
   * @param optionalName is the name of the Optional.
   * @param price is the price of the Optional.
   * @param optionalType is the type of the Optional.
   */
  public Optional(String optionalCode, String optionalName, String optionalType, float price) {
    setOptionalType(optionalType);
    setOptionalName(optionalName);
    setPrice(price);
    setOptionalCode(optionalCode);
  }

  /**
   * Get the type of the optional.
   *
   * @return type, the type of the optional.
   */
  public String getOptionalType() {
    return optionalType;
  }

  /**
   * Set the type of the optional.
   *
   * @param optionalType is the type of the optional.
   */
  public void setOptionalType(String optionalType) {
    this.optionalType = optionalType;
  }

  /**
   * Get the name of the optional.
   *
   * @return name, the name of the optional.
   */
  public String getOptionalName() {
    return optionalName;
  }

  /**
   * Set the name of the optional.
   *
   * @param optionalName is the name of the optional.
   */
  public void setOptionalName(String optionalName) {
    this.optionalName = optionalName;
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
  public String getOptionalCode() {
    return optionalCode;
  }

  /**
   * Set the id of the optional.
   *
   * @param optionalCode is the identifier of the optional.
   */
  public void setOptionalCode(String optionalCode) {
    this.optionalCode = optionalCode;
  }

}
