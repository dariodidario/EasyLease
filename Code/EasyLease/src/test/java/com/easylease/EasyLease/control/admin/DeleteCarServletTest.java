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
    private RequestDispatcher dispatcher;
    private ServletContext context;
    private ServletConfig config;



    @BeforeEach
    public void setUp() throws IOException, ServletException {
        servlet = new DeleteCarServlet();
        config =mock(ServletConfig.class);
        servlet.init(config);
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
        context =mock(ServletContext.class);
        dispatcher= mock(RequestDispatcher.class);
        when(servlet.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }


    @Test
    void testRoleNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn(null);
        when(request.getParameter("ID_Delete")).thenReturn("CAAA111");

        servlet.doGet(request,response);
        verify(context).getRequestDispatcher("/fragments/error403.jsp");
    }

    @Test
    void testRoleNotAdmin() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("client");
        when(request.getParameter("ID_Delete")).thenReturn("CAAA111");

        servlet.doGet(request,response);
        verify(context).getRequestDispatcher("/fragments/error403.jsp");
    }


    @Test
    void testIDDeleteNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("ID_Delete")).thenReturn(null);
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        servlet.doGet(request, response);
        assertEquals("text/html;charset=UTF-8", response.getContentType());
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
