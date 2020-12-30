package com.easylease.EasyLease.model.car;

import java.sql.SQLException;
import java.util.List;

/* this interface is used for the communication
* whit the DataBase*/
public interface CarDAO {

    Car retriveById(String id) throws SQLException;
    Car retriveByModel(String model) throws SQLException;
    List<Car> retriveByBrand(String brand) throws SQLException;
    List<Car> retriveByType(String car_type) throws SQLException;
    List<Car> retriveAll() throws SQLException;
    void update(Car car) throws SQLException;
    void delete(Car car) throws SQLException;
    void insert(Car car) throws SQLException;
}
