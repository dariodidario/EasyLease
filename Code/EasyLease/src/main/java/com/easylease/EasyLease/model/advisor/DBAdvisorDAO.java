package com.easylease.EasyLease.model.advisor;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.admin.DBAdminDAO;
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
 * @version 0.3
 */
public class DBAdvisorDAO implements AdvisorDAO {
  private static final Logger logger = Logger.getLogger(DBAdminDAO.class.getName());
  private static DBAdvisorDAO dao;
  private final Connection connection;

  /**
   * Returns a DBAdvisorDAO Singleton Object.
   *
   * @return the {@link DBAdvisorDAO} Object that accesses the {@link Advisor}
   *     object in the DataBase.
   */
  public static AdvisorDAO getIstance() {
    if (dao == null) {
      dao = new DBAdvisorDAO(DBConnection.getInstance().getConnection());
    }
    return dao;
  }

  /**
   * Constructor for the DBAdvisorDAO object.
   *
   * @param connection the {@link Connection} needed to create the {@link PreparedStatement}.
   */
  private DBAdvisorDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Advisor retrieveById(String id) {
    final String query = "SELECT * FROM users WHERE account_type ='Consulente' AND id_user = ?";
    if (id == null || id.equals("")) {
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
        advisors.add(getAdvisorFromRs(rs));
      }
      return advisors;
    } catch (SQLException throwables) {
      logger.log(Level.SEVERE, throwables.getMessage());
      return null;
    }
  }

  @Override
  public void update(Advisor advisor) {
    final String query = "INSERT INTO users (id_user, first_name, surname, email, pwd,"
        + "account_type, hire_date, birth_place, birth_date, kind, street, city, pc"
        + ") VALUES (?, ?, ?, ?, ?, ?, ?, null, null, null, null, null, null)";
    try {
      executeQuery(advisor, query);
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
    }
  }

  @Override
  public void insert(Advisor advisor) {
    final String query = "INSERT INTO users (id_user, first_name, surname, email, pwd,"
        + "account_type, hire_date, birth_place, birth_date, kind, street, city, pc"
        + ") VALUES (?, ?, ?, ?, ?, ?, ?, null, null, null, null, null, null)";
    try {
      executeQuery(advisor, query);
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
    }
  }

  @Override
  public void delete(Advisor advisor) {
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
   * Returns the {@link Advisor} object created by the ResultSet.
   *
   * @param rs the {@link ResultSet}.
   * @return the {@link Advisor} returned from the ResultSet.
   * @throws SQLException if the ResultSet is null.
   */
  private Advisor getAdvisorFromRs(ResultSet rs) throws SQLException {
    String id = rs.getString("id_user");
    String name = rs.getString("first_name");
    String surname = rs.getString("surname");
    String email = rs.getString("email");
    Date hireDate = rs.getDate("hire_date");
    return new Advisor(id, name, surname, email, hireDate);
  }

  /**
   * Executes the update and insert queries passed.
   *
   * @param advisor the {@link Advisor}.
   * @param query the String of the query to do.
   * @throws SQLException if cant set an attribute on the PreparedStatement.
   */
  private void executeQuery(Advisor advisor, String query) throws SQLException {
    PreparedStatement stm = connection.prepareStatement(query);
    stm.setString(1, advisor.getId());
    stm.setString(2, advisor.getName());
    stm.setString(3, advisor.getSurname());
    stm.setString(4, advisor.getEmail());
    stm.setString(5, "Advisor");
    stm.setDate(6, (java.sql.Date) advisor.getHireDate());
    stm.executeUpdate();
  }

  /**
   * Returns the {@link Advisor} object returned by {@link #getAdvisorFromRs(ResultSet) method}.
   *
   * @param query the String of the query to do.
   * @param s the String to set in the {@link PreparedStatement}.
   * @return the {@link Advisor} object returned by {@link #getAdvisorFromRs(ResultSet) method}.
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
      return getAdvisorFromRs(rs);
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
      return null;
    }
  }
}
