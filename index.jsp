

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link rel="stylesheet" type="text/css" href="css/registrationStyle.css">

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

        <s:form action="registerUser" method="post" onsubmit="return checkPasswordMatch();">
           
            <s:textfield name="firstName" id="firstName" label="First Name" required="true"/>

            <s:textfield name="lastName" id="lastName" label="Last Name" required="true"/>

            <s:textfield name="email" id="email" label="email" required="true"/>

            <s:password name="password" id="password" label="Password" required="true"/>
            
            <s:password name="confirmPassword" id="confirmPassword" label="Confirm Password" required="true"/>
            
            <div id="passwordError">Password does not match</div>

            <input type="submit" value="Register" />
        </s:form>
    </div>
		
    </body>
</html>
