package com.easylease.EasyLease.model.user;

import com.easylease.EasyLease.control.utility.exception.EntityTamperingException;
import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;
import com.easylease.EasyLease.model.order.OrderDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the UserDAO interface, using the singleton DBConnection
 * as the DataBase.
 *
 * @author Francesco Maria Torino
 * @since 0.1
 * @version 0.1
 */

public class DBUserDAO implements UserDAO{

  private static Logger logger = Logger.getLogger(DBUserDAO.class.getName());
  private static DBUserDAO dao;
  private Connection connection;
  private static final String TABLE_NAME = "user";

  /**
   * Returns a DBOrderDAO Singleton Object.
   *
   * @return the {@link DBUserDAO} Object that accesses the {@link User} object
   *      in the Database.
   */
  public static DBUserDAO getInstance() {
    if (dao == null) {
      dao = new DBUserDAO(DBConnection.getInstance().getConnection());
    }
    return dao;
  }

  private DBUserDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public User retrieveById(String id) {
    final String query = "SELECT * FROM " + DBUserDAO.TABLE_NAME + " WHERE ID = ?";
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException(
          String.format("The id(%s) passed as a parameter is not valid", id));
    }
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, id);
      stm.execute();

      ResultSet rs = stm.getResultSet();
      if (!rs.next()) {
        return null;
      }
      User bean = new User();

      while (rs.next()) {
        bean.setId(rs.getString("ID"));
        bean.setName(rs.getString("NAME"));
        bean.setSurname(rs.getString("SURNAME"));
        bean.setEmail(rs.getString("EMAIL"));
        bean.setPassword(rs.getString("PASSWORD"));
      }
      return bean;
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      return null;
    }
  }

  @Override
  public List<User> retrieveByType(String tipo) {
    final String query = "SELECT * FROM " + tipo;
    if (tipo.equals("")) {
      throw new IllegalArgumentException(
          String.format("The id(%s) passed as a parameter is not valid", tipo));
    }
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, tipo);
      stm.execute();

      ResultSet rs = stm.getResultSet();
      if (!rs.next()) {
        return null;
      }
      List<User>  users = new ArrayList<User>();

      while (rs.next()) {
        User bean = new User();

        bean.setId(rs.getString("ID"));
        bean.setName(rs.getString("NAME"));
        bean.setSurname(rs.getString("SURNAME "));
        bean.setEmail(rs.getString("EMAIL"));
        bean.setPassword(rs.getString("PASSWORD"));
        users.add(bean);
      }
      return users;
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      return null;
    }
  }

  @Override
  public User retrieveByEmail(String email)  {
    final String query = "SELECT * FROM " + DBUserDAO.TABLE_NAME + " WHERE EMAIL = ?";
    if (email == null || email.equals("")) {
      throw new IllegalArgumentException(
          String.format("The id(%s) passed as a parameter is not valid", email));
    }
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, email);
      stm.execute();

      ResultSet rs = stm.getResultSet();
      if (!rs.next()) {
        return null;
      }
      User bean = new User();

      while (rs.next()) {
        bean.setId(rs.getString("ID"));
        bean.setName(rs.getString("NAME"));
        bean.setSurname(rs.getString("SURNAME"));
        bean.setEmail(rs.getString("EMAIL"));
        bean.setPassword(rs.getString("PASSWORD"));
      }
      return bean;
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      return null;
    }
  }

  @Override
  public List<User> retrieveAll()  {
    final String query = "SELECT * FROM " + DBUserDAO.TABLE_NAME;
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.execute();

      ResultSet rs = stm.getResultSet();
      if (!rs.next()) {
        return null;
      }
      List<User>  users = new ArrayList<User>();

      while (rs.next()) {
        User bean = new User();

        bean.setId(rs.getString("ID"));
        bean.setName(rs.getString("NAME"));
        bean.setSurname(rs.getString("SURNAME "));
        bean.setEmail(rs.getString("EMAIL"));
        bean.setPassword(rs.getString("PASSWORD"));
        users.add(bean);
      }
      return users;
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      return null;
    }
  }


  @Override
  public void insert(User user) throws EntityTamperingException {
    final String query = "INSERT INTO " + DBUserDAO.TABLE_NAME
        + " (ID, NOME, COGNOME, EMAIL, PASSWORD) VALUES (?,?,?,?,?)";

    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, user.getId());
      stm.setString(2, user.getName());
      stm.setString(3, user.getSurname());
      stm.setString(4, user.getEmail());
      stm.setString(5, user.getPassword());
      stm.executeUpdate();
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      throw new EntityTamperingException("Already existing Order!");
    }
  }

  @Override
  public void update(User user) throws EntityTamperingException {
    final String query = "UPDATE "+ DBUserDAO.TABLE_NAME+" SET name = ?, surname = ?, email = ?, password = ? WHERE ID = ?";

    try {
      PreparedStatement stm = connection.prepareStatement(query);

      stm.setString(1, user.getName());
      stm.setString(2, user.getSurname());
      stm.setString(3, user.getEmail());
      stm.setString(4, user.getPassword());
      stm.setString(5, user.getId());
      stm.executeUpdate();
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      throw new EntityTamperingException("Already existing Order!");
    }
  }

  @Override
  public void delete(User user) throws EntityTamperingException {
    final String query = "DELETE FROM " + DBUserDAO.TABLE_NAME + " WHERE ID = ?";

    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, user.getId());
      stm.executeUpdate();
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      throw new EntityTamperingException("Order does not exist!");
    }
  }

}

