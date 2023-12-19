

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/loginStyle.css">
     <script>
        function validateLoginForm() {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var isValid = true;

            // Clear previous error messages
            clearErrors();

            if (!username.trim()) {
                displayError("usernameError", "You must enter a username.");
                isValid = false;
            }

            if (!password.trim()) {
                displayError("passwordError", "You must enter a password.");
                isValid = false;
            }

            return isValid;
        }

        function displayError(elementId, errorMessage) {
            var errorElement = document.getElementById(elementId);
            errorElement.innerHTML = errorMessage;
            errorElement.style.display = "block";
            errorElement.style.backgroundColor = "#ffcccc"; // Light red background color
        }

        function clearErrors() {
            var errorElements = document.getElementsByClassName("error");
            for (var i = 0; i < errorElements.length; i++) {
                errorElements[i].innerHTML = "";
                errorElements[i].style.display = "none";
            }
        }
    </script>
    </head>
    <body>
     
     <div class="container">
        <h1>Login</h1>

      Login to view your profile

        <s:form action="loginUser" method="post"  onsubmit="return validateLoginForm();" validate="false">
           
            <s:textfield name="username" id="username" label="Username" required="true"/>
            <div id="usernameError" class="error"></div>

            <s:password name="password" id="password" label="Password" required="true"/>
            <div id="passwordError" class="error"></div>

            <s:submit value="Login"/>
        </s:form>

        
         <p>Don't have an account?<a href="<s:url action="reg"/>"> Register</a></p>
    </div>
		
    </body>
</html>
