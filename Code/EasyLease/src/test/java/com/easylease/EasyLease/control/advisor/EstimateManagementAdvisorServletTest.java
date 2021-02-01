package com.easylease.EasyLease.control.advisor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.advisor.DbAdvisorDao;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.estimate.DbEstimateDao;
import com.easylease.EasyLease.model.estimate.Estimate;
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

  private final DbAdvisorDao dbAdvisorDao = (DbAdvisorDao) DbAdvisorDao.getInstance();
  private DbEstimateDao dbEstimateDao;
  private EstimateManagementAdvisorServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new EstimateManagementAdvisorServlet();
    dbEstimateDao = (DbEstimateDao) DbEstimateDao.getInstance();
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
  void successRequiredState() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(
        dbAdvisorDao.retrieveById("ADJdybc"));
    when(request.getParameter("id_estimate")).thenReturn("ES76tRE");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher(
        "/advisor/estimateManagementAdvisor.jsp");
    assertEquals("Preso in carico",
        dbEstimateDao.retrieveById("ES76tRE").getState());
    Estimate estimate = dbEstimateDao.retrieveById("ES76tRE");
    estimate.setState("Richiesto");
    dbEstimateDao.update(estimate);
  }

  @Test
  void successStipulatedState() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(new Advisor());
    when(request.getParameter("id_estimate")).thenReturn("ESdnA9G");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher(
        "/advisor/estimateManagementAdvisor.jsp");
    Estimate estimate = dbEstimateDao.retrieveById("ESdnA9G");
    assertEquals("Stipulato", estimate.getState());
  }

  @Test
  void successTakenState() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(new Advisor());
    when(request.getParameter("id_estimate")).thenReturn("ESgY65R");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher(
        "/advisor/estimateManagementAdvisor.jsp");
    Estimate estimate = dbEstimateDao.retrieveById("ESgY65R");
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
        dbAdvisorDao.retrieveById("ADJdybc"));
    when(request.getParameter("id_estimate")).thenReturn(null);
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void nullEstimateGiven() throws ServletException, IOException {
    when(request.getSession().getAttribute("user")).thenReturn(
        dbAdvisorDao.retrieveById("ADJdybc"));
    when(request.getParameter("id_estimate")).thenReturn("ESxxxxx");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

}
