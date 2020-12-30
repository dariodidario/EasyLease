package com.easylease.EasyLease.model.car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*the class provides the implementation of the methods for the communication with Database*/
public class DBCarDAO implements CarDAO{
    private static final String TABLE_NAME = "car";



    /*this method is used to return the car present into the database
    that have the same id passed as a parameter

    the type of return is Car*/
    @Override
    public synchronized Car retriveById(String id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Car car = new Car();

        String selectSQL = "SELECT * FROM " + DBCarDAO.TABLE_NAME + " WHERE ID = ?";//query sql for the select

        try { //set up of the connection with db
            //connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs!=null){//extraction of the data from the db
                car.setId(rs.getString("ID"));
                car.setBrand(rs.getString("BRAND"));
                car.setModel(rs.getString("MODEL"));
                car.setPrice(rs.getFloat("PRICE"));
                car.setType(rs.getString("CAR_TYPE"));
                car.setVisibility(rs.getBoolean("VISIBILITY"));
                car.setDoors(rs.getInt("DOORS"));
                car.setTrasmision(rs.getString("TRANSMISSION"));
                car.setAvg_consumption(rs.getFloat("AVG_CONSUPTION"));
                car.setHorse_power(rs.getInt("HORSE_POWER"));
                car.setEmission_class(rs.getString("EMISSION_CLASS"));
                car.setCo2_emissions(rs.getInt("CO2_EMISSIONS"));
                car.setPowerSupply(rs.getString("POWER_SUPPLY"));
                car.setCapacity(rs.getInt("CAPACITY"));
                car.setImage(rs.getString("IMAGE"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {//closing the connection
                //DBConnection.resetConnection(connection);
            }
        }
        return car;
    }


    /*this method is used to return the car present into the database
    which is the same model as that passed as a parameter

    the type of return is Car*/
    @Override
    public synchronized Car retriveByModel(String model) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Car car = new Car();

        String selectSQL = "SELECT * FROM " + DBCarDAO.TABLE_NAME + " WHERE MODEL LIKE ?";//query sql for the select

        try {//set up of the connection with the db
            //connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, model);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs!=null) {//extraction of the data from the db
                car.setId(rs.getString("ID"));
                car.setBrand(rs.getString("BRAND"));
                car.setModel(rs.getString("MODEL"));
                car.setPrice(rs.getFloat("PRICE"));
                car.setType(rs.getString("CAR_TYPE"));
                car.setVisibility(rs.getBoolean("VISIBILITY"));
                car.setDoors(rs.getInt("DOORS"));
                car.setTrasmision(rs.getString("TRANSMISSION"));
                car.setAvg_consumption(rs.getFloat("AVG_CONSUPTION"));
                car.setHorse_power(rs.getInt("HORSE_POWER"));
                car.setEmission_class(rs.getString("EMISSION_CLASS"));
                car.setCo2_emissions(rs.getInt("CO2_EMISSIONS"));
                car.setPowerSupply(rs.getString("POWER_SUPPLY"));
                car.setCapacity(rs.getInt("CAPACITY"));
                car.setImage(rs.getString("IMAGE"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {//closing connection
                //DBConnection.resetConnection(connection);
            }
        }
        return car;
    }


    /*this method is used to return the cars present into the database
    which is the same brand as that passed as a parameter

    the type of return is a List of Cars*/
    @Override
    public synchronized List<Car> retriveByBrand(String brand) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Car> cars = new List<Car>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Car> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Car car) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Car> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Car> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Car get(int index) {
                return null;
            }

            @Override
            public Car set(int index, Car element) {
                return null;
            }

            @Override
            public void add(int index, Car element) {

            }

            @Override
            public Car remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Car> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Car> listIterator(int index) {
                return null;
            }

            @Override
            public List<Car> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        String selectSQL = "SELECT * FROM " + DBCarDAO.TABLE_NAME +" WHERE BRAND LIKE ?";//query sql for the select
        selectSQL += " ORDER BY ID";


        try {//set up of the connection with the database
            //connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, brand);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {//extraction of the data from the db
                Car car = new Car();

                car.setId(rs.getString("ID"));
                car.setBrand(rs.getString("BRAND"));
                car.setModel(rs.getString("MODEL"));
                car.setPrice(rs.getFloat("PRICE"));
                car.setType(rs.getString("CAR_TYPE"));
                car.setVisibility(rs.getBoolean("VISIBILITY"));
                car.setDoors(rs.getInt("DOORS"));
                car.setTrasmision(rs.getString("TRANSMISSION"));
                car.setAvg_consumption(rs.getFloat("AVG_CONSUPTION"));
                car.setHorse_power(rs.getInt("HORSE_POWER"));
                car.setEmission_class(rs.getString("EMISSION_CLASS"));
                car.setCo2_emissions(rs.getInt("CO2_EMISSIONS"));
                car.setPowerSupply(rs.getString("POWER_SUPPLY"));
                car.setCapacity(rs.getInt("CAPACITY"));
                car.setImage(rs.getString("IMAGE"));
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
            } finally {//closing connection
                //DBConnection.resetConnection(connection);
            }
        }
        return cars;
    }


