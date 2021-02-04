package com.easylease.easylease.model.car;

import java.util.List;

/* this interface is used for the communication
 * whit the DataBase*/
public interface CarDao {

  /**
   * This method retrieve a Car by his ID.
   *
   * @param id The Car ID to search for in the database.
   * @return The Car with the corresponding ID.
   */
  Car retrieveById(String id);

  /**
   * This method retrieve a Car by his model.
   *
   * @param model The Car model to search for in the database.
   * @return The Car with the corresponding model.
   */
  Car retrieveByModel(String model);

  /**
   * This method retrieve a list of Cars by their brand.
   *
   * @param brand The Car brand to search for in the database.
   * @return The list of Car with the corresponding brand.
   */
  List<Car> retrieveByBrand(String brand);

  /**
   * This method retrieve a list of Cars by their car's typology.
   *
   * @param carType The Car typology to search for in the database.
   * @return The list of Car with the corresponding car typology.
   */
  List<Car> retrieveByType(String carType);

  /**
   * This method retrieve all the Car in the DB.
   *
   * @return A List containing all the Car in the DB.
   */
  List<Car> retrieveAll();

  /**
   * This method update the parameters od an existing Car into the DB.
   *
   * @param car The new Car object containing new parameters to update into the DB.
   */
  void update(Car car);

  /**
   * This method delete a Car from the DB.
   *
   * @param car The Car which has to be deleted from the DB.
   */
  void delete(Car car);

  /**
   * This method insert in the DB a new Car.
   *
   * @param car The new Car which must be inserted into the DB.
   */
  void insert(Car car);
}

