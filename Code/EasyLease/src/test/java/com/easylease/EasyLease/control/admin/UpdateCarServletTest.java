package com.easylease.EasyLease.control.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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


class UpdateCarServletTest {
  private UpdateCarServlet servlet;
  private HttpServletRequest request;
  private HttpServletResponse response;
  private HttpSession session;
  private CarDao carDao;
  private Car car;
  private StringWriter responseWriter;
  private RequestDispatcher dispatcher;
  private ServletContext context;
  private ServletConfig config;
  private static DbConnection dbConnection;
  private Part part;


  @BeforeEach
  public void setUp() throws IOException, ServletException, SQLException {
    MockitoAnnotations.openMocks(this);
    servlet = new UpdateCarServlet();
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
    when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
    context = mock(ServletContext.class);
    dispatcher = mock(RequestDispatcher.class);
    when(servlet.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
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
    when(request.getPart(any())).thenReturn(part);
    when(request.getServletContext()).thenReturn(context);
    when(context.getRealPath("img")).thenReturn(
        "C:\\Users\\39392\\Desktop\\EasyLease\\src\\main\\webapp\\img");
  }


  @Test
  void testRoleNull() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn(null);

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/user/login.jsp");
  }

  @Test
  void testRoleNotAdmin() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("client");

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/user/login.jsp");
  }

  @Test
  void testIdUpdateNull() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn(null);
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }


  @Test
  void testIdUpdateEmpty() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }


  @Test
  void testCarBrandNull() {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CAAA111");
    when(request.getParameter("brand_Update")).thenReturn(null);
    when(request.getParameter("model_Update")).thenReturn("Classe E");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("berlina");
    when(request.getParameter("transmission_Update")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption_Update")).thenReturn("12");
    when(request.getParameter("horse_power_Update")).thenReturn("200");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("113");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("2000");
    when(request.getParameter("price_Update")).thenReturn("500");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");

    assertThrows(NullPointerException.class, () -> {
      servlet.doPost(request, response);
    });
  }

  @Test
  void testBrandEmpty() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testCarModelNull() {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CAAA111");
    when(request.getParameter("brand_Update")).thenReturn("Mercedes");
    when(request.getParameter("model_Update")).thenReturn(null);
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("berlina");
    when(request.getParameter("transmission_Update")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption_Update")).thenReturn("12");
    when(request.getParameter("horse_power_Update")).thenReturn("200");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("113");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("2000");
    when(request.getParameter("price_Update")).thenReturn("500");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");

    assertThrows(NullPointerException.class, () -> {
      servlet.doPost(request, response);
    });
  }


  @Test
  void testModelEmpty() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testCarDoorsNull() {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CAAA111");
    when(request.getParameter("brand_Update")).thenReturn("Mercedes");
    when(request.getParameter("model_Update")).thenReturn("Classe E");
    when(request.getParameter("doors_Update")).thenReturn(null);
    when(request.getParameter("car_type_Update")).thenReturn("berlina");
    when(request.getParameter("transmission_Update")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption_Update")).thenReturn("12");
    when(request.getParameter("horse_power_Update")).thenReturn("200");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("113");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("2000");
    when(request.getParameter("price_Update")).thenReturn("500");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");

    assertThrows(NullPointerException.class, () -> {
      servlet.doPost(request, response);
    });
  }


  @Test
  void testDoorsEmpty() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }

  @Test
  void testCarTypeNull() {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CAAA111");
    when(request.getParameter("brand_Update")).thenReturn("Mercedes");
    when(request.getParameter("model_Update")).thenReturn("Classe E");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn(null);
    when(request.getParameter("transmission_Update")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption_Update")).thenReturn("12");
    when(request.getParameter("horse_power_Update")).thenReturn("200");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("113");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("2000");
    when(request.getParameter("price_Update")).thenReturn("500");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");

    assertThrows(NullPointerException.class, () -> {
      servlet.doPost(request, response);
    });
  }


  @Test
  void testCarTypeEmpty() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testCarTransmissionNull() {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CAAA111");
    when(request.getParameter("brand_Update")).thenReturn("Mercedes");
    when(request.getParameter("model_Update")).thenReturn("Classe E");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("berlina");
    when(request.getParameter("transmission_Update")).thenReturn(null);
    when(request.getParameter("avg_consumption_Update")).thenReturn("12");
    when(request.getParameter("horse_power_Update")).thenReturn("200");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("113");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("2000");
    when(request.getParameter("price_Update")).thenReturn("500");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");

    assertThrows(NullPointerException.class, () -> {
      servlet.doPost(request, response);
    });
  }


  @Test
  void testTransmissionEmpty() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testCarAverageNull() {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CAAA111");
    when(request.getParameter("brand_Update")).thenReturn("Mercedes");
    when(request.getParameter("model_Update")).thenReturn("Classe E");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("berlina");
    when(request.getParameter("transmission_Update")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption_Update")).thenReturn(null);
    when(request.getParameter("horse_power_Update")).thenReturn("200");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("113");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("2000");
    when(request.getParameter("price_Update")).thenReturn("500");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");

    assertThrows(NullPointerException.class, () -> {
      servlet.doPost(request, response);
    });
  }


  @Test
  void testAvgEmpty() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testCarHorsePowerNull() {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CAAA111");
    when(request.getParameter("brand_Update")).thenReturn("Mercedes");
    when(request.getParameter("model_Update")).thenReturn("Classe E");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("berlina");
    when(request.getParameter("transmission_Update")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption_Update")).thenReturn("12");
    when(request.getParameter("horse_power_Update")).thenReturn(null);
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("113");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("2000");
    when(request.getParameter("price_Update")).thenReturn("500");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");

    assertThrows(NullPointerException.class, () -> {
      servlet.doPost(request, response);
    });
  }


  @Test
  void testHorsePowerEmpty() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testCarEmissionClassNull() {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CAAA111");
    when(request.getParameter("brand_Update")).thenReturn("Mercedes");
    when(request.getParameter("model_Update")).thenReturn("Classe E");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("berlina");
    when(request.getParameter("transmission_Update")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption_Update")).thenReturn("12");
    when(request.getParameter("horse_power_Update")).thenReturn("200");
    when(request.getParameter("emission_class_Update")).thenReturn(null);
    when(request.getParameter("co2_emissions_Update")).thenReturn("113");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("2000");
    when(request.getParameter("price_Update")).thenReturn("500");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");

    assertThrows(NullPointerException.class, () -> {
      servlet.doPost(request, response);
    });
  }


  @Test
  void testEmissionClassEmpty() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testCarCo2EmissionsNull() {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CAAA111");
    when(request.getParameter("brand_Update")).thenReturn("Mercedes");
    when(request.getParameter("model_Update")).thenReturn("Classe E");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("berlina");
    when(request.getParameter("transmission_Update")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption_Update")).thenReturn("12");
    when(request.getParameter("horse_power_Update")).thenReturn("200");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn(null);
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("2000");
    when(request.getParameter("price_Update")).thenReturn("500");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");

    assertThrows(NullPointerException.class, () -> {
      servlet.doPost(request, response);
    });
  }


  @Test
  void testCo2EmissionsEmpty() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testCarPowerSupplyNull() {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CAAA111");
    when(request.getParameter("brand_Update")).thenReturn("Mercedes");
    when(request.getParameter("model_Update")).thenReturn("Classe E");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("berlina");
    when(request.getParameter("transmission_Update")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption_Update")).thenReturn("12");
    when(request.getParameter("horse_power_Update")).thenReturn("200");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("113");
    when(request.getParameter("power_supply_Update")).thenReturn(null);
    when(request.getParameter("capacity_Update")).thenReturn("2000");
    when(request.getParameter("price_Update")).thenReturn("500");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");

    assertThrows(NullPointerException.class, () -> {
      servlet.doPost(request, response);
    });
  }


  @Test
  void testPowerSupplyEmpty() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testCarCapacityNull() {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CAAA111");
    when(request.getParameter("brand_Update")).thenReturn("Mercedes");
    when(request.getParameter("model_Update")).thenReturn("Classe E");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("berlina");
    when(request.getParameter("transmission_Update")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption_Update")).thenReturn("12");
    when(request.getParameter("horse_power_Update")).thenReturn("200");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("113");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn(null);
    when(request.getParameter("price_Update")).thenReturn("500");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");

    assertThrows(NullPointerException.class, () -> {
      servlet.doPost(request, response);
    });
  }


  @Test
  void testCapacityEmpty() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testCarPriceNull() {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CAAA111");
    when(request.getParameter("brand_Update")).thenReturn("Mercedes");
    when(request.getParameter("model_Update")).thenReturn("Classe E");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("berlina");
    when(request.getParameter("transmission_Update")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption_Update")).thenReturn("12");
    when(request.getParameter("horse_power_Update")).thenReturn("200");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("113");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("2000");
    when(request.getParameter("price_Update")).thenReturn(null);
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");

    assertThrows(NullPointerException.class, () -> {
      servlet.doPost(request, response);
    });
  }


  @Test
  void testPriceEmpty() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testCarImageNull() {
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CAAA111");
    when(request.getParameter("brand_Update")).thenReturn("Mercedes");
    when(request.getParameter("model_Update")).thenReturn("Classe E");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("berlina");
    when(request.getParameter("transmission_Update")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption_Update")).thenReturn("12");
    when(request.getParameter("horse_power_Update")).thenReturn("200");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("113");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("2000");
    when(request.getParameter("price_Update")).thenReturn("500");
    when(request.getParameter("img_car_Update")).thenReturn(null);

    assertThrows(NullPointerException.class, () -> {
      servlet.doPost(request, response);
    });
  }


  @Test
  void testImageEmpty() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testBrandNotEqual() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("tigre");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    Car car1 = carDao.retrieveById("CA6fSIJ");
    assertNotEquals(car1.getBrand(), car.getBrand());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testModelNotEqual() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("tigre");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    Car car1 = carDao.retrieveById("CA6fSIJ");
    assertNotEquals(car1.getModel(), car.getModel());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }

  @Test
  void testDoorsNotEqual() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("15");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    Car car1 = carDao.retrieveById("CA6fSIJ");
    assertNotEquals(car1.getDoors(), car.getDoors());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testTypeNotEqual() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("tigre");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    Car car1 = carDao.retrieveById("CA6fSIJ");
    assertNotEquals(car1.getType(), car.getType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }

  @Test
  void testTransmissionNotEqual() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("tigre");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    Car car1 = carDao.retrieveById("CA6fSIJ");
    assertNotEquals(car1.getTransmission(), car.getTransmission());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }

  @Test
  void testAvgNotEqual() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("333.660");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    Car car1 = carDao.retrieveById("CA6fSIJ");
    assertNotEquals(car1.getAvgConsumption(), car.getAvgConsumption());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testHorseNotEqual() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("1000");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    Car car1 = carDao.retrieveById("CA6fSIJ");
    assertNotEquals(car1.getHorsePower(), car.getHorsePower());
    car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testEmissionClassNotEqual() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("tigre");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    Car car1 = carDao.retrieveById("CA6fSIJ");
    assertNotEquals(car1.getEmissionClass(), car.getEmissionClass());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testCo2NotEqual() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("196");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    Car car1 = carDao.retrieveById("CA6fSIJ");
    assertNotEquals(car1.getCo2Emissions(), car.getCo2Emissions());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testPowerNotEqual() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Acqua");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    Car car1 = carDao.retrieveById("CA6fSIJ");
    assertNotEquals(car1.getPowerSupply(), car.getPowerSupply());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testCapacityNotEqual() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("2599");
    when(request.getParameter("price_Update")).thenReturn("260");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    Car car1 = carDao.retrieveById("CA6fSIJ");
    assertNotEquals(car1.getCapacity(), car.getCapacity());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testPriceNotEqual() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("SUV");
    when(request.getParameter("transmission_Update")).thenReturn("Manuale");
    when(request.getParameter("avg_consumption_Update")).thenReturn("3.6");
    when(request.getParameter("horse_power_Update")).thenReturn("100");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("96");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("1499");
    when(request.getParameter("price_Update")).thenReturn("380");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    Car car1 = carDao.retrieveById("CA6fSIJ");
    assertNotEquals(car1.getPrice(), car.getPrice());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }


  @Test
  void testDoPostSuccess() throws ServletException, IOException {

    when(request.getPart("img_car_Update")).thenReturn(null);
    when(request.getSession().getAttribute("role")).thenReturn("admin");
    when(request.getParameter("ID_Update")).thenReturn("CA6fSIJ");
    when(request.getParameter("brand_Update")).thenReturn("Peugeot");
    when(request.getParameter("model_Update")).thenReturn("2008");
    when(request.getParameter("doors_Update")).thenReturn("5");
    when(request.getParameter("car_type_Update")).thenReturn("berlina");
    when(request.getParameter("transmission_Update")).thenReturn("Automatico");
    when(request.getParameter("avg_consumption_Update")).thenReturn("12");
    when(request.getParameter("horse_power_Update")).thenReturn("200");
    when(request.getParameter("emission_class_Update")).thenReturn("Euro 6");
    when(request.getParameter("co2_emissions_Update")).thenReturn("113");
    when(request.getParameter("power_supply_Update")).thenReturn("Diesel");
    when(request.getParameter("capacity_Update")).thenReturn("2000");
    when(request.getParameter("price_Update")).thenReturn("500");
    when(request.getParameter("img_car_Update")).thenReturn("peugeot_2008.jpg");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

    servlet.doPost(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
    Car car = carDao.retrieveById("CA6fSIJ");
    carDao.update(car);
  }

}
