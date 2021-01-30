package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.advisor.AdvisorDao;
import com.easylease.EasyLease.model.advisor.DbAdvisorDao;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.ClientDao;
import com.easylease.EasyLease.model.client.DbClientDao;
import com.easylease.EasyLease.model.estimate.DbEstimateDao;
import com.easylease.EasyLease.model.estimate.EstimateDao;
import com.easylease.EasyLease.model.order.DbOrderDao;
import com.easylease.EasyLease.model.order.Order;
import com.easylease.EasyLease.model.order.OrderDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ConfirmOrderServletTest {
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
  private ConfirmOrderServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new ConfirmOrderServlet();
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
  void confirmOrderServlet_Confirm_Success() throws ServletException, IOException {
    Client client = clientDao.retrieveById("CLEE8BD");
    when(session.getAttribute("user")).thenReturn(client);
    when(request.getParameter("choice")).thenReturn("Confermato");
    when(request.getParameter("id_order")).thenReturn("ORhfga2");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/client/orderCheckout.jsp");

    //rollback
    Order updatedOrder = orderDao.retrieveById("ORhfga2");
    updatedOrder.setState("Attesa");
    orderDao.update(updatedOrder);
  }

  @Test
  void confirmOrderServlet_Refuse_Success() throws ServletException, IOException {
    Client client = clientDao.retrieveById("CLEE8BD");
    when(session.getAttribute("user")).thenReturn(client);
    when(request.getParameter("choice")).thenReturn("Non confermato");
    when(request.getParameter("id_order")).thenReturn("ORhfga2");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/HistoryClientServlet");

    //rollback
    Order updatedOrder = orderDao.retrieveById("ORhfga2");
    updatedOrder.setState("Attesa");
    updatedOrder.setVisibility(true);
    orderDao.update(updatedOrder);
  }

  @Test
  void confirmOrderServlet_Pay_Success() throws ServletException, IOException {
    Client client = clientDao.retrieveById("CLEE8BD");
    when(session.getAttribute("user")).thenReturn(client);
    when(request.getParameter("choice")).thenReturn("Paga");
    when(request.getParameter("id_order")).thenReturn("ORhfga2");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/client/orderCheckout.jsp");

    //rollback
    Order updatedOrder = orderDao.retrieveById("ORhfga2");
    updatedOrder.setState("Attesa");
    orderDao.update(updatedOrder);
  }

  @Test
  void confirmOrderServlet_WrongUser_ExceptionThrown() throws ServletException, IOException {
    Advisor advisor = advisorDao.retrieveById("ADJdybc");
    when(session.getAttribute("user")).thenReturn(advisor);
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

}