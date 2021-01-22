package com.easylease.EasyLease.control.fragments;

import com.easylease.EasyLease.control.user.HomePageServlet;
import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.car.CarDAO;
import com.easylease.EasyLease.model.car.DBCarDAO;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.OrderDAO;
import com.mysql.cj.jdbc.MysqlDataSource;
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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class FooterServletTest {
/*

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

  private OrderDAO dbOrder;
  private FooterServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();
  private static DBConnection dbConnection;

  @BeforeEach
  void setUp() throws SQLException, IOException {
    MockitoAnnotations.openMocks(this);
    servlet = new FooterServlet();
    dbConnection = DBConnection.getInstance();
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("master");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);

    dbConnection.setDataSource(mysqlDataSource);
    dbOrder = DBOrderDAO.getInstance();
    when(response.getWriter()).thenReturn(printWriter);

  }


  @Test
  void doPost() throws ServletException, IOException {
    servlet.doPost(request,response);
    verify(response).setContentType("text/plain");
    verify(response.getWriter()).write((dbOrder.retrieveAll().size()+936)+"");
  }

  @Test
  void doGet() throws ServletException, IOException {
    servlet.doGet(request,response);
    verify(response).setContentType("text/plain");
    verify(response.getWriter()).write((dbOrder.retrieveAll().size()+936)+"");
  }
  
 */
}