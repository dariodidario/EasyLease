package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.control.utility.IdGenerator;
import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.DbClientDao;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SignInServlet", value = "/SignInServlet")
public class SignInServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    SimpleDateFormat htmlFormat = new SimpleDateFormat("yyyy-MM-dd");
    Client client = new Client();
    client.setId("CL"+ IdGenerator.randomIdGenerator());
    client.setName(request.getParameter("name"));
    client.setSurname(request.getParameter("surname"));
    client.setEmail(request.getParameter("email"));
    client.setBirthPlace(request.getParameter("birthplace"));
    try {
      client.setBirthDate(htmlFormat.parse(request.getParameter("birthdate")));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    client.setKind(request.getParameter("kind"));
    client.setCity(request.getParameter("city"));
    client.setPc(request.getParameter("pc"));
    client.setStreet(request.getParameter("street"));

    DbClientDao dao = (DbClientDao) DbClientDao.getInstance();

    if (dao.retrieveByEmail(client.getEmail()) != null) {
      request.getRequestDispatcher("/user/login.jsp")
          .forward(request, response);
    }
    else{
      dao.insert(client, request.getParameter("password"));
      request.getRequestDispatcher("/user/login.jsp")
          .forward(request, response);
    }

  }
}
