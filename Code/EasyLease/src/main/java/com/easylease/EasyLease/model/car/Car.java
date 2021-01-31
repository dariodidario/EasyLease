package com.easylease.EasyLease.model.car;

/**
 * This class provides the information about the car of the EasyLease platform,
 *
 * @author Michele Attilio Iodice
 * @version 0.2
 * @since 0.1
 */

public class Car {
  private String idCar;
  private String brand;
  private String model;
  private float price;
  private String carType;
  private boolean visibility;
  private int doors;
  private String transmission;
  private float avgConsumption;
  private int horsePower;
  private String emissionClass;
  private int co2Emissions;
  private String powerSupply;
  private int cc;
  private String imgPath;

  /**
   * This is the Empty constructor for the Client object.
   */
  public Car() {
  }

  /**
   * This is the Constructor of the Client Object.
   *
   * @param idCar          the id  of the car
   * @param brand           the brand  of the car
   * @param model           the model of the car
   * @param price           the price of the car
   * @param carType        the typology of the car
   * @param visibility      the visibility of the car into the catalogue
   * @param doors           the number of the car doors
   * @param transmission    the type of car transmission
   * @param avgConsumption the average consumption of the car
   * @param horsePower     the horse power of the car
   * @param emissionClass  the CO2 emission class of the car
   * @param co2Emissions   the quantity of CO2 emissions  of the car
   * @param powerSupply    the type of car's power
   * @param cc              the engine capacity of the car
   * @param image           the path of car's image
   */
  public Car(
      String idCar, String brand, String model, float price, String carType,
      boolean visibility, int doors, String transmission, float avgConsumption,
      int horsePower, String emissionClass, int co2Emissions,
      String powerSupply, int cc, String image) {
    setIdCar(idCar);
    setBrand(brand);
    setModel(model);
    setPrice(price);
    setType(carType);
    setVisibility(visibility);
    setDoors(doors);
    setTransmission(transmission);
    setAvgConsumption(avgConsumption);
    setHorsePower(horsePower);
    setEmissionClass(emissionClass);
    setCo2Emissions(co2Emissions);
    setPowerSupply(powerSupply);
    setCapacity(cc);
    setImage(image);
  }

  /**
   * Returns the id of the car.
   *
   * @return the id of the car.
   */
  public String getIdCar() {
    return this.idCar;
  }

  /**
   * Set the id of the car.
   *
   * @param idNew the new id of the car.
   */
  public void setIdCar(String idNew) {
    this.idCar = idNew;
  }

  /**
   * Returns the Brand of the car.
   *
   * @return the brand of the car.
   */
  public String getBrand() {
    return this.brand;
  }

  /**
   * Set the brand of the car.
   *
   * @param brandNew the new id of the car.
   */
  public void setBrand(String brandNew) {
    this.brand = brandNew;
  }

  /**
   * Returns the model of the car.
   *
   * @return the model of the car.
   */
  public String getModel() {
    return this.model;
  }

  /**
   * Set the model of the car.
   *
   * @param modelNew the new model of the car.
   */
  public void setModel(String modelNew) {
    this.model = modelNew;
  }

  /**
   * Returns the price of the car.
   *
   * @return the price of the car.
   */
  public float getPrice() {
    return this.price;
  }

  /**
   * Set the price of the car.
   *
   * @param priceNew the new price of the car.
   */
  public void setPrice(float priceNew) {
    this.price = priceNew;
  }

  /**
   * Returns the typology of the car.
   *
   * @return the type of the car.
   */
  public String getType() {
    return this.carType;
  }

  /**
   * Set the typology of the car.
   *
   * @param carTypeNew the new type of the car.
   */
  public void setType(String carTypeNew) {
    this.carType = carTypeNew;
  }

  /**
   * Returns the type of the car's power.
   *
   * @return the type of the car's power.
   */
  public String getPowerSupply() {
    return this.powerSupply;
  }

  /**
   * Set the power of the car.
   *
   * @param powerSupplyNew the new power supply of the car.
   */
  public void setPowerSupply(String powerSupplyNew) {
    this.powerSupply = powerSupplyNew;
  }

  /**
   * Returns the visibility of the car into the catalogue.
   *
   * @return the visibility of the car.
   */
  public boolean getVisibility() {
    return this.visibility;
  }

  /**
   * Set the visibility of the car.
   *
   * @param visibilityNew the new visibility of the car.
   */
  public void setVisibility(boolean visibilityNew) {
    this.visibility = visibilityNew;
  }

  /**
   * Returns the number of the car doors.
   *
   * @return the number of the car doors.
   */
  public int getDoors() {
    return this.doors;
  }

  /**
   * Set the number  of the car doors.
   *
   * @param doorsNew the new number of the car doors.
   */
  public void setDoors(int doorsNew) {
    this.doors = doorsNew;
  }

  /**
   * Returns the type of the car transmission.
   *
   * @return the type of the car transmission.
   */
  public String getTransmission() {
    return this.transmission;
  }

  /**
   * Set the type  of the car transmission.
   *
   * @param transmissionNew the new transmission of the car.
   */
  public void setTransmission(String transmissionNew) {
    this.transmission = transmissionNew;
  }

  /**
   * Returns the average consumption of the car.
   *
   * @return the average consumption of the car.
   */
  public float getAvgConsumption() {
    return this.avgConsumption;
  }

  /**
   * Set the average consumption of the car.
   *
   * @param avgConsumptionNew the new average consumption of the car.
   */
  public void setAvgConsumption(float avgConsumptionNew) {
    this.avgConsumption = avgConsumptionNew;
  }

  /**
   * Returns the horse power of the car.
   *
   * @return the horse power of the car.
   */
  public int getHorsePower() {
    return this.horsePower;
  }

  /**
   * Set the horse power of the car.
   *
   * @param horsePowerNew the new horse power of the car.
   */
  public void setHorsePower(int horsePowerNew) {
    this.horsePower = horsePowerNew;
  }

  /**
   * Returns the emission class of the car.
   *
   * @return the emission class of the car.
   */
  public String getEmissionClass() {
    return this.emissionClass;
  }

  /**
   * Set the emission class of the car.
   *
   * @param emissionClassNew the new emission class of the car.
   */
  public void setEmissionClass(String emissionClassNew) {
    this.emissionClass = emissionClassNew;
  }

  /**
   * Returns the quantity of CO2 emissions of the car.
   *
   * @return the quantity of CO2 emissions of the car.
   */
  public int getCo2Emissions() {
    return this.co2Emissions;
  }

  /**
   * Set the quantity of CO2 emissions of the car.
   *
   * @param co2EmissionsNew the new quantity of CO2 emissions of the car.
   */
  public void setCo2Emissions(int co2EmissionsNew) {
    this.co2Emissions = co2EmissionsNew;
  }

  /**
   * Returns the engine capacity of the car.
   *
   * @return the engine capacity of the car.
   */
  public int getCapacity() {
    return this.cc;
  }

  /**
   * Set the engine capacity of the car.
   *
   * @param ccNew the new engine capacity of the car.
   */
  public void setCapacity(int ccNew) {
    this.cc = ccNew;
  }

  /**
   * Returns the path of the car image.
   *
   * @return the path of the car image.
   */
  public String getImage() {
    return this.imgPath;
  }

  /**
   * Set the path of the car image.
   *
   * @param imageNew the new path of the car image.
   */
  public void setImage(String imageNew) {
    this.imgPath = imageNew;
  }

}
