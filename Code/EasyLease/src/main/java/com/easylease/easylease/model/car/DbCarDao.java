package com.easylease.easylease.model.car;

import com.easylease.easylease.model.DBPool.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods for interacting with the customer database.
 *
 * @author Michele Attilio Iodice
 * @version 0.2
 * @since 0.1
 */
public class DbCarDao implements CarDao {
  private static final String TABLE_NAME = "car";
  private static DbCarDao dao;
  private Connection connection;


  /**
   * This method provides the connection with the DataBase.
   *
   * @return the connection with the Database.
   */
  public static CarDao getInstance() {
    if (dao == null) {
      dao = new DbCarDao(DbConnection.getInstance().getConnection());
    }
    return (CarDao) dao;
  }

  private DbCarDao(Connection connection) {
    this.connection = connection;
  }


  @Override
  public synchronized Car retrieveById(String id) {
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException();
    }
    PreparedStatement preparedStatement = null;

    Car car = null;

    String selectSql =
        "SELECT * FROM " + DbCarDao.TABLE_NAME + " WHERE id_car = ?";

    try {
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, id);

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) { //extraction of the data from the db
        car = new Car();
        car.setIdCar(rs.getString("id_car"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setPrice(rs.getFloat("price"));
        car.setType(rs.getString("car_type"));
        car.setVisibility(rs.getBoolean("visibility"));
        car.setDoors(rs.getInt("doors"));
        car.setTransmission(rs.getString("transmission"));
        car.setAvgConsumption(rs.getFloat("avg_consumption"));
        car.setHorsePower(rs.getInt("horse_power"));
        car.setEmissionClass(rs.getString("emission_class"));
        car.setCo2Emissions(rs.getInt("co2_emissions"));
        car.setPowerSupply(rs.getString("power_supply"));
        car.setCapacity(rs.getInt("cc"));
        car.setImage(rs.getString("img_path"));
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    return car;
  }


  @Override
  public synchronized Car retrieveByModel(String model) {
    if (model == null || model.equals("")) {
      throw new IllegalArgumentException();
    }
    PreparedStatement preparedStatement = null;

    Car car = null;

    String selectSql =
        "SELECT * FROM " + DbCarDao.TABLE_NAME + " WHERE model LIKE ?";

    try {
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, model);

      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) { //extraction of the data from the db
        car = new Car();
        car.setIdCar(rs.getString("id_car"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setPrice(rs.getFloat("price"));
        car.setType(rs.getString("car_type"));
        car.setVisibility(rs.getBoolean("visibility"));
        car.setDoors(rs.getInt("doors"));
        car.setTransmission(rs.getString("transmission"));
        car.setAvgConsumption(rs.getFloat("avg_consumption"));
        car.setHorsePower(rs.getInt("horse_power"));
        car.setEmissionClass(rs.getString("emission_class"));
        car.setCo2Emissions(rs.getInt("co2_emissions"));
        car.setPowerSupply(rs.getString("power_supply"));
        car.setCapacity(rs.getInt("cc"));
        car.setImage(rs.getString("img_path"));
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    return car;
  }


  @Override
  public synchronized List<Car> retrieveByBrand(String brand) {
    if (brand == null || brand.equals("")) {
      throw new IllegalArgumentException();
    }
    PreparedStatement preparedStatement = null;
    ArrayList<Car> cars = new ArrayList<>();
    String selectSql =
        "SELECT * FROM " + DbCarDao.TABLE_NAME + " WHERE brand LIKE ?";
    selectSql += " ORDER BY id_car";

    try {
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, brand);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Car car = new Car();

        car.setIdCar(rs.getString("id_car"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setPrice(rs.getFloat("price"));
        car.setType(rs.getString("car_type"));
        car.setVisibility(rs.getBoolean("visibility"));
        car.setDoors(rs.getInt("doors"));
        car.setTransmission(rs.getString("transmission"));
        car.setAvgConsumption(rs.getFloat("avg_consumption"));
        car.setHorsePower(rs.getInt("horse_power"));
        car.setEmissionClass(rs.getString("emission_class"));
        car.setCo2Emissions(rs.getInt("co2_emissions"));
        car.setPowerSupply(rs.getString("power_supply"));
        car.setCapacity(rs.getInt("cc"));
        car.setImage(rs.getString("img_path"));
        cars.add(car);
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    List<Car> list = cars;
    return list;
  }


  @Override
  public synchronized List<Car> retrieveByType(String carType) {
    if (carType == null || carType.equals("")) {
      throw new IllegalArgumentException();
    }
    PreparedStatement preparedStatement = null;
    ArrayList<Car> cars = new ArrayList<>();
    String selectSql =
        "SELECT * FROM " + DbCarDao.TABLE_NAME + " WHERE car_type LIKE ?";
    selectSql += " ORDER BY id_car";

    try {
      preparedStatement = connection.prepareStatement(selectSql);
      preparedStatement.setString(1, carType);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Car car = new Car();

        car.setIdCar(rs.getString("id_car"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setPrice(rs.getFloat("price"));
        car.setType(rs.getString("car_type"));
        car.setVisibility(rs.getBoolean("visibility"));
        car.setDoors(rs.getInt("doors"));
        car.setTransmission(rs.getString("transmission"));
        car.setAvgConsumption(rs.getFloat("avg_consumption"));
        car.setHorsePower(rs.getInt("horse_power"));
        car.setEmissionClass(rs.getString("emission_class"));
        car.setCo2Emissions(rs.getInt("co2_emissions"));
        car.setPowerSupply(rs.getString("power_supply"));
        car.setCapacity(rs.getInt("cc"));
        car.setImage(rs.getString("img_path"));
        cars.add(car);
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    List<Car> list = cars;
    return list;
  }


  @Override
  public synchronized List<Car> retrieveAll() {
    PreparedStatement preparedStatement = null;
    ArrayList<Car> cars = new ArrayList<>();
    String selectSql = "SELECT * FROM " + DbCarDao.TABLE_NAME;
    selectSql += " ORDER BY id_car";

    try {
      preparedStatement = connection.prepareStatement(selectSql);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Car car = new Car();

        car.setIdCar(rs.getString("id_car"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setPrice(rs.getFloat("price"));
        car.setType(rs.getString("car_type"));
        car.setVisibility(rs.getBoolean("visibility"));
        car.setDoors(rs.getInt("doors"));
        car.setTransmission(rs.getString("transmission"));
        car.setAvgConsumption(rs.getFloat("avg_consumption"));
        car.setHorsePower(rs.getInt("horse_power"));
        car.setEmissionClass(rs.getString("emission_class"));
        car.setCo2Emissions(rs.getInt("co2_emissions"));
        car.setPowerSupply(rs.getString("power_supply"));
        car.setCapacity(rs.getInt("cc"));
        car.setImage(rs.getString("img_path"));
        cars.add(car);
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    List<Car> list = cars;
    return list;
  }


  @Override
  public synchronized void update(Car car) {
    if (car == null) {
      throw new IllegalArgumentException();
    }
    PreparedStatement preparedStatement = null;

    String deleteSql = "UPDATE " + DbCarDao.TABLE_NAME
        + " SET id_car = ?, brand = ?, model = ?, price = ?,"
        + " car_type = ?, visibility = ?, doors = ?, transmission = ?,"
        + " avg_consumption = ?, horse_power = ?, emission_class = ?,"
        + " co2_emissions = ?, power_supply = ?, cc = ?, img_path = ?"
        + " WHERE id_car = ?";

    try {
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setString(1, car.getIdCar());
      preparedStatement.setString(2, car.getBrand());
      preparedStatement.setString(3, car.getModel());
      preparedStatement.setFloat(4, car.getPrice());
      preparedStatement.setString(5, car.getType());
      preparedStatement.setBoolean(6, car.getVisibility());
      preparedStatement.setInt(7, car.getDoors());
      preparedStatement.setString(8, car.getTransmission());
      preparedStatement.setFloat(9, car.getAvgConsumption());
      preparedStatement.setInt(10, car.getHorsePower());
      preparedStatement.setString(11, car.getEmissionClass());
      preparedStatement.setInt(12, car.getCo2Emissions());
      preparedStatement.setString(13, car.getPowerSupply());
      preparedStatement.setInt(14, car.getCapacity());
      preparedStatement.setString(15, car.getImage());
      preparedStatement.setString(16, car.getIdCar());

      preparedStatement.executeUpdate();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
  }


  @Override
  public synchronized void delete(Car car) {
    if (car == null) {
      throw new IllegalArgumentException();
    }
    PreparedStatement preparedStatement = null;

    String deleteSql =
        "DELETE FROM " + DbCarDao.TABLE_NAME + " WHERE id_car = ?";

    try {
      preparedStatement = connection.prepareStatement(deleteSql);
      preparedStatement.setString(1, car.getIdCar());

      preparedStatement.executeUpdate();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
  }


  @Override
  public synchronized void insert(Car car) {
    if (car == null) {
      throw new IllegalArgumentException();
    }
    PreparedStatement preparedStatement = null;

    String insertSql = "INSERT INTO " + DbCarDao.TABLE_NAME
        +
        "(id_car, brand, model, price, car_type, power_supply, img_path,"
        + " visibility, doors, transmission, avg_consumption, horse_power,"
        + " emission_class, co2_emissions, cc) VALUES ( ?, ?, ?, ?, ?, ?, ?,"
        + " ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, car.getIdCar());
      preparedStatement.setString(2, car.getBrand());
      preparedStatement.setString(3, car.getModel());
      preparedStatement.setFloat(4, car.getPrice());
      preparedStatement.setString(5, car.getType());
      preparedStatement.setString(6, car.getPowerSupply());
      preparedStatement.setString(7, car.getImage());
      preparedStatement.setBoolean(8, car.getVisibility());
      preparedStatement.setInt(9, car.getDoors());
      preparedStatement.setString(10, car.getTransmission());
      preparedStatement.setFloat(11, car.getAvgConsumption());
      preparedStatement.setInt(12, car.getHorsePower());
      preparedStatement.setString(13, car.getEmissionClass());
      preparedStatement.setInt(14, car.getCo2Emissions());
      preparedStatement.setInt(15, car.getCapacity());

      preparedStatement.executeUpdate();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
  }
}
