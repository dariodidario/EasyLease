package com.easylease.EasyLease.model.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.user.DBUserDAO;

import com.easylease.EasyLease.model.user.User;
import com.easylease.EasyLease.model.user.UserDAO;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;


public class UserUnitTest {

  private static DataSource dataSource;
  private DBConnection dbConnection;
  private static final String TABLE_NAME = "user";
  private static Logger logger = Logger.getLogger(DBUserDAO.class.getName());
  private static DBUserDAO dao;

  public enum tipo {CLIENT, ADMIN, ADVISOR};

  @BeforeEach
  void setUp() {
    dao = mock(DBUserDAO.class);
  }

  @Test
  public void testRetriveById() {
    User user = new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password");

    when(dao.retrieveById("CL0001")).thenReturn(new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password"));

    User user1 = dao.retrieveById("CL0001");

    assertEquals(user.getId(), user1.getId());
    assertEquals(user.getName(), user1.getName());
    assertEquals(user.getSurname(), user1.getSurname());
    assertEquals(user.getEmail(), user1.getEmail());
    assertEquals(user.getPassword(), user1.getPassword());
  }

  @Test
  public void testRetriveByType() {
    String type = String.valueOf(tipo.CLIENT);
    User user = new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password");
    User user1 = new User("CL0002", "Antonio", "Sarro", "antonio.sarro1999@gmail.com", "password");
    User user2 = new User("CL0003", "Mattia", "Caprio", "mattia.caprio1999@gmail.com", "password");
    ArrayList<User> users = new ArrayList<User>();
    users.add(user);
    users.add(user1);
    users.add(user2);

    when(dao.retrieveByType(type)).thenReturn(users);

    ArrayList<User> users1 =(ArrayList<User>)  dao.retrieveByType(type);

    assertEquals(users.get(0).getId(), users1.get(0).getId());
    assertEquals(users.get(0).getName(), users1.get(0).getName());
    assertEquals(users.get(0).getSurname(), users1.get(0).getSurname());
    assertEquals(users.get(0).getEmail(), users1.get(0).getEmail());
    assertEquals(users.get(0).getPassword(), users1.get(0).getPassword());
    assertEquals(users.get(1).getId(), users1.get(1).getId());
    assertEquals(users.get(1).getName(), users1.get(1).getName());
    assertEquals(users.get(1).getSurname(), users1.get(1).getSurname());
    assertEquals(users.get(1).getEmail(), users1.get(1).getEmail());
    assertEquals(users.get(1).getPassword(), users1.get(1).getPassword());
    assertEquals(users.get(2).getId(), users1.get(2).getId());
    assertEquals(users.get(2).getName(), users1.get(2).getName());
    assertEquals(users.get(2).getSurname(), users1.get(2).getSurname());
    assertEquals(users.get(2).getEmail(), users1.get(2).getEmail());
    assertEquals(users.get(2).getPassword(), users1.get(2).getPassword());
  }

  @Test
  public void testRetriveByEmail() {
    User user = new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password");

    when(dao.retrieveByEmail("francesco.torino1999@gmail.com")).thenReturn(new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password"));

    User user1 = dao.retrieveByEmail("francesco.torino1999@gmail.com");

    assertEquals(user.getId(), user1.getId());
    assertEquals(user.getName(), user1.getName());
    assertEquals(user.getSurname(), user1.getSurname());
    assertEquals(user.getEmail(), user1.getEmail());
    assertEquals(user.getPassword(), user1.getPassword());
  }

  @Test
  public void testRetriveAll() {
    String type = String.valueOf(tipo.CLIENT);
    User user = new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password");
    User user1 = new User("CL0002", "Antonio", "Sarro", "antonio.sarro1999@gmail.com", "password");
    User user2 = new User("CL0003", "Mattia", "Caprio", "mattia.caprio1999@gmail.com", "password");
    ArrayList<User> users = new ArrayList<User>();
    users.add(user);
    users.add(user1);
    users.add(user2);

    when(dao.retrieveAll()).thenReturn(users);

    ArrayList<User> users1 =(ArrayList<User>)  dao.retrieveAll();

    assertEquals(users.get(0).getId(), users1.get(0).getId());
    assertEquals(users.get(0).getName(), users1.get(0).getName());
    assertEquals(users.get(0).getSurname(), users1.get(0).getSurname());
    assertEquals(users.get(0).getEmail(), users1.get(0).getEmail());
    assertEquals(users.get(0).getPassword(), users1.get(0).getPassword());
    assertEquals(users.get(1).getId(), users1.get(1).getId());
    assertEquals(users.get(1).getName(), users1.get(1).getName());
    assertEquals(users.get(1).getSurname(), users1.get(1).getSurname());
    assertEquals(users.get(1).getEmail(), users1.get(1).getEmail());
    assertEquals(users.get(1).getPassword(), users1.get(1).getPassword());
    assertEquals(users.get(2).getId(), users1.get(2).getId());
    assertEquals(users.get(2).getName(), users1.get(2).getName());
    assertEquals(users.get(2).getSurname(), users1.get(2).getSurname());
    assertEquals(users.get(2).getEmail(), users1.get(2).getEmail());
    assertEquals(users.get(2).getPassword(), users1.get(2).getPassword());
  }

  @Test
  public void testInsert() {
    User user = new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password");

    doAnswer(invocation -> {
      return null;
    }).when(dao).insert(any());

    dao.insert(user);
    ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
    verify(dao, times(1)).insert(captor.capture());
    assertEquals(user.getId(), captor.getValue().getId());
  }

  @Test
  public void testUpdate() {
    User user = new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password");

    doAnswer(invocation -> {
      return null;
    }).when(dao).update(any());

    dao.update(user);
    ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
    verify(dao, times(1)).update(captor.capture());
    assertEquals(user.getId(), captor.getValue().getId());
  }

  @Test
  public void testDelete() {
    User user = new User("CL0001", "Francesco", "Torino", "francesco.torino1999@gmail.com", "password");

    doAnswer(invocation -> {
      return null;
    }).when(dao).delete(any());

    dao.delete(user);
    ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
    verify(dao, times(1)).delete(captor.capture());
    assertEquals(user.getId(), captor.getValue().getId());
  }

}
