package com.easylease.EasyLease.model.optional;

/**
 * This class models an Optional Object.
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
  private boolean visibility;

  public Optional() {}

  /**
   * Constructor for the Optional Object.
   */
  public Optional(String type, String name, float price, String id, boolean visibility) {
    setType(type);
    setName(name);
    setPrice(price);
    setId(id);
    setVisibility(visibility);
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean isVisibility() {
    return visibility;
  }

  public void setVisibility(boolean visibility) {
    this.visibility = visibility;
  }
}
