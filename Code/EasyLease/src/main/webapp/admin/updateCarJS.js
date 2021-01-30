function readURL(input) {
    if (input.files && input.files[0]) {
        var imgNew = document.getElementById('image_path');
        for(var i=0;i<input.files.length;i++) {
            var reader = new FileReader();
            var img='#img_carL';
            reader.onload = function (e) {
                $(img)
                    .attr('src', e.target.result)
            };

            reader.readAsDataURL(input.files[i]);

        }

        imgNew.type="file";
        imgNew.accept=".jpg";
        imgNew.maxLength="255";
        imgNew.value="";
        imgNew.name="img_car_Update";
        imgNew.id="img_car";
        imgNew.style.cssText="display:none;";
        document.getElementById('img_car').replaceWith(imgNew);
    }
}

var vis = 1000;
window.confirm = function(message) {
    var w = 400;
    var h = 350;
    var mt=100;
    var bm=20;
    if(screen.width<1024){
        w=200;
        h=150;
        mt=20;
        bm=10;
    }
    var l = Math.floor((screen.width-w)/2);
    var t = Math.floor((screen.height-h)/2);
    var a = document.createElement('div');
    var input = document.createElement('input');
    var y = document.createElement('button');
    var n = document.createElement('button');

    //regole di stile CSS
    a.style.cssText = "width:"+w+"; height:"+h+"; border:1px solid #bbb; border-radius:5px; padding:10px; background:#9b334e; box-shadow:0px 0px 8px #0006; position:fixed; top:"+t+"; left:"+l+"; margin:auto; font-family: \"Arial\", sans-serif; color:black;z-index:"+ vis+ ";";
    input.style.cssText = "width:100%; margin-top:"+mt+"px;";
    input.placeholder = "new "+message;
    input.required= true ;
    if(message=="brand"){
        input.type="text";
        var x = 'autoBrand';
        input.setAttribute('list',x);
        input.pattern='/^[A-Za-z0-9àèéìòù]+$/';
        input.id=message+"_update";
    }else if(message=="model"){
        input.type="text";
        input.pattern='/^[A-Za-z0-9àèéìòù]+$/';
        input.id=message+"_update";
    }else if(message=="price"||message=="avg_consumption"){
        input.type="number";
        input.min="1";
        input.step="0.01";
        input.id=message+"_update";
    }else if(message=="car_type"){
        input.type="text"
        var x = 'autoTipologia';
        input.setAttribute('list',x);
        input.pattern='/^[a-zA-Z]+$/';
        input.id=message+"_update";
    }else if(message=="doors"){
        input.type="number";
        input.min="1";
        var x = 'autoPorte';
        input.setAttribute('list',x);
        input.id=message+"_update";
    }else if(message=="transmission"){
        input.type="text";
        var x = 'autoCambio';
        input.setAttribute('list',x);
        input.pattern='/^[a-zA-Z]+$/';
        input.id=message+"_update";
    }else if(message=="emission_class"){
        input.type="text";
        var x = 'Emision_class';
        input.setAttribute('list',x);
        input.pattern='/^Euro [1-6]{1}$/';
        input.id=message+"_update";
    }else if(message=="co2_emissions"||message=="capacity"||message=="horse_power"){
        input.type="number";
        input.min="1";
        input.id=message+"_update";
    }else if(message=="power_supply"){
        input.type="text";
        var x = 'autoPower';
        input.setAttribute('list',x);
        input.pattern='/^[a-zA-Z]+$/';
        input.id=message+"_update";
    }else if(message=="img_car"){
        input.type="file";
        input.accept=".jpg";
        input.maxLength="255";
        input.value="";
        input.name="img_car_Update";
        input.id="img_car";
    }
    //buttons style
    y.style.cssText = "position:absolute; bottom:10; right:"+bm+"px; width:40%; margin:2px; margin-bottom:10px; clear:both; border-bottom: 2px solid #dec717; background-color: #800000;";
    n.style.cssText = "position:absolute; bottom:10; left:"+bm+"px; width:40%; margin:2px; margin-bottom:10px; clear:both; border-bottom: 2px solid #dec717; background-color: #800000;";
    a.innerHTML = "<b>Modifica "+message+"</b><br>";
    y.innerHTML = "Applica";
    y.id="button_updateSi";
    n.innerHTML = "Annulla";
    n.id="button_updateNo";
    document.body.appendChild(a);
    a.appendChild(input);
    a.appendChild(y);
    a.appendChild(n);
    vis--;

// case YES
    y.addEventListener("click", function(e) {
            var s=input.value;
            if(message=="img_car"){
                //document.getElementById(message).setAttribute("value",s);
                input.style.cssText="display:none;";
                document.getElementById(message).replaceWith(input);
                readURL(input);
            }else {
                document.getElementById(message).setAttribute("value", s);
                document.getElementById(message).style.cssText= "color:white;"
                document.getElementById(message + "L").replaceWith(s);
            }
            a.remove();
        }
    )
    //case NO
    n.addEventListener("click", function(e,resp) {
            a.remove();

        }
    )
}
function confermaDelete() {
    var richiesta=window.alert("Sicuro di voler eliminare?");
    return richiesta;
}
function changeIMG(){
    document.getElementById('image_path').style.cssText="display:inline-block";
}