

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link rel="stylesheet" type="text/css" href="css/registrationStyle.css">
<script src="script/actions.js"></script>
         <script>
        function checkPasswordMatch() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

            if (password !== confirmPassword) {
                document.getElementById("passwordError").style.display = "block";
                return false;
            } else {
                document.getElementById("passwordError").style.display = "none";
                return true;
            }
        }
    </script>
    </head>
    <body>
     
     <div class="container">
        <h1>Registration</h1>

      Enter your details below to register

 
              <s:if test="actionErrors.size() > 0">
        <div class="error-box">
            <s:actionerror />
        </div>
    </s:if>

     <s:if test="actionMessages.size() > 0">
    <div class="action-box">
            <s:actionmessage />
        </div>
</s:if>
      


        <s:form action="registerUser" method="post" onsubmit="return checkPasswordMatch() && validateForm();"  validate="false">
           
            <s:textfield name="firstName" id="firstName" label="First Name"/>
             <div id="firstNameError" class="error"></div>

            <s:textfield name="lastName" id="lastName" label="Last Name"/>
            <div id="lastNameError" class="error"></div>

            <s:textfield name="email" id="email" label="email"/>
            <div id="emailError" class="error"></div>

            <s:textfield name="username" id="username" label="username"/>
            <div id="usernameError" class="error"></div>
           

            <s:password name="password" id="password" label="Password"/>
             <div id="passwordError" class="error"></div>
            
            <s:password name="confirmPassword" id="confirmPassword" label="Confirm Password" />
            <div id="confirmPasswordError" class="error"></div>

            <div id="passwordError">Password does not match</div>

            <s:submit value="Register" />
        </s:form>

        <p>Already have an account?<a href="<s:url action="log"/>"> Login</a></p>
    </div>
		 
        <script>
        function validateForm() {
            var firstName = document.getElementById("firstName").value;
            var lastName = document.getElementById("lastName").value;
            var email = document.getElementById("email").value;
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var isValid = true;

            // Clear previous error messages
            clearErrors();

            if (!firstName.trim()) {
                displayError("firstNameError", "You must enter your first name.");
                isValid = false;
            }

            if (!lastName.trim()) {
                displayError("lastNameError", "You must enter your last name.");
                isValid = false;
            }

            if (!email.trim()) {
                displayError("emailError", "You must enter your email.");
                isValid = false;
            }

            if (!username.trim()) {
                displayError("usernameError", "You must enter a username.");
                isValid = false;
            }

            if (!password.trim()) {
                displayError("passwordError", "You must enter a password.");
                isValid = false;
            }

            if (!confirmPassword.trim()) {
                displayError("confirmPasswordError", "You must confirm your password.");
                isValid = false;
            }

           

            return isValid;
        }

        function displayError(elementId, errorMessage) {
            var errorElement = document.getElementById(elementId);
            document.getElementById(elementId).innerHTML = errorMessage;
            document.getElementById(elementId).style.display = "block";
            document.getElementById(elementId).style.backgroundColor = "#ffcccc";
        }

        function clearErrors() {
            var errorElements = document.getElementsByClassName("error");
            for (var i = 0; i < errorElements.length; i++) {
                errorElements[i].innerHTML = "";
                errorElements[i].style.display = "none";
            }
        }

    </script> 
    </body>
</html>