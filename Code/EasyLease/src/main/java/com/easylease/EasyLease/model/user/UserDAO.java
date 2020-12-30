package com.easylease.EasyLease.model.user;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
  public User retrieveById(String Id) throws SQLException;
  public List<User> retrieveByType(Enum tipo) throws SQLException;
  public User retrieveByEmail(String Email) throws SQLException;
  public List<User> retrieveAll() throws SQLException;
  public void insert(User user) throws SQLException;
  public void update(User bean) throws SQLException;
  public  void delete(String id) throws SQLException;

}
