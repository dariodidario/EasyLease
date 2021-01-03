package com.easylease.EasyLease.model.admin;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the AdminDAO interface, using the singleton DBConnection
 * as the database.
 *
 * @since 0.1
 * @author Antonio Sarro
 * @version 0.1
 */
public class DBAdminDAO implements AdminDAO {

  private static Logger logger = Logger.getLogger(DBAdminDAO.class.getName());
  private static DBAdminDAO dao;
  private Connection connection;

  /**
   * Returns a DBAdminDAO Singleton Object.
   *
   * @return the {@link DBAdminDAO} Object that accesses the {@link Admin} object
   *      in the Database.
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
    final String query = "SELECT * FROM user WHERE id = ?";
    return getAdmin(id, query);
  }

  @Override
  public Admin retrieveByEmail(String email) {
    final String query = "SELECT * FROM user WHERE email = ?";
    return getAdmin(email, query);
  }

  @Override
  public List<Admin> retrieveAll() {
    final String query = "SELECT * FROM user WHERE accountType = ?";

    List<Admin> admins = new ArrayList<>();
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, "Admin");
      stm.execute();
      ResultSet rs = stm.getResultSet();
      while (rs.next()) {
        admins.add(getAdminFromRs(rs));
      }
      return admins;
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      return null;
    }
  }

  /**
   * Returns the {@link Admin} object created by the ResultSet.
   *
   * @param rs the {@link ResultSet}.
   * @return the {@link Admin} returned from the ResultSet.
   * @throws SQLException if the ResultSet is null.
   */
  private Admin getAdminFromRs(ResultSet rs) throws SQLException {
    //AdminDAO adminDAO = DBAdminDAO.getIstance();
    Admin o = new Admin();
    //TODO: Finire quando avrò tutto
    return o;
  }

  /**
   * Run the query retrieveById or retrieveByEmail based on passed parameters.
   *
   * @param param used in the query.
   * @param query for retrieveById or retrieveByEmail
   * @return
   */
  private Admin getAdmin(String param, String query) {
    if (param == null || param.equals("")) {
      throw new IllegalArgumentException(
          String.format("The id(%s) passed as a parameter is not valid", param));
    }
    try {
      PreparedStatement stm = connection.prepareStatement(query);
      stm.setString(1, param);
      stm.execute();

      ResultSet rs = stm.getResultSet();
      if (!rs.next()) {
        return null;
      }
      return getAdminFromRs(rs);
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
      return null;
    }
  }
}
