package com.easylease.EasyLease.control.fragments;

import com.easylease.EasyLease.model.order.DbOrderDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "FooterServlet", value = "/FooterServlet")
public class FooterServlet extends HttpServlet {
  protected void doPost(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(
      HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/plain");
    response.getWriter().write((DbOrderDao.getInstance().retrieveAll().size() + 936) + "");

  }
}
