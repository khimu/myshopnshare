// Search.js
// Creates dynamic styles for the search box on puidokas.com
//
// This file should be included right after the search box
// It doesn't create any global objects, it just adds
// listeners to certain seach box events
//
// created by Eric Puidokas
//

// if the search box gets focus, change style to to look like a normal form element
document.getElementById('search_friends_text').onfocus = function(e) {

	// don't show submit button to browsers that can't fire the blur and still
	// know if the submit button was clicked

	// remove style on the search box
	var searchText = document.getElementById('search_friends_text');
	searchText.className = '';
	
	if (searchText.value == 'Search Friends') {
		searchText.value = '';
	}	
}

	// if the user leaves the search box, put everything back to normal
	document.getElementById('search_friends_text').onblur = function(e) {

		var searchText = document.getElementById('search_friends_text');
		searchText.value = 'Search Friends';
		
		// hide the submit button
		//document.getElementById('search_friends_submit').style.display = 'none';
	
		// set search box style to inactive
		var searchText = document.getElementById('search_friends_text');
		searchText.className = 'search_friends_inactive';
	
		// if the submit button was clicked
		if (e && e.explicitOriginalTarget.id == 'search_friends_submit') {
			// submit the form, since the event won't propgate to the button click
			document.getElementById('search_friends').submit();
		}
	}

// set default style for search box and search button
document.getElementById('search_friends_text').className = 'search_friends_inactive';
//document.getElementById('search_friends_submit').style.display = 'none';