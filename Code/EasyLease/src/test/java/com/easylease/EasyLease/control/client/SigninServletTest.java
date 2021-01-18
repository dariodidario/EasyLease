package com.easylease.EasyLease.control.client;


import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.ClientDAO;
import com.easylease.EasyLease.model.client.DBClientDAO;
import com.easylease.EasyLease.model.estimate.DBEstimateDAO;
import com.easylease.EasyLease.model.estimate.Estimate;
import com.easylease.EasyLease.model.estimate.EstimateDAO;
import com.easylease.EasyLease.model.order.DBOrderDAO;
import com.easylease.EasyLease.model.order.Order;
import com.easylease.EasyLease.model.order.OrderDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SigninServletTest {
  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private ServletContext context;
  @Mock
  private HttpSession session;
  @Mock
  private RequestDispatcher dispatcher;

  private ClientDAO clientDao;
  private SignInServlet servlet;
  private final Map<String, Object> attributes = new HashMap<>();

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    servlet = new SignInServlet();
    clientDao = DBClientDAO.getInstance();
    when(request.getServletContext()).thenReturn(context);
    when(request.getSession()).thenReturn(session);
    when(context.getContextPath()).thenReturn("");
    when(session.isNew()).thenReturn(true);
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

    Mockito.doAnswer((Answer<Object>) invocation -> {
      String key = (String) invocation.getArguments()[0];
      return attributes.get(key);
    }).when(session).getAttribute(anyString());

    Mockito.doAnswer((Answer<Object>) invocation -> {
      String key = (String) invocation.getArguments()[0];
      Object value = invocation.getArguments()[1];
      attributes.put(key,value);
      return null;
    }).when(session).setAttribute(anyString(), any());
  }

  @AfterEach
  void tearDown() {

  }

  @Test
  void signIn_Success() throws ServletException, IOException {
    String email = "Francesco.torino1999@gmail.com";
    when(request.getParameter("name")).thenReturn("Francesco");
    when(request.getParameter("surname")).thenReturn("Torino");
    when(request.getParameter("email")).thenReturn("Francesco.torino1999@gmail.com");
    when(request.getParameter("birthplace")).thenReturn("Caserta");
    when(request.getParameter("birthdate")).thenReturn(("1999-09-18"));
    when(request.getParameter("kind")).thenReturn("Uomo");
    when(request.getParameter("city")).thenReturn("Caserta");
    when(request.getParameter("pc")).thenReturn("81100");
    when(request.getParameter("street")).thenReturn("Isonzo");
    when(request.getParameter("password")).thenReturn("pass");
    servlet.doPost(request, response);
    verify(request).getRequestDispatcher("/user/loginJSP.jsp");
    Client client = clientDao.retrieveByEmail("Francesco.torino1999@gmail.com");
    assertEquals(email, client.getEmail());
    clientDao.delete(client);
  }

  @Test
  void name_null() throws ServletException, IOException {
    String email = "Francesco.torino1999@gmail.com";
    when(request.getParameter("name")).thenReturn(null);
    when(request.getParameter("surname")).thenReturn("Torino");
    when(request.getParameter("email")).thenReturn("Francesco.torino1999@gmail.com");
    when(request.getParameter("birthplace")).thenReturn("Caserta");
    when(request.getParameter("birthdate")).thenReturn(("1999-09-18"));
    when(request.getParameter("kind")).thenReturn("Uomo");
    when(request.getParameter("city")).thenReturn("Caserta");
    when(request.getParameter("pc")).thenReturn("81100");
    when(request.getParameter("street")).thenReturn("Isonzo");
    when(request.getParameter("password")).thenReturn("pass");

    assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
  }

  void surname_null() throws ServletException, IOException {
    String email = "Francesco.torino1999@gmail.com";
    when(request.getParameter("name")).thenReturn("Francesco");
    when(request.getParameter("surname")).thenReturn(null);
    when(request.getParameter("email")).thenReturn("Francesco.torino1999@gmail.com");
    when(request.getParameter("birthplace")).thenReturn("Caserta");
    when(request.getParameter("birthdate")).thenReturn(("1999-09-18"));
    when(request.getParameter("kind")).thenReturn("Uomo");
    when(request.getParameter("city")).thenReturn("Caserta");
    when(request.getParameter("pc")).thenReturn("81100");
    when(request.getParameter("street")).thenReturn("Isonzo");
    when(request.getParameter("password")).thenReturn("pass");

    assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});

  }

  void email_null() throws ServletException, IOException {
    String email = "Francesco.torino1999@gmail.com";
    when(request.getParameter("name")).thenReturn("Francesco");
    when(request.getParameter("surname")).thenReturn("Torino");
    when(request.getParameter("email")).thenReturn(null);
    when(request.getParameter("birthplace")).thenReturn("Caserta");
    when(request.getParameter("birthdate")).thenReturn(("1999-09-18"));
    when(request.getParameter("kind")).thenReturn("Uomo");
    when(request.getParameter("city")).thenReturn("Caserta");
    when(request.getParameter("pc")).thenReturn("81100");
    when(request.getParameter("street")).thenReturn("Isonzo");
    when(request.getParameter("password")).thenReturn("pass");

    assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});

  }

  void birthplace_null() throws ServletException, IOException {
    String email = "Francesco.torino1999@gmail.com";
    when(request.getParameter("name")).thenReturn("Francesco");
    when(request.getParameter("surname")).thenReturn("Torino");
    when(request.getParameter("email")).thenReturn("Francesco.torino1999@gmail.com");
    when(request.getParameter("birthplace")).thenReturn(null);
    when(request.getParameter("birthdate")).thenReturn(("1999-09-18"));
    when(request.getParameter("kind")).thenReturn("Uomo");
    when(request.getParameter("city")).thenReturn("Caserta");
    when(request.getParameter("pc")).thenReturn("81100");
    when(request.getParameter("street")).thenReturn("Isonzo");
    when(request.getParameter("password")).thenReturn("pass");

    assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
  }

  void birthdate_null() throws ServletException, IOException {
    String email = "Francesco.torino1999@gmail.com";
    when(request.getParameter("name")).thenReturn("Francesco");
    when(request.getParameter("surname")).thenReturn("Torino");
    when(request.getParameter("email")).thenReturn("Francesco.torino1999@gmail.com");
    when(request.getParameter("birthplace")).thenReturn("Caserta");
    when(request.getParameter("birthdate")).thenReturn(null);
    when(request.getParameter("kind")).thenReturn("Uomo");
    when(request.getParameter("city")).thenReturn("Caserta");
    when(request.getParameter("pc")).thenReturn("81100");
    when(request.getParameter("street")).thenReturn("Isonzo");
    when(request.getParameter("password")).thenReturn("pass");

    assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});

  }

  void kind_null() throws ServletException, IOException {
    String email = "Francesco.torino1999@gmail.com";
    when(request.getParameter("name")).thenReturn("Francesco");
    when(request.getParameter("surname")).thenReturn("Torino");
    when(request.getParameter("email")).thenReturn("Francesco.torino1999@gmail.com");
    when(request.getParameter("birthplace")).thenReturn("Caserta");
    when(request.getParameter("birthdate")).thenReturn(("1999-09-18"));
    when(request.getParameter("kind")).thenReturn(null);
    when(request.getParameter("city")).thenReturn("Caserta");
    when(request.getParameter("pc")).thenReturn("81100");
    when(request.getParameter("street")).thenReturn("Isonzo");
    when(request.getParameter("password")).thenReturn("pass");

    assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
  }

  void city_null() throws ServletException, IOException {
    String email = "Francesco.torino1999@gmail.com";
    when(request.getParameter("name")).thenReturn("Francesco");
    when(request.getParameter("surname")).thenReturn("Torino");
    when(request.getParameter("email")).thenReturn("Francesco.torino1999@gmail.com");
    when(request.getParameter("birthplace")).thenReturn("Caserta");
    when(request.getParameter("birthdate")).thenReturn(("1999-09-18"));
    when(request.getParameter("kind")).thenReturn("Uomo");
    when(request.getParameter("city")).thenReturn(null);
    when(request.getParameter("pc")).thenReturn("81100");
    when(request.getParameter("street")).thenReturn("Isonzo");
    when(request.getParameter("password")).thenReturn("pass");

    assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
  }

  void pc_null() throws ServletException, IOException {
    String email = "Francesco.torino1999@gmail.com";
    when(request.getParameter("name")).thenReturn("Francesco");
    when(request.getParameter("surname")).thenReturn("Torino");
    when(request.getParameter("email")).thenReturn("Francesco.torino1999@gmail.com");
    when(request.getParameter("birthplace")).thenReturn("Caserta");
    when(request.getParameter("birthdate")).thenReturn(("1999-09-18"));
    when(request.getParameter("kind")).thenReturn("Uomo");
    when(request.getParameter("city")).thenReturn("Caserta");
    when(request.getParameter("pc")).thenReturn(null);
    when(request.getParameter("street")).thenReturn("Isonzo");
    when(request.getParameter("password")).thenReturn("pass");

    assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
  }

  void street_null() throws ServletException, IOException {
    String email = "Francesco.torino1999@gmail.com";
    when(request.getParameter("name")).thenReturn("Francesco");
    when(request.getParameter("surname")).thenReturn("Torino");
    when(request.getParameter("email")).thenReturn("Francesco.torino1999@gmail.com");
    when(request.getParameter("birthplace")).thenReturn("Caserta");
    when(request.getParameter("birthdate")).thenReturn(("1999-09-18"));
    when(request.getParameter("kind")).thenReturn("Uomo");
    when(request.getParameter("city")).thenReturn("Caserta");
    when(request.getParameter("pc")).thenReturn("81100");
    when(request.getParameter("street")).thenReturn(null);
    when(request.getParameter("password")).thenReturn("pass");

    assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
  }

  void password_null() throws ServletException, IOException {
    String email = "Francesco.torino1999@gmail.com";
    when(request.getParameter("name")).thenReturn("Francesco");
    when(request.getParameter("surname")).thenReturn("Torino");
    when(request.getParameter("email")).thenReturn("Francesco.torino1999@gmail.com");
    when(request.getParameter("birthplace")).thenReturn("Caserta");
    when(request.getParameter("birthdate")).thenReturn(("1999-09-18"));
    when(request.getParameter("kind")).thenReturn("Uomo");
    when(request.getParameter("city")).thenReturn("Caserta");
    when(request.getParameter("pc")).thenReturn("81100");
    when(request.getParameter("street")).thenReturn("Isonzo");
    when(request.getParameter("password")).thenReturn(null);

    assertThrows(NullPointerException.class,()->{servlet.doGet(request,response);});
  }

}