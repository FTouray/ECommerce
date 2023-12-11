<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View All Users</title>
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
</nav>

    <div>
        <h2>All Users</h2>
<s:if test="#allUsers.empty">
            <p>No users available.</p>
        </s:if>
        

        <table border="1">
            <thead>
                <tr>
                    <th>Username</th>
                </tr>
            </thead>
            <tbody>
             
                <s:iterator value="allUsers" var="user">
                
                     <s:if test="#user.username != #session.currentUser.username">
                         <tr>
                            <td><s:property value="#user.username" /></td>
                        </tr>
                         <td>
                    <s:form action="viewOtherProfiles">
                        <s:hidden name="userId" value="%{#user.userId}" />
                        <s:submit value="View Profile" />
                    </s:form>
                </td>
                    </s:if>
                </s:iterator>
            </tbody>
        </table>
    </div>

    <s:a action="home">Back to Home</s:a>

</body>
</html>