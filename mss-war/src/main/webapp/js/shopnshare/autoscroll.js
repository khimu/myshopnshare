var $j = jQuery.noConflict();

  $j.fn.autoScroll = function(options) {
	  var headline_count;
	  var headline_interval;
	  var old_headline = 0;
	  var current_headline = 0;
	  
		// default configuration properties
		var defaults = {
			scrollId: 		'#scrollup',
			scrollAreaId:	'div.headline'
			previousBnt: 	'#scroll-previous',
			nextBnt: 		'#scroll-next',	
			speed: 			6000			
		}; 
		
		var options = $.extend(defaults, options);  

		headline_count = $j(defaults.scrollAreaId).size();   
		$j(defaults.scrollAreaId + ":eq("+current_headline+")").css('top', '40px');
		headline_interval = setInterval(headline_rotate,defaults.speed);
	
		$j(scrollId).hover(function() {
		    clearInterval(headline_interval);
		    $j(this).addClass('hoverme');
		}, function() {
		    headline_interval = setInterval(headline_rotate,4000);
		    headline_rotate();	  
		    $j(this).removeClass('hoverme');
		});  
		$j(defaults.previousBnt).click(function(){
			clearInterval(headline_interval);
			
		    current_headline = (old_headline + 1) % headline_count;
		    $j(defaults.scrollAreaId + ":eq(" + old_headline + ")")		  
		      .animate({top: -205},"slow", function() {
		        $j(this).css('top', '210px');
		      });
		
		    $j(defaults.scrollAreaId + ":eq(" + current_headline + ")")
		      .animate({top: 40},"slow");  
		    old_headline = current_headline;
		});
		$j(defaults.nextBnt).click(function(){
			clearInterval(headline_interval);
			
		    current_headline = (old_headline - 1) % headline_count;
		    $j(defaults.scrollAreaId+":eq(" + old_headline + ")")		  
		      .animate({top: 205},"slow", function() {
		        $j(this).css('top', '205px');
		      });
		
		    $j(defaults.scrollAreaId+":eq(" + current_headline + ")")
		      .animate({top: 40},"slow");  
		    old_headline = current_headline;
		});		
	});
	
	function headline_rotate() {	  
	    current_headline = (old_headline + 1) % headline_count;
	    $j(defaults.scrollAreaId + ":eq(" + old_headline + ")")		  
	      .animate({top: -205},"slow", function() {
	        $j(this).css('top', '210px');
	      });
	
	    $j(defaults.scrollAreaId + ":eq(" + current_headline + ")")
	      .animate({top: 40},"slow");  
	    old_headline = current_headline;
	}	    

  };