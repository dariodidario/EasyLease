package com.easylease.EasyLease.control.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.easylease.EasyLease.model.DBPool.DbConnection;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.CarDao;
import com.easylease.EasyLease.model.car.DbCarDao;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class AddCarServletTest {
  private AddCarServlet servlet;
  private HttpServletRequest request;
  private HttpServletResponse response;
  private HttpSession session;
  private CarDao carDao;
  private StringWriter responseWriter;
  private RequestDispatcher dispatcher;
  private ServletContext context;
  private ServletConfig config;
  private static DbConnection dbConnection;
  private Part part;


  @BeforeEach
  public void setUp() throws IOException, ServletException, SQLException {
    MockitoAnnotations.openMocks(this);
    servlet = new AddCarServlet();
    config = mock(ServletConfig.class);
    servlet.init(config);
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    responseWriter = new StringWriter();
    session = mock(HttpSession.class);
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL("jdbc:mysql://127.0.0.1:3306/easylease");
    mysqlDataSource.setUser("root");
    mysqlDataSource.setPassword("root");
    mysqlDataSource.setServerTimezone("UTC");
    mysqlDataSource.setVerifyServerCertificate(false);
    mysqlDataSource.setUseSSL(false);
    dbConnection = DbConnection.getInstance();
    dbConnection.setDataSource(mysqlDataSource);

    carDao = DbCarDao.getInstance();
    when(request.getSession()).thenReturn(session);
    context = mock(ServletContext.class);
    dispatcher = mock(RequestDispatcher.class);
    when(servlet.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
    part = new Part() {
      @Override
      public InputStream getInputStream() throws IOException {
        return new InputStream() {
          @Override
          public int read() throws IOException {
            return 0;
          }
        };
      }

      @Override
      public String getContentType() {
        return null;
      }

      @Override
      public String getName() {
        return null;
      }

      @Override
      public String getSubmittedFileName() {
        return "image_mia.jpg";
      }

      @Override
      public long getSize() {
        return 0;
      }

      @Override
      public void write(String s) throws IOException {

      }

      @Override
      public void delete() throws IOException {

      }

      @Override
      public String getHeader(String s) {
        return null;
      }

      @Override
      public Collection<String> getHeaders(String s) {
        return null;
      }

      @Override
      public Collection<String> getHeaderNames() {
        return null;
      }
    };
    when(request.getPart(any())).thenReturn(null);
    when(request.getServletContext()).thenReturn(context);
    when(context.getRealPath("img")).thenReturn(
        "C:\\Users\\39392\\Desktop\\EasyLease\\src\\main\\webapp\\img");
  }


  @Test
  void testRoleNull() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn(null);

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/fragments/error403.jsp");
  }

  @Test
  void testRoleNotAdmin() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("client");

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/fragments/error403.jsp");
  }


  @Test
  void testBrandNull() throws ServletException, IOException {
    Random random = new Random();
    int n = random.nextInt(1000);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("brand")).thenReturn(null);
    when(request.getParameter("model")).thenReturn(String.valueOf(n));
    when(request.getParameter("doors")).thenReturn("5");
    when(request.getParameter("car_type")).thenReturn("berlina");
    when(request.getParameter("transmission")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption")).thenReturn("12");
    when(request.getParameter("horse_power")).thenReturn("200");
    when(request.getParameter("emission_class")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions")).thenReturn("113");
    when(request.getParameter("power_supply")).thenReturn("Diesel");
    when(request.getParameter("capacity")).thenReturn("2000");
    when(request.getParameter("price")).thenReturn("500");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);

    assertEquals("text/html;charset=UTF-8", response.getContentType());

    Car car = carDao.retrieveByModel(String.valueOf(n));
    carDao.delete(car);
  }


  @Test
  void testDoorsNull() throws ServletException, IOException {
    Random random = new Random();
    int n = random.nextInt(1000);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("brand")).thenReturn("Peugeot");
    when(request.getParameter("model")).thenReturn(String.valueOf(n));
    when(request.getParameter("doors")).thenReturn(null);
    when(request.getParameter("car_type")).thenReturn("berlina");
    when(request.getParameter("transmission")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption")).thenReturn("12");
    when(request.getParameter("horse_power")).thenReturn("200");
    when(request.getParameter("emission_class")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions")).thenReturn("113");
    when(request.getParameter("power_supply")).thenReturn("Diesel");
    when(request.getParameter("capacity")).thenReturn("2000");
    when(request.getParameter("price")).thenReturn("500");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());

    Car car = carDao.retrieveByModel(String.valueOf(n));
    carDao.delete(car);
  }


  @Test
  void testCarTypeNull() throws ServletException, IOException {
    Random random = new Random();
    int n = random.nextInt(1000);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("brand")).thenReturn("Peugeot");
    when(request.getParameter("model")).thenReturn(String.valueOf(n));
    when(request.getParameter("doors")).thenReturn("5");
    when(request.getParameter("car_type")).thenReturn(null);
    when(request.getParameter("transmission")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption")).thenReturn("12");
    when(request.getParameter("horse_power")).thenReturn("200");
    when(request.getParameter("emission_class")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions")).thenReturn("113");
    when(request.getParameter("power_supply")).thenReturn("Diesel");
    when(request.getParameter("capacity")).thenReturn("2000");
    when(request.getParameter("price")).thenReturn("500");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());

    Car car = carDao.retrieveByModel(String.valueOf(n));
    carDao.delete(car);
  }


  @Test
  void testTransmissionNull() throws ServletException, IOException {
    Random random = new Random();
    int n = random.nextInt(1000);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("brand")).thenReturn("Peugeot");
    when(request.getParameter("model")).thenReturn(String.valueOf(n));
    when(request.getParameter("doors")).thenReturn("5");
    when(request.getParameter("car_type")).thenReturn("berlina");
    when(request.getParameter("transmission")).thenReturn(null);
    when(request.getParameter("avg_consumption")).thenReturn("12");
    when(request.getParameter("horse_power")).thenReturn("200");
    when(request.getParameter("emission_class")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions")).thenReturn("113");
    when(request.getParameter("power_supply")).thenReturn("Diesel");
    when(request.getParameter("capacity")).thenReturn("2000");
    when(request.getParameter("price")).thenReturn("500");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());

    Car car = carDao.retrieveByModel(String.valueOf(n));
    carDao.delete(car);
  }


  @Test
  void testAvgNull() throws ServletException, IOException {
    Random random = new Random();
    int n = random.nextInt(1000);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("brand")).thenReturn("Peugeot");
    when(request.getParameter("model")).thenReturn(String.valueOf(n));
    when(request.getParameter("doors")).thenReturn("5");
    when(request.getParameter("car_type")).thenReturn("berlina");
    when(request.getParameter("transmission")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption")).thenReturn(null);
    when(request.getParameter("horse_power")).thenReturn("200");
    when(request.getParameter("emission_class")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions")).thenReturn("113");
    when(request.getParameter("power_supply")).thenReturn("Diesel");
    when(request.getParameter("capacity")).thenReturn("2000");
    when(request.getParameter("price")).thenReturn("500");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());

    Car car = carDao.retrieveByModel(String.valueOf(n));
    carDao.delete(car);
  }


  @Test
  void testHorsePowerNull() throws ServletException, IOException {
    Random random = new Random();
    int n = random.nextInt(1000);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("brand")).thenReturn("Peugeot");
    when(request.getParameter("model")).thenReturn(String.valueOf(n));
    when(request.getParameter("doors")).thenReturn("5");
    when(request.getParameter("car_type")).thenReturn("berlina");
    when(request.getParameter("transmission")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption")).thenReturn("12");
    when(request.getParameter("horse_power")).thenReturn(null);
    when(request.getParameter("emission_class")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions")).thenReturn("113");
    when(request.getParameter("power_supply")).thenReturn("Diesel");
    when(request.getParameter("capacity")).thenReturn("2000");
    when(request.getParameter("price")).thenReturn("500");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());

    Car car = carDao.retrieveByModel(String.valueOf(n));
    carDao.delete(car);
  }


  @Test
  void testEmissionClassNull() throws ServletException, IOException {
    Random random = new Random();
    int n = random.nextInt(1000);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("brand")).thenReturn("Peugeot");
    when(request.getParameter("model")).thenReturn(String.valueOf(n));
    when(request.getParameter("doors")).thenReturn("5");
    when(request.getParameter("car_type")).thenReturn("berlina");
    when(request.getParameter("transmission")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption")).thenReturn("12");
    when(request.getParameter("horse_power")).thenReturn("200");
    when(request.getParameter("emission_class")).thenReturn(null);
    when(request.getParameter("co2_emissions")).thenReturn("113");
    when(request.getParameter("power_supply")).thenReturn("Diesel");
    when(request.getParameter("capacity")).thenReturn("2000");
    when(request.getParameter("price")).thenReturn("500");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());

    Car car = carDao.retrieveByModel(String.valueOf(n));
    carDao.delete(car);
  }


  @Test
  void testCo2EmissionsNull() throws ServletException, IOException {
    Random random = new Random();
    int n = random.nextInt(1000);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("brand")).thenReturn("Peugeot");
    when(request.getParameter("model")).thenReturn(String.valueOf(n));
    when(request.getParameter("doors")).thenReturn("5");
    when(request.getParameter("car_type")).thenReturn("berlina");
    when(request.getParameter("transmission")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption")).thenReturn("12");
    when(request.getParameter("horse_power")).thenReturn("200");
    when(request.getParameter("emission_class")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions")).thenReturn(null);
    when(request.getParameter("power_supply")).thenReturn("Diesel");
    when(request.getParameter("capacity")).thenReturn("2000");
    when(request.getParameter("price")).thenReturn("500");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());

    Car car = carDao.retrieveByModel(String.valueOf(n));
    carDao.delete(car);
  }


  @Test
  void testPowerSupplyNull() throws ServletException, IOException {
    Random random = new Random();
    int n = random.nextInt(1000);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("brand")).thenReturn("Peugeot");
    when(request.getParameter("model")).thenReturn(String.valueOf(n));
    when(request.getParameter("doors")).thenReturn("5");
    when(request.getParameter("car_type")).thenReturn("berlina");
    when(request.getParameter("transmission")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption")).thenReturn("12");
    when(request.getParameter("horse_power")).thenReturn("200");
    when(request.getParameter("emission_class")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions")).thenReturn("113");
    when(request.getParameter("power_supply")).thenReturn(null);
    when(request.getParameter("capacity")).thenReturn("2000");
    when(request.getParameter("price")).thenReturn("500");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());

    Car car = carDao.retrieveByModel(String.valueOf(n));
    carDao.delete(car);
  }


  @Test
  void testCapacityNull() throws ServletException, IOException {
    Random random = new Random();
    int n = random.nextInt(1000);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("brand")).thenReturn("Peugeot");
    when(request.getParameter("model")).thenReturn(String.valueOf(n));
    when(request.getParameter("doors")).thenReturn("5");
    when(request.getParameter("car_type")).thenReturn("berlina");
    when(request.getParameter("transmission")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption")).thenReturn("12");
    when(request.getParameter("horse_power")).thenReturn("200");
    when(request.getParameter("emission_class")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions")).thenReturn("113");
    when(request.getParameter("power_supply")).thenReturn("Diesel");
    when(request.getParameter("capacity")).thenReturn(null);
    when(request.getParameter("price")).thenReturn("500");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());

    Car car = carDao.retrieveByModel(String.valueOf(n));
    carDao.delete(car);
  }


  @Test
  void testPriceNull() throws ServletException, IOException {
    Random random = new Random();
    int n = random.nextInt(1000);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("brand")).thenReturn("Peugeot");
    when(request.getParameter("model")).thenReturn(String.valueOf(n));
    when(request.getParameter("doors")).thenReturn("5");
    when(request.getParameter("car_type")).thenReturn("berlina");
    when(request.getParameter("transmission")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption")).thenReturn("12");
    when(request.getParameter("horse_power")).thenReturn("200");
    when(request.getParameter("emission_class")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions")).thenReturn("113");
    when(request.getParameter("power_supply")).thenReturn("Diesel");
    when(request.getParameter("capacity")).thenReturn("2000");
    when(request.getParameter("price")).thenReturn(null);
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());

    Car car = carDao.retrieveByModel(String.valueOf(n));
    carDao.delete(car);
  }


  @Test
  void testCheckCarFalse() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("brand")).thenReturn("Maserati");
    when(request.getParameter("model")).thenReturn("Ghibli");
    when(request.getParameter("doors")).thenReturn("5");
    when(request.getParameter("car_type")).thenReturn("Berlina");
    when(request.getParameter("transmission")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption")).thenReturn("5.9");
    when(request.getParameter("horse_power")).thenReturn("250");
    when(request.getParameter("emission_class")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions")).thenReturn("220");
    when(request.getParameter("power_supply")).thenReturn("Diesel");
    when(request.getParameter("capacity")).thenReturn("2987");
    when(request.getParameter("price")).thenReturn("789");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }


  @Test
  void testDoPostSuccess() throws ServletException, IOException {
    Random random = new Random();
    int n = random.nextInt(1000);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("brand")).thenReturn("Mercedes");
    when(request.getParameter("model")).thenReturn(String.valueOf(n));
    when(request.getParameter("doors")).thenReturn("5");
    when(request.getParameter("car_type")).thenReturn("berlina");
    when(request.getParameter("transmission")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption")).thenReturn("12");
    when(request.getParameter("horse_power")).thenReturn("200");
    when(request.getParameter("emission_class")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions")).thenReturn("113");
    when(request.getParameter("power_supply")).thenReturn("Diesel");
    when(request.getParameter("capacity")).thenReturn("2000");
    when(request.getParameter("price")).thenReturn("500");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());

    Car car = carDao.retrieveByModel(String.valueOf(n));
    carDao.delete(car);
  }

}
