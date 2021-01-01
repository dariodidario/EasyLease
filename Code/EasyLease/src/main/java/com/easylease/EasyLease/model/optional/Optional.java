package com.easylease.EasyLease.model.optional;

/**
<<<<<<< Updated upstream
 * This class models an Optional Object.
=======
 * This class models an object of type Optional.
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
  private boolean visibility;

=======

  /** Constructor. */
>>>>>>> Stashed changes
  public Optional() {}

  /**
   * Constructor for the Optional Object.
<<<<<<< Updated upstream
   */
  public Optional(String type, String name, float price, String id, boolean visibility) {
=======
   *
   * @param id is the id who identifies the Optional.
   * @param name is the name of the Optional.
   * @param price is the price of the Optional.
   * @param type is the type of the Optional.
   */
  public Optional(String type, String name, float price, String id) {
>>>>>>> Stashed changes
    setType(type);
    setName(name);
    setPrice(price);
    setId(id);
<<<<<<< Updated upstream
    setVisibility(visibility);
  }

=======
  }

  /**
   * Get the type of the optional.
   *
   * @return type, the type of the optional.
   */
>>>>>>> Stashed changes
  public String getType() {
    return type;
  }

<<<<<<< Updated upstream
=======
  /**
   * Set the type of the optional.
   *
   * @param type is the type of the optional.
   */
>>>>>>> Stashed changes
  public void setType(String type) {
    this.type = type;
  }

<<<<<<< Updated upstream
=======
  /**
   * Get the name of the optional.
   *
   * @return name, the name of the optional.
   */
>>>>>>> Stashed changes
  public String getName() {
    return name;
  }

<<<<<<< Updated upstream
=======
  /**
   * Set the name of the optional.
   *
   * @param name is the name of the optional.
   */
>>>>>>> Stashed changes
  public void setName(String name) {
    this.name = name;
  }

<<<<<<< Updated upstream
=======
  /**
   * Get the price of the optional.
   *
   * @return price, the price of the optional.
   */
>>>>>>> Stashed changes
  public float getPrice() {
    return price;
  }

<<<<<<< Updated upstream
=======
  /**
   * Set the price of the optional.
   *
   * @param price is the price of the optional.
   */
>>>>>>> Stashed changes
  public void setPrice(float price) {
    this.price = price;
  }

<<<<<<< Updated upstream
=======
  /**
   * Get the id of the optional.
   *
   * @return id, the identifier of the optional.
   */
>>>>>>> Stashed changes
  public String getId() {
    return id;
  }

<<<<<<< Updated upstream
=======
  /**
   * Set the id of the optional.
   *
   * @param id is the identifier of the optional.
   */
>>>>>>> Stashed changes
  public void setId(String id) {
    this.id = id;
  }

<<<<<<< Updated upstream
  public boolean isVisibility() {
    return visibility;
  }

  public void setVisibility(boolean visibility) {
    this.visibility = visibility;
  }
=======
>>>>>>> Stashed changes
}
