package com.easylease.easylease.control.admin;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.easylease.easylease.model.DBPool.DbConnection;
import com.easylease.easylease.model.car.Car;
import com.easylease.easylease.model.car.CarDao;
import com.easylease.easylease.model.car.DbCarDao;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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


class DeleteCarServletTest {
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
  private DeleteCarServlet servlet;
  private CarDao carDao;
  private StringWriter responseWriter;
  private ServletConfig config;
  private static DbConnection dbConnection;


  @BeforeEach
  public void setUp() throws IOException, ServletException, SQLException {
    MockitoAnnotations.openMocks(this);
    servlet = new DeleteCarServlet();
    config = mock(ServletConfig.class);
    servlet.init(config);
    responseWriter = new StringWriter();
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
    when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
    when(servlet.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
  }

  @AfterEach
  void tearDown() {

  }


  @Test
  void testRoleNull() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn(null);
    when(request.getParameter("ID_Delete")).thenReturn("CAAA111");

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/user/login.jsp");
  }

  @Test
  void testRoleNotAdmin() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("client");
    when(request.getParameter("ID_Delete")).thenReturn("CAAA111");

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/user/login.jsp");
  }


  @Test
  void testIdDeleteNull() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Delete")).thenReturn(null);
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }


  @Test
  void testIdDeleteEmpty() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Delete")).thenReturn("");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }


  @Test
  void testDoGetSuccess() throws ServletException, IOException {
    Car carold = carDao.retrieveById("CA5ezEH");
    File uploads = new File("src/main/webapp/img");
    File file = new File(uploads.getAbsolutePath(), carold.getImage());
    File temp = new File(uploads.getAbsolutePath(), "tmpImg.jpg");

    Files.copy(file.toPath(), temp.toPath());
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Delete")).thenReturn("CA5ezEH");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");
    when(request.getServletContext()).thenReturn(context);
    when(context.getRealPath(any())).thenReturn(uploads.getAbsolutePath());

    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());

    Car car = carDao.retrieveById("CA5ezEH");
    car.setVisibility(true);
    carDao.update(car);
    Files.copy(temp.toPath(), file.toPath());
    Files.delete(temp.toPath());

  }
}
