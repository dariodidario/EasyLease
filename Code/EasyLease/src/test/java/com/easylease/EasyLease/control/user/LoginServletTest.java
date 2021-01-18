package com.easylease.EasyLease.control.user;


import com.easylease.EasyLease.control.client.HistoryClientServlet;
import com.easylease.EasyLease.model.admin.DBAdminDAO;
import com.easylease.EasyLease.model.advisor.DBAdvisorDAO;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.DBClientDAO;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.mysql.cj.jdbc.MysqlDataSource;
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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LoginServletTest {
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

  private LoginServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();
  private DBClientDAO dbClientDAO;
  private DBAdminDAO dbAdminDAO;
  private DBAdvisorDAO dbAdvisorDAO;

  @BeforeEach
  void setUp() throws SQLException {
    MockitoAnnotations.openMocks(this);
    dbClientDAO = (DBClientDAO) DBClientDAO.getInstance();
    dbAdminDAO = (DBAdminDAO) DBAdminDAO.getInstance();
    dbAdvisorDAO = (DBAdvisorDAO) DBAdvisorDAO.getInstance();
    servlet = new LoginServlet();
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql//localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("master");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);
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
      attributes.put(key,value);
      return null;
    }).when(session).setAttribute(anyString(), any());
  }

  @AfterEach
  void tearDown() {

  }

  @Test
  void SuccessAdmin() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("giu.digiamp@giudigiamp.com");
    when(request.getParameter("userPassword")).thenReturn("pass");
    servlet.doPost(request,response);
    verify(request).getRequestDispatcher("/user/homePageJSP.jsp");
    assertEquals("admin", request.getSession().getAttribute("role"));
  }

  @Test
  void SuccessAdvisor() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("rossa.clementina@frutta.com");
    when(request.getParameter("userPassword")).thenReturn("pass");
    servlet.doPost(request,response);
    verify(request).getRequestDispatcher("/user/homePageJSP.jsp");
    assertEquals("admin", request.getSession().getAttribute("role"));
  }

  @Test
  void SuccessClient() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("mattia.caprio@unisa.com");
    when(request.getParameter("userPassword")).thenReturn("pass");
    servlet.doPost(request,response);
    verify(request).getRequestDispatcher("/user/homePageJSP.jsp");
    assertEquals("client", request.getSession().getAttribute("role"));
  }

  @Test
  void unsuccess() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("aaaa@giudigiamp.com");
    when(request.getParameter("userPassword")).thenReturn("pass");
    servlet.doPost(request,response);
    verify(request).getRequestDispatcher("/user/homePageJSP.jsp");
  }

  @Test
  void user_null() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn(null);
    when(request.getParameter("userPassword")).thenReturn("pass");
    assertThrows(IllegalArgumentException.class,()->{servlet.doGet(request,response);});
  }

  @Test
  void password_null() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("giu.digiamp@giudigiamp.com");
    when(request.getParameter("userPassword")).thenReturn(null);
    assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
  }


}

