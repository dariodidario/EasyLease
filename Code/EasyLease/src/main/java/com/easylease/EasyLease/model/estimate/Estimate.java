package com.easylease.EasyLease.model.estimate;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.optional.Optional;

import java.util.Date;
import java.util.List;

/**
 * This class models an object of type Estimate.
 *
 * @since 0.1
 * @author Sara Pepe
 * @version 0.1
 */


public class Estimate {
  private String id_estimate;
  private float price;
  private Client client;
  private Advisor advisor;
  private Car car;
  private int period;
  private List<Optional> optionalList;
  private boolean visibility;
  private String state;
  private Date response_date;
  private Date request_date;

  public Estimate() {}

  /**
   * Constructor for the Estimate Object.
   *
   * @param id_estimate is the ID who identifies the Estimate.
   * @param price is the price of the Estimate.
   * @param client is the client who requested the Estimate.
   * @param advisor is the advisor who compiled the Estimate.
   * @param car is the car for which the Estimate is required.
   * @param period is the time in month for which the car of the Estimate is required.
   * @param optionalList is the list of optionals that the Car shall have.
   * @param visibility tells you if the Estimate is still valid.
   */

  public Estimate(String id_estimate, float price, Client client, Advisor advisor, Car car,
                  int period, List<Optional> optionalList, boolean visibility,
                  String state, Date request_date, Date response_date) {
    setId_estimate(id_estimate);
    setPrice(price);
    setClient(client);
    setAdvisor(advisor);
    setCar(car);
    setPeriod(period);
    setOptionalList(optionalList);
    setVisibility(visibility);
    setState(state);
    setRequest_date(request_date);
    setResponse_date(response_date);
  }

  /**
   * Set estimate's id.
   *
   * @param id_estimate is estimate's identifier.
   */
  public void setId_estimate(String id_estimate) {
    this.id_estimate = id_estimate;
  }

  /**
   * Get estimate's identifier.
   *
   * @return id estimate's identifier.
   */
  public String getId_estimate() {
    return id_estimate;
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
  public Date getResponse_date() {
    return response_date;
  }

  /**
   * Set estimate's responseDate.
   *
   * @param response_date is estimate's responseDate.
   */
  public void setResponse_date(Date response_date) {
    this.response_date = response_date;
  }

  /**
   * Get estimate's requestDate.
   *
   * @return requestDate estimate's requestDate.
   */
  public  Date getRequest_date() {
    return request_date;

  }

  /**
   * Set estimate's requestDate.
   *
   * @param request_date is estimate's requestDate.
   */
  public void setRequest_date(Date request_date) {
    this.request_date = request_date;
  }

}
