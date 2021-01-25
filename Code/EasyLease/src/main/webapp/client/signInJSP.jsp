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
      border: none !important;
    }


    #divCont{
      text-align: center; width: 30%;
    }
    @media screen and (max-width: 600px) {
      #divCont{
        text-align: center; width: 60%;
      }
    }
  </style>

</head>
<body>
<%@include file="../fragments/headerJSP.jsp"%>
<script type="text/javascript">
  function controllo(){
    var cognome = document.getElementById("cognome").value;
    var nome = document.getElementById("nome").value;
    var conferma = document.getElementById("conferma").value;
    var bp = document.getElementById("bp").value;
    var bd = document.getElementById("bd").value;
    var data = Date.parse(bd);
    var city = document.getElementById("city").value;
    var pc = document.getElementById("cap").value;
    var via = document.getElementById("street").value;
    var email=document.getElementById("email").value;
    var password=document.getElementById("password").value;
    var mailformat=/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    var nomeformat=/^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%?&*(){}|~<>;:[\]]{2,}$/;
    var passwordformat = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})/;
    var capformat =/^\d{5}$/;
    var controllo;
    var oggi = new Date();
    controllo = true;
    document.getElementById("nomelb").style.display="none";
    document.getElementById("cognomelb").style.display="none";
    document.getElementById("emaillb").style.display="none";
    document.getElementById("passwordlb").style.display="none";
    document.getElementById("confermalb").style.display="none";
    document.getElementById("confermalb2").style.display="none";
    document.getElementById("birthplacelb").style.display="none";
    document.getElementById("birthdatelb").style.display="none";
    document.getElementById("citylb").style.display="none";
    document.getElementById("caplb").style.display="none";
    document.getElementById("vialb").style.display="none";
    if ((nome == "") || (nome == "undefined") || (!nomeformat.test(nome))){
      m=document.getElementById("nomelb");
      m.style.display="block";
      controllo=false;
    }
    if ((cognome == "") || (cognome == "undefined") || (!nomeformat.test(cognome))){
      m=document.getElementById("cognomelb");
      m.style.display="block";
      controllo=false;
    }
    if((!mailformat.test(email)) || (email == "") || (email == "undefined")){
      var m=document.getElementById("emaillb");
      m.style.display="block";
      controllo=false;
    }
    if((password == "") || (password == "undefined") || (!passwordformat.test(password))){
      m=document.getElementById("passwordlb");
      m.style.display="block";
      controllo=false;
    }
    if((conferma == "") || (conferma == "undefined")){
      m=document.getElementById("confermalb");
      m.style.display="block";
      controllo=false;
    }
    if((conferma != password)){
      m=document.getElementById("confermalb2");
      m.style.display="block";
      controllo=false;
    }
    if ((bp == "") || (bp == "undefined") || (!nomeformat.test(bp))){
      m=document.getElementById("birthplacelb");
      m.style.display="block";
      controllo=false;
    }
    if ((bd == "") || (bd == "undefined") || (data>oggi)){
      m=document.getElementById("birthdatelb");
      m.style.display="block";
      controllo=false;
    }
    if ((city == "") || (city == "undefined") || (!nomeformat.test(city))){
      m=document.getElementById("citylb");
      m.style.display="block";
      controllo=false;
    }
    if ((pc == "") || (pc == "undefined") || (!capformat.test(pc))){
      m=document.getElementById("caplb");
      m.style.display="block";
      controllo=false;
    }
    if((via=="")||(via == "undefined") || (!nomeformat.test(via))){
      m=document.getElementById("vialb");
      m.style.display="block";
      controllo=false;
    }


    return controllo;
  }

</script>
<br/>
<center>
<div id="divCont">

  <form action="${pageContext.request.contextPath}/SignInServlet" method="post" onSubmit="return controllo()">
    <br/>
    <div class="form-group">
      <input name="name" class="form-control" id="nome" type="text" placeholder="Nome"/>
      <label for="nome"></label>
      <label  id="nomelb" style=" color: red; display:none;">*nome non valido</label>
    </div>
    <div class="form-group">
      <input name="surname" class="form-control" id="cognome" type="text" placeholder="Cognome"/>
      <label for="cognome"></label>
      <label  id="cognomelb" style=" color: red; display:none;">*cognome non valido</label>
    </div>
    <div class="form-group">
      <input type="text" name ="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email">
      <label for="email"></label>
      <label  id="emaillb" style=" color: red; display:none;">*email non valida</label>
    </div>
    <div class="form-group">
      <input type="password" name="password" class="form-control" id="password" placeholder="Password">
      <label for="password"></label>
      <label style=" color: red; display:none;" id="passwordlb" >*password non valida</label>
    </div>
    <div class="form-group">
      <input type="password" name="password" class="form-control" id="conferma" placeholder="Conferma password">
      <label for="conferma"></label>
      <label style=" color: red; display:none;" id="confermalb" >*conferma password non valida</label>
      <label style=" color: red; display:none;" id="confermalb2" >*conferma password e password sono diverse</label>
    </div>
    <div class="form-group">
      <input name="birthplace" class="form-control" id="bp" type="text" placeholder="Luogo di nascita"/>
      <label for="bp"></label>
      <label  id="birthplacelb" style=" color: red; display:none;">*luogo di nascita non valido</label>
    </div>
    <div class="form-group">
      <input name="birthdate" class="form-control" id="bd" type="date"/>
      <label for="bd"></label>
      <label  id="birthdatelb" style=" color: red; display:none;">*data di nascita non valido</label>
    </div>
    <select name="kind" id="kind" class="form-control">
      <option value="Uomo">uomo</option>
      <option value="Donna">donna</option>
      <option value="Preferisco non specificarlo">preferisco non specificarlo</option>
    </select>
    <label for="kind"></label>
    <label  id="kindlb" style=" color: red; display:none;">*genere non valido</label>
    <div class="form-group">
      <input name="city" class="form-control" id="city" type="text" placeholder="Città"/>
      <label for="city"></label>
      <label  id="citylb" style=" color: red; display:none;">*citta non valido</label>
    </div>
    <div class="form-group">
      <input name="pc" class="form-control" id="cap" type="text" placeholder="CAP"/>
      <label for="cap"></label>
      <label  id="caplb" style=" color: red; display:none;">*CAP non valido</label>
    </div>
    <div class="form-group">
      <input name="street" class="form-control" id="street" type="text" placeholder="Via"/>
      <label for="street"></label>
      <label  id="vialb" style=" color: red; display:none;">*via non valida</label>
    </div>
    <div class="form-check" style="width: 15%">
      <input class="form-check-input" type="checkbox" value="" id="checkPrivacy" required>
      <label class="form-check-label" for="checkPrivacy">
        Privacy
      </label>
    </div>
    <br>
    <button type="submit" id="registrati" class="btn btn-primary">Registrati</button>
    <input type="reset" id="cancella" class="btn btn-primary" value="Cancella">
    <br>
    <br>
  </form>
</div>
</center>
<%@include file="../fragments/footerJSP.jsp"%>
</body>
</html>