package com.easylease.easylease.control.admin;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.easylease.easylease.model.DBPool.DbConnection;
import com.easylease.easylease.model.admin.Admin;
import com.easylease.easylease.model.car.CarDao;
import com.easylease.easylease.model.car.DbCarDao;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;



class ViewUpdateCarServletTest {
  private ViewUpdateCarServlet servlet;
  private HttpServletRequest request;
  private HttpServletResponse response;
  private HttpSession session;
  private RequestDispatcher dispatcher;
  private ServletContext context;
  private ServletConfig config;
  private CarDao carDao;
  private static DbConnection dbConnection;

  @BeforeEach
  public void setUp() throws IOException, ServletException, SQLException {
    MockitoAnnotations.openMocks(this);
    servlet = new ViewUpdateCarServlet();
    config = mock(ServletConfig.class);
    servlet.init(config);
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    session = mock(HttpSession.class);
    context = mock(ServletContext.class);
    dispatcher = mock(RequestDispatcher.class);
    dbConnection = DbConnection.getInstance();
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://127.0.0.1:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("root");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);

    dbConnection.setDataSource(mysqlDataSource);
    carDao = DbCarDao.getInstance();
    when(request.getSession()).thenReturn(session);
    when(servlet.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);

  }

  @Test
  void roleNull() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn(null);

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/user/login.jsp");
  }

  @Test
  void userNotAdmin() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("client");

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/user/login.jsp");
  }

  @Test
  void carIdNull() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("Car_id")).thenReturn(null);

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/user/login.jsp");
  }

  @Test
  void emptyCarId() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("Car_id")).thenReturn("");

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/user/login.jsp");
  }

  @Test
  void doGet() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("Car_id")).thenReturn("CA0EUZR");
    Admin admin = new Admin("1234567", "Antonio", "Sarro",
        "test@gmail.com", "recovery@gmail.com");
    when(request.getSession().getAttribute("user")).thenReturn(admin);

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/admin/updateCar.jsp");
  }
}