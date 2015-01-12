			var $j = jQuery.noConflict();
					
			$j(window).load(function() {
				ads.rotateAds();
			});

			$j(document).ready(function() {				  
				  $j('#ads-accordion > div:gt(0)').hide();
				  $j('#ads-accordion > h3').click(function() {
					$j(this).next('div:hidden').slideDown('fast')
					.siblings('div:visible').slideUp('fast');
				  });				  
			});								
