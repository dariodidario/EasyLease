<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
      crossorigin="anonymous">
<link rel="stylesheet" href="signin.css">
<head>
  <meta charset="ISO-8859-1">
  <title>Registrazione</title>
</head>
<body>
<div style="text-align: center; border:solid;">
  <form action="jetbrains://idea/navigate/reference?project=EasyLease&fqn=com.easylease.EasyLease.control.client.SignInServlet" method="POST">
    <br/>
    <div class="form-group">
      <input name="name" class="form-control" id="nome" type="text" placeholder="Nome"/><br/>
      <label id="nomelb" for="nome"></label>
    </div>
    <div class="form-group">
      <input name="surname" class="form-control" id="cognome" type="text" placeholder="Cognome"/><br/>
      <label id="cognomelb" for="cognome"></label><br/>
    </div>
    <div class="form-group">
      <input type="email" name = "email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email"><br>
      <label for="exampleInputEmail1"></label>
    </div>
    <div class="form-group">
      <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password"><br>
      <label for="exampleInputPassword1"></label>
    </div>
    <div class="form-group">
      <input name="birthplace" class="form-control" id="bp" type="text" placeholder="Luogo di nascita"/><br/>
      <label id="birthplacelb" for="bp"></label>
    </div>
    <div class="form-group">
      <input name="birthdate" class="form-control" id="bd" type="date"/><br/>
      <label id="birthdatelb" for="bd"></label>
    </div>
    <select name="kind" id="kind" class="form-control">
      <option value="Uomo">uomo</option>
      <option value="Donna">donna</option>
      <option value="Preferisco non specificarlo">preferisco non specificarlo</option>
    </select><br/>
    <label id="kindlb" for="kind"></label>
    <div class="form-group">
      <input name="city" class="form-control" id="city" type="text" placeholder="Città"/><br/>
      <label id="citylb" for="city"></label>
    </div>
    <div class="form-group">
      <input name="pc" class="form-control" id="cap" type="text" placeholder="CAP"/><br/>
      <label id="caplb" for="cap"></label>
    </div>
    <div class="form-group">
      <input name="street" class="form-control" id="street" type="text" placeholder="Via"/><br/>
      <label id="vialb" for="street"></label>
    </div>
    <br>
    <button type="submit" class="btn btn-primary">Registrati</button>
    <input type="reset" class="btn btn-primary" value="Cancella">
    <br>
    <br>
  </form>
</div>
</body>
</html>