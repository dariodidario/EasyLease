package com.easylease.EasyLease.model.DBPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * This class instantiates a Singleton object that will subsequently be shared
 * by all components that will need to access to the Database.
 *
 * @since 0.1
 * @author Antonio Sarro
 * @version 0.1
 */
public class DBConnection {

  private static final Logger logger = Logger.getLogger(DBConnection.class.getName());
  private static final DBConnection dbConnection;
  private DataSource dataSource;
  private Connection connection;

  static {
    dbConnection = new DBConnection();
    try {
      Context ctx = new InitialContext();
      dbConnection.setDataSource((DataSource) ctx.lookup("java:comp/env/jdbc/EasyLease"));
    } catch (NamingException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
    }
  }

  public DBConnection() {
  }

  /**
   * Returns a Connection Object, through which it will possible to
   * communicate and interact with the Database.
   *
   * @return The connection to the Database.
   */
  public static DBConnection getInstance() {
    return dbConnection;
  }

  /**
   * Returns the Connection to the Database.
   *
   * @return The Connection to the Database.
   */
  public Connection getConnection() {
    try {
      if (this.dataSource == null) {
        logger.log(Level.SEVERE,
            "You must first set the DataSource before accessing the Connection");
        return null;
      }
      if (connection == null) {
        connection = this.dataSource.getConnection();
      }
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
    }
    return connection;
  }

  /**
   * The method takes care of resetting the connection.
   */
  private void resetConnection() {
    try {
      this.connection = this.dataSource.getConnection();
    } catch (SQLException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
    }
  }

  /**
   * Returns the Datasource.
   *
   * @return The DataSource to access.
   */
  DataSource getDataSource() {
    return dataSource;
  }

  /**
   * Set the DataSource to access.
   *
   * @param dataSource The Datasource to access.
   */
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    resetConnection();
  }
}
