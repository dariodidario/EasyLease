function controllo(){
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
  var controllo=true;
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
