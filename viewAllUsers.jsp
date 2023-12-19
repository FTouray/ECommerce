<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View All Users</title>
    <link rel="stylesheet" type="text/css" href="css/allUserStyle.css">
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

    <div>
        <h2>All Users</h2>
<s:if test="#allUsers.empty">
            <p>No users available.</p>
        </s:if>
        
<div class="user-cards">
            <s:iterator value="allUsers">
                <s:if test="username != #session.currentUser">
                    <div class="user-card">
                        <s:property value="username" />
                        <s:form action="viewOtherProfiles" method="post">
                            <s:hidden name="idProf" value="%{id}" />
                            <s:submit value="View Profile" />
                        </s:form>
                    </div>
                </s:if>
            </s:iterator>
        </div>
    </div>

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