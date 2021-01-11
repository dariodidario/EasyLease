var confirm_valid;
function checkEmail(input){
    var email=input.value;
    var email_valid =  /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{1,2})+$/;
    if(!email_valid.test(email)){
        document.getElementById("advisor_email").style.background="#dec717";
    }else{
        document.getElementById("advisor_email").style.background="#9b334e";
        document.getElementById("email_valid").setAttribute("value","true");
    }
}
function checkPassword(input){
    var password=input.value;
    var password_valid = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})/;
    if(!password_valid.test(password)){
        document.getElementById("advisor_password").style.background="#dec717";
    }else{
        confirm_valid=password;
        document.getElementById("advisor_password").style.background="#9b334e";
        document.getElementById("password_valid").setAttribute("value","true");
    }
}
function checkConfirm(input){
    var confirm=input.value;
    var label=document.createElement('label');
    label.id="error";
    if(confirm!=confirm_valid){
        document.getElementById("advisor_confirm_password").style.background="#dec717";
    }else{
        document.getElementById("advisor_confirm_password").style.background="#9b334e";
        document.getElementById("confirm_valid").setAttribute("value","true");
    }
}
function checkDate(input){
    var date=Date.parse(input.value);
    var date_valid=new Date();
    if(date<=date_valid){
        document.getElementById("advisor_date").style.background="#9b334e";
        document.getElementById("date_valid").setAttribute("value","true");
    }else{
        document.getElementById("advisor_date").style.background="#dec717";
    }
}