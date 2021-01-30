package com.easylease.EasyLease.model.optional;

import com.easylease.EasyLease.model.DBPool.DbConnection;
import com.easylease.EasyLease.model.estimate.DbEstimateDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbOptionalDao implements OptionalDao {

  private static final String TABLE_NAME = "optional";
  private static final Logger logger = Logger.getLogger(DbEstimateDao.class.getName());
  private static DbOptionalDao dao;
  private final Connection connection;

  /**
   * Returns a DBOptionalDAO Singleton Object.

   * @return the {@link DbOptionalDao} Object that accesses the {@link Optional} object in DB.
   */

  public static OptionalDao getInstance() {
    if (dao == null) {
      dao = new DbOptionalDao(DbConnection.getInstance().getConnection());
    }
    return dao;
  }

  private DbOptionalDao(Connection connection) {
    this.connection = connection;
  }

  @Override
  public synchronized Optional retrieveById(String id) {
    Optional result = null;
    PreparedStatement preparedStatement;
    String selectQuery = "SELECT * FROM " + DbOptionalDao.TABLE_NAME + " WHERE optional_code = ?";
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("The id passed is not valid.");
    }
    try {
      preparedStatement = connection.prepareStatement(selectQuery);
      preparedStatement.setString(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.next()) {
        result = new Optional();
        result.setOptional_code(id);
        result.setOptional_type(rs.getString("optional_type"));
        result.setOptional_name(rs.getString("optional_name"));
        result.setPrice(0);
      }

    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
    }
    return result;
  }

  @Override
  public List<Optional> retrieveByType(String type) {
    List<Optional> results = new ArrayList<>();
    PreparedStatement preparedStatement;
    String selectQuery = "SELECT * FROM " + DbOptionalDao.TABLE_NAME + " WHERE optional_type = ?";
    if (type == null || type.equals("")) {
      throw new IllegalArgumentException("The type passed is not valid.");
    }
    try {
      preparedStatement = connection.prepareStatement(selectQuery);
      preparedStatement.setString(1, type);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        Optional optional = new Optional();
        optional.setOptional_code(rs.getString("optional_code"));
        optional.setOptional_type(rs.getString("optional_type"));
        optional.setOptional_name(rs.getString("optional_name"));
        optional.setPrice(0);
        results.add(optional);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
    }
    return results;
  }
}
