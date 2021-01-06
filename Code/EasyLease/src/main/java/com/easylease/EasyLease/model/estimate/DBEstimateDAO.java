package com.easylease.EasyLease.model.estimate;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.advisor.AdvisorDAO;
import com.easylease.EasyLease.model.advisor.DBAdvisorDAO;
import com.easylease.EasyLease.model.car.CarDAO;
import com.easylease.EasyLease.model.car.DBCarDAO;
import com.easylease.EasyLease.model.client.ClientDAO;
import com.easylease.EasyLease.model.client.DBClientDAO;
import com.easylease.EasyLease.model.optional.DBOptionalDAO;
import com.easylease.EasyLease.model.optional.Optional;
import com.easylease.EasyLease.model.optional.OptionalDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBEstimateDAO implements  EstimateDAO {

  private static final Logger logger = Logger.getLogger(DBEstimateDAO.class.getName());
  private static final String TABLE_NAME = "estimate";
  private static DBEstimateDAO dao;
  private final Connection connection;

  /**
   * Returns a DBEstimateDAO Singleton Object.

   * @return the {@link DBEstimateDAO} Object that accesses the {@link Estimate} object
   *      in the Database.
   */
  public static EstimateDAO getInstance() {
    if (dao == null) {
      dao = new DBEstimateDAO(DBConnection.getInstance().getConnection());
    }
    return dao;
  }

  private DBEstimateDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Estimate retrieveById(String id) {
    Estimate result = null;
    PreparedStatement preparedStatement;
    String selectQuery = "SELECT * FROM " + DBEstimateDAO.TABLE_NAME + " WHERE id_estimate = ?";
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("The id passed is not valid");
    }
    try {
      preparedStatement = connection.prepareStatement(selectQuery);
      preparedStatement.setString(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.next()) {
        result = getResultFromRs(rs);
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
      return null;
    }
    return result;
  }

  @Override
  public List<Estimate> retrieveByAdvisor(String id) {
    List<Estimate> results = new ArrayList<>();
    PreparedStatement preparedStatement;
    String selectQuery = "SELECT * FROM " + DBEstimateDAO.TABLE_NAME + " WHERE id_advisor = ? ";
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("The id passed is not valid");
    }
    try {
      preparedStatement = connection.prepareStatement(selectQuery);
      preparedStatement.setString(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        results.add(getResultFromRs(rs));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
      return null;
    }
    return results;
  }

  @Override
  public List<Estimate> retrieveByClient(String id) {
    List<Estimate> results = new ArrayList<>();
    PreparedStatement preparedStatement;
    String selectQuery = "SELECT * FROM " + DBEstimateDAO.TABLE_NAME + " WHERE id_client = ?";
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException("The id passed is not valid");
    }
    try {
      preparedStatement = connection.prepareStatement(selectQuery);
      preparedStatement.setString(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        results.add(getResultFromRs(rs));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
      return null;
    }
    return results;
  }

  @Override
  public List<Estimate> retrieveAll() {
    List<Estimate> results = new ArrayList<>();
    PreparedStatement preparedStatement;
    String selectQuery = "SELECT * FROM " + DBEstimateDAO.TABLE_NAME;
    try {
      preparedStatement = connection.prepareStatement(selectQuery);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        results.add(getResultFromRs(rs));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
      return null;
    }
    return results;
  }

  @Override
  public void delete(Estimate e) {
    e.setVisibility(false);
    update(e);
  }

  @Override
  public void insert(Estimate e) {
    if (e == null) {
      throw new IllegalArgumentException("The estimate passed is not valid");
    }
    PreparedStatement preparedStatement;
    String insertQuery = "INSERT INTO " + DBEstimateDAO.TABLE_NAME
        + " (id_estimate, price, id_advisor, id_client, id_car, period,"
        + "visibility, state, response_date)"
        + " VALUES(?,?,?,?,?,?,?))";
    try {
      preparedStatement = connection.prepareStatement(insertQuery);
      preparedStatement.setString(1, e.getId());
      preparedStatement.setFloat(2, e.getPrice());
      preparedStatement.setString(3, e.getAdvisor().getId());
      preparedStatement.setString(4, e.getClient().getId());
      preparedStatement.setString(5, e.getCar().getId());
      preparedStatement.setInt(6, e.getPeriod());
      preparedStatement.setBoolean(7, e.isVisibility());
      preparedStatement.setString(8, e.getState());
      preparedStatement.setDate(9, new Date(e.getResponseDate().getTimeInMillis()));
      preparedStatement.executeQuery();
      for (Optional o : e.getOptionalList()) {
        insertOptional(e.getId(), o.getId());
      }
    } catch (SQLException sqlException) {
      logger.log(Level.SEVERE, sqlException.getMessage());
    }
  }

  @Override
  public void update(Estimate e) {
    if (e == null) {
      throw new IllegalArgumentException("The estimate passed is not valid");
    }
    PreparedStatement preparedStatement;
    String updateQuery = "UPDATE" + DBEstimateDAO.TABLE_NAME
        + " SET price = ?, period = ?, visibility = ?, id_client = ?, id_advisor = ?,"
        + " id_car = ?, state = ?, response_date = ?"
        + " WHERE id_estimate = ?";
    try {
      preparedStatement = connection.prepareStatement(updateQuery);
      preparedStatement.setFloat(1, e.getPrice());
      preparedStatement.setInt(2, e.getPeriod());
      preparedStatement.setBoolean(3, e.isVisibility());
      preparedStatement.setString(4, e.getClient().getId());
      preparedStatement.setString(5, e.getAdvisor().getId());
      preparedStatement.setString(6, e.getCar().getId());
      preparedStatement.setString(7, e.getState());
      preparedStatement.setDate(8, new Date(e.getResponseDate().getTimeInMillis()));
      preparedStatement.setString(9, e.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException sqlException) {
      logger.log(Level.SEVERE, sqlException.getMessage());
    }
  }

  private void insertOptional(String idEstimate, String idOptional) {
    PreparedStatement preparedStatement;
    String insertQuery = "INSERT INTO included (optional_code, id_estimate)  VALUES(?,?))";
    try {
      preparedStatement = connection.prepareStatement(insertQuery);
      preparedStatement.setString(2, idEstimate);
      preparedStatement.setString(1, idOptional);
      preparedStatement.executeQuery();
    } catch (SQLException sqlException) {
      logger.log(Level.SEVERE, sqlException.getMessage());
    }
  }

  private Estimate getResultFromRs(ResultSet rs) throws SQLException {
    Estimate result = new Estimate();
    AdvisorDAO advisor = DBAdvisorDAO.getIstance();
    ClientDAO client = DBClientDAO.getInstance();
    CarDAO car = DBCarDAO.getInstance();
    try {
      result.setId(rs.getString("id_estimate"));
      result.setPrice(rs.getFloat("price"));
      result.setVisibility(rs.getBoolean("visibility"));
      result.setPeriod(rs.getInt("period"));
      result.setAdvisor(advisor.retrieveById(rs.getString("id_advisor")));
      result.setClient(client.retrieveById(rs.getString("id_client")));
      result.setCar(car.retriveById(rs.getString("id_car")));
      result.setState(rs.getString("state"));
      GregorianCalendar date = new GregorianCalendar();
      date.setTime(rs.getDate("response_date"));
      result.setResponseDate(date);
      result.setOptionalList(getOptionalList(result.getId()));
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
      return null;
    }
    return result;
  }

  private List<Optional> getOptionalList(String id) throws SQLException {
    String selectQuery = "SELECT optional_code FROM included WHERE id_estimate = ?";
    List<Optional> optionals = new ArrayList<>();
    PreparedStatement preparedStatement;
    OptionalDAO opt = DBOptionalDAO.getInstance();
    try {
      preparedStatement = connection.prepareStatement(selectQuery);
      preparedStatement.setString(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        optionals.add(opt.retrieveById(rs.getString("optional_code")));
      }
    } catch (SQLException e) {
      logger.log(Level.SEVERE, e.getMessage());
      return null;
    }
    return optionals;
  }

}
