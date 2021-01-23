
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fragments/headerJSP.css">
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <style>
        .Zcontainer-fluid{
            background-color: #9B334E;
        }
        .Znavbar-inverse .Znavbar-nav>.open>a, .Znavbar-inverse .Znavbar-nav>.open>a:focus, .Znavbar-inverse .Znavbar-nav>.open>a:hover {
            color: #fff;
            background-color: #9B334E;
        }
        .Znavbar-brand>img{
            margin-top:-4px;
        }
        .Znavbar {
            border-radius: 0;
            background-color: #9B334E;
        }
        .Znavbar-inverse {
            background-color: #9B334E;
            border-color: #9B334E;
        }
        .open>.Zdropdown-menu {
            display: block;
            background-color: #9B334E;
        }
        .Zdropdown-menu>li>a:focus, .Zdropdown-menu>li>a:hover {
            color: white;
            text-decoration: none;
            background-color: #9B3340;
        }
        .Zdropdown-menu>li>a {
            color: white;
            text-decoration: none;
        }
        .Znavbar-inverse .Znavbar-nav>li>a {
            font-size: 14px;
            color: white;
            text-decoration: none !important;
        }
    </style>
</head>
<body>

<nav class="Znavbar Znavbar-inverse">
    <div class="Zcontainer-fluid">
        <div class="Znavbar-header">
            <a class="Znavbar-brand" href="${pageContext.request.contextPath}/user/homePageJSP.jsp"><img src="${pageContext.request.contextPath}/img/misc/Transparent_Logo.png"></a>
        </div>

        <ul class="Znav Znavbar-nav Znavbar-right">
            <li><a href="${pageContext.request.contextPath}/ContattiServlet"> Contatti</a></li>
            <li><a href="${pageContext.request.contextPath}/FAQServlet">FAQ </a></li>
            <%
                String roleUser = (String) request.getSession().getAttribute("role");
                if (roleUser == null){%>
            <li><a href="${pageContext.request.contextPath}/ViewLoginServlet"> Login</a></li>
            <li><a href="${pageContext.request.contextPath}/ViewSignInServlet">Registrati</a></li><%}
        else if(roleUser.equals("client")){%>
            <li class="Zdropdown">
                <a class="Zdropdown-toggle" data-toggle="dropdown" href="#">
                    <img src="${pageContext.request.contextPath}/img/misc/userImage3.png"> <span class="Zcaret"></span></a>
                <ul class="Zdropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/HistoryClientServlet">Ordini e Preventivi</a></li>
                    <li><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>
                </ul>
            </li><%}
        else if (roleUser.equals("advisor")){%>
            <li class="Zdropdown"><a class="Zdropdown-toggle" data-toggle="dropdown" href="#">
                <img src="${pageContext.request.contextPath}/img/misc/userImage3.png"> <span class="Zcaret"></span></a>
                <ul class="Zdropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/HistoryAdvisorServlet">Ordini e Preventivi</a></li>
                    <li><a href="${pageContext.request.contextPath}/ClientsServlet">Visualizza Clienti</a></li>
                    <li><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>
                </ul>
            </li><%}
        else if (roleUser.equals("admin")){%>
            <li class="Zdropdown"><a class="Zdropdown-toggle" data-toggle="dropdown" href="#">
                <img src="${pageContext.request.contextPath}/img/misc/userImage3.png"> <span class="Zcaret"></span></a>
                <ul class="Zdropdown-menu">
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