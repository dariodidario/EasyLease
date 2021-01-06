<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Login</title>
</head>
<body>
<hr>
<h1>Login</h1>
<hr>
<br/><br/>
<form action="jetbrains://idea/navigate/reference?project=EasyLease&fqn=com.easylease.EasyLease.control.user.LoginServlet" method="POST">
  <input name="email" id="email" type="email" value="" placeholder="Email"/><br/><br/>
  <label id="maillb" for="email" class="lb" style="display: none"></label><br/>
  <input name="password" id="password" type="password" value="" placeholder="Password"/>
  <label id="passwordlb" for="password" class="lb" style="display: none"></label><br/>
  <br/><br/>
  <input type="hidden" name="action" value="login">
  <input type="submit" value="Login">
  <input type="reset" value="Cancella">
  <br/><br/>
  <a href="../client/signInJSP.jsp">Registrati</a>
</form>
</body>
</html>