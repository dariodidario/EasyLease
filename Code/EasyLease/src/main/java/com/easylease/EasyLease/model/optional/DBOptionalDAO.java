package com.easylease.EasyLease.model.optional;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBOptionalDAO implements  OptionalDAO {

  private static final String TABLE_NAME = "optional";
  private static final Logger logger = Logger.getLogger(DBEstimateDAO.class.getName());
  private static DBOptionalDAO dao;
  private final Connection connection;

  /**
   * Returns a DBOptionalDAO Singleton Object.

   * @return the {@link DBOptionalDAO} Object that accesses the {@link Optional} object in DB.
   */

  public static OptionalDAO getInstance() {
    if (dao == null) {
      dao = new DBOptionalDAO(DBConnection.getInstance().getConnection());
    }
    return dao;
  }

  private DBOptionalDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public synchronized Optional retrieveById(String id) {
    Optional result = new Optional();
    PreparedStatement preparedStatement;
<<<<<<< Updated upstream
    String selectQuery = "SELECT * FROM " + DBOptionalDAO.TABLE_NAME + " WHERE ID = ?";
=======
    String selectQuery = "SELECT * FROM " + DBOptionalDAO.TABLE_NAME + " WHERE optional_code = ?";
>>>>>>> Stashed changes
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("The id passed is not valid.");
    }
    try {
      preparedStatement = connection.prepareStatement(selectQuery);
      preparedStatement.setString(1, id);
      ResultSet rs = preparedStatement.executeQuery();
<<<<<<< Updated upstream
      result.setType(rs.getString("TYPE"));
      result.setName(rs.getString("NAME"));
      result.setVisibility(rs.getBoolean("VISIBILITY"));
      result.setPrice(rs.getFloat("PRICE"));
=======
      result.setType(rs.getString("optional_type"));
      result.setName(rs.getString("optional_name"));
      result.setPrice(rs.getFloat("optional_price"));
>>>>>>> Stashed changes
      result.setId(id);
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
      return null;
    }
    return result;
  }

  @Override
  public List<Optional> retrieveByType(String type) {
    List<Optional> results = new ArrayList<>();
    PreparedStatement preparedStatement;
<<<<<<< Updated upstream
    String selectQuery = "SELECT * FROM " + DBOptionalDAO.TABLE_NAME + " WHERE TYPE= ?";
=======
    String selectQuery = "SELECT * FROM " + DBOptionalDAO.TABLE_NAME + " WHERE optional_type = ?";
>>>>>>> Stashed changes
    try {
      preparedStatement = connection.prepareStatement(selectQuery);
      preparedStatement.setString(1, type);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        Optional optional = new Optional();
<<<<<<< Updated upstream
        optional.setType(rs.getString("TYPE"));
        optional.setName(rs.getString("NAME"));
        optional.setVisibility(rs.getBoolean("VISIBILITY"));
        optional.setPrice(rs.getFloat("PRICE"));
        optional.setId(rs.getString("ID"));
=======
        optional.setType(rs.getString("optional_type"));
        optional.setName(rs.getString("optional_name"));
        optional.setPrice(rs.getFloat("optional_price"));
        optional.setId(rs.getString("optional_code"));
>>>>>>> Stashed changes
        results.add(optional);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
      return null;
    }
    return results;
  }
}
