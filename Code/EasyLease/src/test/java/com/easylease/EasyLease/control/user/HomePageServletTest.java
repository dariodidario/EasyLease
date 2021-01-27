package com.easylease.EasyLease.control.user;

import com.easylease.EasyLease.model.DBPool.DBConnection;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.CarDAO;
import com.easylease.EasyLease.model.car.DBCarDAO;
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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HomePageServletTest {
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

  private CarDAO dbCar;
  private HomePageServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();
  private static DBConnection dbConnection;

  @BeforeEach
  void setUp() throws SQLException, IOException {
    MockitoAnnotations.openMocks(this);
    servlet = new HomePageServlet();
    dbConnection = DBConnection.getInstance();
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://localhost:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("master");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);

    dbConnection.setDataSource(mysqlDataSource);
    dbCar = DBCarDAO.getInstance();
    when(request.getServletContext()).thenReturn(context);
    when(response.getWriter()).thenReturn(printWriter);
    when(context.getContextPath()).thenReturn("");
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void doGetModel() throws ServletException, IOException {
    when(request.getParameter("tipologia")).thenReturn(null);
    when(request.getParameter("marca")).thenReturn(null);
    when(request.getParameter("modello")).thenReturn("corsa");
    servlet.doGet(request,response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void doGetBrand() throws ServletException, IOException {
    when(request.getParameter("tipologia")).thenReturn(null);
    when(request.getParameter("marca")).thenReturn("Opel");
    when(request.getParameter("modello")).thenReturn(null);
    servlet.doGet(request,response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void doGetType() throws ServletException, IOException {
    List<Car> carList = dbCar.retrieveByType("SUV");
    when(request.getParameter("tipologia")).thenReturn("SUV");
    when(request.getParameter("marca")).thenReturn(null);
    when(request.getParameter("modello")).thenReturn(null);
    servlet.doGet(request,response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void doGetAll() throws ServletException, IOException {
    List<Car> carList = dbCar.retrieveByType("SUV");
    when(request.getParameter("tipologia")).thenReturn(null);
    when(request.getParameter("marca")).thenReturn(null);
    when(request.getParameter("modello")).thenReturn(null);
    servlet.doGet(request,response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

  @Test
  void doPost() throws ServletException, IOException {
    List<Car> carList = dbCar.retrieveByType("SUV");
    when(request.getParameter("tipologia")).thenReturn("SUV");
    when(request.getParameter("marca")).thenReturn(null);
    when(request.getParameter("modello")).thenReturn(null);
    servlet.doPost(request,response);
    verify(request).getRequestDispatcher("/user/homePage.jsp");
  }

}