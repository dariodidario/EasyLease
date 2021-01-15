<%@ page import="com.easylease.EasyLease.model.user.User" %>
<%@ page import="com.easylease.EasyLease.model.client.Client" %>
<%@ page import="com.easylease.EasyLease.model.advisor.Advisor" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/fragments/header.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dangers navbar-danger">
  <!-- Brand -->
  <a class="navbar-brand" href="${pageContext.request.contextPath}/user/homePageJSP.jsp">
      <div class="headerLogo"> <img src="${pageContext.request.contextPath}/img/misc/logo2Trasparente.png"></div></a>

  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="#">Contatti</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">FAQ</a>
    </li>

    <!-- Dropdown -->

          <%
              String roleUser = (String) request.getSession().getAttribute("role");
          if (roleUser == null){%>
      <ul class="navbar-nav">
          <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/ViewLoginServlet">Login</a>
        </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/ViewSignInServlet">Registrati</a>
      </li><%}
    else{%>
      <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
           <img src="${pageContext.request.contextPath}/img/misc/userImage3.png" class="userImg">
          </a>
      <%if (roleUser.equals("client")) {%>
              <div class="dropdown-menu dropdown-menu-right">
              <a class="dropdown-item" href="${pageContext.request.contextPath}/HistoryClientServlet">Ordini e Preventivi</a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/LogoutServlet">Logout</a><%}
          else if (roleUser.equals("advisor")) {%>
                  <div class="dropdown-menu">
              <a class="dropdown-item" href="${pageContext.request.contextPath}/HistoryAdvisorServlet">Ordini e Preventivi</a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/ClientsServlet">Visualizza Clienti</a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/LogoutServlet">Logout</a><%}
          else if (roleUser.equals("admin")){%>
                      <div class="dropdown-menu">
              <a class="dropdown-item" href="${pageContext.request.contextPath}/ViewAddAdvisorServlet">Aggiungi consulente</a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/ViewAddCarServlet">Aggiungi auto</a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/LogoutServlet">Logout</a><%}}%>
    </li>
  </ul>
</nav>
<br>
</body>
</html>