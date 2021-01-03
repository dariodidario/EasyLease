package com.easylease.EasyLease.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.easylease.EasyLease.control.utility.exception.EntityTamperingException;
import com.easylease.EasyLease.model.estimate.Estimate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DBOrderDAOTest {

  DBOrderDAO dbOrderDAO = mock(DBOrderDAO.class);

  private static Order order;
  private static List<Order> orderList;

  @BeforeAll
  static void setUp() {
    order = new Order("OR12RT4", new Estimate(), new Date(),
        new Date(), new Date(), true);
    orderList = new ArrayList<>();
    orderList.add(order);
  }

  @Test
  void retrieveByCorrectId() {
    when(dbOrderDAO.retrieveById("OR12RT4")).thenReturn(order);
    assertEquals(order, dbOrderDAO.retrieveById("OR12RT4"));
  }

  @Test
  void retrieveByNonexistentId() {
    when(dbOrderDAO.retrieveById("OR5729A")).thenReturn(null);
    assertNull(dbOrderDAO.retrieveById("OR5729A"));
  }

  @Test
  void retrieveByWrongId() {
    when(dbOrderDAO.retrieveById("AD34GBA")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbOrderDAO.retrieveById("AD34GBA");
    });
  }

  @Test
  void retrieveByNullId() {
    when(dbOrderDAO.retrieveById(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbOrderDAO.retrieveById(null);
    });
  }

  @Test
  void retrieveByEmptyId() {
    when(dbOrderDAO.retrieveById("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbOrderDAO.retrieveById("");
    });
  }

  @Test
  void retrieveByCorrectAdvisor() {
    when(dbOrderDAO.retrieveByAdvisor("ADGT12D")).thenReturn(orderList);
    assertEquals(orderList, dbOrderDAO.retrieveByAdvisor("ADGT12D"));
  }

  @Test
  void retrieveByNonexistentAdvisor() {
    when(dbOrderDAO.retrieveByAdvisor("AD84F88")).thenReturn(null);
    assertNull(dbOrderDAO.retrieveByAdvisor("AD84F88"));
  }

  @Test
  void retrieveByWrongAdvisor() {
    when(dbOrderDAO.retrieveByAdvisor("CL34GBA")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbOrderDAO.retrieveByAdvisor("CL34GBA");
    });
  }

  @Test
  void retrieveByNullAdvisor() {
    when(dbOrderDAO.retrieveByAdvisor(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbOrderDAO.retrieveByAdvisor(null);
    });
  }

  @Test
  void retrieveByEmptyAdvisor() {
    when(dbOrderDAO.retrieveByAdvisor("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbOrderDAO.retrieveByAdvisor("");
    });
  }

  @Test
  void retrieveByCorrectClient() {
    when(dbOrderDAO.retrieveByClient("CLGT12D")).thenReturn(orderList);
    assertEquals(orderList, dbOrderDAO.retrieveByClient("CLGT12D"));
  }

  @Test
  void retrieveByNonexistentClient() {
    when(dbOrderDAO.retrieveByClient("CLGR12D")).thenReturn(null);
    assertNull(dbOrderDAO.retrieveByClient("CLGR12D"));
  }

  @Test
  void retrieveByWrongClient() {
    when(dbOrderDAO.retrieveByClient("ADGT12D")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbOrderDAO.retrieveByClient("ADGT12D");
    });
  }

  @Test
  void retrieveByNullClient() {
    when(dbOrderDAO.retrieveByClient(null)).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbOrderDAO.retrieveByClient(null);
    });
  }

  @Test
  void retrieveByEmptyClient() {
    when(dbOrderDAO.retrieveByClient("")).thenThrow(IllegalArgumentException.class);
    assertThrows(IllegalArgumentException.class, () -> {
      dbOrderDAO.retrieveByClient("");
    });
  }

  @Test
  void retrieveByAll() {
    when(dbOrderDAO.retrieveAll()).thenReturn(orderList);
    assertNotNull(orderList);
  }

  @Test
  void insertCorrectOrder() {
    doNothing().when(dbOrderDAO).insert(order);
    dbOrderDAO.insert(order);
    verify(dbOrderDAO).insert(order);
  }

  @Test
  void insertIllegalOrder() {
    Order or = null;
    doThrow(EntityTamperingException.class).when(dbOrderDAO).insert(null);
    assertThrows(EntityTamperingException.class, () -> {
      dbOrderDAO.insert(null);
    });
  }

  @Test
  void updateCorrectOrder() {
    doNothing().when(dbOrderDAO).update(order);
    dbOrderDAO.update(order);
    verify(dbOrderDAO).update(order);
  }

  @Test
  void updateIllegalOrder() {
    Order or = null;
    doThrow(EntityTamperingException.class).when(dbOrderDAO).update(null);
    assertThrows(EntityTamperingException.class, () -> {
      dbOrderDAO.update(null);
    });
  }

  @Test
  void deleteCorrectOrder() {
    doNothing().when(dbOrderDAO).delete(order);
    dbOrderDAO.delete(order);
    verify(dbOrderDAO).delete(order);
  }

  @Test
  void deleteIllegalOrder() {
    Order or = null;
    doThrow(EntityTamperingException.class).when(dbOrderDAO).delete(null);
    assertThrows(EntityTamperingException.class, () -> {
      dbOrderDAO.delete(null);
    });
  }
}