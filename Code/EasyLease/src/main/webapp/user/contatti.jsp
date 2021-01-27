<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="user/Contatti.css">
  <title>Contatti</title>
</head>
<body>
<%@include file="/fragments/header.jsp" %>


      <form action="sendmail">
              <table class="table_mail_container">
                <tr>
                  <td class="mail_background">
                   <table class="mail_table">
                      <tr>
                          <td class="subject" colspan="2" valign="bottom">
                              <label class="subject_label" for="subject">Descrivi il problema*</label>
                              <br>
                              <textarea id="subject" name="subject" placeholder="Scrivi qui"  required></textarea>
                          </td> </tr>
                      <tr>
                          <td class="TDmail_button" valign="bottom">
                              <input class="mail_button" id="contatti" type="submit" value="Invia messaggio" />
                          </td>
                          <td class="TDobject" align="left" valign="bottom">
                              <table class="object">
                                <tr>
                                  <td align="right"><label for="firstname">Nome*</label>
                                    <input type="text" id="firstname" name="firstname" placeholder="Il tuo nome" required/>
                                  </td>
                                </tr>
                                <tr>
                                  <td align="right"><label for="lastname">Cognome*</label>
                                    <input type="text" id="lastname" name="lastname" placeholder="Il tuo cognome" required/>
                                  </td>
                                </tr>
                                <tr>
                                  <td align="right"><label for="email">Email*</label>
                                    <input type="email" id="email" name="email" placeholder="La tua mail" required/>
                                  </td>
                                </tr>
                                <tr>
                                  <td align="right"><label for="telefono">Telefono</label>
                                    <input type="telefono" id="telefono" name="telefono" placeholder="Il tuo telefono" pattern="^((00|\+)39[\. ]??)??3\d{2}[\. ]??\d{6,7}$" title="Numero di telefono malformato"/>
                                  </td>
                                </tr>
                              </table>
                          </td>
                      </tr>
                      <tr>
                          <td colspan="2" class="TDinfo">
                              <label class="info">I campi segnati con * sono obbligatori</label>
                          </td>
                      </tr>
                  </table>
                 </td>
                </tr>
              </table>
      </form>
      <img class="table_img" src="img/misc/mail_png.png">


        <div class="tell_container" align="right">
          <label class="tel1">Chiamaci a telefono</label>
            <a href="tel:+390123456789" class="tell_button" id="call">Chiama +39.0123.456789!</a>
            <img class="tell_img" src="img/misc/call_center_png.png">
          <label class="tel2">I centralini sono attivi dal lunedì al venerdi dalle 9:00 alle 18:00</label>
        </div>
<%@include file="/fragments/footer.jsp" %>
</body>
</html>
