package com.easylease.EasyLease.model.client;

import com.easylease.EasyLease.control.utility.PasswordHashing;
import com.easylease.EasyLease.model.DBPool.DBConnection;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class provides methods for interacting with the customer database.
 *
 * @since 0.1
 * @author Mattia Mori
 * @version 0.1
 */
public class DBClientDAO implements ClientDAO {

  private static final Logger logger = Logger.getLogger(DBClientDAO.class.getName());
  private static DBClientDAO dao;
  private Connection connection;

  /**
   * This method provides the connection with the DataBase.
   *
   * @return the connection with the Database.
   */
  public static ClientDAO getInstance() {
    if (dao == null) {
      dao = new DBClientDAO(DBConnection.getInstance().getConnection());
    }
    return dao;
  }

  private DBClientDAO(Connection connection) {
    this.connection = connection;
  }

  @Override
  public Client retrieveById(String id) {
    if (id == null || id.equals("")) {
      throw new IllegalArgumentException();
    }
    PreparedStatement preparedStatement = null;
    Client c = new Client();
    final String query = "SELECT * FROM users WHERE id_user = ?";
    if (id == null || id.equals("") || !id.startsWith("CL")) {
      throw new IllegalArgumentException();
    }
    try {
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.next()) {
        c.setId(rs.getString("id_user"));
        c.setName(rs.getString("first_name"));
        c.setSurname(rs.getString("surname"));
        c.setEmail(rs.getString("email"));
        c.setBirthDate(rs.getDate("birth_date"));
        c.setBirthPlace(rs.getString("birth_place"));
        c.setCity(rs.getString("city"));
        c.setKind(rs.getString("kind"));
        c.setPc(rs.getString("pc"));
        c.setStreet(rs.getString("street"));
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return c;
  }

  @Override
  public Client retrieveByEmail(String email) {
    if (email == null || email.equals("")) {
      throw new IllegalArgumentException();
    }
    PreparedStatement preparedStatement = null;
    Client c = new Client();
    final String query = "SELECT * FROM client WHERE email = ?";
    try {
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, email);
      ResultSet rs = preparedStatement.executeQuery();
      if (rs.next()) {
        c.setId(rs.getString("id_user"));
        c.setName(rs.getString("first_name"));
        c.setSurname(rs.getString("surname"));
        c.setEmail(rs.getString("email"));
        c.setBirthDate(rs.getDate("birth_date"));
        c.setBirthPlace(rs.getString("birth_place"));
        c.setCity(rs.getString("city"));
        c.setKind(rs.getString("kind"));
        c.setPc(rs.getString("pc"));
        c.setStreet(rs.getString("street"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return c;
  }

  @Override
  public List<Client> retrieveAll() {
    PreparedStatement preparedStatement = null;
    List<Client> clientList = new ArrayList<Client>();

    final String query = "SELECT * FROM users WHERE account_type = ?";

    try {
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, "Cliente");
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Client c = new Client();
        c.setId(rs.getString("id_user"));
        c.setName(rs.getString("first_name"));
        c.setSurname(rs.getString("surname"));
        c.setEmail(rs.getString("email"));
        c.setBirthDate(rs.getDate("birth_date"));
        c.setBirthPlace(rs.getString("birth_place"));
        c.setCity(rs.getString("city"));
        c.setKind(rs.getString("kind"));
        c.setPc(rs.getString("pc"));
        c.setStreet(rs.getString("street"));
        clientList.add(c);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return clientList;
  }

  @Override
  public String retrievePasswordByMail(String mail){
    if((mail==null)){
      throw new IllegalArgumentException();
    }
    PreparedStatement preparedStatement = null;
    Client client = new Client();
    String result = "";
    final String query = "SELECT * FROM users WHERE account_type = ? AND email = ?";

    try{
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, "Cliente");
      preparedStatement.setString(2, mail);
      ResultSet rs = preparedStatement.executeQuery();

      if(rs.next()){
        result = rs.getString("pwd");
      } else{
        result = null;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }

    return result;
  }

  @Override
  public void insert(Client c, String password) {
    if (c == null) {
      throw new IllegalArgumentException();
    }

    String pwd = PasswordHashing.generatePassword(password, "SHA-1");
    PreparedStatement preparedStatement = null;
    String query = "INSERT INTO users(id_user, account_type, birth_place, "
        + "birth_date, kind, first_name, surname, email, pwd, street, city, pc) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, c.getId());
      preparedStatement.setString(2, "Cliente");
      preparedStatement.setString(3, c.getBirthPlace());
      preparedStatement.setDate(4, (Date) c.getBirthDate());
      preparedStatement.setString(5, c.getKind());
      preparedStatement.setString(6, c.getName());
      preparedStatement.setString(7, c.getSurname());
      preparedStatement.setString(8, c.getEmail());
      preparedStatement.setString(9, pwd);
      preparedStatement.setString(10, c.getStreet());
      preparedStatement.setString(11, c.getCity());
      preparedStatement.setString(12, c.getPc());
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Client c, String password) {
    if (c == null) {
      throw new IllegalArgumentException();
    }
    String pwd = PasswordHashing.generatePassword(password, "SHA-1");
    PreparedStatement preparedStatement = null;
    String query = "UPDATE users"
        + "SET birth_place = ?, birth_date = ?, kind = ?, first_name = ?, "
        + "surname = ?, email = ?, pwd = ?, street = ?, city = ?, pc = ?"
        + "WHERE id_user = ?";
    try {
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, c.getBirthPlace());
      preparedStatement.setDate(2, (Date) c.getBirthDate());
      preparedStatement.setString(3, c.getKind());
      preparedStatement.setString(4, c.getName());
      preparedStatement.setString(5, c.getSurname());
      preparedStatement.setString(6, c.getEmail());
      preparedStatement.setString(7, pwd);
      preparedStatement.setString(8, c.getStreet());
      preparedStatement.setString(9, c.getCity());
      preparedStatement.setString(10, c.getPc());
      preparedStatement.setString(11, c.getId());
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void delete(Client c) {
    if (c == null) {
      throw new IllegalArgumentException();
    }
    PreparedStatement preparedStatement = null;
    String query = "DELETE FROM users WHERE id_user = ?";
    try {
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, c.getId());
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
