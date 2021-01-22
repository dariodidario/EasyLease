package com.easylease.EasyLease.control.user;


import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.mysql.cj.jdbc.MysqlDataSource;
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
import java.io.PrintWriter;
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
  @Mock
  private PrintWriter printWriter;

  private LoginServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();
  private static DBConnection dbConnection;

  @BeforeEach
  void setUp() throws SQLException {
    MockitoAnnotations.openMocks(this);
    servlet = new LoginServlet();
    dbConnection = DBConnection.getInstance();
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
  void SuccessAdmin() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("giu.digiamp@giudigiamp.com");
    when(request.getParameter("userPassword")).thenReturn("pass");
    servlet.doPost(request,response);
    verify(request).getRequestDispatcher("/user/homePageJSP.jsp");
    //assertEquals("admin", request.getSession().getAttribute("role"));
    request.getSession().invalidate();
  }

  @Test
  void SuccessAdvisor() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("rossa.clementina@frutta.com");
    when(request.getParameter("userPassword")).thenReturn("pass");
    servlet.doPost(request,response);
    verify(request).getRequestDispatcher("/user/homePageJSP.jsp");
    //assertEquals("advisor", request.getSession().getAttribute("role"));
    request.getSession().invalidate();
  }

  @Test
  void SuccessClient() throws ServletException, IOException {
    when(request.getParameter("userEmail")).thenReturn("mattia.caprio@unisa.com");
    when(request.getParameter("userPassword")).thenReturn("pass");
    servlet.doPost(request,response);
    verify(request).getRequestDispatcher("/user/homePageJSP.jsp");
    //assertEquals("client", request.getSession().getAttribute("role"));
    request.getSession().invalidate();
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

