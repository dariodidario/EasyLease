package com.easylease.easylease.model.estimate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.easylease.easylease.model.DBPool.DbConnection;
import com.easylease.easylease.model.advisor.Advisor;
import com.easylease.easylease.model.car.Car;
import com.easylease.easylease.model.client.Client;
import com.easylease.easylease.model.optional.Optional;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;


class DbEstimateDaoTest {

  private EstimateDao dbEstimate;
  private static DbConnection dbConnection;
  List<Optional> optionalList = new ArrayList<>();
  Optional optional = new Optional("OPUi78M", "Auto", "Vetri brillantinati in madreperla", 0);
  Car car = new Car();
  Advisor advisor = new Advisor();
  Client client = new Client();

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
    dbEstimate = DbEstimateDao.getInstance();

  }

  @Test
  void retrieveById_withCorrectId() {
    assertEquals("ESgY65R", dbEstimate.retrieveById("ESgY65R").getIdEstimate());
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
          e.getAdvisor().getIdUser());
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
      assertEquals("CLEE8BD", e.getClient().getIdUser());
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
  void insert_withCorrectEstimate() {
    optionalList.add(optional);
    client.setIdUser("CLBGqLi");
    advisor.setIdUser("ADfake0");
    car.setIdCar("CAmTMob");
    Estimate e = new Estimate("es00000", 240, client, advisor, car, 30,
        optionalList, true, "Attesa", new Date(System.currentTimeMillis()), null);
    dbEstimate.insert(e);
    assertNotNull(dbEstimate.retrieveById("es00000"));

    //rollback
    dbEstimate.delete(e);
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
    e.setPrice(1000);
    dbEstimate.update(e);
    assertEquals(1000, dbEstimate.retrieveById("ESH6f5E").getPrice());

    //rollback
    float price = e.getPrice();
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
  void delete_withSuccess() {
    Estimate e = dbEstimate.retrieveById("ESdnA9G");
    dbEstimate.delete(e);
    assertNull(dbEstimate.retrieveById("ESdnA9G"));

    //rollback
    dbEstimate.insert(e);
  }

  @Test
  void delete_withNullEstimate() {
    assertThrows(IllegalArgumentException.class, () -> {
      dbEstimate.delete(null);
    });
  }
}