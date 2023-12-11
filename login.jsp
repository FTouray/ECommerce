

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="css/loginStyle.css">

    </head>
    <body>
     
     <div class="container">
        <h1>Login</h1>

      Login to view your profile

        <s:form action="loginUser" method="post">
           
            <s:textfield name="username" id="username" label="Username" required="true"/>

            <s:password name="password" id="password" label="Password" required="true"/>
        
            <s:submit value="Login"/>
        </s:form>

        <p>Don't have an account? <a href="login.jsp">Login</a></p>
    </div>
		
    </body>
</html>
