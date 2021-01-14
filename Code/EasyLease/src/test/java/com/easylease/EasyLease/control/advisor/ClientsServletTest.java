package com.easylease.EasyLease.control.advisor;

import com.easylease.EasyLease.control.client.OrderCheckoutServlet;
import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.advisor.AdvisorDAO;
import com.easylease.EasyLease.model.advisor.DBAdvisorDAO;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.order.DBOrderDAO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClientsServletTest {
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

  private ClientsServlet servlet;
  private AdvisorDAO advisorDAO;
  private final Map<String, Object> attributes = new HashMap<>();

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    servlet = new ClientsServlet();
    advisorDAO = DBAdvisorDAO.getIstance();
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
  void Success() throws ServletException, IOException {
    Advisor advisor = advisorDAO.retrieveById("ADJdybc");
    when(request.getSession().getAttribute("user")).thenReturn(advisor);
    servlet.doGet(request, response);
    verify(request).getRequestDispatcher("/advisor/clientsJSP.jsp");
  }

  @Test
  void sessionNull() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(null);
    servlet.doGet(request, response);
    verify(request).getRequestDispatcher("/user/homePageJSP.jsp");
  }
}