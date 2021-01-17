<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17/01/2021
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
        .navbar-inverse .navbar-nav>li>a {
            color: white;
        }

        .container-fluid{
            background-color: #9B334E;
        }

        .navbar-inverse .navbar-nav>.open>a, .navbar-inverse .navbar-nav>.open>a:focus, .navbar-inverse .navbar-nav>.open>a:hover {
            color: #fff;
            background-color: #9B334E;
        }
        .navbar-brand>img{
            margin-top:-4px;
        }
        .navbar {
            border-radius: 0;
            background-color: #9B334E;
        }
        .navbar-inverse {
            background-color: #9B334E;
            border-color: #9B334E;
        }
        .open>.dropdown-menu {
            display: block;
            background-color: #9B334E;
        }
        .dropdown-menu>li>a:focus, .dropdown-menu>li>a:hover {
            color: white;
            text-decoration: none;
            background-color: #9B3340;
        }
        .dropdown-menu>li>a {
            color: white;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/img/misc/Transparent_Logo.png"></a>
        </div>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"> Contatti</a></li>
            <li><a href="#">FAQ </a></li>
            <%
                String roleUser = (String) request.getSession().getAttribute("role");
                if (roleUser == null){%>
            <li><a href="#"> Login</a></li>
            <li><a href="#">Registrati</a></li><%}
        else if(roleUser.equals("client")){%>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/HistoryClientServlet">Ordini e Preventivi</a></li>
                    <li><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>
                </ul>
            </li><%}
        else if (roleUser.equals("advisor")){%>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/HistoryAdvisorServlet">Ordini e Preventivi</a></li>
                    <li><a href="${pageContext.request.contextPath}/ClientsServlet">Visualizza Clienti</a></li>
                    <li><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>
                </ul>
            </li><%}
        else if (roleUser.equals("admin")){%>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/ViewAddAdvisorServlet">Aggiungi consulente</a></li>
                    <li><a href="${pageContext.request.contextPath}/ViewAddCarServlet">Aggiungi auto</a></li>
                    <li><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>
                </ul>
            </li><%}%>
        </ul>
    </div>
</nav>
</body>
</html>