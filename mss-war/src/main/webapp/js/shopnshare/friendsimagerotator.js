
	var friends = function() {}
	
	friends.root_path = 'http://localhost:8080/myshopnshare/images/';
	friends.friendsImages = new Array("img0.jpg", "img1.jpg", "img2.jpg", "img3.jpg", "img4.jpg", "img5.jpg", "img6.jpg", "beach1.jpg", "beach2.jpg", "beach3.jpg", "beach4.jpg", "beach5.jpg");
	friends.friendsNames = new Array("Khim Ung", "Mary Ann", "Sue Kim", "Mark Dark", "Joe Blue", "Don Ly", "Crazy Sam", "Clark Kent", "Sally May", "Lance Armstrong", "Anna Marie", "Ben Heck");
	friends.thisFriend = 0;
	friends.maxFriendListSize = 7;
	friends.stop = false;

	friends.buildFriends = function(){
			var friendList = "";
			var start = friends.thisFriend;
			var displaySize = friends.maxFriendListSize + friends.thisFriend;
			if(displaySize > friends.friendsImages.length){
				start = friends.friendsImages.length - friends.maxFriendListSize;
			}
			for ( var i = start; i < friends.friendsImages.length && i < displaySize; i++) {
				friendList = friendList + "<li><a href=\"javascript: friends.switchImageFriends(" + i + ")\"><img id=\"" + i + "\" src=\"" + friends.root_path + friends.friendsImages[i] + "\" width=\"20\" height=\"20\" alt=\"" + friends.friendsNames[i] + "\" /></a></li>\n";
			}
			document.getElementById("friends-list").innerHTML = friendList;
		}

	friends.switchImageFriends = function(imageIndex){
			document.getElementById("friend-image-link").href = friends.root_path + friends.friendsImages[imageIndex];
			document.getElementById("friend-image").src = friends.root_path + friends.friendsImages[imageIndex];
			document.getElementById("friend-image").title = friends.friendsNames[imageIndex];	
			document.getElementById("friend-name").innerHTML = friends.friendsNames[imageIndex];	
			friends.thisFriend = imageIndex;
			friends.buildFriends();	
		}

	friends.rotateFriends = function(){
		friends.buildFriends();
		friends.thisFriend++;
			if(friends.thisFriend == friends.friendsImages.length){
				friends.thisFriend = 0;
			}
			document.getElementById("friend-image-link").href = friends.root_path + friends.friendsImages[friends.thisFriend];
			document.getElementById("friend-image").src = friends.root_path + friends.friendsImages[friends.thisFriend];
			document.getElementById("friend-image").title = friends.friendsNames[friends.thisFriend];
			document.getElementById("friend-name").innerHTML = friends.friendsNames[friends.thisFriend];
			if(!friends.stop){
				setTimeout(friends.rotateFriends, 3 * 2000);
			}
		}
