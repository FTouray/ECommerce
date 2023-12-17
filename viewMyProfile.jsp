<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View My Profile</title>
    <link rel="stylesheet" type="text/css" href="css/profileStyle.css">
</head>
<body>
    

    <div class="profileDetails">
        <h2>My Profile Information</h2>
             <p><strong>First Name:</strong> <s:property value="myUser.firstName" /></p>
            <p><strong>Last Name:</strong> <s:property value="myUser.lastName" /></p>
            <p><strong>Username:</strong> <s:property value="myUser.username" /></p>
            <p><strong>Email:</strong> <s:property value="myUser.email" /></p>
    </div>

   <!--Bidding info-->
   <!--Items they are selling-->



</body>
</html>
