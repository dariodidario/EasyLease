function readURL(input) {
    if (input.files && input.files[0]) {
        for(var i=0;i<input.files.length;i++) {
            var reader = new FileReader();
            var img='#img_carL';
            reader.onload = function (e) {
                $(img)
                    .attr('src', e.target.result)
            };

            reader.readAsDataURL(input.files[i]);

        }
    }
}

var vis = 1000;
window.confirm = function(message) {
    var w = 400;
    var h = 350;
    var l = Math.floor((screen.width-w)/2);
    var t = Math.floor((screen.height-h)/2);
    var a = document.createElement('div');
    var input = document.createElement('input');
    var y = document.createElement('button');
    var n = document.createElement('button');

    //regole di stile CSS
    a.style.cssText = "width:"+w+"; height:"+h+"; border:1px solid #bbb; border-radius:5px; padding:10px; background:#9b334e; box-shadow:0px 0px 8px #0006; position:fixed; top:"+t+"; left:"+l+"; margin:auto; font-family: \"Arial\", sans-serif; color:black;z-index:"+ vis+ ";";
    input.style.cssText = "width:100%; margin-top:100px;";
    input.placeholder = "new "+message;
    if(message=="brand"){
        input.type="text";
        var x = 'autoBrand';
        input.setAttribute('list',x);
    }else if(message=="model"){
        input.type="text";
    }else if(message=="price"||message=="avg_consumption"){
        input.type="number";
        input.min="0";
        input.step="0.01";
    }else if(message=="car_type"){
        input.type="text"
        var x = 'autoTipologia';
        input.setAttribute('list',x);
    }else if(message=="doors"){
        input.type="number";
        input.min="0";
        var x = 'autoPorte';
        input.setAttribute('list',x);
    }else if(message=="transmission"){
        input.type="text";
        var x = 'autoCambio';
        input.setAttribute('list',x);
    }else if(message=="emission_class"){
        input.type="text";
        var x = 'Emision_class';
        input.setAttribute('list',x);
    }else if(message=="co2_emissions"||message=="capacity"||message=="horse_power"){
        input.type="number";
        input.min="0";
    }else if(message=="power_supply"){
        input.type="text";
        var x = 'autoPower';
        input.setAttribute('list',x);
    }else if(message=="img_car"){
        input.type="file";
        input.accept=".jpg,.png,.jpeg";
        input.maxLength="255";
        input.value="";
    }
    //buttons style
    y.style.cssText = "position:absolute; bottom:10; right:20px; width:40%; margin:2px; margin-bottom:10px; clear:both; border-bottom: 2px solid #dec717; background-color: #800000;";
    n.style.cssText = "position:absolute; bottom:10; left:20px; width:40%; margin:2px; margin-bottom:10px; clear:both; border-bottom: 2px solid #dec717; background-color: #800000;";
    a.innerHTML = "<b>Modifica "+message+"</b><br>";
    y.innerHTML = "Applica";
    n.innerHTML = "Annulla";
    document.body.appendChild(a);
    a.appendChild(input);
    a.appendChild(y);
    a.appendChild(n);
    vis--;

// case YES
    y.addEventListener("click", function(e) {
            var s=input.value;
            if(message=="img_car"){
                document.getElementById(message).setAttribute("value",s);
                readURL(input);
            }else {
                document.getElementById(message).setAttribute("value", s);

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