<%@ page import="com.easylease.EasyLease.model.user.User" %>
<%@ page import="com.easylease.EasyLease.model.client.Client" %>
<%@ page import="com.easylease.EasyLease.model.advisor.Advisor" %>
<%@ page import="com.easylease.EasyLease.model.admin.Admin" %>
<!DOCTYPE html>
<html lang="en">
<style>

    .headerLogo{
        width: 100%;
    }
    .headerLogo>img{
        max-width: 24%;
    }

    nav{
        border-bottom: 1px solid dimgrey; !important;
    }

    .nav-link{
        color: dimgrey;!important;
    }
</style>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-white navbar-white">
  <!-- Brand -->
  <a class="navbar-brand" href="../user/homePageJSP.jsp">
      <div class="headerLogo"> <img src="../foto/logo.png"></div></a>

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
              User user = new User();
              user.setName("name");
          if (user == null){%>
      <ul class="navbar-nav">
          <li class="nav-item">
              <a class="nav-link" href="#">Login</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="#">Registrati</a>
          </li><%}
          else{%>
      <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
           <img src="../foto/userImage3.png" class="userImg">
          </a>
      <%if (user instanceof Client) {%>
                  }
              <div class="dropdown-menu">
              <a class="dropdown-item" href="#">Area Utente</a>
              <a class="dropdown-item" href="#">Ordini e Preventivi</a>
              <a class="dropdown-item" href="#">Logout</a><%}
          else if (user instanceof Advisor) {%>
                  <div class="dropdown-menu">
                  <a class="dropdown-item" href="#">Area Utente</a>
              <a class="dropdown-item" href="#">Ordini e Preventivi</a>
              <a class="dropdown-item" href="#">Visualizza Clienti</a>
              <a class="dropdown-item" href="#">Logout</a><%}
          else {%>
                      <div class="dropdown-menu">
                      <a class="dropdown-item" href="#">Area Utente</a>
              <a class="dropdown-item" href="#">Aggiungi consulente</a>
              <a class="dropdown-item" href="#">Aggiungi auto</a>
              <a class="dropdown-item" href="#">Visualizza Consulenti</a>
              <a class="dropdown-item" href="#">Logout</a><%}}%>
    </li>
  </ul>
</nav>
<br>


</body>
</html>