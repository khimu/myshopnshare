	function getFriends(){
		 $j.ajax({  
		       type: 'POST',  
		       url: '${baseurl}/secure/manageFriends.do',
		       data: {personId: '${person.id}'},
		       success: function(data){ 
			       alert(data);
			       	$j('#friends-list').html(data);
			       	
      				$j('a[@rel*=lightbox]').lightBox({fixedNavigation:true});	 							         

      				$j("ul.gallery").prettyGallery({
      					itemsPerPage : 15,
      					animationSpeed : 'normal', /* fast/normal/slow */
      					of_label: ' of ', /* The content in the page "1 of 2" */
      					previous_title_label: 'Previous page', /* The title of the previous link */
      					next_title_label: 'Next page', /* The title of the next link */
      					previous_label: 'Previous', /* The content of the previous link */
      					next_label: 'Next', /* The content of the next link */
      					galleryWidth: 940,
      					navigation: 'bottom'
      				});
		       } 
		     });
	}

    $j(function () { 
		getFriends();
		$j("ul.gallery").prettyGallery({
			itemsPerPage : 15,
			animationSpeed : 'normal', /* fast/normal/slow */
			of_label: ' of ', /* The content in the page "1 of 2" */
			previous_title_label: 'Previous page', /* The title of the previous link */
			next_title_label: 'Next page', /* The title of the next link */
			previous_label: 'Previous', /* The content of the previous link */
			next_label: 'Next', /* The content of the next link */
			galleryWidth: 940,
			navigation: 'bottom'
		});
  	});  	  