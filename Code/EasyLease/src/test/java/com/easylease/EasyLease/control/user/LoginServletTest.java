package com.easylease.EasyLease.control.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.easylease.EasyLease.model.DBPool.DbConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
  @Mock
  private PrintWriter printWriter;

  private LoginServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();
  private static DbConnection dbConnection;

  @BeforeEach
  void setUp() throws SQLException {
    MockitoAnnotations.openMocks(this);
    servlet = new LoginServlet();
    dbConnection = DbConnection.getInstance();
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("root");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);

    dbConnection.setDataSource(mysqlDataSource);
    when(request.getServletContext()).thenReturn(context);
    try {
      when(response.getWriter()).thenReturn(printWriter);
    } catch (IOException e) {
      e.printStackTrace();
    }
    when(context.getContextPath()).thenReturn("");
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
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
  void successAdmin() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("lucaVerdi@easylease.com");
    when(request.getParameter("userPassword")).thenReturn("pass");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
    request.getSession().removeAttribute("userEmail");
    request.getSession().removeAttribute("userPassword");
  }

  @Test
  void successAdvisor() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("marcoGreco@easylease.com");
    when(request.getParameter("userPassword")).thenReturn("pass");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
    request.getSession().invalidate();
  }

  @Test
  void successClient() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("mattia.caprio@unisa.com");
    when(request.getParameter("userPassword")).thenReturn("pass");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
    request.getSession().invalidate();
  }

  @Test
  void unsuccess() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("aaaa@giudigiamp.com");
    when(request.getParameter("userPassword")).thenReturn("pass");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/login.jsp");
  }

  @Test
  void unsuccessClient() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("mattia.caprio@unisa.com");
    when(request.getParameter("userPassword")).thenReturn("a");
    servlet.doPost(request, response);
    request.getSession().removeAttribute("errata");
    verify(request).getRequestDispatcher("/user/login.jsp");
  }

  @Test
  void unsuccessAdvisor() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("marcoGreco@easylease.com");
    when(request.getParameter("userPassword")).thenReturn("a");
    servlet.doPost(request, response);
    request.getSession().removeAttribute("errata");
    verify(request).getRequestDispatcher("/user/login.jsp");
  }

  @Test
  void unsuccessAdmin() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("lucaVerdi@easylease.com");
    when(request.getParameter("userPassword")).thenReturn("a");
    servlet.doPost(request, response);
    request.getSession().removeAttribute("errata");
    verify(request).getRequestDispatcher("/user/login.jsp");
  }

  @Test
  void user_null() {
    when(request.getParameter("userEmail")).thenReturn(null);
    when(request.getParameter("userPassword")).thenReturn("pass");
    assertThrows(IllegalArgumentException.class, () -> {
      servlet.doGet(request, response); });
  }

  @Test
  void password_null() {
    when(request.getParameter("userEmail")).thenReturn("lucaVerdi@easylease.com");
    when(request.getParameter("userPassword")).thenReturn(null);
    assertThrows(NullPointerException.class, () -> {
      servlet.doGet(request, response);  });
  }


}

