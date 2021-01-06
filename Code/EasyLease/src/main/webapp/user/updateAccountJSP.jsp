<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  //Client cliente=(Client)request.getAttribute("user");
  Client cliente = new Client("CLABC12", "Mario", "Rossi", "m.rossi@gmail.com", "mrossi", "Avellino", new Date(), "Uomo", "Avellino", "83020", "Contrada Petrulli 3");
  request.getSession().setAttribute("user",cliente);
%>
<!DOCTYPE html>
<html>
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
<div style="text-align: center; border:solid;">
  <form action="jetbrains://idea/navigate/reference?project=EasyLease&fqn=com.easylease.EasyLease.control.client.SignInServlet" method="POST">
    <br/><br/>
    <input name="name" id="nome" type="text" value="<%=cliente.getName()%>"/><br/>
    <label id="nomelb" for="nome" class="lb">*Nome non valido</label><br/>
    <input name="surname" id="cognome" type="text" value="<%=cliente.getSurname()%>"/><br/>
    <label id="cognomelb" for="cognome" class="lb">*Cogome non valido</label><br/>
    <input name="mail" id="mail" type="email" value="<%=cliente.getEmail()%>"/><br/>
    <label id="maillb" for="mail" class="lb">*E-mail non valida</label><br/>
    <input name="password" id="password" type="password" value="<%=cliente.getPassword()%>"/><br/>
    <label id="passwordlb" for="password" class="lb">*Password non valida</label><br/>

    <input name="birthplace" id="bp" type="text" value="<%=cliente.getBirthPlace()%>"/><br/>
    <label id="birthplacelb" for="bp" class="lb">*birthplace non valida</label><br/>

    <input name="birthdate" id="bd" type="date" value="<%=cliente.getBirthDate()%>"/><br/>
    <label id="birthdatelb" for="bd" class="lb">*birthplace non valida</label><br/>
    <select name="kind" id="kind">
      <option value="uomo">uomo</option>
      <option value="donna">donna</option>
      <option value="preferisco non specificarlo">preferisco non specificarlo</option>
    </select><br/>
    <label id="kindlb" for="kind" class="lb">*birthplace non valida</label><br/>
    <input name="city" id="city" type="text" value="<%=cliente.getStreet()%>"/><br/>
    <label id="citylb" for="city" class="lb">*Città non valida</label><br/>

    <input name="pc" id="cap" type="text" value="<%=cliente.getPc()%>"/><br/>
    <label id="caplb" for="cap" class="lb">*CAP non valido</label><br/>
    <input name="street" id="street" type="text" value="<%=cliente.getStreet()%>"/><br/>
    <label id="vialb" for="street" class="lb">*Via non valida</label><br/>
    <input type="submit" value="Aggiorna" >
    <input type="reset" value="Cancella">
    <br/><br/>
  </form>
</div>
</body>
</html>