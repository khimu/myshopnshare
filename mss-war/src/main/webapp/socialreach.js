
var appId = '236764179843267';
var returnUrl = 'http://www.cheaplotteryonline.com';
var campaignName = 'khim';

function facebookReady(){
	
    FB.init({
      appId  : appId,
      status : true,
      cookie : true,
      xfbml  : true
    });

    FB.Event.subscribe('edge.create', function(href, widget) {
    	fbLike();
    });    
    
    FB.Event.subscribe('auth.authResponseChange', function(response) {
	    if (response.status === 'connected') {
		    // The response object is returned with a status field that lets the app know the current
		    // login status of the person. In this case, we're handling the situation where they 
		    // have logged in to the app.
	    }else if (response.status === 'not_authorized') {
		    // In this case, the person is logged into Facebook, but not into the app, so we call
		    // FB.login() to prompt them to do so. 
		    // In real-life usage, you wouldn't want to immediately prompt someone to login 
		    // like this, for two reasons:
		    // (1) JavaScript created popup windows are blocked by most browsers unless they 
		    // result from direct interaction from people using the app (such as a mouse click)
		    // (2) it is a bad experience to be continually prompted to login upon page load.
		    FB.login();
	    } else {
		    // In this case, the person is not logged into Facebook, so we call the login() 
		    // function to prompt them to do so. Note that at this stage there is no indication
		    // of whether they are logged into the app. If they aren't then they'll see the Login
		    // dialog right after they log in to Facebook. 
		    // The same caveats as above apply to the FB.login() call here.
		    FB.login();
	    }
  });    

} // end facebookReady();

$(document).ready(function(){
	(function () {
	    $.getScript(document.location.protocol + '//connect.facebook.net/en_US/all.js'); // load Facebook files
	} ());	
	  if(window.FB) {
	    facebookReady();
	  } else {
	    window.fbAsyncInit = facebookReady;
	  }

      $('.ui-loader').hide();
}); // end document().ready

function fbLike(){
    FB.login(function(response) {
        if (response.authResponse) {
		       FB.api('/me?fields=name,id,email', function(response) {
			 		  $.ajax({
			 			  url:'http://www.splitlottery.com/lottery/campaign/pool.htm',
			 			  type: "POST",
                                                  crossDomain: true,
			 			  data: {campaignName: 'khim', email: response.email, fbId: response.id},
			 			  success:function(result){
		 			             window.location.href=returnUrl;
			 			  }, error: function(XMLHttpRequest, textStatus, errorThrown){
			 				 	window.location.href=returnUrl;
		                  }
			 		  });			    	   
			    });                     
        }
 }, {scope: 'publish_stream,user_about_me,email'});

}