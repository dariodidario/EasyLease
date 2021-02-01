<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<p>
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <link rel = "stylesheet" href = "${pageContext.request.contextPath}/user/FAQ.css"/>
        <title>FAQ</title>
    </head>
    <p>
        <%@include file="/fragments/header.jsp" %>
        <div class="container">
            <label class="FAQtitle">FAQ</label>

            <div class="toggle">
               <button class="buttondiv" id="m2" onclick="functionToggle(this.id)"> Ordini</button>
               <div class="domandeContainer">
                 <div class="card FAQ" id="ordini">
                    <label class="FAQh3">ORDINI E PREVENTIVI</label>
                    <div class="toggle">
                       <button id="o1" onclick="functionToggle(this.id)">COME POSSO EFFETTUARE UN ORDINE?</button>
                       <p>Per effettuare un ordine basta scegliere l'auto che più si preferisce presente sulla piattaforma e
                          richiedere un preventivo indicando
                          quali optional (per contratto o auto) si preferiscono. Una volta effettuata la richiesta di preventivo e
                          che quest'ultimo venga stipulato
                          da un nostro consulente specializzato sarà possibile confermarlo. Una volta confermato il preventivo, il
                          sistema creerà automaticamente
                          un ordine il quale potrà essere confermato.
                       </p>
                    </div>
                    <div class="toggle">
                       <button id="o2" onclick="functionToggle(this.id)">IN QUANTO TEMPO RICEVERÒ IL MIO PREVENTIVO?</button>
                       <p>I nostri consulenti lavorano appositamente per permettere agli utenti che intendono adoperare la nostra
                          piattaforma di usufruire di tutte
                          le funzionalità da loro dipendenti nel minor tempo possibile. Natualmente il tempo necessario per la
                          stipulazione di un preventivo è variabile,
                          ma normalmente si aggira tra le 24 e le 48 ore.
                       </p>
                    </div>
                    <div class="toggle">
                       <button id="o3" onclick="functionToggle(this.id)">COME POSSO ACCEDERE ALLA LISTA DEI MIEI ORDINI?</button>
                       <p>Accedi al tuo account e clicca su "ordini e preventivi": qui potrai vedere i dati relativi a tutte le
                          richieste di preventivo e gli ordini da te
                          effettuati in passato e a quelli in corso. Se vuoi invece controllare maggiori informazioni
                          sull'ordine/preventivo, clicca su “visualizza”.
                       </p>
                    </div>
                    <div class="toggle">
                      <button id="o4" onclick="functionToggle(this.id)">EFFETTUATE CONSEGNE ALL'ESTERO?</button>
                      <p>No, per ora ci limitiamo a spedire ordini solamente in Italia.</p>
                    </div>
                 </div>
               </div>
            </div>


            <div class="toggle">
              <button class="buttondiv" id="m3" onclick="functionToggle(this.id)">Pagamenti</button>
              <div class="domandeContainer">
                <div class="card FAQ" id="pagamenti">
                    <label class="FAQh3">PAGAMENTI</label>
                    <div class="toggle">
                       <button id="p1" onclick="functionToggle(this.id)">QUALI METODI DI PAGAMENTO ACCETTATE?</button>
                       <p>Accettiamo unicamente pagamenti tramite carte di credito (Visa, Mastercard, American Express,
                          Diners).
                       </p>
                    </div>
                    <div class="toggle">
                       <button id="p2" onclick="functionToggle(this.id)">PERCHÈ LA MIA CARTA DI CREDITO NON È STATA ACCETTATA?
                       </button>
                       <p>I principali motivi possono essere:<br>
                          • Hai superato il limite di credito della tua carta;<br>
                          • I dati richiesti per il pagamento non coincidono con quelli tua carta. Un semplice errore di battitura
                           può causare il rifiuto dell'operazione;<br>
                          • La tua carta è scaduta: controlla la data di scadenza, che trovi sulla carta stessa;<br>
                          Per ogni ulteriore informazione, la tua banca può darti tutte le informazioni necessarie sulla tua carta
                          e sulle possibilità di pagamento.</p>
                    </div>
                    <div class="toggle">
                       <button id="p3" onclick="functionToggle(this.id)">QUANDO SARÀ EFFETTUATO L’ADDEBITO SULLA MIA CARTA DI
                          CREDITO?
                       </button>
                       <p>L'importo relativo all'acquisto effettuato verrà addebitato sulla tua carta di credito al momento della
                          spedizione dell'ordine.<br>
                          ATTENZIONE: quello che viene visualizzato al momento dell’inserimento dell’ordine non è un prelevamento
                          di fondi ma solo l’autorizzazione
                          che viene richiesta, mentre l’importo viene effettivamente addebitato soltanto nel momento della
                          spedizione della merce.
                       </p>
                    </div>
                    <div class="toggle">
                       <button id="p4" onclick="functionToggle(this.id)">I MIEI PAGAMENTI SONO SICURI?</button>
                       <p>Certamente: sul nostro Portale i pagamenti sono e resteranno protetti mediante un avanzato sistema di
                          difesa dei dati in rete.
                          Il pagamento con carta di credito avviene su un server sicuro gestito da società terza, specializzata e
                          debitamente autorizzata
                          ai sensi delle leggi vigenti a svolgere tale servizio: pertanto, i dati relativi alla carta di credito
                          vengono processati esclusivamente
                          dal Gestore Pagamenti. EasyLease, quindi non avrà accesso ai dati relativi alle carte di credito, ma
                          gestirà solo i Dati dell'Utente.
                          Nessuna responsabilità potrà essere attribuita a EasyLease per qualsiasi utilizzo fraudolento da parte
                          di terzi dei dati relativi alla
                          carta di credito dell'Utente: in tale evenienza l'Utente dovrà contattare immediatamente il gestore
                          pagamenti ed eventualmente le autorità competenti.
                       </p>
                    </div>
                </div>
              </div>
            </div>
            <div class="toggle">
              <button class="buttondiv" id="m4" onclick="functionToggle(this.id)"> Registrazione</button>
              <div class="domandeContainer">
                <div class="card FAQ" id="registrazione">
                    <label class="FAQh3">REGISTRAZIONE</label>
                    <div class="toggle">
                        <button id="r1" onclick="functionToggle(this.id)">A COSA SERVE ISCRIVERSI?</button>
                        <p>L'iscrizione ti consente di essere riconosciuto dal sistema e di accedere alla tua Area personale del
                           sito, dalla quale potrai:<br>
                           • Effettuare richieste di preventivi.<br>
                           • Confermare i preventivi e precedere alla stipulazione di un ordine.<br>
                           • Visualizzare lo storico degli ordini e dei preventivi.<br>
                           • Modificare i tuoi dati di registrazione(in futuro).<br>
                        </p>
                    </div>
                    <div class="toggle">
                        <button id="r2" onclick="functionToggle(this.id)">COME POSSO ISCRIVERMI?</button>
                         <p>Puoi iscriverti cliccando "Registrati" in alto a destra. Ti sarà chiesto di inserire uil tuo nome,
                            cognome, una password e altri dati necessari.
                            È importante inserire un indirizzo e-mail valido perché servirà per notificarti la conferma
                            dell’ordine.
                         </p>
                    </div>
                    <div class="toggle">
                        <button id="r3" onclick="functionToggle(this.id)">L’ISCRIZIONE È GRATUITA?</button>
                        <p>Sì: non ti sarà addebitato alcun costo per esserti registrato.</p>
                    </div>
                    <div class="toggle">
                       <button id="r4" onclick="functionToggle(this.id)">DEVO COMUNICARVI LA MIA EMAIL: RICEVERÒ POSTA
                            INDESIDERATA?
                       </button>
                       <p>No! Riceverai soltanto e-mail riguardanti la gestione dei tuoi ordini.<br>
                          Se hai acconsentito al trattamento dei dati per finalità promozionali, riceverai la nostra newsletter
                          con le migliori promozioni.
                          In qualsiasi momento puoi cancellare l'iscrizione, eliminando l'autorizzazione dalla tua Area personale.
                       </p>
                    </div>
                    <div class="toggle">
                        <button id="r5" onclick="functionToggle(this.id)">I MIEI DATI SARANNO COMUNICATI AD ALTRE PERSONE?</button>
                        <p>No! I tuoi dati saranno utilizzati soltanto da la Feltrinelli e i suoi partner (quali corrieri espressi e
                           banche) per la gestione degli ordini.
                           In nessun caso saranno ceduti ad aziende terze ed estranee al tuo ordine.
                        </p>
                    </div>
                </div>
              </div>
            </div>


            <div class="toggle">
                  <button class="buttondiv" id="m1" onclick="functionToggle(this.id)"> Maggiori domande</button>
                  <div class="domandeContainer">
                     <div class="card FAQ" id="maggioriDomande">
                         <label class="FAQh3"> Di seguito sono riportate le domande che più interessano ai nostri clienti</label>
                         <div class="toggle">
                            <button id="md1" onclick="functionToggle(this.id)">QUANDO SARÀ EFFETTUATO L’ADDEBITO SULLA MIA CARTA DI
                                CREDITO O SUL MIO CONTO PAYPAL?
                            </button>
                            <p>L'importo relativo all'acquisto effettuato verrà addebitato sulla tua carta di credito al momento al
                               momento della convalida dell'ordine. <br>
                               ATTENZIONE: quello che viene visualizzato al momento dell’inserimento dell’ordine non è un prelevamento
                               di fondi ma solo
                               l’autorizzazione che viene richiesta, mentre l’importo viene effettivamente addebitato soltanto al
                               momento della convalida dell'ordine
                               da parte del consulente.
                            </p>
                         </div>
                         <div class="toggle">
                             <button id="md2" onclick="functionToggle(this.id)">VERRÒ AVVERTITO IN CASO DI CONVALIDA DI UN ORDINE?</button>
                             <p>Sì, riceverai una mail nel momento in cui il tuo ordine sarà convalidato. </p>
                         </div>
                         <div class="toggle">
                             <button id="md3" onclick="functionToggle(this.id)">COME SARANNO TRATTATI I MIEI DATI?</button>
                             <p>BookStore Srl garantisce la massima riservatezza sulle informazioni che hai rilasciato al momento della
                                registrazione: sono infatti
                                inviate in una connessione protetta con tecnologia SSL. <br> La sicurezza del sito è garantita e
                                certificata da Verisign società leader
                                mondiale tra i fornitori di servizi per la sicurezza online. Le informazioni facoltative che richiediamo
                                all'atto della registrazione sono
                                utilizzate per migliorare il servizio e offrirti promozioni basate sui tuoi interessi.
                             </p>
                         </div>
                     </div>
                  </div>
            </div>


            <div class="TitleDiv">
                <img class="imgIcon" src="img/misc/auto_icon.png">
                <label class="FAQcloud">Ciao!<br>sono Usain hai<br>bisogno di me?</label><br>
                <label class="FAQsubtitle">Hai domande? clicca e trova quella giusta per te!</label>
            </div>
        </div>
        <%@include file="/fragments/footer.jsp" %>
    </body>
</html>
<script>
  function functionToggle(id) {
    console.log(id);
    $("#" + id).siblings().toggle(300, "linear");
  }
  $(document).ready(function() {
    $("a").on('click', function(event) {
      if (this.hash !== "") {
        event.preventDefault();
        var hash = this.hash;
        $('html, body').animate({
          scrollTop: $(hash).offset().top
        }, 300, function() {
          window.location.hash = hash;
        });
      }
    });
  });
</script>
