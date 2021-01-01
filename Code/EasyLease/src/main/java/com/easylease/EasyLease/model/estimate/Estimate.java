package com.easylease.EasyLease.model.estimate;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.optional.Optional;
import java.util.List;

/**
<<<<<<< Updated upstream
 * This class models an Estimate Object.
=======
 * This class models an object of type Estimate.
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======
   *
   * @param id is the ID who identifies the Estimate.
   * @param price is the price of the Estimate.
   * @param client is the client who requested the Estimate.
   * @param advisor is the advisor who compiled the Estimate.
   * @param car is the car for which the Estimate is required.
   * @param period is the time in month for which the car of the Estimate is required.
   * @param optionalList is the list of optionals that the Car shall have.
   * @param visibility tells you if the Estimate is still valid.
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream

=======
  /**
   * Set estimate's id.
   *
   * @param id is estimate's identifier.
   */
>>>>>>> Stashed changes
  public void setId(String id) {
    this.id = id;
  }

<<<<<<< Updated upstream
=======
  /**
   * Get estimate's identifier.
   *
   * @return id estimate's identifier.
   */
>>>>>>> Stashed changes
  public String getId() {
    return id;
  }

<<<<<<< Updated upstream
=======
  /**
   * Get estimate's price.
   *
   * @return price estimate's price.
   */
>>>>>>> Stashed changes
  public float getPrice() {
    return price;
  }

<<<<<<< Updated upstream
=======
  /**
   * Set estimate's price.
   *
   * @param price is estimate's price.
   */
>>>>>>> Stashed changes
  public void setPrice(float price) {
    this.price = price;
  }

<<<<<<< Updated upstream
=======
  /**
   * Get estimate's advisor.
   *
   * @return advisor estimate's advisor.
   */
>>>>>>> Stashed changes
  public Advisor getAdvisor() {
    return advisor;
  }

<<<<<<< Updated upstream
=======
  /**
   * Set estimate's advisor.
   *
   * @param advisor is estimate's advisor.
   */
>>>>>>> Stashed changes
  public void setAdvisor(Advisor advisor) {
    this.advisor = advisor;
  }

<<<<<<< Updated upstream
=======
  /**
   * Get estimate's client.
   *
   * @return client estimate's client.
   */
>>>>>>> Stashed changes
  public Client getClient() {
    return client;
  }

<<<<<<< Updated upstream
=======
  /**
   * Set estimate's client.
   *
   * @param client is estimate's client.
   */
>>>>>>> Stashed changes
  public void setClient(Client client) {
    this.client = client;
  }

<<<<<<< Updated upstream
=======
  /**
   * Get estimate's car.
   *
   * @return car estimate's car.
   */
>>>>>>> Stashed changes
  public Car getCar() {
    return car;
  }

<<<<<<< Updated upstream
=======
  /**
   * Set estimate's car.
   *
   * @param car is estimate's car.
   */
>>>>>>> Stashed changes
  public void setCar(Car car) {
    this.car = car;
  }

<<<<<<< Updated upstream
=======
  /**
   * Get estimate's period.
   *
   * @return period estimate's period.
   */
>>>>>>> Stashed changes
  public int getPeriod() {
    return period;
  }

<<<<<<< Updated upstream
=======
  /**
   * Set estimate's period.
   *
   * @param period is estimate's period.
   */
>>>>>>> Stashed changes
  public void setPeriod(int period) {
    this.period = period;
  }

<<<<<<< Updated upstream
=======
  /**
   * Get estimate's optionals.
   *
   * @return optionalList estimate's optional list.
   */
>>>>>>> Stashed changes
  public List<Optional> getOptionalList() {
    return optionalList;
  }

<<<<<<< Updated upstream
  public void setOptionalList(
      List<Optional> optionalList) {
    this.optionalList = optionalList;
  }

=======
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
>>>>>>> Stashed changes
  public boolean isVisibility() {
    return visibility;
  }

<<<<<<< Updated upstream
=======
  /**
   * Set estimate's visibility.
   *
   * @param visibility is estimate's visibility.
   */
>>>>>>> Stashed changes
  public void setVisibility(boolean visibility) {
    this.visibility = visibility;
  }
}
