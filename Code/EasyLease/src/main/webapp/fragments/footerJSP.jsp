<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>

        .footer{
            background-color: darkred;
            border-top: 1px solid darkgrey;
            height: 8em;
            margin-top:10%;
        }

        .nOrder{
            margin-top: 1em;
            font-family: sans-serif;
            text-align: center;
            color:white;
        }
        .text{
            font-family: sans-serif;
            text-align: center;
            color: darkgrey;
        }

    </style>
  <title>Footer</title>
    <script> var contextPath = "${pageContext.request.contextPath}";</script>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src = "${pageContext.request.contextPath}/fragments/footer.js"> </script>
</head>

<body>
<div class="footer" id="footer">
    <div class="nOrder"> Sono stati effettuati già <label id="numOrder"></label> ordini su EasyLease quest'anno!</div>
    <div class="text"> <br>
        © 2021 EasyLease - created by C11 team and lead by Dario di Dario
    </div>
</div>
</body>
</html>
