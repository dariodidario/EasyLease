package com.easylease.EasyLease.control.advisor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.easylease.EasyLease.model.advisor.DbAdvisorDao;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.order.DbOrderDao;
import com.easylease.EasyLease.model.order.Order;
import java.io.IOException;
import java.util.HashMap;
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
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;


/**
 * @author Caprio Mattia
 * @version 0.1
 * @since 0.1
 */
class OrderValidationServletTest {
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

  private final DbAdvisorDao dbAdvisorDao = (DbAdvisorDao) DbAdvisorDao.getInstance();
  private DbOrderDao dbOrderDao;
  private OrderValidationServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new OrderValidationServlet();
    dbOrderDao = (DbOrderDao) DbOrderDao.getInstance();
    when(request.getServletContext()).thenReturn(context);
    when(request.getSession()).thenReturn(session);
    when(context.getContextPath()).thenReturn("");
    when(session.isNew()).thenReturn(true);
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

    doAnswer((Answer<Object>) invocation -> {
      String key = (String) invocation.getArguments()[0];
      attributes.get(key);
      return null;
    }).when(session).getAttribute(anyString());

    doAnswer((Answer<Object>) invocation -> {
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
  void successNotValidationOrder() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(
        dbAdvisorDao.retrieveById("ADJdybc"));
    when(request.getParameter("id")).thenReturn("ORd3Jks");
    when(request.getParameter("choice")).thenReturn("false");
    Order order = dbOrderDao.retrieveById("ORd3Jks");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher(
        "/advisor/orderManagementAdvisor.jsp");
    dbOrderDao.update(order);
  }

  @Test
  void successValidationOrder() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(
        dbAdvisorDao.retrieveById("ADJdybc"));
    when(request.getParameter("id")).thenReturn("ORd3Jks");
    when(request.getParameter("choice")).thenReturn("true");
    when(request.getParameter("date")).thenReturn("2021-02-24");
    Order order = dbOrderDao.retrieveById("ORd3Jks");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher(
        "/advisor/orderManagementAdvisor.jsp");
    dbOrderDao.update(order);
  }

  @Test
  void successWrongStateOrder() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(
        dbAdvisorDao.retrieveById("ADJdybc"));
    when(request.getParameter("id")).thenReturn("ORlk7Bn");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void nullSession() throws ServletException, IOException {
    when(request.getSession()).thenReturn(null);
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void wrongUserGiven() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(new Client());
    when(request.getParameter("id")).thenReturn("ORlk7Bn");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void wrongOrderteGiven() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(
        dbAdvisorDao.retrieveById("ADJdybc"));
    when(request.getParameter("id")).thenReturn(null);
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void nullOrderGiven() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(
        dbAdvisorDao.retrieveById("ADJdybc"));
    when(request.getParameter("id")).thenReturn("ORxxxxx");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }
}