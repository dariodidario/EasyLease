package com.easylease.EasyLease.control.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.easylease.EasyLease.control.user.LoginServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




public class LogoutServletTest {
  public LoginServlet servlet;
  public HttpServletResponse response;
  public HttpServletRequest request;


  @BeforeEach
  protected void setUp() {
    servlet = mock(LoginServlet.class);
    response = mock(HttpServletResponse.class);
    request = mock(HttpServletRequest.class);
  }

  @Test
  public void doGetTest() throws ServletException, IOException {
    request.setAttribute("email", "francesco.torino1999@gmail.com");
    request.setAttribute("password", "password");
    servlet.doGet(request, response);
    assertEquals("text/html;charset=UTF=8", response.getContentType());
  }

}
