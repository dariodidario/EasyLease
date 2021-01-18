package com.easylease.EasyLease.control.user;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.advisor.DBAdvisorDAO;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.DBCarDAO;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Torino Francesco Maria
 * @version 0.1
 * @since 0.1
 */
class ViewCarServletTest {
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

  private DBCarDAO dbCarDAO;
  private ViewCarServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new ViewCarServlet();
    dbCarDAO = (DBCarDAO) DBCarDAO.getInstance();
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
  void Success() throws ServletException, IOException {
    Car car = dbCarDAO.retrieveByModel("3008");
    when(request.getParameter("model")).thenReturn(
        "3008");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher(
        "/user/viewCarJSP.jsp");
    assertEquals(car,
        request.getAttribute("car"));
  }

  @Test
  void model_null() throws ServletException, IOException {
    when(request.getParameter("model")).thenReturn(
        null);
    assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
  }
}
