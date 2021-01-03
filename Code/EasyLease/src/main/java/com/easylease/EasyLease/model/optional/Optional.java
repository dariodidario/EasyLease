package com.easylease.EasyLease.model.optional;

/**
 * This class models an object of type Optional.
 *
 * @since 0.1
 * @author Sara Pepe
 * @version 0.1
 */

public class Optional {
  private String type;
  private String name;
  private float price;
  private String id;

  /** Constructor. */
  public Optional() {}

  /**
   * Constructor for the Optional Object.
   *
   * @param id is the id who identifies the Optional.
   * @param name is the name of the Optional.
   * @param price is the price of the Optional.
   * @param type is the type of the Optional.
   */
  public Optional(String id, String name, String type, float price) {
    setType(type);
    setName(name);
    setPrice(price);
    setId(id);
  }

  /**
   * Get the type of the optional.
   *
   * @return type, the type of the optional.
   */
  public String getType() {
    return type;
  }

  /**
   * Set the type of the optional.
   *
   * @param type is the type of the optional.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Get the name of the optional.
   *
   * @return name, the name of the optional.
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the optional.
   *
   * @param name is the name of the optional.
   */
  public void setName(String name) {
    this.name = name;
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
  public String getId() {
    return id;
  }

  /**
   * Set the id of the optional.
   *
   * @param id is the identifier of the optional.
   */
  public void setId(String id) {
    this.id = id;
  }

}
