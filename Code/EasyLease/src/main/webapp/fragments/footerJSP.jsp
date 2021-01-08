<%@ page import="com.easylease.EasyLease.model.order.DBOrderDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>

        .footer{
            border-top: 1px solid darkgrey;
            height: 8em;
            margin-top:10%;
        }

        .nOrder{
            margin-top: 1em;
            font-family: sans-serif;
            text-align: center;
            color:dimgrey;
        }
        .text{
            font-family: sans-serif;
            text-align: center;
            color: darkgrey;
        }

    </style>
  <title>Footer</title>
</head>
<body>
<div class="footer">
 <div class="nOrder"> Sono stati effettuati già <%=DBOrderDAO.getInstance().retrieveAll().size()%> ordini su EasyLease quest'anno!</div>
    <div class="text"> <br>
        © 2021 EasyLease - created by C11 team and lead by Dario di Dario
    </div>
</div>
</body>
</html>
