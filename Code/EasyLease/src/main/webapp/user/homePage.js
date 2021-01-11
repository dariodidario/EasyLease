function funct() {
  var t=document.getElementById("TypeList");
  var tipologia=t.options[t.selectedIndex].value;
  var m=document.getElementById("BrandList");
  var marca=m.options[m.selectedIndex].value;
  $.ajax({
    type : 'GET',
    headers : {
      Accept : "application/json; charset=utf-8",
      "Content-Type" : "application/json; charset=utf-8"
    },
    url : contextPath+"/ResearchServlet?tipologia="+tipologia+"&marca="+marca,
    success : function(data) {
      $("#BrandList option").remove(); // Remove all <option> child tags.
      $("#ModelList option").remove(); // Remove all <option> child tags.
      var flag = false;
      var list = $.parseJSON(data);
      for (var i=0; i<list.length; i++){
        if(list[i]==="STOP"){
          flag=true;
          $("#BrandList").append( // Append an object to the inside of the select box
              $("<option></option>") // Yes you can do this.
                  .text("Marca").val("0")
          );
        }
        if (!flag) {
          $("#ModelList").append( // Append an object to the inside of the select box
              $("<option></option>") // Yes you can do this.
                  .text(list[i]).val(list[i])
          );}
        else if (flag && list[i] !== "STOP") {
          $("#BrandList").append( // Append an object to the inside of the select box
              $("<option></option>") // Yes you can do this.
                  .text(list[i]).val(list[i])
          );
        }
      };
    },
    error: function() {
      alert("ERROR");
    }
  });
}