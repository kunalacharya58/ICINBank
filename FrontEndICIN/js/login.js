
function validateUserName(){
    var validRegEx = /^[0-9a-zA-Z]+$/;
    var userName = document.getElementById("lUserId");
    if(userName.value == ""){
        document.getElementById("usernameError").innerHTML = "";
        return false;
    }
    if(!userName.value.match(validRegEx)){
        document.getElementById("usernameError").innerHTML ="Special Characters not allowed";
        return false;
    }
    
    document.getElementById("usernameError").innerHTML = "";
    return true;
    
    
}

function validatePassword(){

    var errorMessage ="";
    var password = document.getElementById("lpass");
    if(password.value.length < 6 ){
        
        errorMessage = errorMessage.concat("<li>6 characters");
    }

    // if(!password.value.match(/[A-Z]/) ){
        
    //     errorMessage = errorMessage.concat("<li>one uppercase character");
    // }
    // if(!password.value.match(/[a-z]/)){
        
    //     errorMessage = errorMessage.concat("<li>one lowercase character");
    // }
    // if(!password.value.match(/[^0-9a-zA-Z\s]/)){
        
    //     errorMessage = errorMessage.concat("<li>one special characters");
    // }
    // if(!password.value.match(/[0-9]/)){
        
    //     errorMessage = errorMessage.concat("<li>atleast one number");
    // }
    
    if(password.value ==="")
        errorMessage = "";
    document.getElementById("passwordError").innerHTML = errorMessage;
    if(errorMessage === "")
        return true;
    else
        return false;
}

function formValidate(){
    if(!validateUserName())
        document.getElementById("lUserId").focus();
    else if(!validatePassword())
        document.getElementById("lpass").focus();
    else{

    // Process the Valid Inputs 

    }
}