package com.easylease.EasyLease.model.admin;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the AdminDAO interface, using the singleton DBConnection
 * as the database.
 *
 * @author Antonio Sarro
 * @version 0.1
 * @since 0.1
 */
public class DBAdminDAO implements AdminDAO {

  private static final Logger logger = Logger.getLogger(
      DBAdminDAO.class.getName());
  private static DBAdminDAO dao;
  private final Connection connection;

  /**
   * Returns a DBAdminDAO Singleton Object.
   *
   * @return the {@link DBAdminDAO} Object that accesses the {@link Admin} object
   *     in the Database.
   */
  public static AdminDAO getInstance() {
    if (dao == null) {
      dao = new DBAdminDAO(DBConnection.getInstance().getConnection());
    }
    return dao;
  }

  private DBAdminDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Admin retrieveById(String id) {
    final String query = "SELECT * FROM users WHERE id_user = ? AND account_type = ?";
    return getAdmin(id, query);
  }

  @Override
  public Admin retrieveByEmail(String email) {
    final String query = "SELECT * FROM users WHERE email = ? AND account_type = ?";
    return getAdmin(email, query);
  }

  @Override
  public String retrievePasswordByEmail(String email) {
    final String query = "SELECT pwd FROM users WHERE account_type = ? AND email = ?";
    PreparedStatement stm;
    ResultSet rs;

    if (email == null) {
      throw new IllegalArgumentException("The email entered is null!");
    }

    try {
      stm = connection.prepareStatement(query);
      stm.setString(1, "Amministratore");
      stm.setString(2, email);

      rs = stm.executeQuery();

      if (rs == null) {
        return null;
      }

      if (!rs.next()) {
        return null;
      }

      return rs.getString("pwd");

    } catch (SQLException exception) {
      logger.log(Level.SEVERE, exception.getMessage());
    }
    return null;
  }

  @Override
  public List<Admin> retrieveAll() {
    final String query = "SELECT * FROM users WHERE account_type = ?";
    PreparedStatement stm;
    ResultSet rs;

    List<Admin> admins = new ArrayList<>();

    try {
      stm = connection.prepareStatement(query);
      stm.setString(1, "Amministratore");
      stm.execute();

      rs = stm.getResultSet();
      while (rs.next()) {
        admins.add(getAdminFromRs(rs));
      }

      return admins;
    } catch (SQLException exception) {
      logger.log(Level.SEVERE, exception.getMessage());
    }
    return null;
  }

  /**
   * Returns the {@link Admin} object created by the ResultSet.
   *
   * @param rs the {@link ResultSet}.
   * @return the {@link Admin} returned from the ResultSet.
   * @throws SQLException if the ResultSet is null.
   */
  private Admin getAdminFromRs(ResultSet rs) throws SQLException {
    Admin o = new Admin();
    o.setId(rs.getString("id_user"));
    o.setName(rs.getString("first_name"));
    o.setSurname(rs.getString("surname"));
    o.setEmail(rs.getString("email"));
    o.setRecoveryEmail(rs.getString("recovery_email"));
    return o;
  }

  /**
   * Run the query retrieveById or retrieveByEmail based on passed parameters.
   *
   * @param param used in the query.
   * @param query for retrieveById or retrieveByEmail
   * @return the admin get from the query
   */
  private Admin getAdmin(String param, String query) {
    PreparedStatement stm;
    ResultSet rs;

    if (param == null || param.equals("")) {
      throw new IllegalArgumentException(
          String.format("The param (%s) passed as a parameter is not valid",
              param));
    }

    try {
      stm = connection.prepareStatement(query);
      stm.setString(1, param);
      stm.setString(2, "Amministratore");
      stm.execute();

      rs = stm.getResultSet();
      if (!rs.next()) {
        return null;
      }

      return getAdminFromRs(rs);
    } catch (SQLException exception) {
      logger.log(Level.SEVERE, exception.getMessage());
    }
    return null;
  }
}