    /*this method is used to return the cars present into the database
    which is the same type of car as that passed as a parameter

    the type of return is a List of Cars*/
    @Override
    public synchronized List<Car> retriveByType(String type) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Car> cars = new List<Car>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Car> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Car car) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Car> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Car> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Car get(int index) {
                return null;
            }

            @Override
            public Car set(int index, Car element) {
                return null;
            }

            @Override
            public void add(int index, Car element) {

            }

            @Override
            public Car remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Car> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Car> listIterator(int index) {
                return null;
            }

            @Override
            public List<Car> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        String selectSQL = "SELECT * FROM " + DBCarDAO.TABLE_NAME +" WHERE CAR_TYPE LIKE ?";//query sql for the select
        selectSQL += " ORDER BY ID";


        try {//set up of the connection with the db
            //connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, type);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {//extraction of the data from db
                Car car = new Car();

                car.setId(rs.getString("ID"));
                car.setBrand(rs.getString("BRAND"));
                car.setModel(rs.getString("MODEL"));
                car.setPrice(rs.getFloat("PRICE"));
                car.setType(rs.getString("CAR_TYPE"));
                car.setVisibility(rs.getBoolean("VISIBILITY"));
                car.setDoors(rs.getInt("DOORS"));
                car.setTrasmision(rs.getString("TRANSMISSION"));
                car.setAvg_consumption(rs.getFloat("AVG_CONSUPTION"));
                car.setHorse_power(rs.getInt("HORSE_POWER"));
                car.setEmission_class(rs.getString("EMISSION_CLASS"));
                car.setCo2_emissions(rs.getInt("CO2_EMISSIONS"));
                car.setPowerSupply(rs.getString("POWER_SUPPLY"));
                car.setCapacity(rs.getInt("CAPACITY"));
                car.setImage(rs.getString("IMAGE"));
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
            } finally {//closing connection
                //DBConnection.resetConnection(connection);
            }
        }
        return cars;
    }


    /*this method is used to return all the cars present into the Database

    the type of return is a List of Cars*/
    @Override
    public synchronized List<Car> retriveAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        List<Car> cars = new List<Car>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Car> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Car car) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Car> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Car> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Car get(int index) {
                return null;
            }

            @Override
            public Car set(int index, Car element) {
                return null;
            }

            @Override
            public void add(int index, Car element) {

            }

            @Override
            public Car remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Car> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Car> listIterator(int index) {
                return null;
            }

            @Override
            public List<Car> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        String selectSQL = "SELECT * FROM " + DBCarDAO.TABLE_NAME;
        selectSQL += " ORDER BY ID";//query sql for the select


        try {//set up of the connection with the db
            //connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {//extraction of the data from db
                Car car = new Car();

                car.setId(rs.getString("ID"));
                car.setBrand(rs.getString("BRAND"));
                car.setModel(rs.getString("MODEL"));
                car.setPrice(rs.getFloat("PRICE"));
                car.setType(rs.getString("CAR_TYPE"));
                car.setVisibility(rs.getBoolean("VISIBILITY"));
                car.setDoors(rs.getInt("DOORS"));
                car.setTrasmision(rs.getString("TRANSMISSION"));
                car.setAvg_consumption(rs.getFloat("AVG_CONSUPTION"));
                car.setHorse_power(rs.getInt("HORSE_POWER"));
                car.setEmission_class(rs.getString("EMISSION_CLASS"));
                car.setCo2_emissions(rs.getInt("CO2_EMISSIONS"));
                car.setPowerSupply(rs.getString("POWER_SUPPLY"));
                car.setCapacity(rs.getInt("CAPACITY"));
                car.setImage(rs.getString("IMAGE"));
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
            } finally {//closing connection
                //DBConnection.resetConnection(connection);
            }
        }
        return cars;
    }


    /*this method is used to update a pre-existing car into the database
    identified by the ID of the car passed as a parameter


    there isn't a return*/
    @Override
    public synchronized void update(Car car) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        //query sql for the car's update
        String deleteSQL = "UPDATE " + DBCarDAO.TABLE_NAME + "SET ID = ?, BRAND = ?, MODEL = ?, PRICE = ?, CAR_TYPE = ?, VISIBILITY = ?, PORTE = ?, TRANSMISSION = ?, AVG_CONSUMPTION = ?, HORSE_POWER = ?, EMISSION_CLASS = ?, CO2_EMISSIONI = ?, POWER_SUPPLY = ?, CAPACITY = ?, IMAGE = ? WHERE ID = ?";

        try {//set up of the connection with the database
            //connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, car.getId());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setFloat(4, car.getPrice());
            preparedStatement.setString(5, car.getType());
            preparedStatement.setBoolean(6, car.getVisibility());
            preparedStatement.setInt(7, car.getDoors());
            preparedStatement.setString(8, car.getTrasmision());
            preparedStatement.setFloat(9, car.getAvg_consumption());
            preparedStatement.setInt(10, car.getHorse_power());
            preparedStatement.setString(11, car.getEmission_class());
            preparedStatement.setInt(12, car.getCo2_emissions());
            preparedStatement.setString(13, car.getPowerSupply());
            preparedStatement.setInt(14, car.getCapacity());
            preparedStatement.setString(15, car.getImage());
            preparedStatement.setString(16, car.getId());

            result = preparedStatement.executeUpdate();//update execution

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {//closing connection
                //DBConnection.resetConnection(connection);
            }
        }
    }

    /*this method is used to delete a pre-existing car into the database
    identified by the ID of the car passed as a parameter


    there isn't a return*/
    @Override
    public synchronized void delete(Car car) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + DBCarDAO.TABLE_NAME + " WHERE ID = ?";//query sql for the car's delete

        try {//set up of the connection with the db
            //connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, car.getId());

            result = preparedStatement.executeUpdate();//delete execution

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {//closing connection
                //DBConnection.resetConnection(connection);
            }
        }
    }


    /*this method is used to insert into the database the car
    passed as a parameter


    there isn't a return*/
    @Override
    public synchronized void insert(Car car) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + DBCarDAO.TABLE_NAME
                + " (ID, BRAND, MODEL, PRICE, CAR_TYPE, VISIBILITY, DOORS, TRANSMISSION, AVG_CONSUMPTION, HORSE_POWER, EMISSION_CLASS, CO2_EMISSIONS, POWER_SUPPLY, CAPACITY, IMAGE) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";//query sql for the insert

        try {//set up of the connection
            //connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, car.getId());
            preparedStatement.setString(2, car.getBrand());
            preparedStatement.setString(3, car.getModel());
            preparedStatement.setFloat(4, car.getPrice());
            preparedStatement.setString(5, car.getType());
            preparedStatement.setBoolean(6, car.getVisibility());
            preparedStatement.setInt(7, car.getDoors());
            preparedStatement.setString(8, car.getTrasmision());
            preparedStatement.setFloat(9, car.getAvg_consumption());
            preparedStatement.setInt(10, car.getHorse_power());
            preparedStatement.setString(11, car.getEmission_class());
            preparedStatement.setInt(12, car.getCo2_emissions());
            preparedStatement.setString(13, car.getPowerSupply());
            preparedStatement.setInt(14, car.getCapacity());
            preparedStatement.setString(15, car.getImage());

            preparedStatement.executeUpdate();//insert execution

            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {//closing connection
                //DBConnection.resetConnection(connection);
            }
        }
    }
}
