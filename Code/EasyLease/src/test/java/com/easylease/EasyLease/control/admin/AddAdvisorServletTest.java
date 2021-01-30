package com.easylease.EasyLease.control.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.easylease.EasyLease.model.DBPool.DbConnection;
import com.easylease.EasyLease.model.advisor.Advisor;
import com.easylease.EasyLease.model.advisor.AdvisorDao;
import com.easylease.EasyLease.model.advisor.DbAdvisorDao;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import javax.servlet.http.HttpSession;


class AddAdvisorServletTest {
    private AddAdvisorServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private AdvisorDao advisorDAO;
    private static DbConnection dbConnection;
    private RequestDispatcher dispatcher;
    private ServletContext context;
    private ServletConfig config;
    private StringWriter response_writer;



    @BeforeEach
    public void setUp() throws IOException, ServletException, SQLException {
        MockitoAnnotations.openMocks(this);
        servlet = new AddAdvisorServlet();
        config =mock(ServletConfig.class);
        servlet.init(config);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        response_writer = new StringWriter();
        session= mock(HttpSession.class);
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://127.0.0.1:3306/easylease");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("root");
        mysqlDataSource.setServerTimezone("UTC");
        mysqlDataSource.setVerifyServerCertificate(false);
        mysqlDataSource.setUseSSL(false);
        dbConnection = DbConnection.getInstance();
        dbConnection.setDataSource(mysqlDataSource);

        advisorDAO= DbAdvisorDao.getInstance();
        when(request.getSession()).thenReturn(session);
        context =mock(ServletContext.class);
        dispatcher= mock(RequestDispatcher.class);
        when(servlet.getServletContext()).thenReturn(context);
        when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        when(response.getWriter()).thenReturn(new PrintWriter(response_writer));
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
    void testAdvisorDateInvalid() {
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
        verify(context).getRequestDispatcher("/admin/addAdvisor.jsp");
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
        verify(context).getRequestDispatcher("/admin/addAdvisor.jsp");
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
        verify(context).getRequestDispatcher("/admin/addAdvisor.jsp");
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
        verify(context).getRequestDispatcher("/admin/addAdvisor.jsp");
    }



    @Test
    void testCheckAdvisorFalse() throws ServletException, IOException {

        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Clementina");
        when(request.getParameter("advisor_surname")).thenReturn("Rossa");
        when(request.getParameter("advisor_email")).thenReturn("rossa.clementina@frutta.com");
        when(request.getParameter("advisor_date")).thenReturn("2020-08-12");
        when(request.getParameter("advisor_password")).thenReturn("pass");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");

        servlet.doGet(request, response);
        verify(context).getRequestDispatcher("/admin/addAdvisor.jsp");
    }




    @Test
    void testdoGetSuccess() throws ServletException, IOException {
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

        Advisor advisor=advisorDAO.retrieveByEmail("M.iodice21@studenti.unisa.it");
        advisorDAO.delete(advisor);
    }

}
