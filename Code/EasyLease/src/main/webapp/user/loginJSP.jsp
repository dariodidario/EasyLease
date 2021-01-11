<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
      crossorigin="anonymous">
<link rel="stylesheet" href="login.css">
<head>
  <meta charset="ISO-8859-1">
  <title>Login</title>
</head>
<body>
<%@include file="../fragments/headerJSP.jsp"%>
<br/>
<center>
  <div style="text-align: center; width: 70%;">
<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
  <div class="form-group">
    <label for="email"></label><br>
    <input type="email" name="userEmail" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email">
  </div>
  <div class="form-group">
    <label for="password"></label> <br>
    <input type="password" class="form-control" id="password" placeholder="Password" name="userPassword">
  </div> <br>
  <a href="${pageContext.request.contextPath}/client/signInJSP.jsp">Registrati</a>
  <br><br>
  <button type="submit" class="btn btn-primary">Login</button>
  <input type="reset" class="btn btn-primary" value="Cancella">
</form>
  </div>
</center>
<%@include file="../fragments/footerJSP.jsp"%>
</body>
</html>