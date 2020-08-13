function validateUserName(){
    var validRegEx = /^[0-9a-zA-Z]+$/;
    var userName = document.getElementById("rUserId");
    if(userName.value == ""){
        document.getElementById("userIdError").innerHTML = "";
        return false;
    }
    if(!userName.value.match(validRegEx)){
        document.getElementById("userIdError").innerHTML ="<li>Special Characters not allowed";
        return false;
    }
    document.getElementById("userIdError").innerHTML = "";
    return true;

    
    
}

function validateEmail(){
    var email = document.getElementById("rEmail");
    
    if(email.value === ""){
        document.getElementById("emailError").innerHTML = "";
        return false;
    }
    if(!email.value.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/)){
        document.getElementById("emailError").innerHTML = "<li>invalid Email"
        return false;
    }
    document.getElementById("emailError").innerHTML = "";
    return true;
}

function validateContactNumber(){
   
    var errorMessage ="";
    var contactNumber = document.getElementById("rNumber");
    if(!contactNumber.value.match(/^[0-9]/)){
        errorMessage = "<li> Only numericals values";
    }
    if(contactNumber.value.length < 10)
        errorMessage = errorMessage.concat("<li> 10 digits required");
    
    if(contactNumber.value ==="")
        errorMessage = "";
    document.getElementById("contactNumberError").innerHTML = errorMessage;
    if(errorMessage === "")
        return true;
    else
        return false;
}

$(document).ready(function () {
    //called when key is pressed in textbox
    $("#rNumber").keypress(function (e) {
       //if the letter is not digit then display error and don't type anything
       if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
          //display error message
          $("#errmsg").html("Digits Only").show().fadeOut("slow");
                 return false;
      }
     });
  });


function validatePassword(){

    var errorMessage ="";
    var password = document.getElementById("rPass");
    if(password.value.length < 6 ){
        errorMessage = errorMessage.concat("<li>6 characters");
    }
    if(!password.value.match(/[A-Z]/) ){
        errorMessage = errorMessage.concat("<li>one uppercase character");
    }
    if(!password.value.match(/[a-z]/)){
        errorMessage = errorMessage.concat("<li>one lowercase character");
    }
    if(!password.value.match(/[^0-9a-zA-Z\s]/)){
        errorMessage = errorMessage.concat("<li>one special characters");
    }
    if(!password.value.match(/[0-9]/)){
        errorMessage = errorMessage.concat("<li>atleast one number");
    }
    if(password.value ===""){
        errorMessage = "";
    }
    document.getElementById("passwordError").innerHTML = errorMessage;
    if(errorMessage === "")
        return true;
    else
        return false;
}

function validateConfirmPassword(){
    var password = document.getElementById("rPass");
    var confirmPassword = document.getElementById("rConfPass");
    if(!(password.value === confirmPassword.value)){
        document.getElementById("confirmPasswordError").innerHTML = "Passwords do not match"
        return false;
    }
    document.getElementById("confirmPasswordError").innerHTML = "";
    return true;
}

function formValidate(){
    if(!validateUserName())
        document.getElementById("rUserId").focus();
    else if(!validateEmail())
        document.getElementById("rEmail").focus();
    else if(!validateContactNumber())
        document.getElementById("rNumber").focus();
    else if(!validatePassword())
        document.getElementById("rPass").focus();
    else if(!validateConfirmPassword())
        document.getElementById("rConfPass").focus();

}
