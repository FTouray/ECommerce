<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View My Bids</title>
    <link rel="stylesheet" type="text/css" href="css/allItemsStyle.css"/>
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
    <a href="<s:url action="navigation"/>" class="home-icon"> 
        <img src="images/home.png" alt="Home Icon">
    </a>

      <div class="dropdown" onclick="toggleDropdown('bidsDropdown', event)">
        <span class="tbar">  <img src="images/triple bar.png" alt="Triple Bar Icon" ></span>
        <div id="bidsDropdown" class="dropdown-content">
           <a href="<s:url action="viewMyBids"/>">View My Bids</a>
            <a href="<s:url action="viewAllBids"/>">View All Bids</a>
        </div>
    </div>

     
    <div class="add-items-button">
        <a href="addItem.jsp">Add Items</a>
    </div>

    <div class="dropdown profile" onclick="toggleDropdown('profileDropdown', event)">
      <span class="profileIcon">  <img src="images/profile.png" alt="Profile Icon" ></span>
        <div id="profileDropdown" class="dropdown-content">
             <a href="<s:url action="viewMyProfile"/>">View My Profile</a>
            <a href="<s:url action="viewAllUsers"/>">View All Users</a>
            <a href="<s:url action="logout"/>">Logout</a>
 </div>
    </div>
   
</nav>
 

     <div>
        <h2>All Items For Sale</h2>
        
        <s:if test="#allItems.empty">
            <p>No Items available.</p>
        </s:if>

        <div class="card-container">
            <s:iterator value="bidItems">
                <div class="card">
                    <h3><s:property value="itemName" /></h3>
                    <p><s:property value="description" /></p>
                    <p>Start Price: €<s:property value="startPrice" /></p>
                    <p>Current Bid: €<s:property value="currentBid" /></p>
                    <s:form action="viewBids" method="post">
                        <s:hidden name="itemId" value="%{itemId}" />
                        <s:hidden name="itemName" value="%{itemName}" />
                        <s:hidden name="description" value="%{description}" />
                        <s:hidden name="startPrice" value="%{startPrice}" />
                        <s:hidden name="currentBid" value="%{currentBid}" />
                        <s:submit value="View Bids" />
                    </s:form>
                </div>
            </s:iterator>
        </div>
    </div>
</body>
</html>
<script>
 function toggleDropdown(dropdownId, event) {
    event.stopPropagation(); // Prevents the event from reaching the document click handler
    
    var dropdownOptions = document.getElementById(dropdownId);
    dropdownOptions.style.display = (dropdownOptions.style.display === "block") ? "none" : "block";
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
    var dropdowns = document.getElementsByClassName('dropdown-content');
    for (var i = 0; i < dropdowns.length; i++) {
        var dropdown = dropdowns[i];
        if (dropdown.style.display === "block") {
            dropdown.style.display = "none";
        }
    }
}


</script>
</body>
</html>