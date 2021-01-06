<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Registrazione</title>
  <style>
    .lb{
      color: red;
      display:none;
    }
  </style>
</head>
<body>
  <div style="text-align: center; border:solid;">
    <form action="jetbrains://idea/navigate/reference?project=EasyLease&fqn=com.easylease.EasyLease.control.client.SignInServlet" method="POST">
      <br/><br/>
      <input name="name" id="nome" type="text" placeholder="Nome"/><br/>
      <label id="nomelb" for="nome" class="lb">*Nome non valido</label><br/>
      <input name="surname" id="cognome" type="text" placeholder="Cognome"/><br/>
      <label id="cognomelb" for="cognome" class="lb">*Cogome non valido</label><br/>
      <input name="mail" id="mail" type="email" placeholder="E-mail"/><br/>
      <label id="maillb" for="mail" class="lb">*E-mail non valida</label><br/>
      <input name="password" id="password" type="password" placeholder="Password"/><br/>
      <label id="passwordlb" for="password" class="lb">*Password non valida</label><br/>

      <input name="birthplace" id="bp" type="text" placeholder="Luogo di nascita"/><br/>
      <label id="birthplacelb" for="bp" class="lb">*birthplace non valida</label><br/>

      <input name="birthdate" id="bd" type="date"/><br/><br/>
      <label id="birthdatelb" for="bd" class="lb">*birthplace non valida</label><br/>
      <select name="kind" id="kind">
        <option value="uomo">uomo</option>
        <option value="donna">donna</option>
        <option value="preferisco non specificarlo">preferisco non specificarlo</option>
      </select><br/>
      <label id="kindlb" for="kind" class="lb">*birthplace non valida</label><br/>

      <input name="city" id="city" type="text" placeholder="Via"/><br/>
      <label id="citylb" for="city" class="lb">*Città non valida</label><br/>

      <input name="pc" id="cap" type="text" placeholder="CAP"/><br/>
      <label id="caplb" for="cap" class="lb">*CAP non valido</label><br/>
      <input name="street" id="street" type="text" placeholder="Via"/><br/>
      <label id="vialb" for="street" class="lb">*Via non valida</label><br/>
      <input type="submit" value="Registrati" >
      <input type="reset" value="Cancella">
      <br/><br/>
    </form>
  </div>
</body>
</html>