import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import com.easylease.EasyLease.model.optional.DBOptionalDAO;
import com.easylease.EasyLease.model.optional.Optional;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DBOptionalDAOTest {

  private static DBOptionalDAO db;
  Optional optional = new Optional("op12345", "cerchi in lega",  "di serie", 33);
  Optional optional2 = new Optional("op54321", "vetri oscurati", "di serie", 22);

  @BeforeEach
  void setUp() {
    db = mock(DBOptionalDAO.class);
  }

  @Test
  void retrieveById_withCorrectId() {
    Mockito.when(db.retrieveById(optional.getId())).thenReturn(optional);
    Optional result = db.retrieveById(optional.getId());
    assertEquals(optional, result);
  }

  @Test
  void retrieveById_withIncorrectId() {
    Mockito.when(db.retrieveById("000")).thenReturn(null);
    Optional result2 = db.retrieveById("000");
    assertNull(result2);
  }

  @Test
  void retrieveById_withEmptyId() {
    Mockito.when(db.retrieveById("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      db.retrieveById("");
    });
  }

  @Test
  void retrieveById_withEmptyNull() {
    Mockito.when(db.retrieveById(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      db.retrieveById(null);
    });
  }

  @Test
  void retrieveByType_withCorrectType() {
    List<Optional> optionalList = new ArrayList<>();
    optionalList.add(optional);
    optionalList.add(optional2);
    Mockito.when(db.retrieveByType("di serie")).thenReturn(optionalList);
    assertEquals(optionalList, db.retrieveByType("di serie"));
  }

  @Test
  void retrieveByType_withIncorrectType() {
    Mockito.when(db.retrieveByType("000")).thenReturn(new ArrayList<>());
    assertEquals(new ArrayList<Optional>(), db.retrieveByType("000"));
  }

  @Test
  void retrieveByType_withEmptyType() {
    Mockito.when(db.retrieveByType("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      db.retrieveByType("");
    });
  }

  @Test
  void retrieveByType_withEmptyNull() {
    Mockito.when(db.retrieveById(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      db.retrieveById(null);
    });
  }

}