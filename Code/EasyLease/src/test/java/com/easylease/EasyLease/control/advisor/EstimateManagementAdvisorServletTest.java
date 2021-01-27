package com.easylease.EasyLease.control.advisor;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.advisor.DBAdvisorDAO;
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
 * @author Caprio Mattia
 * @version 0.1
 * @since 0.1
 */
class EstimateManagementAdvisorServletTest {
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

  private final DBAdvisorDAO dbAdvisorDAO = (DBAdvisorDAO) DBAdvisorDAO.getInstance();
  private DBEstimateDAO dbEstimateDAO;
  private EstimateManagementAdvisorServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new EstimateManagementAdvisorServlet();
    dbEstimateDAO = (DBEstimateDAO) DBEstimateDAO.getInstance();
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
  void SuccessRequiredState() throws ServletException, IOException {
    Estimate estimate = dbEstimateDAO.retrieveById("ES76tRE");
    when(request.getSession().getAttribute("user")).thenReturn(
        dbAdvisorDAO.retrieveById("ADJdybc"));
    when(request.getParameter("id_estimate")).thenReturn("ES76tRE");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher(
        "/advisor/estimateManagementAdvisor.jsp");
    assertEquals("Preso in carico",
        dbEstimateDAO.retrieveById("ES76tRE").getState());
    estimate.setState("Richiesto");
    dbEstimateDAO.update(estimate);
  }

  @Test
  void SuccessStipulatedState() throws ServletException, IOException {
    Estimate estimate = dbEstimateDAO.retrieveById("ESdnA9G");
    when(request.getSession().getAttribute("user")).thenReturn(new Advisor());
    when(request.getParameter("id_estimate")).thenReturn("ESdnA9G");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher(
        "/advisor/estimateManagementAdvisor.jsp");
    assertEquals("Stipulato", estimate.getState());
  }

  @Test
  void SuccessTakenState() throws ServletException, IOException {
    Estimate estimate = dbEstimateDAO.retrieveById("ESgY65R");
    when(request.getSession().getAttribute("user")).thenReturn(new Advisor());
    when(request.getParameter("id_estimate")).thenReturn("ESgY65R");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher(
        "/advisor/estimateManagementAdvisor.jsp");
    assertEquals("Preso in carico", estimate.getState());
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
    when(request.getParameter("id_estimate")).thenReturn("ESgY65R");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void wrongEstimateGiven() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(
        dbAdvisorDAO.retrieveById("ADJdybc"));
    when(request.getParameter("id_estimate")).thenReturn(null);
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void nullEstimateGiven() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(
        dbAdvisorDAO.retrieveById("ADJdybc"));
    when(request.getParameter("id_estimate")).thenReturn("ESxxxxx");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

}
