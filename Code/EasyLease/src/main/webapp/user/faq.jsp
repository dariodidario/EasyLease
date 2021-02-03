<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>FAQ</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/user/faq.css"/>
</head>
<body>
<%@include file="/fragments/header.jsp" %>
<div class="wrapper">
  <div class="container">

    <div class='nested-accordion'>
      <h3>Ordini e preventivi</h3>
      <div class='comment'>
        <div class='nested-accordion'>
          <h3>Come posso effettuare un ordine?</h3>
          <div class='comment'>Per effettuare un ordine basta scegliere l'auto che più si preferisce presente sulla
            piattaforma e
            richiedere un preventivo indicando
            quali optional (per contratto o auto) si preferiscono. Una volta effettuata la richiesta di preventivo e
            che quest'ultimo venga stipulato
            da un nostro consulente specializzato sarà possibile confermarlo. Una volta confermato il preventivo, il
            sistema creerà automaticamente
            un ordine il quale potrà essere confermato.
          </div>
        </div>
        <div class='nested-accordion'>
          <h3>In quanto tempo riceverò il mio preventivo?</h3>
          <div class='comment'>I nostri consulenti lavorano appositamente per permettere agli utenti che intendono
            adoperare la nostra
            piattaforma di usufruire di tutte
            le funzionalità da loro dipendenti nel minor tempo possibile. Natualmente il tempo necessario per la
            stipulazione di un preventivo è variabile,
            ma normalmente si aggira tra le 24 e le 48 ore.
          </div>
        </div>
        <div class='nested-accordion'>
          <h3>Come posso accedere alla lista dei miei ordini?</h3>
          <div class='comment'>Accedi al tuo account e clicca su "ordini e preventivi": qui potrai vedere i dati
            relativi
            a tutte le
            richieste di preventivo e gli ordini da te
            effettuati in passato e a quelli in corso. Se vuoi invece controllare maggiori informazioni
            sull'ordine/preventivo, clicca su “visualizza”.
          </div>
        </div>
        <div class='nested-accordion'>
          <h3>Effettuate consegne all'estero?</h3>
          <div class='comment'>No, per ora ci limitiamo a spedire ordini solamente in Italia.</div>
        </div>
      </div>

      <div class='nested-accordion'>
        <h3>Pagamenti</h3>
        <div class='comment'>
          <div class='nested-accordion'>
            <h3>Quali metodi di pagamento accettate?</h3>
            <div class='comment'>Accettiamo unicamente pagamenti tramite carte di credito (Visa, Mastercard, American
              Express,
              Diners).
            </div>
          </div>
          <div class='nested-accordion'>
            <h3>Perchè la mia carta di credito non è stata accettata?</h3>
            <div class='comment'>I principali motivi possono essere:<br>
              • Hai superato il limite di credito della tua carta;<br>
              • I dati richiesti per il pagamento non coincidono con quelli tua carta. Un semplice errore di battitura
              può causare il rifiuto dell'operazione;<br>
              • La tua carta è scaduta: controlla la data di scadenza, che trovi sulla carta stessa;<br>
              Per ogni ulteriore informazione, la tua banca può darti tutte le informazioni necessarie sulla tua carta
              e sulle possibilità di pagamento.
            </div>
          </div>
          <div class='nested-accordion'>
            <h3>Quando sarà effettuato l'addebito sulla mia carta di credito?</h3>
            <div class='comment'>L'importo relativo all'acquisto effettuato verrà addebitato sulla tua carta di credito
              al
              momento della
              spedizione dell'ordine.<br>
              ATTENZIONE: quello che viene visualizzato al momento dell’inserimento dell’ordine non è un prelevamento
              di fondi ma solo l’autorizzazione
              che viene richiesta, mentre l’importo viene effettivamente addebitato soltanto nel momento della
              spedizione della merce.
            </div>
          </div>
          <div class='nested-accordion'>
            <h3>I miei pagamenti sono sicuri?</h3>
            <div class='comment'>Certamente: sul nostro Portale i pagamenti sono e resteranno protetti mediante un
              avanzato sistema di
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
            </div>
          </div>
        </div>

        <div class='nested-accordion'>
          <h3>Registrazione</h3>
          <div class='comment'>
            <div class='nested-accordion'>
              <h3>A cosa serve iscriversi?</h3>
              <div class='comment'>L'iscrizione ti consente di essere riconosciuto dal sistema e di accedere alla tua
                Area
                personale del
                sito, dalla quale potrai:<br>
                • Effettuare richieste di preventivi.<br>
                • Confermare i preventivi e precedere alla stipulazione di un ordine.<br>
                • Visualizzare lo storico degli ordini e dei preventivi.<br>
                • Modificare i tuoi dati di registrazione(in futuro).
              </div>
            </div>
            <div class='nested-accordion'>
              <h3>Come posso iscrivermi?</h3>
              <div class='comment'>Puoi iscriverti cliccando "Registrati" in alto a destra. Ti sarà chiesto di inserire
                uil tuo nome,
                cognome, una password e altri dati necessari.
                È importante inserire un indirizzo e-mail valido perché servirà per notificarti la conferma
                dell’ordine.
              </div>
            </div>
            <div class='nested-accordion'>
              <h3>L'iscrizione è gratuita?</h3>
              <div class='comment'>Sì: non ti sarà addebitato alcun costo per esserti registrato.</div>
            </div>
            <div class='nested-accordion'>
              <h3>Devo comunicarvi la mia email: riceverò posta indesiderata?</h3>
              <div class='comment'>No! Riceverai soltanto e-mail riguardanti la gestione dei tuoi ordini.<br>
                Se hai acconsentito al trattamento dei dati per finalità promozionali, riceverai la nostra newsletter
                con le migliori promozioni.
                In qualsiasi momento puoi cancellare l'iscrizione, eliminando l'autorizzazione dalla tua Area personale.
              </div>
            </div>
            <div class='nested-accordion'>
              <h3>I miei dati saranno comunicati ad altre persone?</h3>
              <div class='comment'>No! I tuoi dati saranno utilizzati soltanto da la Feltrinelli e i suoi partner (quali
                corrieri espressi e
                banche) per la gestione degli ordini.
                In nessun caso saranno ceduti ad aziende terze ed estranee al tuo ordine.
              </div>
            </div>
          </div>
        </div>
        <div class='nested-accordion'>
          <h3>Maggiori domande</h3>
          <div class='comment'>
            <div class='nested-accordion'>
              <h3>Quando sarà effettuato l'addebito sulla mia carta di credito?</h3>
              <div class='comment'>L'importo relativo all'acquisto effettuato verrà addebitato sulla tua carta di
                credito
                al momento al
                momento della convalida dell'ordine. <br>
                ATTENZIONE: quello che viene visualizzato al momento dell’inserimento dell’ordine non è un prelevamento
                di fondi ma solo
                l’autorizzazione che viene richiesta, mentre l’importo viene effettivamente addebitato soltanto al
                momento della convalida dell'ordine
                da parte del consulente.
              </div>
            </div>
            <div class='nested-accordion'>
              <h3>Verrò avvertito in caso di convalida di un ordine?</h3>
              <div class='comment'>Sì, riceverai una mail nel momento in cui il tuo ordine sarà convalidato.</div>
            </div>
            <div class='nested-accordion'>
              <h3>Come saranno trattati i miei dati?</h3>
              <div class='comment'>Sì: non ti sarà addebitato alcun costo per esserti registrato.</div>
            </div>
            <div class='nested-accordion'>
              <h3>Come saranno trattati i miei dati?</h3>
              <div class='comment'>EasyLease garantisce la massima riservatezza sulle informazioni che hai rilasciato al
                momento della
                registrazione: sono infatti
                inviate in una connessione protetta con tecnologia SSL. <br> La sicurezza del sito è garantita e
                certificata da Verisign società leader
                mondiale tra i fornitori di servizi per la sicurezza online. Le informazioni facoltative che richiediamo
                all'atto della registrazione sono
                utilizzate per migliorare il servizio e offrirti promozioni basate sui tuoi interessi.
              </div>
            </div>
            <div class='nested-accordion'>
              <h3>I miei dati saranno comunicati ad altre persone?</h3>
              <div class='comment'>No! I tuoi dati saranno utilizzati soltanto da la Feltrinelli e i suoi partner (quali
                corrieri espressi e
                banche) per la gestione degli ordini.
                In nessun caso saranno ceduti ad aziende terze ed estranee al tuo ordine.
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="push"></div>
</div>
<div class="footer-fixed">
  <%@include file="/fragments/footer.jsp" %>
</div>
<script>
  $('.nested-accordion').find('.comment').slideUp();
  $('.nested-accordion').find('h3').click(function() {
    $(this).next('.comment').slideToggle(100);
    $(this).toggleClass('selected');
  });
  $('.nested-accordion').find('h3').each(function() {
    $(this).next('.comment').slideToggle(100);
    $(this).toggleClass('selected');
  });
</script>
</body>
</html>
