

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Name From Session Page</title>
    </head>
    <body>
        <h1>Well done ! Here is the name from the session</h1>
		<s:property value="#session.currentUser" /> <br/>
	 </body>
</html>
