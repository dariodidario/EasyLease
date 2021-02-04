package com.easylease.easylease.control.admin;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ViewAddCarServletTest {
  private ViewAddCarServlet servlet;
  private HttpServletRequest request;
  private HttpServletResponse response;
  private HttpSession session;
  private RequestDispatcher dispatcher;
  private ServletContext context;
  private ServletConfig config;

  @BeforeEach
  public void setUp() throws IOException, ServletException {
    servlet = new ViewAddCarServlet();
    config = mock(ServletConfig.class);
    servlet.init(config);
    request = mock(HttpServletRequest.class);
    response = mock(HttpServletResponse.class);
    session = mock(HttpSession.class);
    context = mock(ServletContext.class);
    dispatcher = mock(RequestDispatcher.class);
    when(request.getSession()).thenReturn(session);
    when(servlet.getServletContext()).thenReturn(context);
    when(context.getRequestDispatcher(anyString())).thenReturn(dispatcher);
  }

  @Test
  void roleNull() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn(null);

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/user/login.jsp");
  }

  @Test
  void userNotAdmin() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("client");

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/user/login.jsp");
  }

  @Test
  void doGet() throws ServletException, IOException {
    when(request.getSession().getAttribute("role")).thenReturn("admin");

    servlet.doGet(request, response);
    verify(context).getRequestDispatcher("/admin/addCar.jsp");
  }

}