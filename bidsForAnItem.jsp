<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Bids For an Item</title>
    <link rel="stylesheet" type="text/css" href="css/allItemsStyle.css"/>
    <script src="script/actions.js"></script>
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
        <h2>All Items For Sale</h2>
<s:if test="actionMessages.size() > 0">
    <div class="action-box">
            <s:actionmessage />
        </div>
</s:if>
        
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


</body>
</html>