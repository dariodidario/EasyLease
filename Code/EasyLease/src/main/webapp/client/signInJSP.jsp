<%@ page contentType="text/html; charset=ISO-8859-1"
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
  <style>
    .btn-primary{
      background-color: #800000 !important;
    }
  </style>

</head>
<body>
<%@include file="../fragments/headerJSP.jsp"%>
<script type="text/javascript" src="SignIn.js">
</script>
<br/>
<center>
<div style="text-align: center; width: 70%;">

  <form action="${pageContext.request.contextPath}/SignInServlet" method="post" onSubmit="return controllo()">
    <br/>
    <div class="form-group">
      <input name="name" class="form-control" id="nome" type="text" placeholder="Nome"/><br/>
      <label for="nome"></label>
      <label  id="nomelb" style=" color: red; display:none;">*nome non valido</label>
    </div>
    <div class="form-group">
      <input name="surname" class="form-control" id="cognome" type="text" placeholder="Cognome"/><br/>
      <label for="cognome"></label>
      <label  id="cognomelb" style=" color: red; display:none;">*cognome non valido</label>
    </div>
    <div class="form-group">
      <input type="email" name = "email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email"><br>
      <label for="exampleInputEmail1"></label>
      <label  id="emaillb" style=" color: red; display:none;">*email non valida</label>
    </div>
    <div class="form-group">
      <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password"><br>
      <label for="exampleInputPassword1"></label>
      <label style=" color: red; display:none;" id="passwordlb" >*password non valida</label>
    </div>
    <div class="form-group">
      <input type="password" name="password" class="form-control" id="conferma" placeholder="Conferma password"><br>
      <label for="conferma"></label>
      <label style=" color: red; display:none;" id="confermalb" >*conferma password non valida</label>
    </div>
    <div class="form-group">
      <input name="birthplace" class="form-control" id="bp" type="text" placeholder="Luogo di nascita"/><br/>
      <label for="bp"></label>
      <label  id="birthplacelb" style=" color: red; display:none;">*luogo di nascita non valido</label>
    </div>
    <div class="form-group">
      <input name="birthdate" class="form-control" id="bd" type="date"/><br/>
      <label for="bd"></label>
      <label  id="birthdatelb" style=" color: red; display:none;">*data di nascita non valido</label>
    </div>
    <select name="kind" id="kind" class="form-control">
      <option value="Uomo">uomo</option>
      <option value="Donna">donna</option>
      <option value="Preferisco non specificarlo">preferisco non specificarlo</option>
    </select><br/>
    <label for="kind"></label>
    <label  id="kindlb" style=" color: red; display:none;">*genere non valido</label>
    <div class="form-group">
      <input name="city" class="form-control" id="city" type="text" placeholder="Città"/><br/>
      <label for="city"></label>
      <label  id="citylb" style=" color: red; display:none;">*citta non valido</label>
    </div>
    <div class="form-group">
      <input name="pc" class="form-control" id="cap" type="text" placeholder="CAP"/><br/>
      <label for="cap"></label>
      <label  id="caplb" style=" color: red; display:none;">*CAP non valido</label>
    </div>
    <div class="form-group">
      <input name="street" class="form-control" id="street" type="text" placeholder="Via"/><br/>
      <label for="street"></label>
      <label  id="vialb" style=" color: red; display:none;">*via non valida</label>
    </div>
    <br>
    <button type="submit" class="btn btn-primary">Registrati</button>
    <input type="reset" class="btn btn-primary" value="Cancella">
    <br>
    <br>
  </form>
</div>
</center>
<%@include file="../fragments/footerJSP.jsp"%>
</body>
</html>