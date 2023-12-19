
function hideMessageBox(messageBoxClass) {
    var messageBox = document.querySelector(messageBoxClass);
    messageBox.style.display = 'none';
}

document.addEventListener('DOMContentLoaded', function() {
    var errorMessages = document.getElementsByClassName('error-box');
    var actionMessages = document.getElementsByClassName('action-box');

    if (errorMessages.length > 0) {
        setTimeout(function() { hideMessageBox('.error-box'); }, 5000);
    }

    if (actionMessages.length > 0) {
        setTimeout(function() { hideMessageBox('.action-box'); }, 5000);
    }
});

function toggleDropdown(dropdownId, event) {
    event.stopPropagation();
    var dropdownOptions = document.getElementById(dropdownId);
    dropdownOptions.style.display = (dropdownOptions.style.display === 'block') ? 'none' : 'block';
}

window.onclick = function(event) {
    var dropdowns = document.getElementsByClassName('dropdown-content');
    for (var i = 0; i < dropdowns.length; i++) {
        var dropdown = dropdowns[i];
        if (dropdown.style.display === 'block') {
            dropdown.style.display = 'none';
        }
    }
};

