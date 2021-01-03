import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.optional.Optional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DBEstimateDAOTest {

  private static DBEstimateDAO db;

  Optional optional = new Optional("op12345", "cerchi in lega",  "di serie", 33);
  Optional optional2 = new Optional("op54321", "vetri oscurati", "di serie", 22);
  Client client = new Client("Roma", new Date(), "cliente", "Roma", "84018",
      "Via", "cl123", "Marco", "Polo", "email", "password");
  Advisor advisor = new Advisor("ad12345", "Mario", "Rossi", "email",
      "password", new Date());
  Car car = new Car("ca12345", "Peugeot", "3008", 134, "city car",
      true, 6, "trasmission", 4, 300, "B",
      2, "Diesel", 33, "path");
  List<Optional> optionalList = new ArrayList<>();

  @BeforeEach
  void setUp() {
    db = mock(DBEstimateDAO.class);
  }

  @Test
  void retrieveById_withCorrectId() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate e = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);

    Mockito.when(db.retrieveById(e.getId())).thenReturn(e);
    assertEquals(e, db.retrieveById(e.getId()));
  }

  @Test
  void retrieveById_withIncorrectId() {
    Mockito.when(db.retrieveById("000")).thenReturn(null);
    assertNull(db.retrieveById("000"));
  }

  @Test
  void retrieveById_withEmptyId() {
    Mockito.when(db.retrieveById("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      db.retrieveById("");
    });
  }

  @Test
  void retrieveById_withNullId() {
    Mockito.when(db.retrieveById(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      db.retrieveById(null);
    });
  }

  @Test
  void retrieveByAdvisor_withCorrectId() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate e = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);
    List<Estimate> result = new ArrayList<Estimate>();
    result.add(e);

    Mockito.when(db.retrieveByAdvisor(e.getAdvisor().getId())).thenReturn(result);
    assertEquals(result, db.retrieveByAdvisor(e.getAdvisor().getId()));
  }

  @Test
  void retrieveByAdvisor_withIncorrectId() {
    Mockito.when(db.retrieveByAdvisor("000")).thenReturn(new ArrayList<Estimate>());
    assertEquals(new ArrayList<Estimate>(), db.retrieveByAdvisor("000"));
  }

  @Test
  void retrieveByAdvisor_withEmptyId() {
    Mockito.when(db.retrieveByAdvisor("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      db.retrieveByAdvisor("");
    });
  }

  @Test
  void retrieveByAdvisor_withNullId() {
    Mockito.when(db.retrieveByAdvisor(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      db.retrieveByAdvisor(null);
    });
  }

  @Test
  void retrieveByClient_withCorrectId() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate e = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);
    List<Estimate> result = new ArrayList<>();
    result.add(e);

    Mockito.when(db.retrieveByClient(e.getClient().getId())).thenReturn(result);
    assertEquals(result, db.retrieveByClient(e.getClient().getId()));
  }

  @Test
  void retrieveByClient_withIncorrectId() {
    Mockito.when(db.retrieveByClient("000")).thenReturn(new ArrayList<Estimate>());
    assertEquals(new ArrayList<Estimate>(), db.retrieveByClient("000"));
  }

  @Test
  void retrieveByClient_withEmptyId() {
    Mockito.when(db.retrieveByClient("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      db.retrieveByClient("");
    });
  }

  @Test
  void retrieveByClient_withNullId() {
    Mockito.when(db.retrieveByClient(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      db.retrieveByClient(null);
    });
  }

  @Test
  void retrieveAll() {
    optionalList.add(optional);
    optionalList.add(optional2);
    List<Estimate> estimateList = new ArrayList<>();
    estimateList.add(new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true));
    estimateList.add(new Estimate("es0000", 240, client, advisor, car, 30, optionalList, true));

    Mockito.when(db.retrieveAll()).thenReturn(estimateList);

    assertEquals(estimateList, db.retrieveAll());
  }

  @Test
  void delete_withCorrectEstimate() {
    optionalList = new ArrayList<>();
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate e = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);

    Mockito.doNothing().when(db).delete(e);
    db.delete(e);
    Mockito.verify(db).delete(e);
    Mockito.verifyNoMoreInteractions(db);
  }

  @Test
  void delete_withNullEstimate() {
    Mockito.doThrow(IllegalArgumentException.class).when(db).delete(null);
    assertThrows(IllegalArgumentException.class, () -> {
      db.delete(null);
    });
  }

  @Test
  void insert_withCorrectEstimate() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate e = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);

    Mockito.doNothing().when(db).insert(e);
    db.insert(e);
    Mockito.verify(db).insert(e);
    Mockito.verifyNoMoreInteractions(db);
  }

  @Test
  void insert_withNullEstimate() {
    Mockito.doThrow(IllegalArgumentException.class).when(db).insert(null);
    assertThrows(IllegalArgumentException.class, () -> {
      db.insert(null);
    });
  }

  @Test
  void update_withCorrectEstimate() {
    optionalList.add(optional);
    optionalList.add(optional2);
    Estimate e = new Estimate("es4312", 240, client, advisor, car, 30, optionalList, true);

    Mockito.doNothing().when(db).update(e);
    db.update(e);
    Mockito.verify(db).update(e);
    Mockito.verifyNoMoreInteractions(db);
  }

  @Test
  void update_withNullEstimate() {
    Mockito.doThrow(IllegalArgumentException.class).when(db).update(null);
    assertThrows(IllegalArgumentException.class, () -> {
      db.update(null);
    });
  }
}