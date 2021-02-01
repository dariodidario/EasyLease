package com.easylease.EasyLease.control.client;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.ClientDao;
import com.easylease.EasyLease.model.client.DbClientDao;
import com.easylease.EasyLease.model.estimate.DbEstimateDao;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.estimate.EstimateDao;
import com.easylease.EasyLease.model.order.DbOrderDao;
import com.easylease.EasyLease.model.order.Order;
import com.easylease.EasyLease.model.order.OrderDao;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;


class HistoryClientServletTest {
  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private ServletContext context;
  @Mock
  private HttpSession session;
  @Mock
  private RequestDispatcher dispatcher;

  private ClientDao clientDao;
  private EstimateDao estimateDao;
  private OrderDao orderDao;
  private HistoryClientServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new HistoryClientServlet();
    clientDao = DbClientDao.getInstance();
    estimateDao = DbEstimateDao.getInstance();
    orderDao = DbOrderDao.getInstance();
    when(request.getServletContext()).thenReturn(context);
    when(request.getSession()).thenReturn(session);
    when(context.getContextPath()).thenReturn("");
    when(session.isNew()).thenReturn(true);
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

    Mockito.doAnswer((Answer<Object>) invocation -> {
      String key = (String) invocation.getArguments()[0];
      return attributes.get(key);
    }).when(session).getAttribute(anyString());

    Mockito.doAnswer((Answer<Object>) invocation -> {
      String key = (String) invocation.getArguments()[0];
      Object value = invocation.getArguments()[1];
      attributes.put(key, value);
      return null;
    }).when(session).setAttribute(anyString(), any());
  }

  @AfterEach
  void tearDown() {

  }

  @Test
  void historyServletTest_Success() throws ServletException, IOException {
    Client client = clientDao.retrieveById("CLEE8BD");
    when(session.getAttribute("user")).thenReturn(client);
    when(session.getAttribute("role")).thenReturn("client");
    servlet.doGet(request, response);
    verify(request).getRequestDispatcher("/client/historyClient.jsp");

    //rollback
    List<Estimate> updatedEstimate = estimateDao.retrieveAll();
    List<Order> updatedOrder = orderDao.retrieveAll();
    List<Order> originalOrder = orderDao.retrieveAll();
    List<Estimate> originalEstimate = estimateDao.retrieveAll();
    for (Order item : originalOrder) {
      for (Order updated : updatedOrder) {
        if (item.getIdOrder().equals(updated.getIdOrder())
            && !(item.getState().equals(updated.getState()))) {
          orderDao.update(item);
        }
      }
    }

    for (Estimate item : originalEstimate) {
      for (Estimate updated : updatedEstimate) {
        if (item.getIdEstimate().equals(updated.getIdEstimate())
            && !(item.getState().equals(updated.getState()))) {
          estimateDao.update(item);
        }
      }
    }
  }

  @Test
  void historyServletTest_NullSession_ExceptionThrown() throws ServletException, IOException {
    when(request.getSession()).thenReturn(null);
    servlet.doGet(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void historyServletTest_WrongRole_ExceptionThrown() throws ServletException, IOException {
    when(session.getAttribute("role")).thenReturn("advisor");
    servlet.doGet(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void historyServletTest_clientRole_ExceptionThrown() throws ServletException, IOException {
    when(session.getAttribute("role")).thenReturn("client");
    when(session.getAttribute("user")).thenReturn(null);
    servlet.doGet(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

}