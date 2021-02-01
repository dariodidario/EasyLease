package com.easylease.EasyLease.control.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.easylease.EasyLease.model.DBPool.DbConnection;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.ClientDao;
import com.easylease.EasyLease.model.client.DbClientDao;
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


class SigninServletTest {
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

  private ClientDao clientDao;
  private SignInServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();
  private static DbConnection dbConnection;

  @BeforeEach
  void setUp() throws SQLException {
    MockitoAnnotations.openMocks(this);
    servlet = new SignInServlet();
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("root");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);
    dbConnection = DbConnection.getInstance();
    dbConnection.setDataSource(mysqlDataSource);
    clientDao = DbClientDao.getInstance();
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
  void signIn_Success() throws ServletException, IOException {
    when(request.getParameter("name")).thenReturn("Francesco");
    when(request.getParameter("surname")).thenReturn("Torino");
    when(request.getParameter("email")).thenReturn(
        "francesco.torino1999@gmail.com");
    when(request.getParameter("birthplace")).thenReturn("Caserta");
    when(request.getParameter("birthdate")).thenReturn(("1999-09-18"));
    when(request.getParameter("kind")).thenReturn("Uomo");
    when(request.getParameter("city")).thenReturn("Caserta");
    when(request.getParameter("pc")).thenReturn("81100");
    when(request.getParameter("street")).thenReturn("Isonzo");
    when(request.getParameter("password")).thenReturn("pass");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/login.jsp");
    Client client = clientDao.retrieveByEmail("francesco.torino1999@gmail.com");
    String email = "francesco.torino1999@gmail.com";
    assertEquals(email, client.getEmail());
    request.getSession().removeAttribute("ok");
    clientDao.delete(client);
  }

  @Test
  void signIn_unsuccess_exist() throws ServletException, IOException {
    when(request.getParameter("name")).thenReturn("Francesco");
    when(request.getParameter("surname")).thenReturn("Torino");
    when(request.getParameter("email")).thenReturn("mattia.caprio@unisa.com");
    when(request.getParameter("birthplace")).thenReturn("Caserta");
    when(request.getParameter("birthdate")).thenReturn(("1999-09-18"));
    when(request.getParameter("kind")).thenReturn("Uomo");
    when(request.getParameter("city")).thenReturn("Caserta");
    when(request.getParameter("pc")).thenReturn("81100");
    when(request.getParameter("street")).thenReturn("Isonzo");
    when(request.getParameter("password")).thenReturn("pass");
    servlet.doPost(request, response);
    request.getSession().removeAttribute("exist");
    verify(request).getRequestDispatcher("/client/signIn.jsp");
  }

}