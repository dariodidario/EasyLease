package com.easylease.easylease.model.optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.easylease.easylease.control.client.ViewRequestEstimateServlet;
import com.easylease.easylease.model.DBPool.DbConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class DbOptionalDaoTest {

  private OptionalDao dbOptional;
  private ViewRequestEstimateServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();
  private static DbConnection dbConnection;
  List<Optional> optionalList;
  Optional optional = new Optional("OPUi78M", "Auto", "Vetri brillantinati in madreperla", 0);
  Optional optional2 = new Optional("op54321", "vetri oscurati", "di serie", 22);


  @BeforeEach
  void setUp() throws SQLException {
    MockitoAnnotations.openMocks(this);
    dbConnection = DbConnection.getInstance();
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("root");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);
    dbConnection.setDataSource(mysqlDataSource);
    dbOptional = DbOptionalDao.getInstance();
  }


  @Test
  void retrieveById_withCorrectId() {
    dbOptional.retrieveById("OPUi78M");
    assertEquals(optional.getOptionalCode(),
        dbOptional.retrieveById("OPUi78M").getOptionalCode());
  }

  @Test
  void retrieveById_withEmptyId() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbOptional.retrieveById("");
    });
  }

  @Test
  void retrieveById_withWrongId() {
    assertNull(dbOptional.retrieveById("0"));
  }


  @Test
  void retrieveById_withNullId() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbOptional.retrieveById(null);
    });
  }


  @Test
  void retrieveById_withCorrectType() {
    optionalList = dbOptional.retrieveByType("Auto");
    for (Optional o : optionalList) {
      assertEquals("Auto", o.getOptionalType());
    }
  }

  @Test
  void retrieveByType_withIncorrectType() {
    optionalList = new ArrayList<>();
    assertEquals(optionalList, dbOptional.retrieveByType("type"));
  }

  @Test
  void retrieveByType_withEmptyType() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbOptional.retrieveByType("");
    });
  }

  @Test
  void retrieveByType_withEmptyNull() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbOptional.retrieveByType(null);
    });
  }

}