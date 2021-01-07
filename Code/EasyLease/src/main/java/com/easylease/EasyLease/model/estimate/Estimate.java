package com.easylease.EasyLease.model.estimate;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.optional.Optional;

import java.sql.Date;
import java.util.List;

/**
 * This class models an object of type Estimate.
 *
 * @since 0.1
 * @author Sara Pepe
 * @version 0.1
 */


public class Estimate {
  private String id;
  private float price;
  private Client client;
  private Advisor advisor;
  private Car car;
  private int period;
  private List<Optional> optionalList;
  private boolean visibility;
  private String state;
  private Date responseDate;

  public Estimate() {}

  /**
   * Constructor for the Estimate Object.
   *
   * @param id is the ID who identifies the Estimate.
   * @param price is the price of the Estimate.
   * @param client is the client who requested the Estimate.
   * @param advisor is the advisor who compiled the Estimate.
   * @param car is the car for which the Estimate is required.
   * @param period is the time in month for which the car of the Estimate is required.
   * @param optionalList is the list of optionals that the Car shall have.
   * @param visibility tells you if the Estimate is still valid.
   */

  public Estimate(String id, float price, Client client, Advisor advisor, Car car,
                  int period, List<Optional> optionalList, boolean visibility,
                  String state, Date responseDate) {
    setId(id);
    setPrice(price);
    setClient(client);
    setAdvisor(advisor);
    setCar(car);
    setPeriod(period);
    setOptionalList(optionalList);
    setVisibility(visibility);
    setState(state);
    setResponseDate(responseDate);
  }

  /**
   * Set estimate's id.
   *
   * @param id is estimate's identifier.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Get estimate's identifier.
   *
   * @return id estimate's identifier.
   */
  public String getId() {
    return id;
  }

  /**
   * Get estimate's price.
   *
   * @return price estimate's price.
   */
  public float getPrice() {
    return price;
  }

  /**
   * Set estimate's price.
   *
   * @param price is estimate's price.
   */
  public void setPrice(float price) {
    this.price = price;
  }

  /**
   * Get estimate's advisor.
   *
   * @return advisor estimate's advisor.
   */
  public Advisor getAdvisor() {
    return advisor;
  }

  /**
   * Set estimate's advisor.
   *
   * @param advisor is estimate's advisor.
   */
  public void setAdvisor(Advisor advisor) {
    this.advisor = advisor;
  }

  /**
   * Get estimate's client.
   *
   * @return client estimate's client.
   */
  public Client getClient() {
    return client;
  }

  /**
   * Set estimate's client.
   *
   * @param client is estimate's client.
   */
  public void setClient(Client client) {
    this.client = client;
  }

  /**
   * Get estimate's car.
   *
   * @return car estimate's car.
   */
  public Car getCar() {
    return car;
  }

  /**
   * Set estimate's car.
   *
   * @param car is estimate's car.
   */
  public void setCar(Car car) {
    this.car = car;
  }

  /**
   * Get estimate's period.
   *
   * @return period estimate's period.
   */
  public int getPeriod() {
    return period;
  }

  /**
   * Set estimate's period.
   *
   * @param period is estimate's period.
   */
  public void setPeriod(int period) {
    this.period = period;
  }

  /**
   * Get estimate's optionals.
   *
   * @return optionalList estimate's optional list.
   */
  public List<Optional> getOptionalList() {
    return optionalList;
  }

  /**
   * Set the estimate's optionals.
   *
   * @param optionalList is estimate's optionals.
   */
  public void setOptionalList(List<Optional> optionalList) {
    this.optionalList = optionalList;
  }

  /**
   * Get estimate's visibility.
   *
   * @return visibility estimate's visibility.
   */
  public boolean isVisibility() {
    return visibility;
  }

  /**
   * Set estimate's visibility.
   *
   * @param visibility is estimate's visibility.
   */
  public void setVisibility(boolean visibility) {
    this.visibility = visibility;
  }

  /**
   * Get estimate's state.
   *
   * @return state estimate's state.
   */
  public String getState() {
    return state;
  }

  /**
   * Set estimate's state.
   *
   * @param state is estimate's state.
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * Get estimate's responseDate.
   *
   * @return responseDate estimate's responseDate.
   */
  public Date getResponseDate() {
    return responseDate;
  }

  /**
   * Set estimate's responseDate.
   *
   * @param responseDate is estimate's responseDate.
   */
  public void setResponseDate(Date responseDate) {
    this.responseDate = responseDate;
  }
}
