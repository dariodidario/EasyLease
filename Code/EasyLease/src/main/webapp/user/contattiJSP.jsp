<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link rel = "stylesheet" href = "${pageContext.request.contextPath}/user/Contatti.css"/>
  <title>FAQ</title>
</head>
<body>
<%@include file="/fragments/headerJSP.jsp" %>
          <h2>Contatti</h2>
        <div class="container">
          <div class="contact-container" >
            <h3>Inviaci una mail</h3>
            <form action="sendmail">
              <div class="row">
                <div class="col-25">
                  <label for="firstname">Nome*</label>
                </div>
                <div class="col-75">
                  <input
                    type="text"
                    id="firstname"
                    name="firstname"
                    placeholder="Il tuo nome"
                    required
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-25">
                  <label for="lastname">Cognome*</label>
                </div>
                <div class="col-75">
                  <input
                    type="text"
                    id="lastname"
                    name="lastname"
                    placeholder="Il tuo cognome"
                    required
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-25">
                  <label for="email">Email*</label>
                </div>
                <div class="col-75">
                  <input
                    type="email"
                    id="email"
                    name="email"
                    placeholder="La tua mail"
                    required
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-25">
                  <label for="telefono">Telefono</label>
                </div>
                <div class="col-75">
                  <input
                          type="telefono"
                          id="telefono"
                          name="telefono"
                          placeholder="Il tuo numero di telefono"
                          pattern="^(\((00|\+)39\)|(00|\+)39)?(38[890]|34[4-90]|36[680]|33[13-90]|32[89]|35[01]|37[019])\d{6,7}$"
                          title="Numero di telefono malformato"
                  />
                </div>
              </div>
              <div class="row">
                <div class="col-25">
                  <label for="subject">Descrivi il problema*</label>
                </div>
                <div class="col-75">
                  <textarea
                    id="subject"
                    name="subject"
                    placeholder="Scrivi qui"
                    style="height: 200px;"
                    required
                  ></textarea>
                </div>
              </div>
              <div class="row">
                <input id="contatti" type="submit" value="Invia messaggio" />
              </div>
            </form>
            <h5 class="check">I campi segnati con * sono obbligatori</h5>
          </div>
        </div>
        <div class="container">
          <h3>Chiamaci a telefono</h3>
          <div class="row">
            <a href="tel:+390123456789" class="button" id="call">Chiama +39.0123.456789!</a>
          </div>
          <h5 class="check">I centralini sono attivi dal lunedì al venerdi dalle 9:00 alle 18:00</h5>
        </div>
<%@include file="/fragments/footerJSP.jsp" %>
</body>
</html>
