<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Other Profiles</title>
    <link rel="stylesheet" type="text/css" href="css/profileStyle.css">
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
    <div class="nav-item">
        <a href="<s:url action="navigation"/>" class="home-icon"> 
            <img src="images/home.png" alt="Home Icon">
        </a>
        <p>Home</p>
    </div>

    <div class="nav-item">
        <div class="dropdown" onclick="toggleDropdown('bidsDropdown', event)">
            <span class="tbar">  <img src="images/triple bar.png" alt="Triple Bar Icon" ></span>
            <div id="bidsDropdown" class="dropdown-content">
                <a href="<s:url action="viewMyBids"/>">View My Bids</a>
                <a href="<s:url action="viewAllBids"/>">View All Bids</a>
                <a href="<s:url action="viewBidsForItem"/>">View Bids For An Item</a>
            </div>
        </div>
        <p>Bids</p>
    </div>

    <div class="nav-item">
        <a href="<s:url action="sellItem"/>" class="sell-icon"> 
            <img src="images/addItem.png" alt="Sell Icon">
        </a>
        <p>Sell Items</p>
    </div>

    <div class="nav-item">
        <div class="dropdown profile" onclick="toggleDropdown('profileDropdown', event)">
            <span class="profileIcon">  <img src="images/profile.png" alt="Profile Icon" ></span>
            <div id="profileDropdown" class="dropdown-content">
                <a href="<s:url action="viewMyProfile"/>">View My Profile</a>
                <a href="<s:url action="viewAllUsers"/>">View All Users</a>
                <a href="<s:url action="logout"/>">Logout</a>
            </div>
        </div>
        <p>Profile</p>
    </div>
</nav>

<h2><s:property value="otherUser.username" />'s Profile Information</h2>
 <div class="profileDetails">
        
             <p><strong>First Name:</strong> <s:property value="otherUser.firstName" /></p>
            <p><strong>Last Name:</strong> <s:property value="otherUser.lastName" /></p>
            <p><strong>Username:</strong> <s:property value="otherUser.username" /></p>
            <p><strong>Email:</strong> <s:property value="otherUser.email" /></p>
        
    </div>


    <h2><s:property value="otherUser.username" />'s Items for Sale</h2>

    <s:if test="otherItems != null && !otherItems.isEmpty()">
        <s:iterator value="otherItems">
        <div class="profileDetails">
            <p><strong>Item Name:</strong> <s:property value="itemName" /></p>
            <p><strong>Description:</strong> <s:property value="description" /></p>
            <p><strong>Start Price €:</strong> <s:property value="startPrice" /></p>
            <p><strong>Current Bid €:</strong> <s:property value="currentBid" /></p>
       </div>
        </s:iterator>
    </s:if>
    <s:else>
        <p>No items available.</p>
    </s:else>

     <h2><s:property value="otherUser.username" />'s Bids</h2>

    <s:if test="otherBidss != null && !otherBids.isEmpty()">
        <s:iterator value="otherBids">
        <div class="profileDetails">
            <p><strong>Item Name:</strong> <s:property value="itemName" /></p>
            <p><strong>Description:</strong> <s:property value="description" /></p>
            <p><strong>Start Price €:</strong> <s:property value="startPrice" /></p>
            <p><strong>Current Bid €:</strong> <s:property value="currentBid" /></p>
             <p><strong><s:property value="otherUser.username" />'s Bid Amount:</strong> €<s:property value="bidAmount" /></p>
            <p><strong>Bid Date:</strong> <s:property value="bidDate" /></p>
       </div>
        </s:iterator>
    </s:if>
    <s:else>
        <p>No Bids.</p>
    </s:else>

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