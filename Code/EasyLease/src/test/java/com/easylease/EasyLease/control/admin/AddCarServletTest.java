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
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class AddCarServletTest {
    private AddCarServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private CarDAO carDAO;
    private List<Car> cars;
    private RequestDispatcher dispatcher;
    private ServletContext context;
    private ServletConfig config;



    @BeforeEach
    public void setUp() throws IOException, ServletException {
        servlet = new AddCarServlet();
        config =mock(ServletConfig.class);
        servlet.init(config);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session= mock(HttpSession.class);
        carDAO = mock(CarDAO.class);
        cars =new ArrayList<>();
        cars.add(new Car("CAAA111", "Peugeot", "3008", 249, "SUV",
                true, 5, "Automatico", 3.9f,
                130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
                when(carDAO.retrieveAll()).thenReturn(cars);
        doAnswer(invocation -> {
            return null;
        }).when(carDAO).insert(any());
        when(request.getSession()).thenReturn(session);
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
    void testCarBrandNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn(null);
        when(request.getParameter("model")).thenReturn("Classe E");
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

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }

    @Test
    void testCarModelNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn("Mercedes");
        when(request.getParameter("model")).thenReturn(null);
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

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }

    @Test
    void testCarDoorsNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn("Mercedes");
        when(request.getParameter("model")).thenReturn("Classe E");
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

        assertThrows(NumberFormatException.class,()->{servlet.doPost(request,response);});
    }

    @Test
    void testCarTypeNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn("Mercedes");
        when(request.getParameter("model")).thenReturn("Classe E");
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

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }

    @Test
    void testCarTransmissionNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn("Mercedes");
        when(request.getParameter("model")).thenReturn("Classe E");
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

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }


    @Test
    void testCarAverageNull() {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn("Mercedes");
        when(request.getParameter("model")).thenReturn("Classe E");
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

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }

    @Test
    void testCarHorsePowerNull() {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn("Mercedes");
        when(request.getParameter("model")).thenReturn("Classe E");
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

        assertThrows(NumberFormatException.class,()->{servlet.doPost(request,response);});
    }

    @Test
    void testCarEmissionClassNull() {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn("Mercedes");
        when(request.getParameter("model")).thenReturn("Classe E");
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

        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }


    @Test
    void testCarCo2EmissionsNull() {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn("Mercedes");
        when(request.getParameter("model")).thenReturn("Classe E");
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

        assertThrows(NumberFormatException.class,()->{servlet.doPost(request,response);});
    }


    @Test
    void testCarPowerSupplyNull()  {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn("Mercedes");
        when(request.getParameter("model")).thenReturn("Classe E");
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


        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }


    @Test
    void testCarCapacityNull()  {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn("Mercedes");
        when(request.getParameter("model")).thenReturn("Classe E");
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


        assertThrows(NumberFormatException.class,()->{servlet.doPost(request,response);});
    }


    @Test
    void testCarPriceNull()  {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn("Mercedes");
        when(request.getParameter("model")).thenReturn("Classe E");
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


        assertThrows(NullPointerException.class,()->{servlet.doPost(request,response);});
    }



    @Test
    void testUploadImageFail() throws ServletException, IOException {
        servlet=mock(AddCarServlet.class);
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn("Mercedes");
        when(request.getParameter("model")).thenReturn("Classe E");
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
    }


    @Test
    void testDoPostSuccess() throws ServletException, IOException {
        servlet=mock(AddCarServlet.class);
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("brand")).thenReturn("Mercedes");
        when(request.getParameter("model")).thenReturn("Classe E");
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
    }

}
