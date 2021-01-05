package com.easylease.EasyLease.model.car;

import com.easylease.EasyLease.model.DBPool.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * This class provides methods for interacting with the customer database.
 *
 * @since 0.1
 * @author Michele Attilio Iodice
 * @version 0.2
 */
public class DBCarDAO implements CarDAO{
    private static final String TABLE_NAME = "car";
    private static DBCarDAO dao;
    private Connection connection;



    /**
     * This method provides the connection with the DataBase.
     *
     * @return the connection with the Database.
     */
    public static CarDAO getInstance() {
        if (dao == null) {
            dao = new DBCarDAO(DBConnection.getInstance().getConnection());
        }
        return (CarDAO) dao;
    }

    private DBCarDAO(Connection connection) {
        this.connection = connection;
    }



    @Override
    public synchronized Car retrieveById(String id) {
        if (id == null || id.equals("")) {
            throw new IllegalArgumentException();
        }
        PreparedStatement preparedStatement = null;

        Car car = new Car();

        String selectSQL = "SELECT * FROM " + DBCarDAO.TABLE_NAME + " WHERE id_car = ?";

        try {
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){//extraction of the data from the db
                car.setId(rs.getString("id_car"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setPrice(rs.getFloat("price"));
                car.setType(rs.getString("car_type"));
                car.setVisibility(rs.getBoolean("visibility"));
                car.setDoors(rs.getInt("doors"));
                car.setTransmission(rs.getString("transmission"));
                car.setAvg_consumption(rs.getFloat("avg_consumption"));
                car.setHorse_power(rs.getInt("horse_power"));
                car.setEmission_class(rs.getString("emission_class"));
                car.setCo2_emissions(rs.getInt("co2_emissions"));
                car.setPowerSupply(rs.getString("power_supply"));
                car.setCapacity(rs.getInt("cc"));
                car.setImage(rs.getString("img_path"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
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

        Car car = new Car();

        String selectSQL = "SELECT * FROM " + DBCarDAO.TABLE_NAME + " WHERE model LIKE ?";

        try {
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, model);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {//extraction of the data from the db
                car.setId(rs.getString("id_car"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setPrice(rs.getFloat("price"));
                car.setType(rs.getString("car_type"));
                car.setVisibility(rs.getBoolean("visibility"));
                car.setDoors(rs.getInt("doors"));
                car.setTransmission(rs.getString("transmission"));
                car.setAvg_consumption(rs.getFloat("avg_consumption"));
                car.setHorse_power(rs.getInt("horse_power"));
                car.setEmission_class(rs.getString("emission_class"));
                car.setCo2_emissions(rs.getInt("co2_emissions"));
                car.setPowerSupply(rs.getString("power_supply"));
                car.setCapacity(rs.getInt("cc"));
                car.setImage(rs.getString("img_path"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
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
        ArrayList<Car> cars=new ArrayList<>();
        String selectSQL = "SELECT * FROM " + DBCarDAO.TABLE_NAME +" WHERE BRAND LIKE ?";
        selectSQL += " ORDER BY ID";


        try {
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, brand);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Car car = new Car();

                car.setId(rs.getString("id_car"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setPrice(rs.getFloat("price"));
                car.setType(rs.getString("car_type"));
                car.setVisibility(rs.getBoolean("visibility"));
                car.setDoors(rs.getInt("doors"));
                car.setTransmission(rs.getString("transmission"));
                car.setAvg_consumption(rs.getFloat("avg_consumption"));
                car.setHorse_power(rs.getInt("horse_power"));
                car.setEmission_class(rs.getString("emission_class"));
                car.setCo2_emissions(rs.getInt("co2_emissions"));
                car.setPowerSupply(rs.getString("power_supply"));
                car.setCapacity(rs.getInt("cc"));
                car.setImage(rs.getString("img_path"));
                cars.add(car);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        List<Car> list=cars;
        return list;
    }



    @Override
    public synchronized List<Car> retrieveByType(String type) {
        if (type == null || type.equals("")) {
            throw new IllegalArgumentException();
        }
        PreparedStatement preparedStatement = null;
        ArrayList<Car> cars=new ArrayList<>();
        String selectSQL = "SELECT * FROM " + DBCarDAO.TABLE_NAME +" WHERE CAR_TYPE LIKE ?";
        selectSQL += " ORDER BY ID";


        try {
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, type);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Car car = new Car();

                car.setId(rs.getString("id_car"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setPrice(rs.getFloat("price"));
                car.setType(rs.getString("car_type"));
                car.setVisibility(rs.getBoolean("visibility"));
                car.setDoors(rs.getInt("doors"));
                car.setTransmission(rs.getString("transmission"));
                car.setAvg_consumption(rs.getFloat("avg_consumption"));
                car.setHorse_power(rs.getInt("horse_power"));
                car.setEmission_class(rs.getString("emission_class"));
                car.setCo2_emissions(rs.getInt("co2_emissions"));
                car.setPowerSupply(rs.getString("power_supply"));
                car.setCapacity(rs.getInt("cc"));
                car.setImage(rs.getString("img_path"));
                cars.add(car);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        List<Car> list=cars;
        return list;
    }



    @Override
    public synchronized List<Car> retrieveAll() {
        PreparedStatement preparedStatement = null;
        ArrayList<Car> cars=new ArrayList<>();
        String selectSQL = "SELECT * FROM " + DBCarDAO.TABLE_NAME;
        selectSQL += " ORDER BY ID";


        try {
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Car car = new Car();

                car.setId(rs.getString("id_car"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setPrice(rs.getFloat("price"));
                car.setType(rs.getString("car_type"));
                car.setVisibility(rs.getBoolean("visibility"));
                car.setDoors(rs.getInt("doors"));
                car.setTransmission(rs.getString("transmission"));
                car.setAvg_consumption(rs.getFloat("avg_consumption"));
                car.setHorse_power(rs.getInt("horse_power"));
                car.setEmission_class(rs.getString("emission_class"));
                car.setCo2_emissions(rs.getInt("co2_emissions"));
                car.setPowerSupply(rs.getString("power_supply"));
                car.setCapacity(rs.getInt("cc"));
                car.setImage(rs.getString("img_path"));
                cars.add(car);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        List<Car> list=cars;
        return list;
    }



    @Override
    public synchronized void update(Car car) {
        if (car == null) {
            throw new IllegalArgumentException();
        }
        PreparedStatement preparedStatement = null;


        String deleteSQL = "UPDATE " + DBCarDAO.TABLE_NAME + "SET id_car = ?, brand = ?, model = ?, price = ?, car_type = ?, visibility = ?, doors = ?, transmission = ?, avg_consumption = ?, horse_power = ?, emission_class = ?, co2_emissions = ?, power_supply = ?, cc = ?, img_path = ? WHERE id_car = ?";

        try {
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, car.getId());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setFloat(4, car.getPrice());
            preparedStatement.setString(5, car.getType());
            preparedStatement.setBoolean(6, car.getVisibility());
            preparedStatement.setInt(7, car.getDoors());
            preparedStatement.setString(8, car.getTransmission());
            preparedStatement.setFloat(9, car.getAvg_consumption());
            preparedStatement.setInt(10, car.getHorse_power());
            preparedStatement.setString(11, car.getEmission_class());
            preparedStatement.setInt(12, car.getCo2_emissions());
            preparedStatement.setString(13, car.getPowerSupply());
            preparedStatement.setInt(14, car.getCapacity());
            preparedStatement.setString(15, car.getImage());
            preparedStatement.setString(16, car.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
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



        String deleteSQL = "DELETE FROM " + DBCarDAO.TABLE_NAME + " WHERE id_car = ?";

        try {
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, car.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
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

        String insertSQL = "INSERT INTO " + DBCarDAO.TABLE_NAME
                + " car(id_car, brand, model, price, car_type, power_supply, img_path, visibility, doors, transmission, avg_consumption, horse_power, emission_class, co2_emissions, cc) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, car.getId());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setFloat(4, car.getPrice());
            preparedStatement.setString(5, car.getType());
            preparedStatement.setString(6, car.getPowerSupply());
            preparedStatement.setString(7, car.getImage());
            preparedStatement.setBoolean(8, car.getVisibility());
            preparedStatement.setInt(9, car.getDoors());
            preparedStatement.setString(10, car.getTransmission());
            preparedStatement.setFloat(11, car.getAvg_consumption());
            preparedStatement.setInt(12, car.getHorse_power());
            preparedStatement.setString(13, car.getEmission_class());
            preparedStatement.setInt(14, car.getCo2_emissions());
            preparedStatement.setInt(15, car.getCapacity());


            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
