<%@ page language="java" %>
<html>
<head>
    <style>

        .footer{
            background-color: #9B334E;
            border-top: 1px solid darkgrey;
            height: 8em;
            margin-top:10%;
        }

        .nOrder{
            background-color: #9B334E;
            margin-top: 1em;
            font-family: sans-serif;
            text-align: center;
            color:white;
        }
        .text{
            background-color: #9B334E;
            font-family: sans-serif;
            text-align: center;
            color: darkgrey;
        }

    </style>
  <title>Footer</title>
    <script> var contextPath = "${pageContext.request.contextPath}";</script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src = "${pageContext.request.contextPath}/fragments/footer.js"> </script>
</head>

<body>
<div class="footer" id="footer">
    <div class="nOrder"> Sono stati effettuati gi&agrave; <label id="numOrder"></label> ordini su EasyLease quest'anno!</div>
    <div class="text"> <br>
        &#169; 2021 EasyLease - created by C11 team and lead by Dario di Dario
    </div>
</div>
</body>
</html>
