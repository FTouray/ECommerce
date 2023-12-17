function loadContent(actionName) {
    var contentContainer = document.getElementById('content-container');
    
    // Retrieve action URLs from hidden input fields
    var viewMyProfileUrl = document.getElementById('viewMyProfileUrl').value;
    var viewAllUsersUrl = document.getElementById('viewAllUsersUrl').value;

    // Choose the appropriate URL based on the actionName
    var url = actionName === 'viewMyProfile' ? viewMyProfileUrl : (actionName === 'viewAllUsers' ? viewAllUsersUrl : '');

    if (url) {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', url, true);

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                contentContainer.innerHTML = xhr.responseText;
            } else if (xhr.readyState === 4 && xhr.status !== 200) {
                console.error('Error loading content for action ' + actionName + '. Status:', xhr.status);
            }
        };

        xhr.send();
    } else {
        console.error('Invalid actionName:', actionName);
    }
}
