<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All Items</title>
    <link rel="stylesheet" type="text/css" href="css/allItemsStyle.css">
</head>
<body>
  

     <div>
        <h2>All Items For Sale</h2>
        
        <s:if test="#allItems.empty">
            <p>No Items available.</p>
        </s:if>

        <div class="card-container">
            <s:iterator value="allItems">
                <div class="card">
                    <h3><s:property value="itemName" /></h3>
                    <p><s:property value="description" /></p>
                    <p>Start Price: €<s:property value="startPrice" /></p>
                    <p>Current Bid: €<s:property value="currentBid" /></p>
                    <s:form action="makeABid" method="post">
                        <s:hidden name="itemId" value="%{itemId}" />
                        <s:submit value="Make A Bid" />
                    </s:form>
                </div>
            </s:iterator>
        </div>
    </div>
</body>
</html>