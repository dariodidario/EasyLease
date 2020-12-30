package com.easylease.EasyLease.model.user;

import com.easylease.EasyLease.model.DBPool.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBUserDAO implements UserDAO{

  private static final String TABLE_NAME = "user";

  public synchronized User retrieveById(String Id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    User bean = new User();

    String selectSQL = "SELECT * FROM " + DBUserDAO.TABLE_NAME + " WHERE ID = ?";

    try {
      connection = ConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setString(1, Id);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setId(rs.getString("ID"));
        bean.setName(rs.getString("NAME"));
        bean.setSurname(rs.getString("SURNAME"));
        bean.setEmail(rs.getString("EMAIL"));
        bean.setPassword(rs.getString("PASSWORD"));
      }

    } finally {
      try {
        if (preparedStatement != null)
          preparedStatement.close();
      } finally {
        ConnectionPool.releaseConnection(connection);
      }
    }
    return bean;
  }

  public synchronized List<User> retrieveByType(Enum tipo) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<User>  users = new ArrayList<User>();

    String selectSQL = "SELECT * FROM " + DBUserDAO.TABLE_NAME + " WHERE TYPE = ?";

    try {
      connection = ConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setString(1, tipo.name());

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        User bean = new User();

        bean.setId(rs.getString("ID"));
        bean.setName(rs.getString("NAME"));
        bean.setSurname(rs.getString("SURNAME "));
        bean.setEmail(rs.getString("EMAIL"));
        bean.setPassword(rs.getString("PASSWORD"));
        users.add(bean);
      }

    } finally {
      try {
        if (preparedStatement != null)
          preparedStatement.close();
      } finally {
        ConnectionPool.releaseConnection(connection);
      }
    }
    return users;
  }

  public synchronized User retrieveByEmail(String Email) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    User bean = new User();

    String selectSQL = "SELECT * FROM " + DBUserDAO.TABLE_NAME + " WHERE EMAIL = ?";

    try {
      connection = ConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setString(1, Email);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        bean.setId(rs.getString("ID"));
        bean.setName(rs.getString("NAME"));
        bean.setSurname(rs.getString("SURNAME"));
        bean.setEmail(rs.getString("EMAIL"));
        bean.setPassword(rs.getString("PASSWORD"));
      }

    } finally {
      try {
        if (preparedStatement != null)
          preparedStatement.close();
      } finally {
        ConnectionPool.releaseConnection(connection);
      }
    }
    return bean;
  }

  public synchronized List<User> retrieveAll() throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    List<User>  users = new ArrayList<User>();

    String selectSQL = "SELECT * FROM " + DBUserDAO.TABLE_NAME;

    try {
      connection = ConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(selectSQL);

      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        User bean = new User();

        bean.setId(rs.getString("ID"));
        bean.setName(rs.getString("NAME"));
        bean.setSurname(rs.getString("SURNAME "));
        bean.setEmail(rs.getString("EMAIL"));
        bean.setPassword(rs.getString("PASSWORD"));
        users.add(bean);
      }

    } finally {
      try {
        if (preparedStatement != null)
          preparedStatement.close();
      } finally {
        ConnectionPool.releaseConnection(connection);
      }
    }
    return users;
  }

  public synchronized void insert(User user) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String insertSQL = "INSERT INTO " + DBUserDAO.TABLE_NAME
        + " (ID, NOME, COGNOME, EMAIL, PASSWIRD) VALUES (?,?,?,?,?)";

    try {
      connection = ConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(insertSQL);
      preparedStatement.setString(1, user.getId());
      preparedStatement.setString(2, user.getName());
      preparedStatement.setString(3, user.getSurname());
      preparedStatement.setString(4, user.getEmail());
      preparedStatement.setString(5, user.getPassword());
      preparedStatement.executeUpdate();
      connection.commit();
    } finally {
      try {
        if (preparedStatement != null)
          preparedStatement.close();
      } finally {
        ConnectionPool.releaseConnection(connection);
      }
    }
  }

  public synchronized void update(User bean) throws SQLException {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String updateSQL ="UPDATE "+ DBUserDAO.TABLE_NAME+" SET name = ?, surname = ?, email = ?, password = ? WHERE ID = ?";

    try {
      connection = ConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(updateSQL);
      preparedStatement.setString(1, bean.getName());
      preparedStatement.setString(2, bean.getSurname());
      preparedStatement.setString(3, bean.getEmail());
      preparedStatement.setString(4, bean.getPassword());
      preparedStatement.setString(5, bean.getId());
      preparedStatement.executeUpdate();

    } finally {
      try {
        if (preparedStatement != null)
          preparedStatement.close();
      } finally {
        ConnectionPool.releaseConnection(connection);
      }
    }
  }

  public synchronized void delete(String id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    String deleteSQL = "DELETE FROM " + DBUserDAO.TABLE_NAME + " WHERE ID = ?";

    try {
      connection = ConnectionPool.getConnection();
      preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setString(1, id);
      preparedStatement.executeUpdate();

    } finally {
      try {
        if (preparedStatement != null)
          preparedStatement.close();
      } finally {
        ConnectionPool.releaseConnection(connection);
      }
    }
  }

}

