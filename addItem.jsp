<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Item</title>
   
</head>
<body>
    <header>
        <div class="header-content">
            <h1>Luxe</h1>
            <div class="logged-in">
                Logged in as <strong><s:property value="#session.currentUser"/></strong>
            </div>
        </div>
    </header>

  <nav>
    <div class="dropdown" onclick="toggleDropdown('bidsDropdown', event)">
        <span class="tbar">  <img src="images/triple bar.png" alt="Triple Bar Icon" ></span>
        <div id="bidsDropdown" class="dropdown-content">
            <s:a href="viewMyBids.jsp">View My Bids</s:a>
            <s:a href="viewAllBids.jsp">View All Bids</s:a>
        </div>
    </div>

    <div class="dropdown profile" onclick="toggleDropdown('profileDropdown', event)">
      <span class="profileIcon">  <img src="images/profile.png" alt="Profile Icon" ></span>
        <div id="profileDropdown" class="dropdown-content">
            <s:a href="viewMyProfile.jsp">View My Profile</s:a>
            <s:a href="viewAllUsers.jsp">View All Users</s:a>
            <s:a href="logout">Logout</s:a>
        </div>
    </div>
</nav>

    <div>
        <h2>Add Item for Sale</h2>
        <s:form action="addItem" method="post">
            <s:textfield name="itemName"  label="Item Name" required="true"/>
            <s:textfield name="description"  label="Description" required="true"/>
            <s:textfield name="startPrice" id="startPrice" label="Starting Price €" type="number" min="0" step="any" required="true"/>

            <s:submit value="Add Item" />
        </s:form>
    </div>


</body>
</html>