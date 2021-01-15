package com.easylease.EasyLease.control.admin;

import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.CarDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class UpdateCarServletTest {
    private UpdateCarServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private CarDAO carDAO;
    private Car car;
    private StringWriter response_writer;
    private RequestDispatcher dispatcher;
    private ServletContext context;
    private ServletConfig config;



    @BeforeEach
    public void setUp() throws IOException, ServletException {
        servlet = new UpdateCarServlet();
        config =mock(ServletConfig.class);
        servlet.init(config);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        response_writer = new StringWriter();
        session= mock(HttpSession.class);
        carDAO = mock(CarDAO.class);
        car =new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
                true, 5, "Automatico", 3.9f,
                130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg");
        when(carDAO.retrieveById("CAAA111")).thenReturn(car);
        doAnswer(invocation -> {
            return null;
        }).when(carDAO).insert(any());
        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(new PrintWriter(response_writer));
        context =mock(ServletContext.class);
        dispatcher= mock(RequestDispatcher.class);
        when(servlet.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }


    @Test
    void testRoleNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn(null);

        servlet.doGet(request,response);
        verify(context).getRequestDispatcher("/fragments/error403.jsp");
    }

    @Test
    void testRoleNotAdmin() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("client");

        servlet.doGet(request,response);
        verify(context).getRequestDispatcher("/fragments/error403.jsp");
    }

    @Test
    void testIDUpdateNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("ID_Update")).thenReturn(null);
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        servlet.doPost(request, response);
        assertEquals("text/html;charset=UTF-8", response.getContentType());
    }


    @Test
    void testIDUpdateEmpty() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("ID_Update")).thenReturn("");
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        servlet.doPost(request, response);
        assertEquals("text/html;charset=UTF-8", response.getContentType());
    }


    @Test
    void testCarBrandNull() throws ServletException, IOException {
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
        when(request.getParameter("img_car_Update")).thenReturn("peugeot_3008.jpg");

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }

    @Test
    void testCarModelNull() throws ServletException, IOException {
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
        when(request.getParameter("img_car_Update")).thenReturn("peugeot_3008.jpg");

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }

    @Test
    void testCarDoorsNull() throws ServletException, IOException {
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
        when(request.getParameter("img_car_Update")).thenReturn("peugeot_3008.jpg");

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }

    @Test
    void testCarTypeNull() throws ServletException, IOException {
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
        when(request.getParameter("img_car_Update")).thenReturn("peugeot_3008.jpg");

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }

    @Test
    void testCarTransmissionNull() throws ServletException, IOException {
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
        when(request.getParameter("img_car_Update")).thenReturn("peugeot_3008.jpg");

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
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
        when(request.getParameter("img_car_Update")).thenReturn("peugeot_3008.jpg");

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
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
        when(request.getParameter("img_car_Update")).thenReturn("peugeot_3008.jpg");

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
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
        when(request.getParameter("img_car_Update")).thenReturn("peugeot_3008.jpg");

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
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
        when(request.getParameter("img_car_Update")).thenReturn("peugeot_3008.jpg");

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }


    @Test
    void testCarPowerSupplyNull()  {
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
        when(request.getParameter("img_car_Update")).thenReturn("peugeot_3008.jpg");


        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }


    @Test
    void testCarCapacityNull()  {
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
        when(request.getParameter("img_car_Update")).thenReturn("peugeot_3008.jpg");


        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }


    @Test
    void testCarPriceNull()  {
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
        when(request.getParameter("img_car_Update")).thenReturn("peugeot_3008.jpg");


        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }

    @Test
    void testCarImageNull()  {
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


        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }



    @Test
    void testDoPostSuccess() throws ServletException, IOException {
        servlet=mock(UpdateCarServlet.class);
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
        when(request.getParameter("img_car_Update")).thenReturn("peugeot_3008.jpg");
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        servlet.doPost(request, response);
        assertEquals("text/html;charset=UTF-8", response.getContentType());
    }

}
