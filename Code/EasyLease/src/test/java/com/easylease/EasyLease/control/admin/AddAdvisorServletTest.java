package com.easylease.EasyLease.control.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.advisor.AdvisorDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.servlet.http.HttpSession;


class AddAdvisorServletTest {
    private AddAdvisorServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private AdvisorDAO advisorDAO;
    private List<Advisor> advisors;
    private RequestDispatcher dispatcher;
    private ServletContext context;
    private ServletConfig config;




    @BeforeEach
    public void setUp() throws IOException, ServletException {
        servlet = new AddAdvisorServlet();
        config =mock(ServletConfig.class);
        servlet.init(config);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session= mock(HttpSession.class);
        advisorDAO= mock(AdvisorDAO.class);
        advisors=new ArrayList<>();
        advisors.add(new Advisor("ADJdybc", "Clementina", "Rossa", "rossa.clementina@frutta.com", Date.valueOf("2020-08-12")));
        when(advisorDAO.retrieveAll()).thenReturn(advisors);
        doAnswer(invocation -> {
            return null;
        }).when(advisorDAO).insert(any(),any() );
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
    void testAdvisorNameNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn(null);
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");

        assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
    }

    @Test
    void testAdvisorSurnameNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn(null);
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");

        assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
    }

    @Test
    void testAdvisorEmailNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn(null);
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");

        assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
    }

    @Test
    void testAdvisorDateInvalid() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021/01/211");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");

        assertThrows(IllegalArgumentException.class,()->{servlet.doGet(request,response);});
    }

    @Test
    void testAdvisorPasswordNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn(null);
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");

        assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
    }


    @Test
    void testEmailValidFalse() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("false");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");

        servlet.doGet(request,response);
        verify(context).getRequestDispatcher("/admin/addAdvisorJSP.jsp");
    }

    @Test
    void testDateValidFalse() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("false");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");

        servlet.doGet(request,response);
        verify(context).getRequestDispatcher("/admin/addAdvisorJSP.jsp");
    }

    @Test
    void testPasswordValidFalse() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("false");
        when(request.getParameter("confirm_valid")).thenReturn("true");

        servlet.doGet(request,response);
        verify(context).getRequestDispatcher("/admin/addAdvisorJSP.jsp");
    }


    @Test
    void testConfirmValidFalse() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("false");

        servlet.doGet(request,response);
        verify(context).getRequestDispatcher("/admin/addAdvisorJSP.jsp");
    }


    @Test
    void testCheckAdvisorFalse() throws ServletException, IOException {
        servlet=mock(AddAdvisorServlet.class);
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");

        servlet.doGet(request, response);
        assertEquals("admin",request.getSession().getAttribute("role"));
    }




    @Test
    void testdoGetSuccess() throws ServletException, IOException {
        servlet=mock(AddAdvisorServlet.class);
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        servlet.doGet(request, response);
        assertEquals("text/html;charset=UTF-8", response.getContentType());
    }
}
