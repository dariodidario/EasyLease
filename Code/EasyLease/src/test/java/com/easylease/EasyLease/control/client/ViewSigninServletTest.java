package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.model.DBPool.DbConnection;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Torino Francesco Maria
 * @version 0.1
 * @since 0.1
 */

class ViewSigninServletTest {
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

  private ViewSignInServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();
  private static DbConnection dbConnection;

  @BeforeEach
  void setUp() throws SQLException {
    MockitoAnnotations.openMocks(this);
    servlet = new ViewSignInServlet();
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("root");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);
    dbConnection = DbConnection.getInstance();
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
  void Success() throws ServletException, IOException {
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher(
        "/client/signIn.jsp");
  }

}



