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
<br/>
<center>
<div style="text-align: center; width: 70%;">
  <script type="text/javascript">
    function controllo(){
      var controllo=true;
      var mail=document.getElementById("email").value;
      var nome=document.getElementById("nome").value;
      var password=document.getElementById("password").value;
      var conferma=document.getElementById("conferma").value;
      var cognome=document.getElementById("cognome").value;
      var bd=document.getElementById("bd").value;
      var bp=document.getElementById("bp").value;
      var kind=document.getElementById("kind").value;
      var via=document.getElementById("via").value;
      var cap=document.getElementById("cap").value;
      var citta=document.getElementById("citta").value;
      var mailformat=/^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-]{2,})+.)+([a-zA-Z0-9]{2,})+$/;
      var nomeformat=/^[a-zA-Z]+$/;
      var cognomeformat=/^[a-zA-Z]+$/;
      var viaformat=/^[a-zA-Z]+$/;
      var capformat=/^[0-9]+$/;
      var bpformat=/^[a-zA-Z]+$/;
      var cittaformat=/^[a-zA-Z]+$/;
      var kindformat=/^[a-zA-Z]+$/;
      if((!mailformat.test(mail)) || (mail == "") || (mail == "undefined")){
        var m=document.getElementById("emaillb");
        m.style.display="block";
        controllo=false;
      }
      if((password == "") || (password == "undefined")){
        m=document.getElementById("passwordlb");
        m.style.display="block";
        controllo=false;
      }
      if((conferma == "") || (conferma == "undefined")||(conerma!=password)){
        m=document.getElementById("passwordlb");
        m.style.display="block";
        controllo=false;
      }
      if ((nome == "") || (nome == "undefined") || (!nomeformat.test(nome))){
        m=document.getElementById("nomelb");
        m.style.display="block";
        controllo=false;
      }
      if ((cognome == "") || (cognome == "undefined") || (!cognomeformat.test(cognome))){
        m=document.getElementById("cognomelb");
        m.style.display="block";
        controllo=false;
      }
      if ((via == "") || (via == "undefined") || (!viaformat.test(via))){
        m=document.getElementById("vialb");
        m.style.display="block";
        controllo=false;
      }
      if ((cap == "") || (cap == "undefined") || (!capformat.test(cap))){
        m=document.getElementById("caplb");
        m.style.display="block";
        controllo=false;
      }
      if ((bp == "") || (bp == "undefined") || (!bpformat.test(bp))){
        m=document.getElementById("bplb");
        m.style.display="block";
        controllo=false;
      }
      if ((citta == "") || (citta == "undefined") || (!cittaformat.test(citta))){
        m=document.getElementById("cittalb");
        m.style.display="block";
        controllo=false;
      }
      if ((kind == "") || (kind == "undefined") || (!kindformat.test(kind))){
        m=document.getElementById("kindlb");
        m.style.display="block";
        controllo=false;
      }
      if (bd == ""){
        m=document.getElementById("bdlb");
        m.style.display="block";
        controllo=false;
      }
      return controllo;
    }
  </script>
  <form action="${pageContext.request.contextPath}/SignInServlet" method="post" onSubmit="return controllo();">
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
      <input type="password" name="password" class="form-control" id="conferma"><br>
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