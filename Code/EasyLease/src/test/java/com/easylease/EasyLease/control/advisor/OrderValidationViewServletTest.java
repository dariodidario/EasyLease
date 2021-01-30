package com.easylease.EasyLease.control.advisor;

import com.easylease.EasyLease.model.advisor.DbAdvisorDao;
import com.easylease.EasyLease.model.client.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import static org.mockito.Mockito.*;

/**
 * @author Caprio Mattia
 * @version 0.1
 * @since 0.1
 */
class OrderValidationViewServletTest {
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

  private final DbAdvisorDao dbAdvisorDAO = (DbAdvisorDao) DbAdvisorDao.getInstance();
  private OrderValidationViewServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new OrderValidationViewServlet();
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
  void SuccessOrder() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(
        dbAdvisorDAO.retrieveById("ADJdybc"));
    when(request.getParameter("id")).thenReturn("ORd3Jks");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/advisor/orderValidation.jsp");
  }

  @Test
  void SuccessWrongStateOrder() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(
        dbAdvisorDAO.retrieveById("ADJdybc"));
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
        dbAdvisorDAO.retrieveById("ADJdybc"));
    when(request.getParameter("id")).thenReturn(null);
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void nullOrderGiven() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(
        dbAdvisorDAO.retrieveById("ADJdybc"));
    when(request.getParameter("id")).thenReturn("ORxxxxx");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

}
