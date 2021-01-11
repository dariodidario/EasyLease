package com.easylease.EasyLease.control.admin;

import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.advisor.AdvisorDAO;
import com.easylease.EasyLease.model.car.Car;
import com.easylease.EasyLease.model.car.CarDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class DeleteCarServletTest {

    private DeleteCarServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private CarDAO carDAO;
    private List<Car> cars;
    private StringWriter response_writer;



    @BeforeEach
    public void setUp() throws IOException {
        servlet = new DeleteCarServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        response_writer = new StringWriter();
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
        when(response.getWriter()).thenReturn(new PrintWriter(response_writer));

    }


    @Test
    void testRoleNull() {

        when(request.getSession().getAttribute("role")).thenReturn(null);
        when(request.getParameter("ID_Delete")).thenReturn("CAAA111");


        assertThrows(IllegalStateException.class,()->{servlet.doGet(request,response);});
    }

    @Test
    void testRoleNotAdmin() {

        when(request.getSession().getAttribute("role")).thenReturn("client");
        when(request.getParameter("ID_Delete")).thenReturn("CAAA111");

        assertThrows(IllegalStateException.class,()->{servlet.doGet(request,response);});
    }


    @Test
    void testIDDeleteNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("ID_Delete")).thenReturn(null);

        assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
    }


    @Test
    void testIDDeleteEmpty() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("ID_Delete")).thenReturn("");

        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        servlet.doGet(request, response);
        assertEquals("text/html;charset=UTF-8", response.getContentType());
    }



    @Test
    void testDoGetSuccess() throws ServletException, IOException {
        servlet=mock(DeleteCarServlet.class);
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("ID_Delete")).thenReturn("");

        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        servlet.doGet(request, response);
        assertEquals("text/html;charset=UTF-8", response.getContentType());
    }
}