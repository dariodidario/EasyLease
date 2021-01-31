package com.easylease.EasyLease.control.fragments;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.easylease.EasyLease.model.DBPool.DbConnection;
import com.easylease.EasyLease.model.order.DbOrderDao;
import com.easylease.EasyLease.model.order.OrderDao;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class FooterServletTest {
  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private ServletContext context;
  @Mock
  private PrintWriter printWriter;
  @Mock
  private RequestDispatcher dispatcher;

  private OrderDao dbOrder;
  private FooterServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();
  private static DbConnection dbConnection;

  @BeforeEach
  void setUp() throws SQLException, IOException {
    MockitoAnnotations.openMocks(this);
    servlet = new FooterServlet();
    dbConnection = DbConnection.getInstance();
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("root");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);
    dbConnection.setDataSource(mysqlDataSource);
    dbOrder = DbOrderDao.getInstance();
    when(response.getWriter()).thenReturn(printWriter);

  }


  @Test
  void doPost() throws ServletException, IOException {
    servlet.doPost(request, response);
    verify(response).setContentType("text/plain");
    verify(response.getWriter()).write((dbOrder.retrieveAll().size() + 936) + "");
  }

  @Test
  void doGet() throws ServletException, IOException {
    servlet.doGet(request, response);
    verify(response).setContentType("text/plain");
    verify(response.getWriter()).write((dbOrder.retrieveAll().size() + 936) + "");
  }

}