package control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.easylease.EasyLease.control.user.LoginServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LoginServletTest {
  public LoginServlet servlet;
  public HttpServletResponse response;
  public HttpServletRequest request;


  @BeforeEach
  protected void setUp() {
    servlet =  new LoginServlet();
    response = mock(HttpServletResponse.class);
    request = mock(HttpServletRequest.class);
  }

  @Test
  public void loginDoGetTestFailMailNull() throws ServletException, IOException {
    servlet =  mock(LoginServlet.class);
    when(request.getParameter("email")).thenReturn(null);
    when(request.getParameter("password")).thenReturn("password");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");
    servlet.doGet(request, response);
    assertThrows(NullPointerException.class, () -> servlet.doGet(request, response));
  }

  @Test
  public void loginDoGetTestSuccess() throws ServletException, IOException {
    servlet =  mock(LoginServlet.class);
    when(request.getParameter("email")).thenReturn("francesco.torino1999@gmail.com");
    when(request.getParameter("password")).thenReturn("password");
    when(response.getContentType()).thenReturn("text/html;charset=UTF-8");
    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF-8", response.getContentType());
  }

  @AfterEach
  public void tearDown() {
    response = null;
    request = null;
  }

  /*
  package com.easylease.EasyLease.control.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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



    @BeforeEach
    public void setUp() throws IOException {
        servlet = new AddAdvisorServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session= mock(HttpSession.class);
        advisorDAO= mock(AdvisorDAO.class);
        advisors=new ArrayList<>();
        advisors.add(new Advisor("ADJdybc", "Clementina", "Rossa", "rossa.clementina@frutta.com", "pass", Date.valueOf("2020-08-12")));
        when(advisorDAO.retrieveAll()).thenReturn(advisors);
        doAnswer(invocation -> {
            return null;
        }).when(advisorDAO).insert(any());
        when(request.getSession()).thenReturn(session);

    }


    @Test
    void testFailRoleNull() {

        //when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("false");
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        assertThrows(IllegalStateException.class,()->{servlet.doGet(request,response);});
    }

    @Test
    void testFailRoleFalse() {

        when(request.getSession().getAttribute("role")).thenReturn("false");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("false");
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        assertThrows(IllegalStateException.class,()->{servlet.doGet(request,response);});
    }


    @Test
    void testFailAdvisorNameNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        //when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
    }

    @Test
    void testFailAdvisorSurnameNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        //when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
    }

    @Test
    void testFailAdvisorEmailNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        //when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
    }

    @Test
    void testFailAdvisorDateInvalid() throws ServletException, IOException {
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
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        assertThrows(IllegalArgumentException.class,()->{servlet.doGet(request,response);});
    }

    @Test
    void testFailAdvisorPasswordNull() throws ServletException, IOException {
        when(request.getSession().getAttribute("role")).thenReturn("admin");
        when(request.getParameter("advisor_name")).thenReturn("Michele");
        when(request.getParameter("advisor_surname")).thenReturn("Iodice");
        when(request.getParameter("advisor_email")).thenReturn("M.iodice21@studenti.unisa.it");
        when(request.getParameter("advisor_date")).thenReturn("2021-01-21");
        //when(request.getParameter("advisor_password")).thenReturn("Michele123");
        when(request.getParameter("email_valid")).thenReturn("true");
        when(request.getParameter("date_valid")).thenReturn("true");
        when(request.getParameter("password_valid")).thenReturn("true");
        when(request.getParameter("confirm_valid")).thenReturn("true");
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
    }


    @Test
    void testFailEmailValidFalse() {

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
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        assertThrows(IllegalStateException.class,()->{servlet.doGet(request,response);});
    }

    @Test
    void testFailDateValidFalse() {

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
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        assertThrows(IllegalStateException.class,()->{servlet.doGet(request,response);});
    }

    @Test
    void testFailPasswordValidFalse() {

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
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        assertThrows(IllegalStateException.class,()->{servlet.doGet(request,response);});
    }


    @Test
    void testFailConfirmValidFalse() {

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
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        assertThrows(IllegalStateException.class,()->{servlet.doGet(request,response);});
    }





    @Test
    void doGetSuccess() throws ServletException, IOException {
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
   */
}