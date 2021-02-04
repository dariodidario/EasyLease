package com.easylease.easylease.control.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.easylease.easylease.model.advisor.Advisor;
import com.easylease.easylease.model.advisor.AdvisorDao;
import com.easylease.easylease.model.advisor.DbAdvisorDao;
import com.easylease.easylease.model.client.Client;
import com.easylease.easylease.model.client.ClientDao;
import com.easylease.easylease.model.client.DbClientDao;
import com.easylease.easylease.model.estimate.DbEstimateDao;
import com.easylease.easylease.model.estimate.Estimate;
import com.easylease.easylease.model.estimate.EstimateDao;
import com.easylease.easylease.model.order.DbOrderDao;
import com.easylease.easylease.model.order.Order;
import com.easylease.easylease.model.order.OrderDao;
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


class ConfirmEstimateServletTest {
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
  private AdvisorDao advisorDao;
  private ConfirmEstimateServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new ConfirmEstimateServlet();
    clientDao = DbClientDao.getInstance();
    estimateDao = DbEstimateDao.getInstance();
    orderDao = DbOrderDao.getInstance();
    advisorDao = DbAdvisorDao.getInstance();
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
  void confirmEstimateServlet_Confirmed_Success() throws ServletException, IOException {
    List<Order> orderList = orderDao.retrieveAll();
    Client client = clientDao.retrieveById("CLEE8BD");
    when(session.getAttribute("user")).thenReturn(client);
    when(request.getParameter("id_estimate")).thenReturn("ESdnA9G");
    when(request.getParameter("choice")).thenReturn("Confermato");
    servlet.doGet(request, response);
    verify(request).getRequestDispatcher("/HistoryClientServlet");

    //rollback
    Estimate estimate = estimateDao.retrieveById("ESdnA9G");
    estimate.setState("Stipulato");
    estimate.setVisibility(true);
    estimateDao.update(estimate);
    List<Order> updatedOrders = orderDao.retrieveAll();
    for (Order item : updatedOrders) {
      boolean found = false;
      for (Order item2 : orderList) {
        if (found == false && item.getIdOrder().equals(item2.getIdOrder())) {
          found = true;
        }
      }
      if (found == false) {
        orderDao.delete(item);
      }
    }
  }

  @Test
  void confirmEstimateServlet_Refused_Success() throws ServletException, IOException {
    Client client = clientDao.retrieveById("CLEE8BD");
    when(session.getAttribute("user")).thenReturn(client);
    when(request.getParameter("id_estimate")).thenReturn("ESdnA9G");
    when(request.getParameter("choice")).thenReturn("Non confermato");
    servlet.doGet(request, response);
    verify(request).getRequestDispatcher("/HistoryClientServlet");

    //rollback
    Estimate estimate = estimateDao.retrieveById("ESdnA9G");
    estimate.setState("Stipulato");
    estimate.setVisibility(true);
    estimateDao.update(estimate);
  }

  @Test
  void confirmEstimateServlet_WrongUser_ThrowsException() throws ServletException, IOException {
    Advisor advisor = advisorDao.retrieveById("ADJdybc");
    when(session.getAttribute("user")).thenReturn(advisor);
    servlet.doGet(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");

  }
}