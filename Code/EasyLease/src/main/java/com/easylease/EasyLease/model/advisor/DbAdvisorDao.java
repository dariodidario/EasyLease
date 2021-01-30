package com.easylease.EasyLease.model.advisor;

import com.easylease.EasyLease.control.utility.PasswordHashing;
import com.easylease.EasyLease.model.DBPool.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the AdvisorDAO interface, using the singleton DBConnection
 * as the DataBase.
 *
 * @author Caprio Mattia
 * @since 0.1
 * @version 0.5
 */
public class DbAdvisorDao implements AdvisorDao {
  private static final Logger logger = Logger.getLogger(DbAdvisorDao.class.getName());
  private static DbAdvisorDao dao;
  private final Connection connection;

  /**
   * Returns a DBAdvisorDAO Singleton Object.
   *
   * @return the {@link DbAdvisorDao} Object that accesses the {@link Advisor}
   *     object in the DataBase.
   */
  public static AdvisorDao getInstance() {
    if (dao == null) {
      dao = new DbAdvisorDao(DbConnection.getInstance().getConnection());
    }
    return dao;
  }

  /**
   * Constructor for the DBAdvisorDAO object.
   *
   * @param connection the {@link Connection} needed to create the {@link PreparedStatement}.
   */
  private DbAdvisorDao(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Advisor retrieveById(String id) {
    final String query = "SELECT * FROM users WHERE account_type ='Consulente' AND id_user = ?";
    if (id == null || id.equals("") || !id.startsWith("AD") || id.length() != 7) {
      throw new IllegalArgumentException(
          String.format("The id(%s) passed as a parameter is not valid", id));
    }
    return singleRetrieve(query, id);
  }

  @Override
  public Advisor retrieveByEmail(String email) {
    final String query = "SELECT * FROM users WHERE account_type ='Consulente' AND email = ?";
    if (email == null || email.equals("")) {
      throw new IllegalArgumentException(
          String.format("The email(%s) passed as a parameter is not valid", email));
    }
    return singleRetrieve(query, email);
  }

  @Override
  public String retrievePasswordByEmail(String email) throws SQLException {
    final String query = "SELECT pwd FROM users WHERE account_type ='Consulente' AND email = ?";
    if (email == null || email.equals("")) {
      throw new IllegalArgumentException(
          String.format("The email(%s) passed as a parameter is not valid", email));
    }
    PreparedStatement stm = connection.prepareStatement(query);
    stm.setString(1, email);
    stm.execute();
    ResultSet rs = stm.getResultSet();
    if (!rs.next()) {
      return null;
    }
    return rs.getString("pwd");
  }

  @Override
  public List<Advisor> retrieveAll() {
    final String query = "SELECT * FROM users WHERE account_type ='Consulente'";
    List<Advisor> advisors = new ArrayList<>();
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.execute();
      ResultSet rs = stm.getResultSet();
      while (rs.next()) {
        String id = rs.getString("id_user");
        String name = rs.getString("first_name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        Date hireDate = rs.getDate("hire_date");
        advisors.add(new Advisor(id, name, surname, email, hireDate));
      }
      return advisors;
    } catch (SQLException throwables) {
      logger.log(Level.SEVERE, throwables.getMessage());
      return null;
    }
  }

  @Override
  public void insert(Advisor advisor, String password) {
    if (advisor == null || password == null) {
      throw new IllegalArgumentException();
    }
    if(DbAdvisorDao.getInstance().retrieveById(advisor.getId()) != null){
      throw new IllegalArgumentException("Advisor already exitsts.");
    }
    final String query = "INSERT INTO users (id_user, first_name, surname, email,"
        + " pwd, account_type, hire_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, advisor.getId());
      stm.setString(2, advisor.getName());
      stm.setString(3, advisor.getSurname());
      stm.setString(4, advisor.getEmail());
      stm.setString(5, PasswordHashing.generatePassword(password, "SHA-1"));
      stm.setString(6, "Consulente");
      stm.setDate(7, (java.sql.Date) advisor.getHireDate());
      stm.executeUpdate();
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
    }
  }

  @Override
  public void update(Advisor advisor, String password) {
    if (advisor == null || password == null) {
      throw new IllegalArgumentException();
    }
    if(DbAdvisorDao.getInstance().retrieveById(advisor.getId()) == null){
      throw new IllegalArgumentException("Advisor doesn't exitst.");
    }
    String hashedPassword = PasswordHashing.generatePassword(password, "SHA-1");
    final String query = "UPDATE users "
        + "SET first_name = ?, surname = ?, email = ?, pwd = ?, hire_date = ? "
        + "WHERE id_user = ?";
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, advisor.getName());
      stm.setString(2, advisor.getSurname());
      stm.setString(3, advisor.getEmail());
      stm.setString(4, hashedPassword);
      stm.setDate(5, (java.sql.Date) advisor.getHireDate());
      stm.setString(6, advisor.getId());
      stm.executeUpdate();
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
    }
  }

  @Override
  public void delete(Advisor advisor) {
    if (advisor == null) {
      throw new IllegalArgumentException();
    }
    if(DbAdvisorDao.getInstance().retrieveById(advisor.getId()) == null){
      throw new IllegalArgumentException("Advisor doesn't exitst.");
    }
    final String query = "DELETE FROM users WHERE id_user = ?";
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, advisor.getId());
      stm.executeUpdate();
    } catch (SQLException throwables) {
      logger.log(Level.SEVERE, throwables.getMessage());
    }
  }


  /**
   * Returns the {@link Advisor} object present in Database.
   *
   * @param query the {@link String} of the query to do.
   * @param s the {@link String} to set in the {@link PreparedStatement}.
   * @return the {@link Advisor} object present in Database..
   */
  private Advisor singleRetrieve(String query, String s) {
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, s);
      stm.execute();
      ResultSet rs = stm.getResultSet();
      if (!rs.next()) {
        return null;
      }

      String id = rs.getString("id_user");
      String name = rs.getString("first_name");
      String surname = rs.getString("surname");
      String email = rs.getString("email");
      Date hireDate = rs.getDate("hire_date");
      return new Advisor(id, name, surname, email, hireDate);
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
      return null;
    }
  }
}
