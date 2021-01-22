package com.easylease.EasyLease.model.estimate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.easylease.EasyLease.control.utility.IdGenerator;
import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.optional.Optional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class DBEstimateDAOTest {
/*
  private EstimateDAO dbEstimate;
  private static DBConnection dbConnection;
  List<Optional> optionalList = new ArrayList<>();
  Optional optional = new Optional("OPUi78M", "Auto", "Vetri brillantinati in madreperla",0);
  Car car = new Car();
  Advisor advisor = new Advisor();
  Client client = new Client();

  @BeforeEach
  void setUp() throws SQLException {
    MockitoAnnotations.openMocks(this);
    dbConnection = DBConnection.getInstance();
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("S.PEPE41a");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);

    dbConnection.setDataSource(mysqlDataSource);
    dbEstimate = DBEstimateDAO.getInstance();
  }

  @Test
  void retrieveById_withCorrectId() {
  assertEquals("ESgY65R", dbEstimate.retrieveById("ESgY65R").getId());
  }

  @Test
  void retrieveById_withIncorrectId() {
    assertNull(dbEstimate.retrieveById("0"));
  }

  @Test
  void retrieveById_withEmptyId() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbEstimate.retrieveById("");
    });
  }

  @Test
  void retrieveById_withNullId() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbEstimate.retrieveById(null);
    });
  }

  @Test
  void retrieveByAdvisor_withCorrectId() {
    for (Estimate e : dbEstimate.retrieveByAdvisor("ADJdybc")) {
      assertEquals("ADJdybc",
          e.getAdvisor().getId());
    }
  }

  @Test
  void retrieveByAdvisor_withIncorrectId() {
    assertEquals(new ArrayList<Estimate>(), dbEstimate.retrieveByAdvisor("000"));
  }

  @Test
  void retrieveByAdvisor_withEmptyId() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbEstimate.retrieveByAdvisor("");
    });
  }

  @Test
  void retrieveByAdvisor_withNullId() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbEstimate.retrieveByAdvisor(null);
    });
  }

  @Test
  void retrieveByClient_withCorrectId() {
    for (Estimate e : dbEstimate.retrieveByClient("CLEE8BD")) {
      assertEquals("CLEE8BD", e.getClient().getId());
    }
  }

  @Test
  void retrieveByClient_withIncorrectId() {
    assertEquals(new ArrayList<Estimate>(), dbEstimate.retrieveByClient("000"));
  }

  @Test
  void retrieveByClient_withEmptyId() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbEstimate.retrieveByClient("");
    });
  }

  @Test
  void retrieveByClient_withNullId() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbEstimate.retrieveByClient(null);
    });
  }

  @Test
  void retrieveAll() {
    assertNotNull(dbEstimate.retrieveAll());
  }

  @Test
  void delete_withCorrectEstimate() {
    Estimate e = dbEstimate.retrieveById("ESdnA9G");
    dbEstimate.delete(e);
    assertEquals(false, dbEstimate.retrieveById("ESdnA9G").isVisibility());

    //rollback
    e.setVisibility(true);
    dbEstimate.update(e);
  }

  @Test
  void delete_withNullEstimate() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbEstimate.delete(null);
    });
  }

  @Test
  void insert_withCorrectEstimate() {
    optionalList.add(optional);
    client.setId("CLBGqLi");
    advisor.setId("ADfake0");
    car.setId("CAmTMob");
    Estimate e = new Estimate("es00000", 240, client, advisor, car, 30,
        optionalList, true, "Attesa", new Date(System.currentTimeMillis()),null);
    dbEstimate.insert(e);
    assertNotNull(dbEstimate.retrieveById("es00000"));

    //rollback
    dbEstimate.deleteForever(e);
  }

  @Test
  void insert_withNullEstimate() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbEstimate.insert(null);
    });
  }

  @Test
  void update_withCorrectEstimate() {
    Estimate e = dbEstimate.retrieveById("ESH6f5E");
    float price = e.getPrice();
    e.setPrice(1000);
    dbEstimate.update(e);
    assertEquals(1000, dbEstimate.retrieveById("ESH6f5E").getPrice());

    //rollback
    e.setPrice(price);
    dbEstimate.update(e);

  }

  @Test
  void update_withNullEstimate() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbEstimate.update(null);
    });
  }

  @Test
  void deleteForever_withSuccess() {
    Estimate e = dbEstimate.retrieveById("ESdnA9G");
    dbEstimate.deleteForever(e);
    assertNull(dbEstimate.retrieveById("ESdnA9G"));

    //rollback
    dbEstimate.insert(e);
  }

  @Test
  void deleteForever_withNullEstimate() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbEstimate.deleteForever(null);
    });
  }
*/
}