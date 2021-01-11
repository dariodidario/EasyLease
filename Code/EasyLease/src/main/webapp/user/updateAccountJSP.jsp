<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  Client cliente=(Client)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
      crossorigin="anonymous">
<link rel="stylesheet" href="update.css">
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,com.easylease.EasyLease.model.client.Client"%>
<head>
  <meta charset="ISO-8859-1">
  <title>Aggiorna Account</title>
  <style>
    .lb{
      color: red;
      display:none;
    }
  </style>
</head>
<body>
<%@include file="../fragments/headerJSP.jsp"%>
<center>
<div style="text-align: center; width: 70%;">
  <form action="jetbrains://idea/navigate/reference?project=EasyLease&fqn=com.easylease.EasyLease.control.client.SignInServlet" method="POST">
    <br/>
    <br/>
    <div class="form-group">
      <input name="name" class="form-control" id="nome" type="text" value="<%=cliente.getName()%>"/><br/>
      <label id="nomelb" for="nome"></label>
    </div>
    <div class="form-group">
      <input name="surname" class="form-control" id="cognome" type="text" value="<%=cliente.getSurname()%>"/><br/>
      <label id="cognomelb" for="cognome"></label><br/>
    </div>
    <div class="form-group">
      <input type="email" name = "email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="<%=cliente.getEmail()%>"><br>
      <label for="exampleInputEmail1"></label>
    </div>
    <div class="form-group">
      <input type="password" name="password" class="form-control" id="exampleInputPassword1" value="<%=cliente.getPassword()%>"><br>
      <label for="exampleInputPassword1"></label>
    </div>
    <div class="form-group">
      <input name="birthplace" class="form-control" id="bp" type="text" value="<%=cliente.getBirthPlace()%>"/><br/>
      <label id="birthplacelb" for="bp"></label>
    </div>
    <div class="form-group">
      <input name="birthdate" class="form-control" id="bd" type="date" value="<%=cliente.getBirthDate()%>"/><br/>
      <label id="birthdatelb" for="bd"></label>
    </div>
    <select name="kind" id="kind" class="form-control">
      <option value="Uomo">uomo</option>
      <option value="Donna">donna</option>
      <option value="Preferisco non specificarlo">preferisco non specificarlo</option>
    </select><br/>
    <label id="kindlb" for="kind"></label>
    <div class="form-group">
      <input name="city" class="form-control" id="city" type="text" value="<%=cliente.getCity()%>"/><br/>
      <label id="citylb" for="city"></label>
    </div>
    <div class="form-group">
      <input name="pc" class="form-control" id="cap" type="text" value="<%=cliente.getPc()%>"/><br/>
      <label id="caplb" for="cap"></label>
    </div>
    <div class="form-group">
      <input name="street" class="form-control" id="street" type="text" value="<%=cliente.getStreet()%>"/><br/>
      <label id="vialb" for="street"></label>
    </div>
    <br>
    <button type="submit" class="btn btn-primary">Aggiorna</button>
    <input type="reset" class="btn btn-primary" value="Cancella">
    <br>
    <br>
  </form>
</div>
</center>
<%@include file="../fragments/footerJSP.jsp"%>
</body>
</html>