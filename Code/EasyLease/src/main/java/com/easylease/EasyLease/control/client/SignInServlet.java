package com.easylease.EasyLease.control.client;

import com.easylease.EasyLease.model.client.Client;
import com.easylease.EasyLease.model.client.DBClientDAO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SignInServlet")
public class SignInServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    Client client = new Client();
    client.setId(request.getParameter("id"));
    client.setName(request.getParameter("name"));
    client.setSurname(request.getParameter("surname"));
    client.setEmail(request.getParameter("email"));
    client.setPassword(request.getParameter("password"));
    client.setBirthPlace(request.getParameter("bithplace"));
    try {
      client.setBirthDate(new SimpleDateFormat(
          "dd/MM/yyyy").parse((request.getParameter("bithdate"))));
    } catch (ParseException e) {
      System.out.println(e.getMessage());
    }
    client.setKind(request.getParameter("kind"));
    client.setCity(request.getParameter("city"));
    client.setPc(request.getParameter("pc"));
    client.setStreet(request.getParameter("street"));

    DBClientDAO dao = (DBClientDAO) DBClientDAO.getInstance();
    dao.insert(client);

    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/loginJSP.jsp");
    dispatcher.forward(request, response);
  }
}
