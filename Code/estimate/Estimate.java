package com.easylease.EasyLease.model.estimate;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.optional.Optional;
import java.util.List;

/**
 * This class models an Estimate Object.
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

  public Estimate() {}

  /**
   * Constructor for the Estimate Object.
   */

  public Estimate(String id, float price, Client client, Advisor advisor, Car car, int period,
                  List<Optional> optionalList, boolean visibility) {
    setId(id);
    setPrice(price);
    setClient(client);
    setAdvisor(advisor);
    setCar(car);
    setPeriod(period);
    setOptionalList(optionalList);
    setVisibility(visibility);
  }


  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public Advisor getAdvisor() {
    return advisor;
  }

  public void setAdvisor(Advisor advisor) {
    this.advisor = advisor;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public int getPeriod() {
    return period;
  }

  public void setPeriod(int period) {
    this.period = period;
  }

  public List<Optional> getOptionalList() {
    return optionalList;
  }

  public void setOptionalList(
      List<Optional> optionalList) {
    this.optionalList = optionalList;
  }

  public boolean isVisibility() {
    return visibility;
  }

  public void setVisibility(boolean visibility) {
    this.visibility = visibility;
  }
}
