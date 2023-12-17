<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Item</title>
   <link rel="stylesheet" type="text/css" href="css/addItemStyle.css">
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

   

<div class="addItemForSale">
    <h2>Add Item for Sale</h2>
    <s:form id="addItemForm" action="addItem" method="post">
        <s:textfield name="itemName" id="itemName" label="Item Name" required="true" />
        <s:textfield name="description" id="description" label="Description" required="true" />
        <s:textfield name="startPrice" id="startPrice" label="Starting Price €" required="true" />

        <s:submit value="Add Item" />
    </s:form>
</div>

<div id="validationMessage" class="validation-message">
<s:property value="#session.addItemSuccessMessage"/>
</div>



<script>
    function toggleDropdown(dropdownId, event) {
        event.stopPropagation();
        var dropdownOptions = document.getElementById(dropdownId);
        dropdownOptions.style.display = (dropdownOptions.style.display === "block") ? "none" : "block";
    }

    window.onclick = function(event) {
        var dropdowns = document.getElementsByClassName('dropdown-content');
        for (var i = 0; i < dropdowns.length; i++) {
            var dropdown = dropdowns[i];
            if (dropdown.style.display === "block") {
                dropdown.style.display = "none";
            }
        }
    }

   document.addEventListener("DOMContentLoaded", function() {
        var startPriceInput = document.getElementById("startPrice");
        var itemNameInput = document.getElementById("itemName");
        var descriptionInput = document.getElementById("description");
        var validationMessage = document.getElementById("validationMessage");

        function validateForm() {
            var isValidInput = /^[0-9]+(\.[0-9]+)?$/.test(startPriceInput.value);
            isValidInput = isValidInput && itemNameInput.value.trim() !== "" && descriptionInput.value.trim() !== "";

            validationMessage.textContent = isValidInput ? "" : "All fields must be filled appropriately.";
            return isValidInput;
        }

        startPriceInput.addEventListener("input", function() {
            var startPrice = this.value;
            var isValidInput = /^[0-9]+(\.[0-9]+)?$/.test(startPrice);

            validationMessage.textContent = isValidInput ? "" : "Invalid input for Starting Price. Please enter a valid number.";
            this.style.backgroundColor = isValidInput ? '#4CAF50' : '#FF2200';
        });

        document.getElementById("addItemForm").addEventListener("submit", function(event) {
            if (!validateForm()) {
                event.preventDefault(); // Prevent form submission
            } else {
        itemNameInput.value = "";
        descriptionInput.value = "";
        startPriceInput.value = "";
    }
        });
    });


</script>
</body>
</html>