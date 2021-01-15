package com.easylease.EasyLease.control.admin;

import com.easylease.EasyLease.model.admin.Admin;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class ViewUpdateCarServletTest {
    private ViewUpdateCarServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private ServletContext context;
    private ServletConfig config;
    private CarDAO carDAO;

    @BeforeEach
    public void setUp() throws IOException, ServletException {

        servlet = new ViewUpdateCarServlet();
        config =mock(ServletConfig.class);
        servlet.init(config);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session= mock(HttpSession.class);
        context =mock(ServletContext.class);
        dispatcher= mock(RequestDispatcher.class);
        carDAO=mock(CarDAO.class);
        when(request.getSession()).thenReturn(session);
        when(servlet.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);

    }

    @Test
    void RoleNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn(null);

        servlet.doGet(request,response);
        verify(context).getRequestDispatcher("/fragments/error403.jsp");
    }

    @Test
    void UserNotAdmin() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("client");

        servlet.doGet(request,response);
        verify(context).getRequestDispatcher("/fragments/error403.jsp");
    }

    @Test
    void Car_idNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("Car_id")).thenReturn(null);

        servlet.doGet(request,response);
        verify(context).getRequestDispatcher("/fragments/error403.jsp");
    }

    @Test
    void EmptyCar_id() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("Car_id")).thenReturn("");

        servlet.doGet(request,response);
        verify(context).getRequestDispatcher("/fragments/error403.jsp");
    }

    @Test
    void doGet() throws ServletException, IOException {
        servlet=mock(ViewUpdateCarServlet.class);
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("Car_id")).thenReturn("CA0EUZR");
        when(carDAO.retrieveById(any())).thenReturn(new Car("ca11111", "Peugeot", "3008", 249, "SUV",
                true, 5, "Automatico", 3.9f,
                130, "Euro 6", 104, "Diesel", 1499, "peugeot_3008.jpg"));
        Admin admin=new Admin("1234567", "Antonio", "Sarro",
                "test@gmail.com", "recovery@gmail.com");
        when(request.getSession().getAttribute("user")).thenReturn(admin);

        servlet.doGet(request,response);
        verify(servlet).doGet(request,response);
        assertAll(
                ()->assertEquals("admin",request.getSession().getAttribute("role")),
                ()->assertEquals(admin,request.getSession().getAttribute("user")));
    }
}